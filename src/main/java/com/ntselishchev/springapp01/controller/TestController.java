package com.ntselishchev.springapp01.controller;

import com.ntselishchev.springapp01.domain.Person;
import com.ntselishchev.springapp01.domain.TestEntry;
import com.ntselishchev.springapp01.service.PersonService;
import com.ntselishchev.springapp01.service.PropertiesService;
import com.ntselishchev.springapp01.service.TestProcessingService;
import com.ntselishchev.springapp01.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class TestController {

    private final TestProcessingService testProcessingService;
    private final PropertiesService propertiesService;
    private final TranslationService translationService;
    private final PersonService personService;
    public void processTest() {

        int correctAnswersAmount = 0;
        Locale locale = propertiesService.getLocale();
        Scanner sc = new Scanner(System.in);

        Person person = personService.initPerson(sc, locale);
        System.out.println(translationService.getUserGreetingTranslation(person, locale));

        List<TestEntry> entryList = testProcessingService.getTestEntries(propertiesService.getQuestionsAmount(), propertiesService.getRandomise(), locale);

        for (TestEntry entry : entryList) {
            System.out.println(entry.getQuestion());

            boolean isCorrect = testProcessingService.validateAnswer(entry, sc.nextLine().trim());
            if (isCorrect) {
                correctAnswersAmount++;
            }
        }

        String statusTranslation = translationService.getStatusTranslation(correctAnswersAmount, propertiesService.getPassedBorder(), locale);

        System.out.println(translationService.getTestResultsTranslation(correctAnswersAmount, entryList.size(), locale));
        System.out.println(translationService.getTestStatusTranslation(locale) + " " + statusTranslation);
    }
}
