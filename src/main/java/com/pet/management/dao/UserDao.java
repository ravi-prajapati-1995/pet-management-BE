package com.pet.management.dao;

import com.pet.management.model.auth.User;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Singleton
public class UserDao {
    @PersistenceContext(unitName = "MyPU")
    private EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User find(Long id) {
        return em.find(User.class, id);
    }
}