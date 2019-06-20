package com.ntselishchev.springapp01.service;

import com.ntselishchev.springapp01.config.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TranslationServiceImpl implements TranslationService {

    private final MessageSource messageSource;
    private final ApplicationProperties applicationProperties;

    public String getTranslation(String field) {
        return messageSource.getMessage(field, null, applicationProperties.getLocale());
    }

    public String getTranslationUsingParams(String field, String[] params) {
        return messageSource.getMessage(field, params, applicationProperties.getLocale());
    }

}
