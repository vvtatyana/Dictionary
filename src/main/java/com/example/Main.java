package com.example;

import com.example.config.SpringConfig;
import com.example.console.Console;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);
        Console console = context.getBean("console", Console.class);
        console.start();
    }
}
