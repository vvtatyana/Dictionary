package com.example.dictionary;

import com.example.exception.WordValidationError;
import com.example.pair.Couples;
import com.example.pair.Pair;
import com.example.workingWithFile.FileIOStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Dictionary implements IDictionary {
    private final FileIOStream fileIOStream;
    private final Couples couples;

    @Autowired
    public Dictionary(FileIOStream fileIOStream, Couples couples) {
        this.fileIOStream = fileIOStream;
        this.couples = couples;
    }

    public void setData(DictionaryData dictionaryData){
        fileIOStream.setFilePath(dictionaryData.getFilePath());
        couples.createValidator(dictionaryData.getRegex());
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