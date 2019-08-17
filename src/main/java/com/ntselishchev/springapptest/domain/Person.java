package com.ntselishchev.springapptest.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {

    private String firstName;
    private String lastName;
    private boolean testProcessed;
    private int questionAmount;
    private int correctAnswersAmount;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void incrementCorrectAnswersAmount() {
        correctAnswersAmount++;
    }
}
