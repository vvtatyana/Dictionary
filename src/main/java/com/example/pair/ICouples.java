package com.example.pair;

import java.util.List;


public interface ICouples {
    Boolean add(Pair pair);

    void addAll(List<Pair> pairList);

    Boolean remove(String word);

    Boolean contains(String word);

    Pair getPair(String word);

    Boolean checkWord(Pair pair);
}
