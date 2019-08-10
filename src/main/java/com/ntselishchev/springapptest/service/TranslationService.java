package com.ntselishchev.springapptest.service;

public interface TranslationService {

    String getTranslation(String field);

    String getTranslationUsingParams(String field, String[] params);

}
