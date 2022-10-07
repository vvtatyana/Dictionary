package com.example.dictionary;

public enum DictionaryData {
    ALPHABETIC("actionAlphabetic", "./src/main/resources/alphabeticDictionary.txt", "^[a-zA-Z]{1,4}"),
    NUMERICAL("actionNumerical", "./src/main/resources/numericalDictionary.txt", "[0-9]{1,5}");

    private final String bean;
    private final String filePath;
    private final String regex;

    DictionaryData(String bean, String dilePath, String regex){
        this.filePath = dilePath;
        this.bean = bean;
        this.regex = regex;
    }

    public String getBean() {
        return bean;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getRegex() {
        return regex;
    }
}
