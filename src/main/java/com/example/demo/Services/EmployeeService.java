package com.example.demo.Services;

import com.example.demo.Models.Person;
import com.example.demo.Models.Type;
import com.example.demo.Repositorys.XmlEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final XmlEmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(XmlEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Person findEmployee(Type type, String firstName, String lastName, String mobile) {
        return employeeRepository.find(type, firstName, lastName, mobile);
    }

    public void addEmployee(Person person) {
        employeeRepository.create(person);
    }

    public boolean removeEmployee(String personId) {
        return employeeRepository.remove(personId);
    }

    public void updateEmployee(Person person) {
        employeeRepository.modify(person);
    }

    public List<Person> findAll() {
        return employeeRepository.findAll();
    }
}
