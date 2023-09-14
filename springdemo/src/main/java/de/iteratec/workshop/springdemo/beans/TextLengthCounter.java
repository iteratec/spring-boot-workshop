package de.iteratec.workshop.springdemo.beans;

import org.springframework.stereotype.Component;

@Component
public class TextLengthCounter {

    public long count(String text) {
        String pureText = text.replaceAll("[ \t\r\n!?.,:;\"']", "");
        return pureText.length();
    }
}
