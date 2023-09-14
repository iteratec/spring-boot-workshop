package de.iteratec.workshop.springdemo.plain;

public class VocalCounter {

    public long count(String text) {
        return text.chars().filter(this::isVocal).count();
    }

    private boolean isVocal(int c) {
        char upper = Character.toUpperCase((char) c);
        return upper == 'A' || upper == 'E' || upper == 'I' || upper == 'O' || upper == 'U';
    }
}
