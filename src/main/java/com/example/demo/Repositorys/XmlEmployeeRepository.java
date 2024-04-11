package com.example.demo.Repositorys;

import com.example.demo.Models.Employees;
import com.example.demo.Models.Person;
import com.example.demo.Models.Type;
import com.example.demo.Services.PersonUtils;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Repository;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class XmlEmployeeRepository implements IEmployeeRepository {
    private final List<Person> internalEmployees;
    private final List<Person> externalEmployees;
    private static final String INTERNAL_XML_FILE = "internal_employees.xml";
    private static final String EXTERNAL_XML_FILE = "external_employees.xml";

    public XmlEmployeeRepository() {
        internalEmployees = readEmployeesFromFile(INTERNAL_XML_FILE);
        externalEmployees = readEmployeesFromFile(EXTERNAL_XML_FILE);
    }

    private List<Person> readEmployeesFromFile(String fileName) {
        List<Person> employees = new ArrayList<>();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
            if (file.exists()) {
                JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                Employees employeesData = (Employees) jaxbUnmarshaller.unmarshal(file);
                employees = employeesData.getPersonList();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private void writeEmployeesToFile(List<Person> employees, String fileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Employees employeesWrapper = new Employees();
            employeesWrapper.setEmployees(employees);
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
            jaxbMarshaller.marshal(employeesWrapper, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Person find(Type type, String firstName, String lastName, String mobile) {
        List<Person> employees = (type == Type.INTERNAL) ? internalEmployees : externalEmployees;
        for (Person employee : employees) {
            if (employee.getFirstName().equals(firstName) &&
                    employee.getLastName().equals(lastName) &&
                    employee.getMobile().equals(mobile)) {
                return employee;
            }
        }
        return null;
    }
    @Override
    public List<Person> findAll() {
        List<Person> allEmployees = new ArrayList<>();
        allEmployees.addAll(internalEmployees);
        allEmployees.addAll(externalEmployees);
        return allEmployees;
    }

    @Override
    public void create(Person person) {
        validateBasicData(person);
        if (person.getType() == Type.INTERNAL) {
            if (PersonUtils.isIndexOccupied(person.getPersonId(),internalEmployees)) {
                throw new IllegalArgumentException("Indeks " + person.getPersonId() + " jest już zajęty");
            }
            internalEmployees.add(person);
            writeEmployeesToFile(internalEmployees, INTERNAL_XML_FILE);
        } else if (person.getType() == Type.EXTERNAL) {
            if (PersonUtils.isIndexOccupied(person.getPersonId(), externalEmployees)) {
                throw new IllegalArgumentException("Indeks " + person.getPersonId() + " jest już zajęty");
            }
            externalEmployees.add(person);
            writeEmployeesToFile(externalEmployees, EXTERNAL_XML_FILE);
        }
        else {
            throw new RuntimeException();
        }
    }

    private static void validateBasicData(Person person) {
        if (!PersonUtils.isValidPesel(person.getPesel())) {
            throw new IllegalArgumentException("Nieprawidłowy numer PESEL: " + person.getPesel());
        }
        if (!PersonUtils.isValidFirstName(person.getFirstName())) {
            throw new IllegalArgumentException("Nieprawidłowe Imię: " + person.getFirstName());
        }
        if (!PersonUtils.isValidLastName(person.getLastName())) {
            throw new IllegalArgumentException("Nieprawidłowe Nazwisko: " + person.getLastName());
        }
        if (!PersonUtils.isValidMobile(person.getMobile())) {
            throw new IllegalArgumentException("Nieprawidłowy numer telefonu: " + person.getMobile());
        }
        if (!PersonUtils.isValidEmail(person.getEmail())) {
            throw new IllegalArgumentException("Nieprawidłowy email: " + person.getEmail());
        }
    }

    @Override
    public boolean remove(String personId) {
        for (Person employee : internalEmployees) {
            if (employee.getPersonId().equals(personId)) {
                internalEmployees.remove(employee);
                writeEmployeesToFile(internalEmployees, INTERNAL_XML_FILE);
                return true;
            }
        }
        for (Person employee : externalEmployees) {
            if (employee.getPersonId().equals(personId)) {
                externalEmployees.remove(employee);
                writeEmployeesToFile(externalEmployees, EXTERNAL_XML_FILE);
                return true;
            }
        }
        return false;
    }

    @Override
    public void modify(Person person) {
        List<Person> employees = (person.getType() == Type.INTERNAL) ? internalEmployees : externalEmployees;
        for (Person employee : employees) {
            if (employee.getPersonId().equals(person.getPersonId())) {
                employee.setFirstName(person.getFirstName());
                employee.setLastName(person.getLastName());
                employee.setMobile(person.getMobile());
                employee.setEmail(person.getEmail());
                employee.setPesel(person.getPesel());
                writeEmployeesToFile(employees, (person.getType() == Type.INTERNAL) ? INTERNAL_XML_FILE : EXTERNAL_XML_FILE);
                return;
            }
        }
    }


}