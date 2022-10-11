package com.example.dictionary.models;

public class Dictionary {
    private int id;
    private String name;
    private String regex;

    public Dictionary(){}

    public Dictionary(int id, String name, String regex) {
        this.id = id;
        this.name = name;
        this.regex = regex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
}
