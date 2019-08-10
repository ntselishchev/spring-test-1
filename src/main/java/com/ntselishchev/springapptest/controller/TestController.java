package com.ntselishchev.springapptest.controller;

import com.ntselishchev.springapptest.config.ApplicationProperties;
import com.ntselishchev.springapptest.domain.Person;
import com.ntselishchev.springapptest.domain.TestEntry;
import com.ntselishchev.springapptest.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestController {

    private final TestProcessingService testProcessingService;
    private final ApplicationProperties applicationProperties;
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

        Person person = personService.initPerson();
        String greetingTranslation = translationService.getTranslationUsingParams(USER_GREETING_KEY, new String[] {person.getFirstName(), person.getLastName()});
        inOutService.print(greetingTranslation);

        List<TestEntry> entryList = testProcessingService.getTestEntries(applicationProperties.getQuestions().getAmount(), applicationProperties.getQuestions().isRandomise());

        for (TestEntry entry : entryList) {
            inOutService.print(entry.getQuestion());

            boolean isCorrect = testProcessingService.validateAnswer(entry, inOutService.getUserInputMessage());
            if (isCorrect) {
                correctAnswersAmount++;
            }
        }

        String statusTranslationKey = testProcessingService.isPassed(correctAnswersAmount, applicationProperties.getQuestions().getPassedBorder()) ? TEST_PASSED_KEY : TEST_FAILED_KEY;
        String statusTranslation = translationService.getTranslation(statusTranslationKey);
        String testResultsTranslation = translationService.getTranslationUsingParams(TEST_RESULT_KEY, new String[]{String.valueOf(correctAnswersAmount), String.valueOf(entryList.size())});
        inOutService.print(testResultsTranslation);
        String testStatusTranslation = translationService.getTranslation(TEST_STATUS_KEY) + " " + statusTranslation;
        inOutService.print(testStatusTranslation);
    }
}
