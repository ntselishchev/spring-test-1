package com.ntselishchev.springapptest.service;

import com.ntselishchev.springapptest.config.ApplicationProperties;
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
