package dictionary;

public enum DictionaryData {
    ALPHABETIC("./src/main/resources/alphabeticDictionary.txt", "^[a-zA-Z]{1,4}"),
    NUMERICAL("./src/main/resources/numericalDictionary.txt", "[0-9]{1,5}");

    private final String filePath;
    private final String regex;

    DictionaryData(String dilePath, String regex){
        this.filePath = dilePath;
        this.regex = regex;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getRegex() {
        return regex;
    }
}
