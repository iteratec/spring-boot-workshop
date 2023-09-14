package de.iteratec.workshop.springdemo.plain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class FinalRatingCalculatorTest {

    private final FinalRatingCalculator calculator = new FinalRatingCalculator();

    @Test
    void calculate_empty() {
        // given
        List<Rating> ratings = List.of();
        // when
        Optional<Rating> rating = calculator.calculate(ratings);
        // then
        assertThat(rating).isEmpty();
    }

    @Test
    void calculate_single() {
        // given
        List<Rating> ratings = List.of(Rating.VALUABLE);
        // when
        Optional<Rating> rating = calculator.calculate(ratings);
        // then
        assertThat(rating).get().isEqualTo(Rating.VALUABLE);
    }

    @Test
    void calculate_same() {
        // given
        List<Rating> ratings = List.of(Rating.VALUABLE, Rating.VALUABLE, Rating.VALUABLE);
        // when
        Optional<Rating> rating = calculator.calculate(ratings);
        // then
        assertThat(rating).get().isEqualTo(Rating.VALUABLE);
    }

    @Test
    void calculate_mix1() {
        // given
        List<Rating> ratings = List.of(Rating.VALUABLE, Rating.MEDIOCRE, Rating.VALUABLE);
        // when
        Optional<Rating> rating = calculator.calculate(ratings);
        // then
        assertThat(rating).get().isEqualTo(Rating.MEDIOCRE);
    }

    @Test
    void calculate_mix2() {
        // given
        List<Rating> ratings = List.of(Rating.POOR, Rating.MEDIOCRE, Rating.VALUABLE);
        // when
        Optional<Rating> rating = calculator.calculate(ratings);
        // then
        assertThat(rating).get().isEqualTo(Rating.POOR);
    }
}