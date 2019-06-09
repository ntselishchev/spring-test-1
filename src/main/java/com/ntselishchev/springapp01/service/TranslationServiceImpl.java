package com.ntselishchev.springapp01.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TranslationServiceImpl implements TranslationService {

    private final MessageSource messageSource;
    private final PropertiesService propertiesService;

    public String getTranslation(String field) {
        return messageSource.getMessage(field, null, propertiesService.getLocale());
    }

    public String getTranslationUsingParams(String field, String[] params) {
        return messageSource.getMessage(field, params, propertiesService.getLocale());
    }

}
