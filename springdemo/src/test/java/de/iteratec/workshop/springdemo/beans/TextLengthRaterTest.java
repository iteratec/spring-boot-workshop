package de.iteratec.workshop.springdemo.beans;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class TextLengthRaterTest {

    private final static String SAMPLE_TEXT = "ABC";

    private final TextLengthCounter textLengthCounterMock = Mockito.mock(TextLengthCounter.class);
    private final TextLengthRater textLengthRater = new TextLengthRater(textLengthCounterMock);

    @Test
    void rate_empty() {
        // given
        Mockito.when(textLengthCounterMock.count(SAMPLE_TEXT)).thenReturn(49L);
        // when
        Optional<Rating> rating = textLengthRater.rate(SAMPLE_TEXT);
        // then
        assertThat(rating).isEmpty();
    }

    @Test
    void rate_poor1() {
        // given
        Mockito.when(textLengthCounterMock.count(SAMPLE_TEXT)).thenReturn(50L);
        // when
        Optional<Rating> rating = textLengthRater.rate(SAMPLE_TEXT);
        // then
        assertThat(rating).get().isEqualTo(Rating.POOR);
    }

    @Test
    void rate_poor2() {
        // given
        Mockito.when(textLengthCounterMock.count(SAMPLE_TEXT)).thenReturn(119L);
        // when
        Optional<Rating> rating = textLengthRater.rate(SAMPLE_TEXT);
        // then
        assertThat(rating).get().isEqualTo(Rating.POOR);
    }

    @Test
    void rate_valuable1() {
        // given
        Mockito.when(textLengthCounterMock.count(SAMPLE_TEXT)).thenReturn(120L);
        // when
        Optional<Rating> rating = textLengthRater.rate(SAMPLE_TEXT);
        // then
        assertThat(rating).get().isEqualTo(Rating.VALUABLE);
    }

    @Test
    void rate_valuable2() {
        // given
        Mockito.when(textLengthCounterMock.count(SAMPLE_TEXT)).thenReturn(324L);
        // when
        Optional<Rating> rating = textLengthRater.rate(SAMPLE_TEXT);
        // then
        assertThat(rating).get().isEqualTo(Rating.VALUABLE);
    }

    @Test
    void rate_mediocre() {
        // given
        Mockito.when(textLengthCounterMock.count(SAMPLE_TEXT)).thenReturn(325L);
        // when
        Optional<Rating> rating = textLengthRater.rate(SAMPLE_TEXT);
        // then
        assertThat(rating).get().isEqualTo(Rating.MEDIOCRE);
    }
}