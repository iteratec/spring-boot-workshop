package de.iteratec.workshop.springdemo.plain;

import java.util.List;
import java.util.Optional;

public class FinalRatingCalculator {

    public Optional<Rating> calculate(List<Rating> ratings) {
        return ratings.stream().min(Rating::compareTo);
    }
}
