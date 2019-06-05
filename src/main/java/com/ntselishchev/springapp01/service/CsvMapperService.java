package com.ntselishchev.springapp01.service;

import com.ntselishchev.springapp01.domain.TestEntry;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CsvMapperService {

    public List<TestEntry> mapToTestEntry(List<List<String>> data) {
       return data.stream().map(entry -> new TestEntry(entry.get(0), entry.get(1))).collect(Collectors.toList());
    }

}
