package com.rmmcosta.deliveringflowers.controller;

import com.rmmcosta.deliveringflowers.data.delivery.DeliveryRepository;
import com.rmmcosta.deliveringflowers.data.delivery.entities.Delivery;
import com.rmmcosta.deliveringflowers.data.inventory.FlowerRepository;
import com.rmmcosta.deliveringflowers.data.inventory.ShrubRepository;
import com.rmmcosta.deliveringflowers.data.inventory.entities.Flower;
import com.rmmcosta.deliveringflowers.data.inventory.entities.Plant;
import com.rmmcosta.deliveringflowers.data.inventory.entities.Shrub;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlantControllerTests {
    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    FlowerRepository flowerRepository;
    @Autowired
    ShrubRepository shrubRepository;

    @Test
    public void getPlantsWithSuccess() {
        ResponseEntity<PlantDTO[]> responseEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/plant", PlantDTO[].class);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        PlantDTO[] plants = responseEntity.getBody();

        int initPlantsCount = plants.length;

        Delivery delivery = new Delivery();
        delivery.setName("Ricardo Costa");
        delivery.setAddress("Rua Luciano Cordeiro, 9, 4 Drt, 1150-211 Lisboa, Portugal");
        delivery.setCompleted(true);
        delivery.setDeliveryDate(LocalDate.now());
        delivery.setDeliveryTime(LocalTime.MIDNIGHT);
        delivery = deliveryRepository.save(delivery);

        Flower flower = new Flower();
        flower.setName("Rosa");
        flower.setColor("Red");
        flower.setPrice(BigDecimal.valueOf(6.5));
        flower.setDelivery(delivery);
        flower = flowerRepository.save(flower);

        Shrub shrub = new Shrub();
        shrub.setHeight(100);
        shrub.setWidth(500);
        shrub.setName("Sebes");
        shrub.setPrice(BigDecimal.valueOf(500));
        shrub.setDelivery(delivery);
        shrubRepository.save(shrub);

        responseEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/plant", PlantDTO[].class);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        plants = responseEntity.getBody();
        assertEquals(initPlantsCount+2, plants.length);
    }

    @Test
    public void getAPlantWithSuccess() {
        Delivery delivery = new Delivery();
        delivery.setName("Ricardo Costa");
        delivery.setAddress("Rua Luciano Cordeiro, 9, 4 Drt, 1150-211 Lisboa, Portugal");
        delivery.setCompleted(true);
        delivery.setDeliveryDate(LocalDate.now());
        delivery.setDeliveryTime(LocalTime.MIDNIGHT);
        delivery = deliveryRepository.save(delivery);

        Flower flower = new Flower();
        flower.setName("Rosa");
        flower.setColor("Red");
        flower.setPrice(BigDecimal.valueOf(6.5));
        flower.setDelivery(delivery);
        flower = flowerRepository.save(flower);

        ResponseEntity<PlantDTO> responseEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/plant/"+flower.getId(), PlantDTO.class);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        PlantDTO plant = responseEntity.getBody();
        assertNotNull(plant);
    }
}
