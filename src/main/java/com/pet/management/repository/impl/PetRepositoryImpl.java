package com.pet.management.repository.impl;

import com.pet.management.dto.PetDetailsDTO;
import com.pet.management.model.Pet;
import com.pet.management.repository.PetRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Stateless
@AllArgsConstructor
@NoArgsConstructor
public class PetRepositoryImpl implements PetRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Pet> findById(Long id) {
        Pet pet = em.find(Pet.class, id);
        return ofNullable(pet);
    }

    @Override
    public List<PetDetailsDTO> findAll() {
        String jpql = getSelectAllPets();
        TypedQuery<PetDetailsDTO> query = em.createQuery(jpql, PetDetailsDTO.class);
        return query.getResultList();
    }

    private static String getSelectAllPets() {
        return """
                    SELECT new com.pet.management.dto.PetDetailsDTO(
                        p.id, p.name, p.age,
                        o.id, o.name, o.phoneNumber,
                        (SELECT MAX(v.vaccinationTime) FROM Vaccine v WHERE v.pet.id = p.id)
                    )
                    FROM Pet p
                    JOIN p.owner o
                """;
    }


    @Override
    public List<PetDetailsDTO> findByName(String name) {
        String jpql = getSelectAllPets() + " WHERE LOWER(p.name) LIKE LOWER(:name)";
        TypedQuery<PetDetailsDTO> query = em.createQuery(jpql, PetDetailsDTO.class);
        query.setParameter("name", "%" + name.trim().toLowerCase() + "%");
        return query.getResultList();
    }

    @Override
    public void save(Pet pet) {
        if (pet.getId() == null || pet.getId() == 0) {
            em.persist(pet);
        } else {
            em.merge(pet);
        }
    }

    @Override
    public void delete(Long id) {
        Pet pet = em.find(Pet.class, id);
        if (pet != null) {
            em.remove(pet);
        }
    }

    @Override
    public List<PetDetailsDTO> findByIds(final List<Long> petIds) {
        String jpql = getSelectAllPets() + " WHERE p.id IN :ids";
        TypedQuery<PetDetailsDTO> query = em.createQuery(jpql, PetDetailsDTO.class);
        query.setParameter("ids", petIds);
        return query.getResultList().stream().distinct().toList();
    }
}
