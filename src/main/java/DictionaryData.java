public enum DictionaryData {
    LETTER("alphabeticDictionary", 4, "^[a-zA-Z]+$"),
    NUMBER("numericalDictionary", 5, "\\d+");

    private final String nameFile;
    private final int size;
    private final String filter;

    DictionaryData(String nameFile, int size, String filter) {
        this.nameFile = nameFile;
        this.size = size;
        this.filter = filter;
    }

    String getPath() {
        return generatePath(nameFile);
    }

    int getSize() {
        return size;
    }

    String getFilter() {
        return filter;
    }

    private String generatePath(String nameFile){
        return "./src/main/resources/" + nameFile + ".txt";
    }
}
