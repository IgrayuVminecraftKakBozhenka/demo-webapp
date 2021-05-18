package com.example.demo.pages;

import com.example.demo.domain.Payment;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.domain.Person;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PagesController {
    private final PersonRepository personRepository;
    private final PaymentRepository paymentRepository;

    public PagesController(PersonRepository personRepository, PaymentRepository paymentRepository) {
        this.personRepository = personRepository;
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("persons", personRepository.findAll());
        return "index";
    }

    @GetMapping("/person/{id}")
    public String personPage(
            @PathVariable("id") int id,
            Model model
    ) {
        Person person = findPersonById(id);

        model.addAttribute("person", person);
        List<Payment> personPayments = new ArrayList<>();
        for (Payment p : paymentRepository.findAll()) {
            if (p.getPersonId() == id) {
                personPayments.add(p);
            }
        }
        model.addAttribute("payments", personPayments);
        return "person";
    }

    @GetMapping("/payment/{id}")
    public String paymentPage(
            @PathVariable("id") int id,
            Model model
    ) {
        Payment payment = findPaymentById(id);
        model.addAttribute("payment", payment);
        return "payment";
    }

    @PostMapping("/person/{id}")
    public String personEdit(
            @PathVariable("id") int id,
            @RequestParam("name") String name,
            @RequestParam("salary") int salary
    ) {
        Person person = findPersonById(id);
        person.setName(name);
        person.setSalary(salary);
        return "redirect:/person/" + id;
    }

    @PostMapping("/person")
    public String personSave(
            @RequestParam("name") String name,
            @RequestParam("salary") int salary
    ) {
        Person person = new Person();
        person.setName(name);
        person.setSalary(salary);
        personRepository.save(person);
        return "redirect:/";
    }

    @RequestMapping(value="/delete", method=RequestMethod.GET)
    public String deletePayment(@RequestParam("paymentId") int paymentId, Model model) {
        Payment payment = findPaymentById(paymentId);
        int personId = payment.getPersonId();
        paymentRepository.delete(payment);
        return "redirect:/person/" + personId;
    }

    @PostMapping("/payment/{id}")
    public String paymentEdit(
            @PathVariable("id") int id,
            @RequestParam("salary") int salary,
            @RequestParam("prize") int prize
    ) {
        Payment payment = findPaymentById(id);
        payment.setSalary(salary);
        payment.setPrize(prize);
        return "redirect:/payment/" + id;
    }

    @PostMapping("/person/{personId}/payment")
    public String paymentSave(
            @PathVariable("personId") int personId,
            @RequestParam("salary") int salary,
            @RequestParam("prize") int prize,
            @RequestParam("date") String date
    ) {
        Payment payment = new Payment();
        payment.setPrize(prize);
        payment.setSalary(salary);
        payment.setPersonId(personId);
        payment.setDate(date);
        paymentRepository.save(payment);
        return "redirect:/person/" + personId;
    }

    public Payment findPaymentById (int id) {
        Payment payment = null;
        for (Payment p : paymentRepository.findAll()) {
            if (p.getId() == id) {
                payment = p;
            }
        }
        return payment;
    }

    public Person findPersonById (int id) {
        Person person = null;
        for (Person p : personRepository.findAll()) {
            if (p.getId() == id) {
                person = p;
            }
        }
        return person;
    }
}
