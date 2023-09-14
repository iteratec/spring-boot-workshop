package de.iteratec.workshop.springdemo.plain;

import java.util.Optional;

public interface TextRater {

    Optional<Rating> rate(String text);
}
