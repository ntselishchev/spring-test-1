package com.ntselishchev.springapptest.config;

import com.ntselishchev.springapptest.domain.properties.Questions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Getter
@Setter
@Component
@EnableConfigurationProperties
@ConfigurationProperties("application")
public class ApplicationProperties {

    private Questions questions;

    @Value("#{systemProperties['user.language']}")
    private Locale locale;

    public Locale getLocale() {
        return locale != null ? locale : Locale.ENGLISH;
    }

    public String getLocalizedTestCsvFileName() {
        return questions.getFileName() + "_" + getLocale() + "." + questions.getFileExtension();
    }

}
