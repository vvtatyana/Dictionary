package com.example.dictionary.dao;

import com.example.dictionary.models.Dictionary;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class DictionaryDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public DictionaryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Dictionary> selectAllDictionary() {
        Session session = sessionFactory.openSession();
        return session.createQuery("SELECT d FROM Dictionary d", Dictionary.class).getResultList();
    }

    public Dictionary selectDictionary(String name) {
        Session session = sessionFactory.openSession();
        return session.createQuery("SELECT d FROM Dictionary d WHERE name =: value",
                Dictionary.class).setParameter("value", name).getResultList().stream().findFirst().orElse(null);
    }

    public Dictionary selectDictionary(int id) {
        Session session = sessionFactory.openSession();
        return session.createQuery("SELECT d FROM Dictionary d WHERE id =: value",
                Dictionary.class).setParameter("value", id).getResultList().stream().findFirst().orElse(null);
    }

    public void insetDictionary(Dictionary dictionary) {
        Session session = sessionFactory.openSession();
        session.save(dictionary);
    }

    public void updateDictionary(int id, Dictionary newDictionary) {
        Session session = sessionFactory.openSession();
        Dictionary dictionary = selectDictionary(id);
        dictionary.setName(newDictionary.getName());
        dictionary.setRegex(newDictionary.getRegex());
        session.beginTransaction();
        session.update(dictionary);
        session.getTransaction().commit();
    }

    public void deleteDictionary(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(session.get(Dictionary.class, id));
        session.getTransaction().commit();
    }
}
