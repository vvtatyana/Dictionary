package com.example.dictionary.service;

import com.example.dictionary.dao.PairDAO;
import com.example.dictionary.dao.WordDAO;
import com.example.dictionary.models.Pair;
import com.example.dictionary.models.RelatedPair;
import com.example.dictionary.models.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PairService {
    private final PairDAO pairDAO;
    private final WordDAO wordDAO;

    @Autowired
    public PairService(PairDAO pairDAO, WordDAO wordDAO) {
        this.pairDAO = pairDAO;
        this.wordDAO = wordDAO;
    }

    public List<RelatedPair> selectAll(int idDictionary){
        List<RelatedPair> relatedPairs = new ArrayList<>();
        List <Pair> pairs = pairDAO.selectAllPairs(idDictionary);
        for (Pair pair : pairs){
            Word word = wordDAO.selectWord(pair.getIdWord());
            if (word != null) {
                Word translation = wordDAO.selectWord(pair.getIdTranslation());
                if (translation != null) {
                    relatedPairs.add(new RelatedPair(pair.getId(), word.getWord(), translation.getWord()));
                }
            }
        }
        return relatedPairs;
    }

    public RelatedPair select(int id){
        Pair pair = pairDAO.selectPair(id);
        Word word = wordDAO.selectWord(pair.getIdWord());
        if (word != null) {
            Word translation = wordDAO.selectWord(pair.getIdTranslation());
            if (translation != null) {
                return new RelatedPair(pair.getId(), word.getWord(), translation.getWord());
            }
        }
        return null;
    }

    public void insert(int idDictionary, RelatedPair relatedPair){
        int idWord = insert(relatedPair.getWord());
        int idTranslation = insert(relatedPair.getTranslation());
        pairDAO.insetPairs(idDictionary, idWord, idTranslation);
    }

    private int insert(String wordStr){
        Word word = wordDAO.selectWord(wordStr);
        if (word == null) {
            wordDAO.insertWord(wordStr);
            return wordDAO.selectWord(wordStr).getId();
        } else {
            return word.getId();
        }
    }

    public void update(int id, RelatedPair relatedPair) {
        Pair pair = pairDAO.selectPair(id);
        int idWord = update(pair.getIdWord(), relatedPair.getWord());
        int idTranslation = update(pair.getIdTranslation(), relatedPair.getTranslation());
        pairDAO.updatePairs(id, idWord, idTranslation);
    }

    private int update(int idWord, String word) {
        if (!wordDAO.selectWord(idWord).getWord().equals(word)) {
            if (pairDAO.selectPairs(idWord).size() == 1) {
                wordDAO.updateWord(idWord, word);
            } else {
                wordDAO.insertWord(word);
                return wordDAO.selectWord(word).getId();
            }
        }
        return idWord;
    }

    public void delete(int id) {
        Pair pair = pairDAO.selectPair(id);
        pairDAO.deletePair(id);
        if(pairDAO.selectPairs(pair.getIdWord()).size() == 1){
            wordDAO.deleteWords(pair.getIdWord());
        }
        if(pairDAO.selectPairs(pair.getIdTranslation()).size() == 1){
            wordDAO.deleteWords(pair.getIdTranslation());
        }
    }

    public List<RelatedPair> search(int idDictionary, String keyword) {
        List<RelatedPair> relatedPairs = new ArrayList<>();
        Word word = wordDAO.selectWord(keyword);
        if (word != null) {
            List<Pair> pairs = pairDAO.selectAllPairs(idDictionary);
            for (Pair pair : pairs) {
                if (pair.getIdWord() == word.getId()) {
                    Word translation = wordDAO.selectWord(pair.getIdTranslation());
                    if (translation != null) {
                        relatedPairs.add(new RelatedPair(pair.getId(), word.getWord(), translation.getWord()));
                    }
                }
            }
        }
        return relatedPairs;
    }

}