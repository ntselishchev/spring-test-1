package com.ntselishchev.springapp01.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@Getter
@Setter
public class PropertiesService {

    @Value("${application.questions.file-name}")
    private String testCsvFileName;
    @Value("${application.questions.amount}")
    private Integer questionsAmount;
    @Value("${application.questions.randomise}")
    private Boolean randomise;
    @Value("${application.questions.passed-border}")
    private Integer passedBorder;
    @Value("#{systemProperties['user.language']}")
    private Locale locale;

    public Locale getLocale() {
        return locale != null ? locale : Locale.ENGLISH;
    }
}
