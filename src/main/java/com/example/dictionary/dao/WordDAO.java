package com.example.dictionary.dao;

import com.example.dictionary.models.Pair;
import com.example.dictionary.models.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WordDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WordDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Word selectWord(int id) {
        return selectWords("id = " + id).stream().findFirst().orElse(null);
    }

    public Word selectWord(String word) {
        return selectWords("word = '" + word + "'").stream().findFirst().orElse(null);
    }

    public List<Word> selectWords(String where) {
        return jdbcTemplate.query(
                "SELECT * FROM words WHERE " + where,
                new BeanPropertyRowMapper<>(Word.class)
        );
    }

    public void insertWord(String word) {
        jdbcTemplate.update("INSERT INTO words (word) VALUES(?)", word);
    }

    public void updateWord(int idWord, String word) {
        jdbcTemplate.update("UPDATE words SET word = '" + word + "' WHERE id = " + idWord);
    }

    public void deleteWords(int id) {
        jdbcTemplate.update("DELETE FROM words WHERE id = " + id);
    }
}
