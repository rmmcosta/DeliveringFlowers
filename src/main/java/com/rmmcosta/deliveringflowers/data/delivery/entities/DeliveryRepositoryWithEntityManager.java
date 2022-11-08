package com.rmmcosta.deliveringflowers.data.delivery.entities;

import com.rmmcosta.deliveringflowers.data.inventory.entities.PlantDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DeliveryRepositoryWithEntityManager {

    private static final String FIND_DELIVERY_WITH_PLANTS =
            "select new com.rmmcosta.deliveringflowers.data.inventory.entities.PlantDTO(p.name, p.price) FROM Plant as p JOIN p.delivery as d WHERE d.id = :id";

    @PersistenceContext
    EntityManager entityManager;

    public void persist(Delivery delivery) {
        entityManager.persist(delivery);
    }

    public Delivery find(Long id) {
        return entityManager.find(Delivery.class, id);
    }

    public List<PlantDTO> findPlants(Long id) {
        TypedQuery<PlantDTO> query = entityManager.createQuery(FIND_DELIVERY_WITH_PLANTS, PlantDTO.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public Delivery merge(Delivery delivery) {
        return entityManager.merge(delivery);
    }

    public void delete(Long id) {
        Delivery delivery = entityManager.find(Delivery.class, id);
        entityManager.remove(delivery);
    }
}
