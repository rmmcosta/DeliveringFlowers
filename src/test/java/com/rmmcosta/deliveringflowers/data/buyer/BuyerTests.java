package com.rmmcosta.deliveringflowers.data.buyer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BuyerTests {
    @Autowired
    BuyerRepository buyerRepository;

    @Test
    public void insertBuyerWithSuccess() {
        BuyerPK buyerPK = new BuyerPK();
        buyerPK.setName("Ricardo");
        buyerPK.setSurname("Costa");
        Buyer buyer = new Buyer();
        buyer.setId(buyerPK);
        buyer.setAddress("Rua Luciano Cordeiro, 9, 4 Drt, 1150-211 Lisboa");
        buyer.setBirthdate(LocalDate.of(1985, 10, 10));
        buyer = buyerRepository.save(buyer);
        Buyer savedBuyer = buyerRepository.findById(buyerPK).get();
        assertEquals(buyer.getId(), savedBuyer.getId());
        assertEquals(buyer.getBirthdate(), savedBuyer.getBirthdate());
    }
}
