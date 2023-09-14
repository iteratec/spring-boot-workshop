package de.iteratec.workshop.springdemo.beans;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TextConsumer {

    private final List<String> textList = new ArrayList<>();

    public void sendText(String text) {
        textList.add(text);
        System.out.println("Received: " + text);
    }
}
