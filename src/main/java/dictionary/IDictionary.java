package dictionary;

import pair.Couples;
import pair.Pair;

public interface IDictionary {
    Boolean add(String word, String translation);
    Boolean remove(String word);
    Boolean contains(String word);
    Couples getDictionary();
    Pair getPair(String word);
}
