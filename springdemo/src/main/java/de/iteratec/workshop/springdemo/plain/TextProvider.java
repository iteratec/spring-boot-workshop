package de.iteratec.workshop.springdemo.plain;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TextProvider {

    protected List<String> textList;

    public TextProvider() {
        Path inputFile = Paths.get("input.txt");
        try (Stream<String> lines = Files.lines(inputFile)) {
            textList = new ArrayList<>(lines.toList());
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    public String nextText() {
        if (textList.size() > 0) {
            return textList.remove(0);
        } else {
            return null;
        }
    }
}