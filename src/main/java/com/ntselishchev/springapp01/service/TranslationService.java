package com.ntselishchev.springapp01.service;

public interface TranslationService {

    String getTranslation(String field);

    String getTranslationUsingParams(String field, String[] params);

}
