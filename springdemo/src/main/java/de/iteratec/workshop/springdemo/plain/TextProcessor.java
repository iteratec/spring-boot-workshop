package de.iteratec.workshop.springdemo.plain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TextProcessor {

    private final TextProvider textProvider = new TextProvider();
    private final FinalRatingCalculator finalRatingCalculator = new FinalRatingCalculator();
    private final TextConsumer textConsumer = new TextConsumer();
    private final List<TextRater> textRaterList = List.of(
            new VocalCountRater(),
            new TextLengthRater()
    );

    public void run() {
        String nextText = textProvider.nextText();
        while (nextText != null) {
            processText(nextText);
            nextText = textProvider.nextText();
        }
    }

    private void processText(String text) {
        Optional<Rating> finalRating = rateText(text);
        finalRating.ifPresent(r -> sendForMediocreAndUp(r, text));
    }

    private Optional<Rating> rateText(String text) {
        List<Rating> ratings = new ArrayList<>();
        for (TextRater textRater : textRaterList) {
            textRater.rate(text).ifPresent(ratings::add);
        }

        return finalRatingCalculator.calculate(ratings);
    }

    private void sendForMediocreAndUp(Rating rating, String text) {
        if (rating.compareTo(Rating.MEDIOCRE) >= 0) {
            textConsumer.sendText(text);
        }
    }
}
