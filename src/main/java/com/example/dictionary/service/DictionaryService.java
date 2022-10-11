package com.example.dictionary.service;

import com.example.dictionary.dao.DictionaryDAO;
import com.example.dictionary.models.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DictionaryService {
    private final DictionaryDAO dictionaryDAO;

    @Autowired
    public DictionaryService(DictionaryDAO dictionaryDAO) {
        this.dictionaryDAO = dictionaryDAO;
    }

    public List<Dictionary> select(){
        return dictionaryDAO.selectAllDictionary();
    }

    public Dictionary select(int id){
        return dictionaryDAO.selectDictionary(id);
    }

    public Dictionary select(String name){
        return dictionaryDAO.selectDictionary(name);
    }

    public void insert(Dictionary dictionary){
        dictionaryDAO.insetDictionary(dictionary);
    }

    public void update(int id, Dictionary dictionary) {
        dictionaryDAO.updateDictionary(id, dictionary);
    }

    public void delete(int id) {
        dictionaryDAO.deleteDictionary(id);
    }
}
