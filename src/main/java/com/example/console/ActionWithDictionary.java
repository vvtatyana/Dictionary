package com.example.console;

import com.example.dictionary.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionWithDictionary {

    @Autowired
    private Dictionary dictionary;

    public void createDictionary(DictionaryData dictionaryData) {
        dictionary.setData(dictionaryData);
    }

    public String readAllDictionary() {
        return dictionary.getDictionary().toString();
    }

    public Boolean deletePair(String key) {
        return dictionary.remove(key);
    }

    public String readPair(String key) {
        if (dictionary.contains(key)) {
            return dictionary.getPair(key).toString();
        } else return "";
    }

    public Boolean writePair(String key, String value) {
        return dictionary.add(key, value);
    }
}