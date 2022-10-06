package console;

import dictionary.*;

public class ActionWithDictionary {
    private final IDictionary dictionary;

    public ActionWithDictionary(DictionaryBuild dictionaryData) {
        this.dictionary = dictionaryData.getDictionary();
    }

    public String readAllDictionary() {
        return dictionary.getDictionary().toString();
    }

    public void deletePair(String key) {
        dictionary.remove(key);
    }

    public String readPair(String key) {
        if (dictionary.contains(key)) {
            return dictionary.getPair(key).toString();
        } else return null;
    }

    public void writePair(String key, String value) {
        dictionary.add(key, value);
    }
}