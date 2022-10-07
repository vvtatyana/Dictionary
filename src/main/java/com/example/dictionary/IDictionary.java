package com.example.dictionary;

import com.example.pair.Couples;
import com.example.pair.Pair;
import org.springframework.stereotype.Component;

public interface IDictionary {
    Boolean add(String word, String translation);
    Boolean remove(String word);
    Boolean contains(String word);
    Couples getDictionary();
    Pair getPair(String word);
}
