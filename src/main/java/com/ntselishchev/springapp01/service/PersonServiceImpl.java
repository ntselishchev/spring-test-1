package com.ntselishchev.springapp01.service;

import com.ntselishchev.springapp01.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final TranslationService translationService;
    private final InOutService inOutService;

    private static final String TEST_FIRST_NAME_KEY = "test.ask-first-name";
    private static final String TEST_LAST_NAME_KEY = "test.ask-last-name";

    public Person initPerson(Scanner sc) {
        inOutService.print(translationService.getTranslation(TEST_FIRST_NAME_KEY));
        String firstName = sc.nextLine().trim();

        inOutService.print(translationService.getTranslation(TEST_LAST_NAME_KEY));
        String lastName = sc.nextLine().trim();

        return new Person(firstName, lastName);
    }

}
