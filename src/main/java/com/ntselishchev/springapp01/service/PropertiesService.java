package com.ntselishchev.springapp01.service;

import java.util.Locale;

public interface PropertiesService {

    Locale getLocale();

    String getTestCsvFileName();

    Integer getQuestionsAmount();

    Boolean getRandomise();

    Integer getPassedBorder();

    String getTestCsvFileExtension();
}
