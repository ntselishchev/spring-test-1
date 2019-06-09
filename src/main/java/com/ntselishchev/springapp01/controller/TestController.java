package com.ntselishchev.springapp01.controller;

import com.ntselishchev.springapp01.domain.Person;
import com.ntselishchev.springapp01.domain.TestEntry;
import com.ntselishchev.springapp01.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class TestController {

    private final TestProcessingService testProcessingService;
    private final PropertiesService propertiesService;
    private final TranslationService translationService;
    private final PersonService personService;
    private final InOutService inOutService;

    private static final String TEST_PASSED_KEY = "test.passed";
    private static final String TEST_FAILED_KEY = "test.failed";
    private static final String TEST_RESULT_KEY = "test.result";
    private static final String TEST_STATUS_KEY = "test.status";
    private static final String USER_GREETING_KEY = "user.greeting";

    public void processTest() {

        int correctAnswersAmount = 0;
        Scanner sc = inOutService.initScanner();

        Person person = personService.initPerson(sc);
        String greetingTranslation = translationService.getTranslationUsingParams(USER_GREETING_KEY, new String[] {person.getFirstName(), person.getLastName()});
        inOutService.print(greetingTranslation);

        List<TestEntry> entryList = testProcessingService.getTestEntries(propertiesService.getQuestionsAmount(), propertiesService.getRandomise());

        for (TestEntry entry : entryList) {
            inOutService.print(entry.getQuestion());

            boolean isCorrect = testProcessingService.validateAnswer(entry, inOutService.getUserInputMessage(sc));
            if (isCorrect) {
                correctAnswersAmount++;
            }
        }

        String statusTranslationKey = TestProcessingService.isPassed(correctAnswersAmount, propertiesService.getPassedBorder()) ? TEST_PASSED_KEY : TEST_FAILED_KEY;
        String statusTranslation = translationService.getTranslation(statusTranslationKey);
        String testResultsTranslation = translationService.getTranslationUsingParams(TEST_RESULT_KEY, new String[]{String.valueOf(correctAnswersAmount), String.valueOf(entryList.size())});
        inOutService.print(testResultsTranslation);
        String testStatusTranslation = translationService.getTranslation(TEST_STATUS_KEY) + " " + statusTranslation;
        inOutService.print(testStatusTranslation);
    }
}
