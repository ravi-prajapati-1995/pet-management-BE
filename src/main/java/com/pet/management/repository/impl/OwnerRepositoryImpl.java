package com.pet.management.repository.impl;

import com.pet.management.model.Owner;
import com.pet.management.repository.OwnerRepository;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Singleton
@AllArgsConstructor
@NoArgsConstructor
public class OwnerRepositoryImpl implements OwnerRepository {

    @PersistenceContext
    public EntityManager em;

    public void save(Owner owner) {
        if (owner.getId() == 0) {
            em.persist(owner);
        } else {
            em.merge(owner);
        }
    }

    public Owner findById(int id) {
        return em.find(Owner.class, id);
    }

    public List<Owner> findAll() {
        TypedQuery<Owner> query = em.createQuery("SELECT o FROM Owner o", Owner.class);
        return query.getResultList();
    }

    public void delete(int id) {
        Owner owner = findById(id);
        if (owner != null) {
            em.remove(owner);
        }
    }

    public List<Owner> findByName(String name) {
        TypedQuery<Owner> query = em.createQuery(
                "SELECT o FROM Owner o WHERE o.name = :name", Owner.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

}
