package com.ntselishchev.springapptest.controller;

import com.ntselishchev.springapptest.domain.Person;
import com.ntselishchev.springapptest.service.TranslationService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class ShellController {

    private final TranslationService translationService;
    private final TestController testController;

    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    private Person person;

    private static final String METHOD_UNAVAILABLE_KEY = "shell.method-unavailable";
    private static final String HAS_BEEN_PROCESSED_REASON = "test.has-been-processed";

    @ShellMethod(value = "set ussr first name", key = {"fn", "first-name"})
    public void setUserFirstName(@ShellOption(defaultValue = "John") String firstName) {
        this.firstName = firstName;
    }

    @ShellMethod(value = "set ussr last name", key = {"ln", "last-name"})
    public void setUserLastName(@ShellOption(defaultValue = "Doe") String lastName) {
        this.lastName = lastName;
    }

    @ShellMethod(value = "start test", key = {"st", "start-test"})
    public void startTest() {
        person = new Person(firstName, lastName);
        testController.processTest(person);
    }

    @ShellMethod(value = "rerun test", key = {"rrt", "rerun-test"})
    public void rerunTest() {
        startTest();
    }

    @ShellMethod(value = "display test results", key = {"tr", "test-results"})
    public void testResults() {
        testController.displayTestResults(person);
    }

    public Availability setUserFirstNameAvailability() {
        return person == null || !person.isTestProcessed() ? Availability.available() : Availability
                .unavailable(translationService.getTranslation(HAS_BEEN_PROCESSED_REASON));
    }

    public Availability setUserLastNameAvailability() {
        if (person != null && person.isTestProcessed()) {
            return Availability.unavailable(translationService.getTranslation(HAS_BEEN_PROCESSED_REASON));
        }
        return firstName != null ? Availability.available() : Availability
                .unavailable(StringUtils
                        .join(translationService.getTranslation(METHOD_UNAVAILABLE_KEY), "first-name"));
    }

    public Availability startTestAvailability() {
        if (person != null && person.isTestProcessed()) {
            return Availability.unavailable(translationService.getTranslation(HAS_BEEN_PROCESSED_REASON));
        }
        return lastName != null ? Availability.available() : Availability
                .unavailable(StringUtils
                        .join(translationService.getTranslation(METHOD_UNAVAILABLE_KEY), "last-name"));
    }

    public Availability rerunTestAvailability() {
        return person != null && person.isTestProcessed() ? Availability.available() : Availability
                .unavailable(StringUtils
                        .join(translationService.getTranslation(METHOD_UNAVAILABLE_KEY), "start-test"));
    }

    public Availability testResultsAvailability() {
        return person != null && person.isTestProcessed() ? Availability.available() : Availability
                .unavailable(StringUtils
                        .join(translationService.getTranslation(METHOD_UNAVAILABLE_KEY), "start-test"));
    }
}
