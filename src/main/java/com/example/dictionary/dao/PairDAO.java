package com.example.dictionary.dao;

import com.example.dictionary.models.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PairDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PairDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Pair> selectAllPairs(int idDictionary) {
        return selectPairs("id_dictionary = " + idDictionary);
    }

    public List<Pair> selectPairs(int idDictionary, int idWord) {
        return selectPairs("id_dictionary = " + idDictionary + " AND id_word = '" + idWord + "'");
    }

    public List<Pair> selectPairs(int idWord) {
        return selectPairs("id_word = '" + idWord + "'");
    }

    public Pair selectPair(int id) {
        return selectPairs("id = " + id).stream().findFirst().orElse(null);
    }

    private List<Pair> selectPairs(String where) {
        return jdbcTemplate.query(
                "SELECT * FROM pairs WHERE " + where,
                new BeanPropertyRowMapper<>(Pair.class)
        );
    }

    public void insetPairs(int id_dictionary, int id_word, int id_translation) {
        jdbcTemplate.update(
                "INSERT INTO pairs(id_dictionary, id_word, id_translation) VALUES(?, ?, ?)", id_dictionary, id_word, id_translation
        );
    }

    public void updatePairs(int id, int id_word, int id_translation) {
        jdbcTemplate.update(
                "UPDATE pairs SET id_word = " + id_word + ", id_translation = " + id_translation + " WHERE id = " + id
        );
    }

    public void deletePair(int id) {
        jdbcTemplate.update("DELETE FROM pairs WHERE id = " + id);
    }

}
