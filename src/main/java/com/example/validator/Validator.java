package com.example.validator;

import com.example.pair.Pair;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class Validator {
    Pattern pattern;

    public void setPattern(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Boolean validationWord(Pair pair) {
        return pattern.matcher(pair.getWord()).find();
    }

    public Boolean validationTranslation(Pair pair) {
        return pattern.matcher(pair.getTranslation()).find();
    }

    public Boolean validationPair(Pair pair) {
        return validationWord(pair) && validationTranslation(pair);
    }
}