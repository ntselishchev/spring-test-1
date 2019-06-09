package com.ntselishchev.springapp01.service;

import com.ntselishchev.springapp01.domain.TestEntry;

import java.util.List;

public interface TestProcessingService {

    List<TestEntry> getTestEntries(Integer questionAmount, Boolean randomise);

    boolean validateAnswer(TestEntry entry, String answer);

    static boolean isPassed(Integer correctAnswersAmount, Integer border) {
        return correctAnswersAmount >= border;
    }

}
