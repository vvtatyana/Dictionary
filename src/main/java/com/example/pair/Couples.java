package com.example.pair;

import com.example.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Couples implements ICouples {
    private final List<Pair> couples = new ArrayList<Pair>();

    @Autowired
    private Validator validator;

    public void createValidator(String regex) {
        validator.setPattern(regex);
    }

    @Override
    public Boolean add(Pair pair) {
        if (checkWord(pair)) {
            couples.add(pair);
            return true;
        }
        return false;
    }

    @Override
    public void addAll(List<Pair> pairList) {
        couples.addAll(pairList);
    }

    @Override
    public Boolean remove(String word) {
        if (contains(word)) {
            couples.remove(getPair(word));
            return true;
        }
        return false;
    }

    @Override
    public Boolean contains(String word) {
        if (getPair(word) != null) {
            return true;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public Pair getPair(String word) {
        for (Pair pair : couples) {
            if (pair.getWord().equals(word)) {
                return pair;
            }
        }
        return null;
    }

    @Override
    public Boolean checkWord(Pair pair){
        return validator.validationWord(pair);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Pair pair : couples) {
            result.append(pair.toString()).append("\n");
        }
        return result.toString();
    }
}
