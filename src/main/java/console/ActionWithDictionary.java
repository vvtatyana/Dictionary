package console;

import dictionary.AbstractDictionary;
import dictionary.AlphabeticDictionary;
import dictionary.NumericalDictionary;

public class ActionWithDictionary {

    private final AbstractDictionary dictionary;

    public ActionWithDictionary(String dictionaryData) {
        if(dictionaryData.equals("alphabeticDictionary"))
            this.dictionary = new AlphabeticDictionary();
        else this.dictionary = new NumericalDictionary();
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