package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static com.example.demo.domain.NOLOGI.NOLOG;

@Entity
public class Payment {
    @Id
    @GeneratedValue
    private int id;
    private int salary;
    private int prize;
    private int personId;
    private String date;

    public Payment(int id, int salary, int prize, int personId, String date) {
        this.id = id;
        this.salary = salary;
        this.prize = prize;
        this.personId = personId;
        this.date = date;
    }

    public Payment() {
    }


    public int getId() {
        return id;
    }

    public int getSalary() {
        double salary = this.salary*NOLOG;
        return (int)Math.round(salary);
    }

    public int getPrize() {
        return prize;
    }

    public int getPersonId() {
        return personId;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
