package com.ntselishchev.springtest1.service;

import com.ntselishchev.springapptest.domain.TestEntry;
import com.ntselishchev.springapptest.service.QuestionsDao;
import com.ntselishchev.springapptest.service.TestProcessingServiceImpl;
import com.ntselishchev.springtest1.SpringTestApplicationTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestProcessingServiceImplTest extends SpringTestApplicationTests {

    protected TestProcessingServiceImpl testProcessingService;

    @Mock
    protected QuestionsDao questionsDao;

    @Before
    public void setUp() {
        testProcessingService = new TestProcessingServiceImpl(questionsDao);
    }

    @Test
    public void testGetTestEntriesWhenThereAreNoTestEntriesAvailableShouldReturnEmptyList() {
        int questionAmount = 2;
        boolean randomise = false;

        Mockito.when(questionsDao.getEntries()).thenReturn(Collections.emptyList());

        List<TestEntry> allEntries = testProcessingService.getTestEntries(questionAmount, randomise);

        Assert.assertTrue(allEntries.isEmpty());
    }

    @Test
    public void testGetTestEntriesWhenThereAreTestEntriesAvailableAndQuestionAmountEqualsToEntriesSizeShouldReturnEntryListWithEntryListSize() {
        int questionAmount = 2;
        boolean randomise = false;

        TestEntry entry1 = new TestEntry("q 1", "a 1");
        TestEntry entry2 = new TestEntry("q 2", "a 2");
        List<TestEntry> entryList = new ArrayList<>(Arrays.asList(entry1, entry2));

        Mockito.when(questionsDao.getEntries()).thenReturn(entryList);

        List<TestEntry> allEntries = testProcessingService.getTestEntries(questionAmount, randomise);

        Assert.assertEquals(entryList.size(), allEntries.size());
    }

    @Test
    public void testGetTestEntriesWhenThereAreTestEntriesAvailableAndQuestionAmountGreaterThanEntriesSizeShouldReturnEntryListWithEntryListSize() {
        int questionAmount = 5;
        boolean randomise = false;

        TestEntry entry1 = new TestEntry("q 1", "a 1");
        TestEntry entry2 = new TestEntry("q 2", "a 2");
        List<TestEntry> entryList = new ArrayList<>(Arrays.asList(entry1, entry2));

        Mockito.when(questionsDao.getEntries()).thenReturn(entryList);

        List<TestEntry> allEntries = testProcessingService.getTestEntries(questionAmount, randomise);

        Assert.assertEquals(entryList.size(), allEntries.size());
    }

    @Test
    public void testGetTestEntriesWhenThereAreTestEntriesAvailableAndQuestionAmountLessThanEntriesSizeShouldReturnEntryListWithQuestionAmountSize() {
        int questionAmount = 1;
        boolean randomise = false;

        TestEntry entry1 = new TestEntry("q 1", "a 1");
        TestEntry entry2 = new TestEntry("q 2", "a 2");
        List<TestEntry> entryList = new ArrayList<>(Arrays.asList(entry1, entry2));

        Mockito.when(questionsDao.getEntries()).thenReturn(entryList);

        List<TestEntry> allEntries = testProcessingService.getTestEntries(questionAmount, randomise);

        Assert.assertEquals(questionAmount, allEntries.size());
    }

    @Test
    public void testGetTestEntriesWhenThereAreTestEntriesAvailableAndRandomizeIsTrueSizeShouldReturnEntryListWithEntriesSizeEqualsToQuestionAmount() {
        int questionAmount = 2;
        boolean randomise = true;

        TestEntry entry1 = new TestEntry("q 1", "a 1");
        TestEntry entry2 = new TestEntry("q 2", "a 2");
        List<TestEntry> entryList = new ArrayList<>(Arrays.asList(entry1, entry2));

        Mockito.when(questionsDao.getEntries()).thenReturn(entryList);

        List<TestEntry> allEntries = testProcessingService.getTestEntries(questionAmount, randomise);

        Assert.assertEquals(entryList.size(), allEntries.size());
        Assert.assertTrue(allEntries.containsAll(entryList));
    }

    @Test
    public void testValidateAnswerWithWrongEntryAnswerWithSameCaseShouldReturnFalse() {
        String answer = "a 2";
        TestEntry entry1 = new TestEntry("q 1", "a 1");

        Mockito.when(questionsDao.getEntries()).thenReturn(Collections.singletonList(entry1));

        boolean validateAnswer = testProcessingService.validateAnswer(entry1, answer);

        Assert.assertFalse(validateAnswer);
    }

    @Test
    public void testValidateAnswerWithWrongEntryAnswerWithDifferentCaseShouldReturnFalse() {
        String answer = "A 2";
        TestEntry entry1 = new TestEntry("q 1", "a 1");

        Mockito.when(questionsDao.getEntries()).thenReturn(Collections.singletonList(entry1));

        boolean validateAnswer = testProcessingService.validateAnswer(entry1, answer);

        Assert.assertFalse(validateAnswer);
    }

    @Test
    public void testValidateAnswerWithCorrectEntryAnswerWithSameCaseShouldReturnTrue() {
        String answer = "a 1";
        TestEntry entry1 = new TestEntry("q 1", "a 1");

        Mockito.when(questionsDao.getEntries()).thenReturn(Collections.singletonList(entry1));

        boolean validateAnswer = testProcessingService.validateAnswer(entry1, answer);

        Assert.assertTrue(validateAnswer);
    }

    @Test
    public void testValidateAnswerWithCorrectEntryAnswerWithDifferentCaseShouldReturnTrue() {
        String answer = "A 1";
        TestEntry entry1 = new TestEntry("q 1", "a 1");

        boolean validateAnswer = testProcessingService.validateAnswer(entry1, answer);

        Assert.assertTrue(validateAnswer);
    }

    @Test
    public void testIsPassedWithCorrectAnswersAmountLessThanBorderValueShouldReturnFalse() {
        int correctAnswersAmount = 1;
        int border = 4;

        boolean isPassed = testProcessingService.isPassed(correctAnswersAmount, border);

        Assert.assertFalse(isPassed);
    }

    @Test
    public void testIsPassedWithCorrectAnswersAmountGreaterThatBorderValueShouldReturnTrue() {
        int correctAnswersAmount = 4;
        int border = 3;

        boolean isPassed = testProcessingService.isPassed(correctAnswersAmount, border);

        Assert.assertTrue(isPassed);
    }

    @Test
    public void testIsPassedWithCorrectAnswersAmountEqualsToBorderValueShouldReturnTrue() {
        int correctAnswersAmount = 4;
        int border = 4;

        boolean isPassed = testProcessingService.isPassed(correctAnswersAmount, border);

        Assert.assertTrue(isPassed);
    }
}
