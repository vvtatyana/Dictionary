import java.util.Map;

public class Dictionary {
    private final Map<String, String> dictionary;
    private final DictionaryData dictionaryData;

    Dictionary(DictionaryData dictionaryData) {
        this.dictionaryData = dictionaryData;
        this.dictionary = new WorkingWithFile().read(dictionaryData.getPath());
    }

    void add(String key, String translation) {
        if (new CheckWord().checkWord(key, dictionaryData.getSize(), dictionaryData.getFilter())) {
            dictionary.put(key, translation);
            new WorkingWithFile().write(
                    dictionaryData.getPath(),
                    key + Separator.DICTIONARY.getSeparator() + translation
            );
        } else
            System.out.println("Не верные символы в слове или не верное количество символов");
    }

    String getTranslation(String key) {
        return dictionary.get(key);
    }

    void remove(String key) {
        dictionary.remove(key);
        new WorkingWithFile().write(dictionaryData.getPath(), dictionary.toString());
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String key : dictionary.keySet()) {
            result.append(key).append(Separator.OUTPUT.getSeparator()).append(dictionary.get(key)).append("\n");
        }
        return result.toString();
    }
}
