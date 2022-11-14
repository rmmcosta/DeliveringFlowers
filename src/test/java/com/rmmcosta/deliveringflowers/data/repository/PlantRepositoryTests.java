package com.rmmcosta.deliveringflowers.data.repository;

import com.rmmcosta.deliveringflowers.data.delivery.entities.Delivery;
import com.rmmcosta.deliveringflowers.data.inventory.PlantRepository;
import com.rmmcosta.deliveringflowers.data.inventory.entities.Plant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PlantRepositoryTests {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    PlantRepository plantRepository;

    @Test
    public void getAllPlantsCheaperThanWithSuccess() {
        int initDeliveredPlants = plantRepository.findAllByPriceLessThan(BigDecimal.valueOf(50.0)).size();
        Plant plant = new Plant();
        plant.setName("Cheaper Plant");
        plant.setPrice(BigDecimal.valueOf(35.5));
        testEntityManager.persist(plant);
        assertEquals(initDeliveredPlants + 1, plantRepository.findAllByPriceLessThan(BigDecimal.valueOf(50.0)).size());
        Plant plant2 = new Plant();
        plant2.setName("Expensive Plant");
        plant2.setPrice(BigDecimal.valueOf(51.0));
        testEntityManager.persist(plant2);
        assertEquals(initDeliveredPlants + 1, plantRepository.findAllByPriceLessThan(BigDecimal.valueOf(50.0)).size());
    }

    @Test
    public void getPlantIsDeliveredInfoWithSuccess() {
        Delivery delivery = new Delivery();
        delivery.setName("Completa");
        delivery.setAddress("Rua Luciano Cordeiro, 9, 4 Drt, 1150-211 Lisboa, Portugal");
        delivery.setCompleted(false);
        delivery.setDeliveryDate(LocalDate.now());
        delivery.setDeliveryTime(LocalTime.MIDNIGHT);
        Plant plant = new Plant();
        plant.setName("Cheaper Plant 2");
        plant.setPrice(BigDecimal.valueOf(35.5));
        delivery.setPlants(List.of(plant));
        delivery = testEntityManager.persist(delivery);
        plant.setDelivery(delivery);
        plant = testEntityManager.persist(plant);
        assertFalse(plantRepository.existsPlantByIdAndDeliveryIsCompletedIsTrue(plant.getId()));
        delivery.setCompleted(true);
        testEntityManager.persist(delivery);
        assertTrue(plantRepository.existsPlantByIdAndDeliveryIsCompletedIsTrue(plant.getId()));
    }
}
