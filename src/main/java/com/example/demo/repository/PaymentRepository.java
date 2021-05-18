package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.domain.Payment;

import java.util.ArrayList;
import java.util.List;


public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> payments = new ArrayList<>();
}
