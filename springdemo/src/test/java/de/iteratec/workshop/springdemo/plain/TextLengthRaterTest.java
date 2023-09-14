package de.iteratec.workshop.springdemo.plain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class TextLengthRaterTest {

    private final TextLengthRater textLengthRater = new TextLengthRater();

    @Test
    void rate_empty() {
        // given
        String text = "a".repeat(49);
        // when
        Optional<Rating> rating = textLengthRater.rate(text);
        // then
        assertThat(rating).isEmpty();
    }

    @Test
    void rate_poor1() {
        // given
        String text = "a".repeat(50);
        // when
        Optional<Rating> rating = textLengthRater.rate(text);
        // then
        assertThat(rating).get().isEqualTo(Rating.POOR);
    }

    @Test
    void rate_poor2() {
        // given
        String text = "a".repeat(119);
        // when
        Optional<Rating> rating = textLengthRater.rate(text);
        // then
        assertThat(rating).get().isEqualTo(Rating.POOR);
    }

    @Test
    void rate_valuable1() {
        // given
        String text = "a".repeat(120);
        // when
        Optional<Rating> rating = textLengthRater.rate(text);
        // then
        assertThat(rating).get().isEqualTo(Rating.VALUABLE);
    }

    @Test
    void rate_valuable2() {
        // given
        String text = "a".repeat(324);
        // when
        Optional<Rating> rating = textLengthRater.rate(text);
        // then
        assertThat(rating).get().isEqualTo(Rating.VALUABLE);
    }

    @Test
    void rate_mediocre() {
        // given
        String text = "a".repeat(325);
        // when
        Optional<Rating> rating = textLengthRater.rate(text);
        // then
        assertThat(rating).get().isEqualTo(Rating.MEDIOCRE);
    }
}