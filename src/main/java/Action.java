public class Action {

    private static Dictionary dictionary;

    Action(DictionaryData dictionaryData) {
        dictionary = new Dictionary(dictionaryData);
    }

    String readAllDictionary() {
        return dictionary.toString();
    }

    void deletePair(String key) {
        dictionary.remove(key);
    }

    String readPair(String key) {
        return key + Separator.OUTPUT.getSeparator() + dictionary.getTranslation(key);
    }

    void writePair(String key, String value) {
        dictionary.add(key, value);
    }
}
