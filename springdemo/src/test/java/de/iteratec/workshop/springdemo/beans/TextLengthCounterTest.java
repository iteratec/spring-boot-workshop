package de.iteratec.workshop.springdemo.beans;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TextLengthCounterTest {

    private final TextLengthCounter textLengthCounter = new TextLengthCounter();

    @Test
    void count_1() {
        // given
        String text = "ABC";
        // when
        long count = textLengthCounter.count(text);
        // then
        assertThat(count).isEqualTo(3);
    }

    @Test
    void count_2() {
        // given
        String text = "1234567890 abcdefghijklmnopqrstuvwxyz!";
        // when
        long count = textLengthCounter.count(text);
        // then
        assertThat(count).isEqualTo(36);
    }

    @Test
    void count_3() {
        // given
        String text = "";
        // when
        long count = textLengthCounter.count(text);
        // then
        assertThat(count).isEqualTo(0);
    }

    @Test
    void count_4() {
        // given
        String text = """
                !, ?. \t:
                """;
        // when
        long count = textLengthCounter.count(text);
        // then
        assertThat(count).isEqualTo(0);
    }
}