package de.iteratec.workshop.springdemo.plain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class VocalCountRaterTest {

    private final VocalCountRater vocalCountRater = new VocalCountRater();

    @Test
    void rate_tooShortText_shouldReturnEmpty() {
        // given
        String text = "aaaa";
        // when
        Optional<Rating> rating = vocalCountRater.rate(text);
        // then
        assertThat(rating).isEmpty();
    }

    @Test
    void rate_manyVocals_shouldReturnPoor() {
        // given
        // TODO isn't there a better way to get a matching vocal ration?
        String text = "ababababababababababababababababababababababababab";
        // when
        Optional<Rating> rating = vocalCountRater.rate(text);
        // then
        assertThat(rating).get().isEqualTo(Rating.POOR);
    }

    @Test
    void rate_someVocals_shouldReturnMediocre() {
        // given
        String text = "abcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcd";
        // when
        Optional<Rating> rating = vocalCountRater.rate(text);
        // then
        assertThat(rating).get().isEqualTo(Rating.MEDIOCRE);
    }

    @Test
    void rate_barelyVocals_shouldReturnValuable() {
        // given
        String text = "bbbbbbbbbbbbbbbbbbbbbbabbbbbbbbbbbbbbbbbbbbbbbbbbb";
        // when
        Optional<Rating> rating = vocalCountRater.rate(text);
        // then
        assertThat(rating).get().isEqualTo(Rating.VALUABLE);
    }
}