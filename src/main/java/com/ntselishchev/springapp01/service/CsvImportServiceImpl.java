package com.ntselishchev.springapp01.service;

import com.ntselishchev.springapp01.config.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class CsvImportServiceImpl implements CsvImportService {

    private final ApplicationProperties applicationProperties;

    public InputStream getFile() {
        return this.getClass().getClassLoader().getResourceAsStream(applicationProperties.getLocalizedTestCsvFileName());
    }

}
