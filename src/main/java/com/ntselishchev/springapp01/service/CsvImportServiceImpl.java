package com.ntselishchev.springapp01.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class CsvImportServiceImpl implements CsvImportService {

    private final PropertiesService propertiesService;

    public InputStream getFile() {
        String fileName = propertiesService.getTestCsvFileName() + "_" + propertiesService.getLocale() + "." + propertiesService.getTestCsvFileExtension();
        return this.getClass().getClassLoader().getResourceAsStream(fileName);
    }

}
