package com.ntselishchev.springapp01.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class CsvImportService {

    private final PropertiesService propertiesService;

    public InputStream getFile() {
       return this.getClass().getClassLoader().getResourceAsStream(propertiesService.getTestCsvFileName());
    }

}
