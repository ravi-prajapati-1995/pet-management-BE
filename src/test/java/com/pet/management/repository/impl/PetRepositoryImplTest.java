package com.pet.management.repository.impl;

import com.pet.management.model.Owner;
import com.pet.management.model.Pet;
import com.pet.management.repository.OwnerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
class PetRepositoryImplTest {
    private static EntityManagerFactory emf;
    private EntityManager em;
    private PetRepositoryImpl repo;
    private OwnerRepository ownerRepository;

    @BeforeAll
    static void initFactory() {
        emf = Persistence.createEntityManagerFactory("test-pu");
    }

    @BeforeEach
    void initEntityManager() {
        em = emf.createEntityManager();
        repo = new PetRepositoryImpl(em);
        ownerRepository = new OwnerRepositoryImpl(em);
        em.getTransaction().begin();
    }

    @AfterEach
    void closeEm() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
        em.close();
    }

    @AfterAll
    static void closeFactory() {
        emf.close();
    }

    @Test
    void findById() {
        // given
        Pet pet = new Pet("Buddy", 4);
        repo.save(pet);

        // when
        Pet found = repo.findById(pet.getId()).get();

        // then
        assertNotNull(found);
        assertEquals("Buddy", found.getName());
        assertEquals(4, found.getAge());
    }

    @Test
    void testUpdatePet() {
        // given
        Pet pet = new Pet("Max", 2);
        repo.save(pet);
        pet = repo.findById(pet.getId()).get();
        Pet updatedPet = pet.toBuilder()
                .name("Maximus")
                .age(3)
                .build();

        // when
        repo.save(updatedPet);


        // then
        Pet updated = repo.findById(pet.getId()).get();
        assertEquals("Maximus", updated.getName());
        assertEquals(3, updated.getAge());
    }

    @Test
    void findAll() {
        // given
        Owner owner = getOwner();
        Pet pet1 = Pet.builder().name("Charlie").age(5).owner(owner).build();
        Pet pet2 = Pet.builder().name("Bella").age(3).owner(owner).build();


        repo.save(pet1);
        repo.save(pet2);

        // when
        final var pets = repo.findAll();

        // then
        assertTrue(pets.size() >= 2);
    }

    private Owner getOwner() {
        Owner owner = Owner.builder()
                .name("ravi")
                .phoneNumber("9802883865")
                .build();
        ownerRepository.save(owner);
        return owner;
    }

    @Test
    void findByName() {
        // given
        Owner owner = getOwner();
        Pet pet1 = Pet.builder().name("Charlie").age(5).owner(owner).build();
        Pet pet2 = Pet.builder().name("Luna").age(3).owner(owner).build();
        repo.save(pet1);
        repo.save(pet2);

        // when
        final var found = repo.findByName("Luna");

        // then
        assertFalse(found.isEmpty());
        assertEquals("Luna", found.get(0).getName());
    }

    @Test
    void save() {
        // given
        Pet pet = new Pet("Buddy", 4);

        // when
        repo.save(pet);

        // then
        Pet found = repo.findById(pet.getId()).get();
        assertNotNull(found);
        assertEquals("Buddy", found.getName());
        assertEquals(4, found.getAge());
    }

    @Test
    void delete() {
        // given
        Pet pet = new Pet("Rocky", 4);
        repo.save(pet);

        // when
        repo.delete(pet.getId());
        Pet deleted = repo.findById(pet.getId()).get();

        // then
        assertNull(deleted);
    }

    @Test
    void findByIds() {
        // given
        Owner owner = getOwner();
        Pet pet1 = Pet.builder().name("Charlie").age(5).owner(owner).build();
        Pet pet2 = Pet.builder().name("Luna").age(3).owner(owner).build();
        repo.save(pet1);
        repo.save(pet2);

        // when
        final var petDetailsDTOS = repo.findByIds(List.of(pet1.getId(), pet2.getId()));

        // then
        assertEquals(2, petDetailsDTOS.size());

    }
}