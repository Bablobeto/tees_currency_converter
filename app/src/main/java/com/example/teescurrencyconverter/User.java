package com.example.teescurrencyconverter;

public class User {
    public String firstName, lastName;
    public Integer age;

    public User(){

    }

    public User(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = (age != null) ? age : 0;
    }
}
