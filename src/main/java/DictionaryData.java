public enum DictionaryData {
    LETTER("./src/main/resources/alphabeticDictionary.txt", 4),
    NUMBER("./src/main/resources/numericalDictionary.txt", 5);

    private String path = "";
    private int size = 0;

    DictionaryData(String path, int size) {
        this.path = path;
        this.size = size;
    }

    String getPath() {
        return path;
    }

    int getSize() {
        return size;
    }
}
