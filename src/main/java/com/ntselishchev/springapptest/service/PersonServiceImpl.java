package com.ntselishchev.springapptest.service;

import com.ntselishchev.springapptest.domain.Person;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class PersonServiceImpl implements PersonService {

    private final TranslationService translationService;
    private final InOutService inOutService;
    private final Scanner scanner;

    private static final String TEST_FIRST_NAME_KEY = "test.ask-first-name";
    private static final String TEST_LAST_NAME_KEY = "test.ask-last-name";

    public PersonServiceImpl(TranslationService translationService, InOutService inOutService) {
        this.translationService = translationService;
        this.inOutService = inOutService;
        scanner = new Scanner(System.in);
    }

    public Person initPerson() {
        inOutService.print(translationService.getTranslation(TEST_FIRST_NAME_KEY));
        String firstName = scanner.nextLine().trim();

        inOutService.print(translationService.getTranslation(TEST_LAST_NAME_KEY));
        String lastName = scanner.nextLine().trim();

        return new Person(firstName, lastName);
    }

}
