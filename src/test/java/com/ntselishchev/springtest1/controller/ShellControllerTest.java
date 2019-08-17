package com.ntselishchev.springtest1.controller;

import com.ntselishchev.springapptest.controller.ShellController;
import com.ntselishchev.springapptest.controller.TestController;
import com.ntselishchev.springapptest.domain.Person;
import com.ntselishchev.springapptest.service.TranslationService;
import com.ntselishchev.springtest1.SpringTestApplicationTests;
import org.apache.logging.log4j.util.Strings;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Availability;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ShellControllerTest extends SpringTestApplicationTests {

    @Autowired
    protected ShellController shellController;

    @MockBean
    protected TestController testController;

    @MockBean
    protected TranslationService translationService;

    @Before
    public void setUp() {
        shellController.setFirstName(null);
        shellController.setLastName(null);
        shellController.setPerson(null);
    }

    @Test
    public void testSetUserFirstNameShouldSetUserFirstName() {
        shellController.setUserFirstName("test");

        Assert.assertEquals("test", shellController.getFirstName());
    }

    @Test
    public void testSetUserLastNameShouldSetUserLastName() {
        shellController.setUserLastName("test");

        Assert.assertEquals("test", shellController.getLastName());
    }

    @Test
    public void testStartTestShouldSetPersonAndInvokeStartTest() {
        shellController.setFirstName("test f");
        shellController.setLastName("test l");

        shellController.startTest();

        Assert.assertNotNull(shellController.getPerson());
        verify(testController, times(1)).processTest(any(Person.class));
    }

    @Test
    public void testTestResultsShouldInvokeTestResults() {
        shellController.setPerson(new Person("test", "test"));

        shellController.testResults();

        verify(testController, times(1)).displayTestResults(any(Person.class));
    }

    @Test
    public void testSetUserFirstNameAvailabilityWhenPersonIsNullShouldReturnAvailable() {
        Availability availability = shellController.setUserFirstNameAvailability();

        Assert.assertTrue(availability.isAvailable());
    }

    @Test
    public void testSetUserFirstNameAvailabilityWhenTestHasBeenProceedShouldReturnUnavailable() {
        Person person = new Person("test", "test");
        person.setTestProcessed(true);
        shellController.setPerson(person);

        Mockito.when(translationService.getTranslation(anyString())).thenReturn(Strings.EMPTY);

        Availability availability = shellController.setUserFirstNameAvailability();

        Assert.assertFalse(availability.isAvailable());
    }

    @Test
    public void testSetUserLastNameAvailabilityWhenTestHasBeenProcessedShouldReturnUnavailable() {
        Person person = new Person("test", "test");
        person.setTestProcessed(true);
        shellController.setPerson(person);

        Mockito.when(translationService.getTranslation(eq("test.has-been-processed"))).thenReturn("processed");

        Availability availability = shellController.setUserLastNameAvailability();

        Assert.assertFalse(availability.isAvailable());
        Assert.assertEquals("processed", availability.getReason());
    }


    @Test
    public void testSetUserLastNameAvailabilityWhenFirstNameIsNullShouldReturnUnavailable() {
        Availability availability = shellController.setUserLastNameAvailability();

        Assert.assertFalse(availability.isAvailable());
        Assert.assertEquals("first-name", availability.getReason());
    }

    @Test
    public void testSetUserLastNameAvailabilityWhenFirstNameIsNotNullShouldReturnAvailable() {
        shellController.setFirstName("test");

        Availability availability = shellController.setUserLastNameAvailability();

        Assert.assertTrue(availability.isAvailable());
    }
}
