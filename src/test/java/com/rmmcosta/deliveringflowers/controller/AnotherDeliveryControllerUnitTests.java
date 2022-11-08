package com.rmmcosta.deliveringflowers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmmcosta.deliveringflowers.data.buyer.*;
import com.rmmcosta.deliveringflowers.data.delivery.DeliveryRepository;
import com.rmmcosta.deliveringflowers.data.delivery.entities.Delivery;
import com.rmmcosta.deliveringflowers.data.inventory.FlowerRepository;
import com.rmmcosta.deliveringflowers.data.inventory.ShrubRepository;
import com.rmmcosta.deliveringflowers.service.AnotherDeliveryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnotherDeliveryController.class)
public class AnotherDeliveryControllerUnitTests {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    AnotherDeliveryService anotherDeliveryService;

    @MockBean
    DeliveryRepository deliveryRepository;

    @MockBean
    FlowerRepository flowerRepository;

    @MockBean
    ShrubRepository shrubRepository;

    @MockBean
    BuyerRepository buyerRepository;

    @MockBean
    OutfitRepository outfitRepository;

    @MockBean
    SharedOutfitRepository sharedOutfitRepository;

    @MockBean
    OneEyeHumanoidRepository oneEyeHumanoidRepository;

    @MockBean
    TwoEyesHumanoidRepository twoEyesHumanoidRepository;

    @Test
    public void scheduleDeliveryWithSuccess() throws Exception {
        String deliveryJson = mapper.writeValueAsString(new Delivery());
        mockMvc.perform(post("/another-delivery")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deliveryJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(anotherDeliveryService, times(1)).save(new Delivery());
    }
}
