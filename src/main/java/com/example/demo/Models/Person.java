package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String personId;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String pesel;
    private Type type;

}
