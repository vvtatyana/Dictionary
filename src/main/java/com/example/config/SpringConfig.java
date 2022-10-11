package com.example.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.example")
@PropertySource("classpath:application.properties")
public class SpringConfig {}
