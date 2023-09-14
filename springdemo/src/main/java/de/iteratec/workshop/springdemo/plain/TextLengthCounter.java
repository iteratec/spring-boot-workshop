package de.iteratec.workshop.springdemo.plain;

public class TextLengthCounter {

    public long count(String text) {
        String pureText = text.replaceAll("[ \t\r\n!?.,:;\"']", "");
        return pureText.length();
    }
}
