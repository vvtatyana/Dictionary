package com.example.dictionary.dao;

import com.example.dictionary.models.Pair;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class PairDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PairDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Pair> selectAllPairs(int idDictionary) {
        Session session = sessionFactory.openSession();
        return session.createQuery("SELECT p FROM Pair p WHERE idDictionary =: value", Pair.class)
                .setParameter("value", idDictionary).getResultList();
    }

    public List<Pair> selectPairs(int idDictionary, int idWord) {
        Session session = sessionFactory.openSession();
        return session.createQuery(
                "SELECT p FROM Pair p WHERE idDictionary =: valueDic AND idWord =: valueWor", Pair.class)
                .setParameter("valueDic", idDictionary)
                .setParameter("valueWor", idWord)
                .getResultList();
    }

    public List<Pair> selectPairs(int idWord) {
        Session session = sessionFactory.openSession();
        return session.createQuery(
                "SELECT p FROM Pair p WHERE idWord =: value", Pair.class)
                .setParameter("value", idWord).getResultList();
    }

    public Pair selectPair(int id) {
        Session session = sessionFactory.openSession();
        return session.createQuery(
                        "SELECT p FROM Pair p WHERE id =: value", Pair.class)
                .setParameter("value", id).getResultList().stream().findFirst().orElse(null);
    }

    public void insetPairs(int id_dictionary, int id_word, int id_translation) {
        Session session = sessionFactory.openSession();
        session.save(new Pair(id_dictionary, id_word, id_translation));
    }

    public void updatePairs(int id, int id_word, int id_translation) {
        Session session = sessionFactory.openSession();
        Pair pair = selectPair(id);
        pair.setIdWord(id_word);
        pair.setIdTranslation(id_translation);
        session.beginTransaction();
        session.update(pair);
        session.getTransaction().commit();
    }

    public void deletePair(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(session.get(Pair.class, id));
        session.getTransaction().commit();
    }
}
