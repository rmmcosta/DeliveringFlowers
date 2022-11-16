package com.rmmcosta.deliveringflowers.data;

import com.rmmcosta.deliveringflowers.CandyDAOImpl;
import com.rmmcosta.deliveringflowers.data.delivery.entities.Delivery;
import com.rmmcosta.deliveringflowers.service.DeliveryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CandyDAOImplTests {
    @Autowired
    CandyDAOImpl candyDAO;

    @Autowired
    DeliveryService deliveryService;

    @Test
    public void crudCandyWithSuccess() {
        List<Candy> candies = candyDAO.getAllCandy();
        int initCandiesNum = candies.size();
        Long nextId = Long.valueOf(initCandiesNum);
        Candy candy = new Candy();
        Optional<Candy> maxId = candies.stream().max(Comparator.comparing(Candy::getId));
        if (maxId.isPresent()) {
            nextId = maxId.get().getId() + 1;
        }
        candy.setId(nextId);
        candy.setName("Gomas");
        candy.setPrice(BigDecimal.valueOf(3.55));
        candyDAO.saveCandy(candy);
        assertEquals(initCandiesNum + 1, candyDAO.getAllCandy().size());
        Delivery delivery = new Delivery();
        delivery.setName("Ricardo Costa");
        delivery.setAddress("Rua Luciano Cordeiro, 9, 4 Drt, 1150-211 Lisboa, Portugal");
        delivery.setCompleted(false);
        delivery.setDeliveryDate(LocalDate.now());
        delivery.setDeliveryTime(LocalTime.MIDNIGHT);
        delivery = deliveryService.save(delivery);
        int candiesInDelivery = candyDAO.getAllCandyByDeliveryId(delivery.getId()).size();
        assertEquals(0, candiesInDelivery);
        candyDAO.addCandyToDelivery(candy.getId(), delivery.getId());
        assertEquals(1, candyDAO.getAllCandyByDeliveryId(delivery.getId()).size());
        Candy candyByDelivery = candyDAO.getAllCandyByDeliveryId(delivery.getId()).get(0);
        assertEquals(candy.getId(), candyByDelivery.getId());
        assertEquals(candy.getName(), candyByDelivery.getName());
        assertEquals(0, candy.getPrice().compareTo(candyByDelivery.getPrice()));
    }
}
