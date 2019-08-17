package com.ntselishchev.springtest1.domain;

import com.ntselishchev.springapptest.domain.Person;
import com.ntselishchev.springtest1.SpringTestApplicationTests;
import org.junit.Assert;
import org.junit.Test;

public class PersonTest extends SpringTestApplicationTests {

    @Test
    public void testCreateNewPersonShouldCreatePersonCorrectly() {
        Person person = new Person("John", "Doe");

        Assert.assertEquals("John", person.getFirstName());
        Assert.assertEquals("Doe", person.getLastName());
        Assert.assertEquals(0, person.getCorrectAnswersAmount());
        Assert.assertEquals(0, person.getQuestionAmount());
        Assert.assertFalse(person.isTestProcessed());
    }

    @Test
    public void testIncrementPersonCorrectAnswersAmountShouldIncrementCorrectAnswersAmountByOne() {
        Person person = new Person("John", "Doe");
        person.incrementCorrectAnswersAmount();
        person.incrementCorrectAnswersAmount();

        Assert.assertEquals(2, person.getCorrectAnswersAmount());
    }
}
