package com.rmmcosta.deliveringflowers.data.delivery;

import com.rmmcosta.deliveringflowers.data.delivery.entities.Delivery;
import com.rmmcosta.deliveringflowers.data.delivery.entities.Delivery_;
import com.rmmcosta.deliveringflowers.data.delivery.entities.RecipientAndPrice;
import com.rmmcosta.deliveringflowers.data.inventory.entities.Plant;
import com.rmmcosta.deliveringflowers.data.inventory.entities.PlantDTO;
import com.rmmcosta.deliveringflowers.data.inventory.entities.Plant_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DeliveryRepositoryWithEntityManager {
    private static final String FIND_DELIVERY_BY_NAME =
            "select d from Delivery d where d.name = :name";

    private static final String FIND_PLANT_BY_DELIVERY_NAME =
            "select p from Plant p join p.delivery d where d.name = :name";
    private static final String DELETE_DELIVERIES_BY_NAME =
            "delete from Delivery d where d.name = :name";

    private static final String DELETE_PLANT_BY_ID =
            "delete from Plant p where p.id = :id";
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

    public List<Delivery> findAllDeliveriesByName(String name) {
        var query = entityManager.createQuery(FIND_DELIVERY_BY_NAME);
        query.setParameter("name", name);
        return (List<Delivery>) query.getResultList();
    }

    public List<Plant> findAllPlantsByDeliveryName(String deliveryName) {
        var query = entityManager.createQuery(FIND_PLANT_BY_DELIVERY_NAME);
        query.setParameter("name", deliveryName);
        return (List<Plant>) query.getResultList();
    }

    public void deleteAllDeliveriesByName(String name) {
        List<Plant> plants = findAllPlantsByDeliveryName(name);
        for (Plant p : plants) {
            var query = entityManager.createQuery(DELETE_PLANT_BY_ID);
            query.setParameter("id", p.getId());
            query.executeUpdate();
        }
        var query = entityManager.createQuery(DELETE_DELIVERIES_BY_NAME);
        query.setParameter("name", name);
        query.executeUpdate();
    }

    public RecipientAndPrice getRecipientAndPricesSum(Long deliveryId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<RecipientAndPrice> criteria = builder.createQuery(RecipientAndPrice.class);
        Root<Plant> plant = criteria.from(Plant.class);
        Join<Plant, Delivery> delivery = plant.join(Plant_.delivery);
        criteria.where(builder.equal(delivery.get(Delivery_.id), deliveryId));
        Path<String> deliveryName = delivery.get(Delivery_.name);
        criteria.select(builder.construct(RecipientAndPrice.class, deliveryName, builder.sum(plant.get(Plant_.price))));
        return entityManager.createQuery(criteria).getSingleResult();
    }
}
