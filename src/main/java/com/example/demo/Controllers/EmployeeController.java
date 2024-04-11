package com.example.demo.Controllers;

import com.example.demo.Services.EmployeeService;
import com.example.demo.Models.Person;
import com.example.demo.Models.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllEmployees() {
        List<Person> employees = employeeService.findAll();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/find")
    public ResponseEntity<Person> findEmployee(@RequestParam Type type,
                                               @RequestParam String firstName,
                                               @RequestParam String lastName,
                                               @RequestParam String mobile) {
        Person person = employeeService.findEmployee(type, firstName, lastName, mobile);
        if (person != null) {
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> addEmployee(@RequestBody Person person) {
        employeeService.addEmployee(person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> removeEmployee(@PathVariable String personId) {
        boolean removed = employeeService.removeEmployee(personId);
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateEmployee(@RequestBody Person person) {
        employeeService.updateEmployee(person);
        return ResponseEntity.ok().build();
    }
}
