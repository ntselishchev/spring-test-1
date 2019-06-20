package com.ntselishchev.springapp01.service;

import com.ntselishchev.springapp01.domain.TestEntry;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestProcessingServiceImpl implements TestProcessingService {

    private final QuestionsDao questionsDao;

    public List<TestEntry> getTestEntries(int questionAmount, boolean randomise) {
        List<TestEntry> allEntries = questionsDao.getEntries();

        if (randomise) {
            Collections.shuffle(allEntries);
        }

        questionAmount = questionAmount > allEntries.size() ? allEntries.size() : questionAmount;

        if (questionAmount == allEntries.size()) {
            return allEntries;
        }

        return ListUtils.partition(allEntries, questionAmount).get(0);
    }

    public boolean validateAnswer(TestEntry entry, String answer){
        return answer.equalsIgnoreCase(entry.getAnswer());
    }

    public boolean isPassed(int correctAnswersAmount, int border) {
        return correctAnswersAmount >= border;
    }
}
