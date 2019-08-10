package com.ntselishchev.springapptest.service;

import com.ntselishchev.springapptest.domain.TestEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvQuestionsDaoImpl implements QuestionsDao {

    private final CsvParserService csvParserService;
    private final CsvImportService importService;
    private final CsvMapperService mapperService;

    public List<TestEntry> getEntries() {
        InputStream inputStream = importService.getFile();
        List<List<String>> records = csvParserService.parseCsv(inputStream);

        return mapperService.mapToTestEntry(records);
    }

}
