package de.iteratec.workshop.springdemo.plain;

import java.util.Optional;

public class VocalCountRater implements TextRater {

    private final VocalCounter vocalCounter = new VocalCounter();
    private final TextLengthCounter lengthCounter = new TextLengthCounter();

    @Override
    public Optional<Rating> rate(String text) {
        long length = lengthCounter.count(text);
        if (length < 50) {
            return Optional.empty();
        }

        long count = vocalCounter.count(text);
        double ratio = count / (double) length;

        return getRatingForRatio(ratio);
    }

    private Optional<Rating> getRatingForRatio(double ratio) {
        if (ratio > 0.35) {
            return Optional.of(Rating.POOR);
        } else if (ratio  > 0.2 ) {
            return Optional.of(Rating.MEDIOCRE);
        } else {
            return Optional.of(Rating.VALUABLE);
        }
    }
}
