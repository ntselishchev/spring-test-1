package com.ntselishchev.springapptest.service;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class CsvParserServiceImpl implements CsvParserService {

    public List<List<String>> parseCsv(InputStream inputStream) {
        List<List<String>> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String[] rows;
            while ((rows = csvReader.readNext()) != null) {
                List<String> columns;
                for(String row : rows) {
                    columns = Arrays.asList(row.split(";"));
                    if (!CollectionUtils.isEmpty(columns)) {
                        records.add(columns);
                    }
                }
            }
        } catch (IOException e) {
            return Collections.emptyList();
        }
        return records;
    }

}
