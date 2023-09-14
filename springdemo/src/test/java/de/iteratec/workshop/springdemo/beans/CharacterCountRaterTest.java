package de.iteratec.workshop.springdemo.beans;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class CharacterCountRaterTest {

    private final static String SAMPLE_TEXT = "ABC";

    private final CharacterCounter characterCounterMock = Mockito.mock(CharacterCounter.class);
    private final TextLengthCounter textLengthCounterMock = Mockito.mock(TextLengthCounter.class);
    private final CharacterCountRater characterCountRater = new CharacterCountRater(characterCounterMock, textLengthCounterMock);

    @Test
    void rate_tooShortText_shouldReturnEmpty() {
        // given
        Mockito.when(textLengthCounterMock.count(SAMPLE_TEXT)).thenReturn(49L);
        // when
        Optional<Rating> rating = characterCountRater.rate(SAMPLE_TEXT);
        // then
        assertThat(rating).isEmpty();
    }

    @Test
    void rate_manyVocals_shouldReturnPoor() {
        // given
        Mockito.when(characterCounterMock.count(SAMPLE_TEXT)).thenReturn(36L);
        Mockito.when(textLengthCounterMock.count(SAMPLE_TEXT)).thenReturn(100L);
        // when
        Optional<Rating> rating = characterCountRater.rate(SAMPLE_TEXT);
        // then
        assertThat(rating).get().isEqualTo(Rating.POOR);
    }

    @Test
    void rate_someVocals_shouldReturnMediocre() {
        // given
        Mockito.when(characterCounterMock.count(SAMPLE_TEXT)).thenReturn(35L);
        Mockito.when(textLengthCounterMock.count(SAMPLE_TEXT)).thenReturn(100L);
        // when
        Optional<Rating> rating = characterCountRater.rate(SAMPLE_TEXT);
        // then
        assertThat(rating).get().isEqualTo(Rating.MEDIOCRE);
    }

    @Test
    void rate_someLessVocals_shouldReturnMediocre() {
        // given
        Mockito.when(characterCounterMock.count(SAMPLE_TEXT)).thenReturn(21L);
        Mockito.when(textLengthCounterMock.count(SAMPLE_TEXT)).thenReturn(100L);
        // when
        Optional<Rating> rating = characterCountRater.rate(SAMPLE_TEXT);
        // then
        assertThat(rating).get().isEqualTo(Rating.MEDIOCRE);
    }

    @Test
    void rate_barelyVocals_shouldReturnValuable() {
        // given
        Mockito.when(characterCounterMock.count(SAMPLE_TEXT)).thenReturn(20L);
        Mockito.when(textLengthCounterMock.count(SAMPLE_TEXT)).thenReturn(100L);
        // when
        Optional<Rating> rating = characterCountRater.rate(SAMPLE_TEXT);
        // then
        assertThat(rating).get().isEqualTo(Rating.VALUABLE);
    }
}