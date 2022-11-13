package com.rmmcosta.deliveringflowers.service;

import com.rmmcosta.deliveringflowers.data.delivery.DeliveryRepository;
import com.rmmcosta.deliveringflowers.data.delivery.entities.Delivery;
import com.rmmcosta.deliveringflowers.data.inventory.FlowerRepository;
import com.rmmcosta.deliveringflowers.data.inventory.ShrubRepository;
import com.rmmcosta.deliveringflowers.data.inventory.entities.Flower;
import com.rmmcosta.deliveringflowers.data.inventory.entities.Plant;
import com.rmmcosta.deliveringflowers.data.inventory.entities.Shrub;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlantServiceTests {
    @Autowired
    PlantService plantService;
    @Autowired
    DeliveryService deliveryService;
    @Autowired
    FlowerRepository flowerRepository;
    @Autowired
    ShrubRepository shrubRepository;

    @Test
    public void twoSubPlantsInsertWithSuccess() {
        Long initPlantsCount = plantService.count();
        Delivery delivery = new Delivery();
        delivery.setName("Ricardo Costa");
        delivery.setAddress("Rua Luciano Cordeiro, 9, 4 Drt, 1150-211 Lisboa, Portugal");
        delivery.setCompleted(true);
        delivery.setDeliveryDate(LocalDate.now());
        delivery.setDeliveryTime(LocalTime.MIDNIGHT);
        delivery = deliveryService.save(delivery);

        Flower flower = new Flower();
        flower.setName("Rosa");
        flower.setColor("Red");
        flower.setPrice(BigDecimal.valueOf(6.5));
        flower.setDelivery(delivery);
        flower = flowerRepository.save(flower);
        assertEquals(initPlantsCount + 1, plantService.count());

        Shrub shrub = new Shrub();
        shrub.setHeight(100);
        shrub.setWidth(500);
        shrub.setName("Sebes");
        shrub.setPrice(BigDecimal.valueOf(500));
        shrub.setDelivery(delivery);
        shrubRepository.save(shrub);
        assertEquals(initPlantsCount + 2, plantService.count());
    }

    @Test
    public void getPlantLazyLoadDelivery() {
        Long initCount = plantService.count();
        Plant plant = new Plant();
        Delivery delivery = deliveryService.getDeliveries().get(0);
        assertThrows(LazyInitializationException.class, () -> {
            System.out.println(delivery);
        });
        List<Plant> plantList = delivery.getPlants();
        plant.setDelivery(delivery);
        plant.setPrice(BigDecimal.valueOf(55.55));
        plant.setName("Erva Daninha");
        Plant savedPlant = plantService.savePlant(plant);
        assertThrows(LazyInitializationException.class, () -> {
            System.out.println(savedPlant);
        });
        Delivery associatedDelivery = savedPlant.getDelivery();
    }

    @Test
    public void getAllPlantsCheaperThenWithSuccess() {
        int initDeliveredPlants = plantService.getAllPlantsCheaperThan(BigDecimal.valueOf(50.0)).size();
        Plant plant = new Plant();
        plant.setName("Cheaper Plant");
        plant.setPrice(BigDecimal.valueOf(35.5));
        plantService.savePlant(plant);
        assertEquals(initDeliveredPlants+1, plantService.getAllPlantsCheaperThan(BigDecimal.valueOf(50.0)).size());
        Plant plant2 = new Plant();
        plant2.setName("Expensive Plant");
        plant2.setPrice(BigDecimal.valueOf(51.0));
        plantService.savePlant(plant2);
        assertEquals(initDeliveredPlants + 1, plantService.getAllPlantsCheaperThan(BigDecimal.valueOf(50.0)).size());
    }

    @Test
    public void getPlantIsDeliveredInfoWithSuccess() {
        Delivery delivery = new Delivery();
        delivery.setName("Completa");
        delivery.setAddress("Rua Luciano Cordeiro, 9, 4 Drt, 1150-211 Lisboa, Portugal");
        delivery.setCompleted(true);
        delivery.setDeliveryDate(LocalDate.now());
        delivery.setDeliveryTime(LocalTime.MIDNIGHT);
        Plant plant = new Plant();
        plant.setName("Cheaper Plant 2");
        plant.setPrice(BigDecimal.valueOf(35.5));
        delivery.setPlants(List.of(plant));
        delivery = deliveryService.save(delivery);
        plant.setDelivery(delivery);
        plant = plantService.savePlant(plant);
        assertTrue(plantService.hasBeenDelivered(plant.getId()));
    }
}
