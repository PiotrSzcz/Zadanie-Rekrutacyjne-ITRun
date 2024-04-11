package com.example.demo;

import com.example.demo.Controllers.EmployeeController;
import com.example.demo.Models.Person;
import com.example.demo.Models.Type;
import com.example.demo.Services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeController = new EmployeeController(employeeService);
    }

    @Test
    void testGetAllEmployees() {
        List<Person> employees = new ArrayList<>();
        employees.add(new Person("1", "John", "Doe", "123", "john@example.com", "12345678901", Type.INTERNAL));
        when(employeeService.findAll()).thenReturn(employees);
        ResponseEntity<List<Person>> response = employeeController.getAllEmployees();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employees, response.getBody());
    }

    @Test
    void testFindEmployee() {
        Type type = Type.INTERNAL;
        String firstName = "John";
        String lastName = "Doe";
        String mobile = "123";
        Person person = new Person("1", firstName, lastName, mobile, "john@example.com", "12345678901", type);
        when(employeeService.findEmployee(type, firstName, lastName, mobile)).thenReturn(person);
        ResponseEntity<Person> response = employeeController.findEmployee(type, firstName, lastName, mobile);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(person, response.getBody());
    }

    @Test
    public void testAddEmployee() {
        Person person = new Person();
        ResponseEntity<Void> response = employeeController.addEmployee(person);
        assertEquals(200, response.getStatusCodeValue());
        verify(employeeService, times(1)).addEmployee(person);
    }

    @Test
    public void testRemoveEmployee() {
        String personId = "123";
        when(employeeService.removeEmployee(anyString())).thenReturn(true);
        ResponseEntity<Void> response = employeeController.removeEmployee(personId);
        assertEquals(200, response.getStatusCodeValue());
        verify(employeeService, times(1)).removeEmployee(personId);
    }

    @Test
    public void testUpdateEmployee() {
        Person person = new Person();
        ResponseEntity<Void> response = employeeController.updateEmployee(person);
        assertEquals(200, response.getStatusCodeValue());
        verify(employeeService, times(1)).updateEmployee(person);
    }

}