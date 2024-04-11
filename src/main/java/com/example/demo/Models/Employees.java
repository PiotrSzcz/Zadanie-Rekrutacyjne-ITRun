package com.example.demo.Models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Employees {

    @XmlElement(name = "person")
    private List<Person> privateList;

    public List<Person> getPersonList() {
        return privateList;
    }

    public void setEmployees(List<Person> employees) {
        this.privateList = employees;
    }
}
