package com.rmmcosta.deliveringflowers.service;

import com.rmmcosta.deliveringflowers.data.delivery.entities.Delivery;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AnotherDeliveryServiceTests {
    @Autowired
    AnotherDeliveryService deliveryService;

    @Test
    public void saveDeliveryWithSuccess() {
        Delivery delivery = new Delivery();
        delivery.setName("Ricardo Costa");
        delivery.setAddress("Rua Luciano Cordeiro, 9, 4 Drt, 1150-211 Lisboa, Portugal");
        delivery.setCompleted(true);
        delivery.setDeliveryDate(LocalDate.now());
        delivery.setDeliveryTime(LocalTime.MIDNIGHT);
        delivery.setPlants(new ArrayList<>());
        Long id = deliveryService.save(delivery);
        assertNotNull(id);
    }

    @Test
    public void associateDeliveryToPlantWithSuccess() {
        Delivery delivery = new Delivery();
        delivery.setName("Ricardo Costa");
        delivery.setAddress("Rua Luciano Cordeiro, 9, 4 Drt, 1150-211 Lisboa, Portugal");
        delivery.setCompleted(true);
        delivery.setDeliveryDate(LocalDate.now());
        delivery.setDeliveryTime(LocalTime.MIDNIGHT);
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
}
