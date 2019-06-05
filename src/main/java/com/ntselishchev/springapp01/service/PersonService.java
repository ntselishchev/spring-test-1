package com.ntselishchev.springapp01.service;

import com.ntselishchev.springapp01.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final TranslationService translationService;

    public Person initPerson(Scanner sc, Locale locale) {
        System.out.println(translationService.getAskFirstNameTranslation(locale));
        String firstName = sc.nextLine().trim();

        System.out.println(translationService.getAskLastNameTranslation(locale));
        String lastName = sc.nextLine().trim();

        return new Person(firstName, lastName);
    }

}
