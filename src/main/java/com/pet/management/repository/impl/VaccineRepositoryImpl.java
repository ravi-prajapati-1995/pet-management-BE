package com.pet.management.repository.impl;

import com.pet.management.model.Vaccine;
import com.pet.management.repository.VaccineRepository;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.time.LocalDateTime.now;

@Singleton
@AllArgsConstructor
@NoArgsConstructor
public class VaccineRepositoryImpl implements VaccineRepository {
    @PersistenceContext
    public EntityManager em;

    @Override
    public List<Vaccine> findByPetId(Long petId) {
        return em.createQuery("SELECT v FROM Vaccine v WHERE v.pet.id = :petId", Vaccine.class)
                .setParameter("petId", petId)
                .getResultList();
    }

    @Override
    public void saveAll(List<Vaccine> vaccines) {
        for (Vaccine v : vaccines) {
            if (v.getId() == null) {
                em.persist(v);
            } else {
                em.merge(v);
            }
        }
        em.flush();
    }

    @Override
    public int deleteByPetId(Long petId) {
        String jpql = "DELETE FROM Vaccine v WHERE v.pet.id = :petId";
        return em.createQuery(jpql)
                .setParameter("petId", petId)
                .executeUpdate();
    }

    @Override
    public List<Long> getPetIdsWhereVaccineExpire() {
        final var resultList = em.createNativeQuery(getQueryToGeRecentVaccines())
                .setParameter("oneHourAgo", now().minusHours(1))
                .getResultList();
        return resultList.stream()
                .map(o -> ((Number) o).longValue())
                .toList();
    }

    @Override
    public List<Vaccine> findAll() {
        return em.createQuery("SELECT u FROM Vaccine u", Vaccine.class)
                .getResultList();
    }

    private static String getQueryToGeRecentVaccines() {
        return """
                    SELECT v.pet_id
                    FROM vaccine v
                    INNER JOIN (
                        SELECT pet_id, MAX(vaccination_time) AS max_time
                        FROM vaccine
                        GROUP BY pet_id
                    ) latest ON v.pet_id = latest.pet_id AND v.vaccination_time = latest.max_time
                    WHERE latest.max_time < :oneHourAgo
                """;
    }
}
