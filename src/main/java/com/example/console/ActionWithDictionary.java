package com.example.console;

import com.example.dictionary.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionWithDictionary {

    private final Dictionary dictionary;

    @Autowired
    public ActionWithDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void createDictionary(DictionaryData dictionaryData) {
        dictionary.setData(dictionaryData);
    }

    public String readAllDictionary() {
        String dictionaryAll = dictionary.getDictionary().toString();
        if (dictionaryAll.isEmpty()) {
            return "Словарь пустой";
        }
        return dictionaryAll;
    }

    public String deletePair(String key) {
        if(dictionary.remove(key)){
            return "Пара удалена из словаря";
        } else {
            return "Не удалось удалить пару слов из словаря";
        }
    }

    public String searchPair(String key) {
        if (dictionary.contains(key)) {
            return dictionary.getPair(key).toString();
        } else {
            return "Пара с таким ключем не найдена";
        }
    }

    public String writePair(String key, String value) {
        if (dictionary.add(key, value)){
            return "Пара записана в словарь";
        } else {
            return "Не удалось записать в словарь";
        }
    }
}