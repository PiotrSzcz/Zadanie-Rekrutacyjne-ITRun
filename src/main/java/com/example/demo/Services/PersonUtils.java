package com.example.demo.Services;

import com.example.demo.Models.Person;
import java.util.List;
import java.util.regex.Pattern;

public class PersonUtils {
    public static boolean isValidPesel(String pesel) {
        return Pattern.matches("\\d{11}", pesel);
    }

    public static boolean isIndexOccupied(String index, List<Person> Persons) {
        return Persons.stream().anyMatch(n -> n.getPersonId().equals(index));
    }

    public static boolean isValidFirstName(String firstName) {
        return !firstName.isEmpty() && firstName.matches("[A-Z][a-zA-Z]+");
    }
    public static boolean isValidLastName(String lastName) {
        return !lastName.isEmpty() && lastName.matches("[A-Z][a-zA-Z]+");
    }
    public static boolean isValidMobile(String mobile) {
        return Pattern.matches("\\d{9}", mobile);
    }
    public static boolean isValidEmail(String email) {
        return email.contains("@");
    }
}
