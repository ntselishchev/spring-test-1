package com.ntselishchev.springapp01.service;

import com.ntselishchev.springapp01.domain.TestEntry;

import java.util.List;

public interface CsvMapperService {

    List<TestEntry> mapToTestEntry(List<List<String>> data);

}
