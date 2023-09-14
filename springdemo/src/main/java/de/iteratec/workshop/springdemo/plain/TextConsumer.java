package de.iteratec.workshop.springdemo.plain;

import java.util.ArrayList;
import java.util.List;

public class TextConsumer {

    protected List<String> textList = new ArrayList<>();

    public void sendText(String text) {
        textList.add(text);
        System.out.println("Received: " + text);
    }
}
