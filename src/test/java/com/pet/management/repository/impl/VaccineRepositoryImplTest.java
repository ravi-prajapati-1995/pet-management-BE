package com.pet.management.repository.impl;

import com.pet.management.model.Pet;
import com.pet.management.model.Vaccine;
import com.pet.management.repository.OwnerRepository;
import com.pet.management.repository.VaccineRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.assertEquals;

class VaccineRepositoryImplTest {
    private static EntityManagerFactory emf;
    private EntityManager em;
    private PetRepositoryImpl repo;
    private OwnerRepository ownerRepository;
    private VaccineRepository vaccineRepository;

    @BeforeAll
    static void initFactory() {
        emf = Persistence.createEntityManagerFactory("test-pu");
    }

    @BeforeEach
    void initEntityManager() {
        em = emf.createEntityManager();
        repo = new PetRepositoryImpl(em);
        ownerRepository = new OwnerRepositoryImpl(em);
        vaccineRepository = new VaccineRepositoryImpl(em);
        em.getTransaction().begin();
    }

    @AfterEach
    void closeEm() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
        em.close();
    }

    @Test
    void findByPetId() {
        // give
        Pet pet = new Pet("Buddy", 4);
        repo.save(pet);
        final var vaccine = new Vaccine("Rabis", now(), pet);
        final var vaccine1 = new Vaccine("vaccine1", now(), pet);
        final var vaccine2 = new Vaccine("vaccine2", now(), pet);

        final var vaccineList = List.of(vaccine, vaccine1, vaccine2);
        vaccineRepository.saveAll(vaccineList);

        // when
        final var byPetId = vaccineRepository.findByPetId(pet.getId());

        // then
        assertEquals(3, byPetId.size());
    }

    @Test
    void getPetIdsWhereVaccineExpire() {
        // given
        Pet pet = new Pet("Buddy", 4);
        repo.save(pet);
        final var vaccine = new Vaccine("Rabis", now().minusHours(1), pet);
        final var vaccine1 = new Vaccine("vaccine1", now().minusHours(2), pet);
        final var vaccine2 = new Vaccine("vaccine2", now().minusHours(3), pet);
        final var vaccineList = List.of(vaccine, vaccine1, vaccine2);
        vaccineRepository.saveAll(vaccineList);

        //when
        final var recentVaccineIds = vaccineRepository.getPetIdsWhereVaccineExpire();

        // then

        assertEquals(1, recentVaccineIds.size());
        assertEquals(pet.getId(), recentVaccineIds.getFirst());

    }
}