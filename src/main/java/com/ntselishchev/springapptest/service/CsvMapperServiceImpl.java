package com.ntselishchev.springapptest.service;

import com.ntselishchev.springapptest.domain.TestEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CsvMapperServiceImpl implements CsvMapperService {

    private static final Logger logger = LoggerFactory.getLogger("csv-processing-data");

    public List<TestEntry> mapToTestEntry(List<List<String>> data) {

        List<List<String>> fullDataEntries = data.stream().filter(e -> e.size() == 2).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(fullDataEntries) || fullDataEntries.size() < data.size()) {
            logger.error("Test file has an incomplete data");
        }
        if(CollectionUtils.isEmpty(fullDataEntries)) {
            return Collections.emptyList();
        }

        return fullDataEntries.stream().map(entry -> new TestEntry(entry.get(0), entry.get(1))).collect(Collectors.toList());
    }

}
