package com.rmmcosta.deliveringflowers.service;

import com.rmmcosta.deliveringflowers.data.delivery.entities.Delivery;
import com.rmmcosta.deliveringflowers.data.delivery.entities.RecipientAndPrice;
import com.rmmcosta.deliveringflowers.data.inventory.entities.Plant;
import com.rmmcosta.deliveringflowers.data.inventory.entities.PlantDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AnotherDeliveryServiceTests {
    @Autowired
    AnotherDeliveryService deliveryService;

    @Test
    public void saveDeliveryWithSuccess() {
        Delivery delivery = getDeliverySample("");
        delivery.setPlants(new ArrayList<>());
        Long id = deliveryService.save(delivery);
        assertNotNull(id);
    }

    @Test
    public void associateDeliveryToPlantWithSuccess() {
        Delivery delivery = getDeliverySample("");
        Plant plant = new Plant();
        plant.setPrice(BigDecimal.valueOf(55.55));
        plant.setName("Erva Daninha");
        delivery.setPlants(List.of(plant));
        Long id = deliveryService.save(delivery);
        assertNotNull(id);
        List<PlantDTO> savedPlants = deliveryService.getPlants(id);
        assertTrue(!savedPlants.isEmpty());
        for (PlantDTO p : savedPlants) {
            System.out.println(p);
        }
        PlantDTO savedPlant = savedPlants.get(0);
        assertNotNull(savedPlant);
    }

    @Test
    public void twoDeliveriesWithoutPlantsWithSameName() {
        String deliveryName = "xpto";
        assertTrue(deliveryService.getAllDeliveriesByName(deliveryName).isEmpty());
        deliveryService.save(getDeliverySample(deliveryName));
        deliveryService.save(getDeliverySample(deliveryName));
        assertEquals(2, deliveryService.getAllDeliveriesByName(deliveryName).size());
        deliveryService.deleteAllDeliveriesByName(deliveryName);
        assertTrue(deliveryService.getAllDeliveriesByName(deliveryName).isEmpty());
    }

    @Test
    public void twoDeliveriesWithPlantsWithSameName() {
        String deliveryName = "abc";
        assertTrue(deliveryService.getAllDeliveriesAndPlantsByDeliveryName(deliveryName).isEmpty());
        Delivery delivery1 = getDeliverySample(deliveryName);
        Plant plant1 = getPlantSample("", (double) 0);
        delivery1.setPlants(List.of(plant1));
        deliveryService.save(delivery1);
        Delivery delivery2 = getDeliverySample(deliveryName);
        Plant plant2 = getPlantSample("Unknown", 33.44);
        delivery2.setPlants(List.of(plant2));
        deliveryService.save(delivery2);
        List<Plant> plants = deliveryService.getAllDeliveriesAndPlantsByDeliveryName(deliveryName);
        assertEquals(2, plants.size());
        deliveryService.deleteAllDeliveriesByName(deliveryName);
        assertTrue(deliveryService.getAllDeliveriesByName(deliveryName).isEmpty());
    }

    @Test
    public void sumPrice2PlantsReturnedWithSuccess() {
        String deliveryName = "xyz";
        assertTrue(deliveryService.getAllDeliveriesByName(deliveryName).isEmpty());
        Delivery delivery1 = getDeliverySample(deliveryName);
        Plant plant1 = getPlantSample("", (double) 0);
        Plant plant2 = getPlantSample("Tulipa", 30.0);
        delivery1.setPlants(List.of(plant1, plant2));
        Long deliveryId = deliveryService.save(delivery1);
        RecipientAndPrice recipientAndPrice = deliveryService.getRecipientAndPricesSum(deliveryId);
        assertTrue(plant1.getPrice().add(plant2.getPrice()).compareTo(recipientAndPrice.getPrice())==0);
        assertEquals(deliveryName, recipientAndPrice.getName());
    }

    private static Delivery getDeliverySample(String name) {
        Delivery delivery = new Delivery();
        delivery.setName(name.isEmpty() ? "Ricardo Costa" : name);
        delivery.setAddress("Rua Luciano Cordeiro, 9, 4 Drt, 1150-211 Lisboa, Portugal");
        delivery.setCompleted(true);
        delivery.setDeliveryDate(LocalDate.now());
        delivery.setDeliveryTime(LocalTime.MIDNIGHT);
        return delivery;
    }

    private static Plant getPlantSample(String name, Double value) {
        Plant plant = new Plant();
        plant.setPrice(value > 0 ? BigDecimal.valueOf(value) : BigDecimal.valueOf(55.55));
        plant.setName(name.isEmpty() ? "Erva Daninha" : name);
        return plant;
    }
}
