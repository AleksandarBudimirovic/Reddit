package com.example.reddit.configuration;

import java.io.IOException;
import org.apache.tika.language.detect.LanguageDetector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.reddit.exceptionhandling.exception.NotFoundException;

@Configuration
public class BeanConfiguration {

    @Bean
    public LanguageDetector languageDetector() {
        LanguageDetector languageDetector;
        try {
            languageDetector = LanguageDetector.getDefaultLanguageDetector().loadModels();
        } catch (IOException e) {
            throw new NotFoundException("Error while loading language models.");
        }
        return languageDetector;
    }
}
