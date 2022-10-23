package com.example.dictionary.models;

import javax.persistence.*;

@Entity
@Table(name = "pairs")
public class Pair {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_dictionary")
    private int idDictionary;

    @Column(name = "id_word")
    private int idWord;

    @Column(name = "id_translation")
    private int idTranslation;

    public Pair() {}

    public Pair(int id, int idDictionary, int idWord, int idTranslation) {
        this.id = id;
        this.idDictionary = idDictionary;
        this.idWord = idWord;
        this.idTranslation = idTranslation;
    }

    public Pair(int idDictionary, int idWord, int idTranslation) {
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
