package com.example.dictionary.dao;

import com.example.dictionary.models.Word;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Transactional
@Repository
public class WordDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public WordDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Word selectWord(int id) {
        Session session = sessionFactory.openSession();
        return session.createQuery("SELECT w FROM Word w WHERE id =: value", Word.class)
                .setParameter("value", id)
                .getResultList().stream().findFirst().orElse(null);
    }

    @Transactional
    public Word selectWord(String word) {
        Session session = sessionFactory.openSession();
        return session.createQuery("SELECT w FROM Word w WHERE word =: value", Word.class)
                .setParameter("value", word)
                .getResultList().stream().findFirst().orElse(null);
    }

    public void insertWord(String word) {
        Session session = sessionFactory.openSession();
        session.save(new Word(word));
    }

    public void updateWord(int id, String newWord) {
        Session session = sessionFactory.openSession();
        Word word = selectWord(id);
        word.setWord(newWord);
        session.beginTransaction();
        session.update(word);

        session.getTransaction().commit();
    }

    public void deleteWords(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(session.get(Word.class, id));
        session.getTransaction().commit();
    }
}
