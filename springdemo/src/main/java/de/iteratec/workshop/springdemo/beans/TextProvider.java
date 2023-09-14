package de.iteratec.workshop.springdemo.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class TextProvider {

    private final List<String> textList;

    public TextProvider(@Value("${inputFile}") String inputFile) {
        Path inputPath = Paths.get(inputFile);
        try (Stream<String> lines = Files.lines(inputPath)) {
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
