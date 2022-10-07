package com.example.config;

import com.example.console.Console;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.example")
public class SpringConfig {
    @Bean
    public Console console(){
        return new Console();
    }
}
