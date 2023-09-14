package de.iteratec.workshop.springdemo.beans;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TextProcessor {

    private final TextProvider textProvider;
    private final FinalRatingCalculator finalRatingCalculator;
    private final TextConsumer textConsumer;
    private final List<TextRater> textRaterList;

    public TextProcessor(TextProvider textProvider, FinalRatingCalculator finalRatingCalculator,
                         TextConsumer textConsumer, List<TextRater> textRaterList) {
        this.textProvider = textProvider;
        this.finalRatingCalculator = finalRatingCalculator;
        this.textConsumer = textConsumer;
        this.textRaterList = textRaterList;
    }

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
