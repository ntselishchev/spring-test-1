package com.ntselishchev.springapptest.domain.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Getter
@Setter
@EnableConfigurationProperties
public class Questions {
    private Integer amount;
    private boolean randomise;
    private Integer passedBorder;
    private String fileName;
    private String fileExtension;
}
