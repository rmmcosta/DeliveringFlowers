package com.rmmcosta.deliveringflowers.controller;

import com.rmmcosta.deliveringflowers.data.delivery.entities.Delivery;
import com.rmmcosta.deliveringflowers.data.inventory.entities.Plant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnotherDeliveryControllerIntegrationTests {
    @LocalServerPort
    int port;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void scheduleDeliveryWithSuccess() {
        Delivery delivery = new Delivery();
        delivery.setPlants(List.of(new Plant()));
        ResponseEntity<Long> response = testRestTemplate.postForEntity("http://localhost:" + port + "/another-delivery", delivery, Long.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
}
