package de.iteratec.workshop.springdemo.beans;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TextLengthRater implements TextRater {

    private final TextLengthCounter textLengthCounter;

    public TextLengthRater(TextLengthCounter textLengthCounter) {
        this.textLengthCounter = textLengthCounter;
    }

    @Override
    public Optional<Rating> rate(String text) {
        long count = textLengthCounter.count(text);
        if (count < 50) {
            return Optional.empty();
        } else if (count < 120) {
            return Optional.of(Rating.POOR);
        } else if ( count < 325) {
            return Optional.of(Rating.VALUABLE);
        } else {
            return Optional.of(Rating.MEDIOCRE);
        }
    }
}
