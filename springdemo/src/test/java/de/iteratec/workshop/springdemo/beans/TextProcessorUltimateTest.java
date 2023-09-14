package de.iteratec.workshop.springdemo.beans;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(TestConfig.class)
@ActiveProfiles("ultimate")
class TextProcessorUltimateTest {

    @Autowired
    private TextProcessor textProcessor;

    @Autowired
    private TextProvider textProviderMock;

    @Autowired
    private TextConsumer textConsumerMock;

    @Test
    void run() {
        // given
        String tooShort = "This text is too short to get rated";
        String ultimate = "The ULTIMATE EDITION is out! Buy now and get 10% off!";
        String tooManyVocals = "A".repeat(200);
        Mockito.doReturn(tooShort, ultimate, tooManyVocals, null).when(textProviderMock).nextText();
        // when
        textProcessor.run();
        // then
        Mockito.verify(textConsumerMock).sendText(ultimate);
    }
}