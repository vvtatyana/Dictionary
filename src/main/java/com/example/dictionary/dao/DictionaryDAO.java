package com.example.dictionary.dao;

import com.example.dictionary.models.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DictionaryDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DictionaryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Dictionary> selectAllDictionary() {
        return jdbcTemplate.query(
                "SELECT * FROM dictionary", new BeanPropertyRowMapper<>(Dictionary.class)
        );
    }

    public Dictionary selectDictionary(String name) {
        return jdbcTemplate.query(
                "SELECT * FROM dictionary WHERE name = '" + name + "'",
                new BeanPropertyRowMapper<>(Dictionary.class)
        ).stream().findFirst().orElse(null);
    }

    public Dictionary selectDictionary(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM dictionary WHERE id = " + id,
                new BeanPropertyRowMapper<>(Dictionary.class)
        ).stream().findFirst().orElse(null);
    }

    public void insetDictionary(Dictionary dictionary) {
        jdbcTemplate.update("INSERT INTO dictionary(name, regex) VALUES(?, ?)", dictionary.getName(), dictionary.getRegex());
    }

    public void updateDictionary(int id, Dictionary newDictionary) {
        jdbcTemplate.update(
                "UPDATE dictionary SET name = '" + newDictionary.getName() + "', regex = '" + newDictionary.getRegex() + "' WHERE id = " + id
        );
    }

    public void deleteDictionary(int id) {
        jdbcTemplate.update("DELETE FROM dictionary WHERE id = " + id);
    }
}
