package com.ntselishchev.springapp01.service;

import com.ntselishchev.springapp01.domain.TestEntry;

import java.util.List;

public interface TestProcessingService {

    List<TestEntry> getTestEntries(int questionAmount, boolean randomise);

    boolean validateAnswer(TestEntry entry, String answer);

    boolean isPassed(int correctAnswersAmount, int border);

}
