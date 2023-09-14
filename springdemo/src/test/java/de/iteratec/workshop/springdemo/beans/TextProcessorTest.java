package de.iteratec.workshop.springdemo.beans;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(TestConfig.class)
class TextProcessorTest {

    @Autowired
    private TextProcessor textProcessor;

    @Autowired
    private TextProvider textProviderMock;

    @Autowired
    private TextConsumer textConsumerMock;

    @Test
    void run() {
        // given
        String tooShort = "Zu kurzer Text";
        String matching = "Q".repeat(200);
        String tooManyVocals = "Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquid ex ea commodi consequat. Quis aute iure reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        Mockito.doReturn(tooShort, matching, tooManyVocals, null).when(textProviderMock).nextText();
        // when
        textProcessor.run();
        // then
        Mockito.verify(textConsumerMock).sendText(matching);
    }
}