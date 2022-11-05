package com.rmmcosta.deliveringflowers.data.delivery;

import com.rmmcosta.deliveringflowers.data.delivery.entities.Delivery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeliveryTests {
    @Autowired
    DeliveryRepository deliveryRepository;

    @Test
    public void insertDeliveryWithSuccess() {
        Delivery delivery = new Delivery();
        delivery.setName("Ricardo Costa");
        delivery.setAddress("Rua Luciano Cordeiro, 9, 4 Drt, 1150-211 Lisboa, Portugal");
        delivery.setCompleted(true);
        delivery.setDeliveryDate(LocalDate.now());
        delivery.setDeliveryTime(LocalTime.MIDNIGHT);
        delivery = deliveryRepository.save(delivery);
        Delivery savedDelivery = deliveryRepository.findById(delivery.getId()).get();
        assertEquals(delivery.getDeliveryDate(), savedDelivery.getDeliveryDate());
        assertEquals(delivery.getDeliveryTime(), savedDelivery.getDeliveryTime());
        assertEquals(delivery.getAddress(), savedDelivery.getAddress());
        assertEquals(delivery.getName(), savedDelivery.getName());
        assertEquals(delivery.isCompleted(), savedDelivery.isCompleted());
    }
}
