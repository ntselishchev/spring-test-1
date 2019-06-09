package com.ntselishchev.springapp01.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Getter
@Setter
@Component
public class ApplicationProperties {

    @Value("${application.questions.file-name}")
    private String testCsvFileName;
    @Value("${application.questions.file-extension}")
    private String testCsvFileExtension;
    @Value("${application.questions.amount}")
    private Integer questionsAmount;
    @Value("${application.questions.randomise}")
    private boolean randomise;
    @Value("${application.questions.passed-border}")
    private int passedBorder;
    @Value("#{systemProperties['user.language']}")
    private Locale locale;

    public Locale getLocale() {
        return locale != null ? locale : Locale.ENGLISH;
    }

    public String getLocalizedTestCsvFileName() {
        return getTestCsvFileName() + "_" + getLocale() + "." + getTestCsvFileExtension();
    }

}
