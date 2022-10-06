package dictionary;

import exception.WordValidationError;
import pair.Couples;
import pair.Pair;
import workingWithFile.FileIOStream;

public class Dictionary implements IDictionary {
    protected FileIOStream fileIOStream;
    protected Couples couples;

    public Dictionary(DictionaryData dictionaryData){
        String filePath = dictionaryData.getFilePath();
        fileIOStream = new FileIOStream(filePath);
        couples = new Couples(dictionaryData.getRegex());
        couples.addAll(fileIOStream.read());
    }

    @Override
    public Boolean add(String word, String translation) {
        Pair pair = new Pair(word, translation);
        if (couples.add(pair)) {
            fileIOStream.write(couples.toString());
            return true;
        } else throw new WordValidationError();
    }

    @Override
    public Boolean remove(String word) {
        if (couples.remove(word)) {
            fileIOStream.write(couples.toString());
            return true;
        }
        return false;
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