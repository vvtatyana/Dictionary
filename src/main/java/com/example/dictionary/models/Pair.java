package com.example.dictionary.models;

public class Pair {
    private int id;
    int idDictionary;
    int idWord;
    int idTranslation;

    public Pair() {}

    public Pair(int id, int idDictionary, int idWord, int idTranslation) {
        this.id = id;
        this.idDictionary = idDictionary;
        this.idWord = idWord;
        this.idTranslation = idTranslation;
    }

    public int getId() {
        return id;
    }

    public int getIdDictionary() {
        return idDictionary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdWord() {
        return idWord;
    }

    public void setIdWord(int idWord) {
        this.idWord = idWord;
    }

    public int getIdTranslation() {
        return idTranslation;
    }

    public void setIdTranslation(int idTranslation) {
        this.idTranslation = idTranslation;
    }
}
