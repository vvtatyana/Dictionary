package dictionary;

import exception.WordValidationError;
import workingWithFile.WritingToFile;
import pair.Couples;
import pair.Pair;

public abstract class AbstractDictionary implements IDictionary {
    protected WritingToFile writingToFile;
    protected Couples couples;

    AbstractDictionary(){
        createDictionary();
    }

    abstract void createDictionary();

    @Override
    public void add(String word, String translation) {
        Pair pair = new Pair(word, translation);
        if (couples.add(pair)) {
            writingToFile.write(couples.toString());
        } else throw new WordValidationError();
    }

    @Override
    public void remove(String word) {
        if (couples.remove(word)) {
            writingToFile.write(couples.toString());
        }
    }

    @Override
    public Boolean contains(String word) {
        return couples.contains(word);
    }

    @Override
    public Couples getDictionary() {
        return couples;
    }

    @Override
    public Pair getPair(String word){
        return couples.getPair(word);
    }
}