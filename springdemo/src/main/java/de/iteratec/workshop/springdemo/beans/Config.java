package de.iteratec.workshop.springdemo.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:springdemo.properties")
class Config {

    @Bean
    TextLengthCounter textLengthCounter() {
        return new TextLengthCounter();
    }

    @Bean
    CharacterCounter vocalCounter() {
        return new CharacterCounter('A', 'E', 'I', 'O', 'U');
    }

    @Bean
    CharacterCounter xyzCounter() {
        return new CharacterCounter('X', 'Y', 'Z');
    }

    @Bean
    CharacterCountRater vocalCountRater() {
        return new CharacterCountRater(vocalCounter(), textLengthCounter());
    }

    @Bean
    CharacterCountRater xyzCountRater() {
        return new CharacterCountRater(xyzCounter(), textLengthCounter());
    }
}
