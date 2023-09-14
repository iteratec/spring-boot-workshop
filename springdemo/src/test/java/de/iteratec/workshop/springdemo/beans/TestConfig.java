package de.iteratec.workshop.springdemo.beans;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("de.iteratec.workshop.springdemo.beans")
class TestConfig {

    @Bean
    @Primary
    TextProvider textProviderMock() {
        return Mockito.mock(TextProvider.class);
    }

    @Bean
    @Primary
    TextConsumer textConsumerMock() {
        return Mockito.mock(TextConsumer.class);
    }
}
