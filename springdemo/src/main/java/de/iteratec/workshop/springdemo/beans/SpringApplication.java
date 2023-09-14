package de.iteratec.workshop.springdemo.beans;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("de.iteratec.workshop.springdemo.beans");
        TextProcessor processor = ctx.getBean(TextProcessor.class);
        processor.run();
    }

}
