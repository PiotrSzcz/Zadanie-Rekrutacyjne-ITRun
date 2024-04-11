package com.example.demo.Repositorys;

import com.example.demo.Models.Person;
import com.example.demo.Models.Type;

import java.util.List;

interface IEmployeeRepository {
    Person find(Type type, String firstName, String lastName, String mobile);

    List<Person> findAll();

    void create(Person person);
    boolean remove(String personId);
    void modify(Person person);
}