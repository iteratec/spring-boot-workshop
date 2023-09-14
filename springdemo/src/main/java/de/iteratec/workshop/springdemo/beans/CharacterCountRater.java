package de.iteratec.workshop.springdemo.beans;

import java.util.Optional;

public class CharacterCountRater implements TextRater {

    private final CharacterCounter characterCounter;
    private final TextLengthCounter lengthCounter;

    public CharacterCountRater(CharacterCounter characterCounter, TextLengthCounter lengthCounter) {
        this.characterCounter = characterCounter;
        this.lengthCounter = lengthCounter;
    }

    @Override
    public Optional<Rating> rate(String text) {
        long length = lengthCounter.count(text);
        if (length < 50) {
            return Optional.empty();
        }

        long count = characterCounter.count(text);
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
