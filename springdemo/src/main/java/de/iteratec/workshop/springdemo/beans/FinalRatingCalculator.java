package de.iteratec.workshop.springdemo.beans;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FinalRatingCalculator {

    public Optional<Rating> calculate(List<Rating> ratings) {
        return ratings.stream().min(Rating::compareTo);
    }
}
