package de.iteratec.workshop.springdemo.beans;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("ultimate")
public class UtimateEditionRater implements TextRater {

    @Override
    public Optional<Rating> rate(String text) {
        if (text.toUpperCase().contains("ULTIMATE")) {
            return Optional.of(Rating.VALUABLE);
        } else {
            return Optional.empty();
        }
    }
}
