package com.rmmcosta.deliveringflowers.data.inventory;

import com.rmmcosta.deliveringflowers.data.inventory.entities.Flower;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FlowerTests {
    @Autowired
    FlowerRepository flowerRepository;

    @Test
    public void insertFlowerWithSuccess() {
        Flower flower = new Flower();
        flower.setName("Rosa");
        flower.setColor("Red");
        flower.setPrice(BigDecimal.valueOf(6.5));
        flower = flowerRepository.save(flower);
        Flower savedFlower = flowerRepository.findById(flower.getId()).get();
        assertEquals(flower.getName(), savedFlower.getName());
        assertEquals(flower.getColor(), savedFlower.getColor());
        assertEquals(flower.getPrice().compareTo(savedFlower.getPrice()), 0);
    }
}
