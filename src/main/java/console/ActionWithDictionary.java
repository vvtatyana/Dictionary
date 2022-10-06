package console;

import dictionary.*;

public class ActionWithDictionary {
    private final IDictionary dictionary;

    public ActionWithDictionary(DictionaryData dictionaryData) {
        this.dictionary = new Dictionary(dictionaryData);
    }

    public String readAllDictionary() {
        return dictionary.getDictionary().toString();
    }

    public Boolean deletePair(String key) {
        return dictionary.remove(key);
    }

    public String readPair(String key) {
        if (dictionary.contains(key)) {
            return dictionary.getPair(key).toString();
        } else return "";
    }

    public Boolean writePair(String key, String value) {
        return dictionary.add(key, value);
    }
}