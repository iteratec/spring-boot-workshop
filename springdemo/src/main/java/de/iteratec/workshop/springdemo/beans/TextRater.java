package de.iteratec.workshop.springdemo.beans;

import java.util.Optional;

public interface TextRater {

    Optional<Rating> rate(String text);
}
