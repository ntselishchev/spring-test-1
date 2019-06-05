package com.ntselishchev.springapp01.service;

import com.ntselishchev.springapp01.domain.TestEntry;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class TestProcessingService {

    private final CsvParserService csvParserService;
    private final CsvImportService importService;
    private final CsvMapperService mapperService;
    private final TranslationService translationService;

    public List<TestEntry> getTestEntries(Integer questionAmount, Boolean randomise, Locale locale) {
        InputStream inputStream = importService.getFile();
        List<List<String>> records = csvParserService.parseCsv(inputStream);
        List<TestEntry> allEntries = mapperService.mapToTestEntry(records);

        if (randomise) {
            Collections.shuffle(allEntries);
        }

        translationService.translateTestQuestions(allEntries, locale);

        questionAmount = questionAmount > allEntries.size() ? allEntries.size() : questionAmount;

        if (questionAmount == allEntries.size()) {
            return allEntries;
        }

        return ListUtils.partition(allEntries, questionAmount).get(0);
    }

    public boolean validateAnswer(TestEntry entry, String answer){
        return answer.equalsIgnoreCase(entry.getAnswer());
    }

    public static boolean isPassed(Integer correctAnswersAmount, Integer border){
        return correctAnswersAmount >= border;
    }


}
