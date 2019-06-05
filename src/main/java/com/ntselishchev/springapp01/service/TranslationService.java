package com.ntselishchev.springapp01.service;

import com.ntselishchev.springapp01.domain.Person;
import com.ntselishchev.springapp01.domain.TestEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TranslationService {

    private final MessageSource messageSource;

    private static final String TEST_PASSED_KEY = "test.passed";
    private static final String TEST_FAILED_KEY = "test.failed";
    private static final String TEST_RESULT_KEY = "test.result";
    private static final String TEST_STATUS_KEY = "test.status";
    private static final String USER_GREETING_KEY = "user.greeting";
    private static final String TEST_FIRST_NAME_KEY = "test.ask-first-name";
    private static final String TEST_LAST_NAME_KEY = "test.ask-last-name";

    private String getTranslation(String field, Locale locale) {
        return messageSource.getMessage(field, null, locale);
    }

    public String getStatusTranslation(Integer correctAnswersAmount, Integer border, Locale locale) {
        String field = TestProcessingService.isPassed(correctAnswersAmount, border) ? TEST_PASSED_KEY : TEST_FAILED_KEY;
        return getTranslation(field, locale);
    }

    public String getTestResultsTranslation(Integer correctAnswersAmount, Integer questionsAmount, Locale locale) {
        return messageSource.getMessage(TEST_RESULT_KEY, new String[]{correctAnswersAmount.toString(), questionsAmount.toString()}, locale);
    }

    public String getTestStatusTranslation(Locale locale) {
        return getTranslation(TEST_STATUS_KEY, locale);
    }

    public String getUserGreetingTranslation(Person person, Locale locale) {
        return messageSource.getMessage(USER_GREETING_KEY, new String[]{person.getFirstName(), person.getLastName()}, locale);
    }

    public String getAskFirstNameTranslation(Locale locale) {
        return getTranslation(TEST_FIRST_NAME_KEY, locale);
    }

    public String getAskLastNameTranslation(Locale locale) {
        return getTranslation(TEST_LAST_NAME_KEY, locale);
    }

    public void translateTestQuestions(List<TestEntry> entries, Locale locale){
        entries.forEach(e -> e.setQuestion(getTranslation("test." + e.getQuestion(), locale)));
    }

}
