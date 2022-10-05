package dictionary;

import pair.Couples;
import pair.Pair;

public interface IDictionary {
    void add(String word, String translation);
    void remove(String word);
    Boolean contains(String word);
    Couples getDictionary();
    Pair getPair(String word);
}
