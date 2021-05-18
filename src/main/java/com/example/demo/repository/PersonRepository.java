package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.domain.Person;

import java.util.ArrayList;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> persons = new ArrayList<>();
}
