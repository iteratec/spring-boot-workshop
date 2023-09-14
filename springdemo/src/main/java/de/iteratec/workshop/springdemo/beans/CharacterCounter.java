package de.iteratec.workshop.springdemo.beans;

import java.util.HashSet;
import java.util.Set;

public class CharacterCounter {

    private final Set<Character> characterSet;

    public CharacterCounter(char... chars) {
        characterSet = new HashSet<>();
        for (char c : chars) {
            characterSet.add(Character.toUpperCase(c));
        }
    }

    public long count(String text) {
        return text.chars().filter(this::isMatching).count();
    }

    private boolean isMatching(int c) {
        char upperChar = Character.toUpperCase((char) c);
        return characterSet.contains(upperChar);
    }
}
