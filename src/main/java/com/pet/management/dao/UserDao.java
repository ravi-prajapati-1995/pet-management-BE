package com.pet.management.dao;

import com.pet.management.model.auth.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

@Stateless
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