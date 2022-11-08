package com.rmmcosta.deliveringflowers.controller;

import com.rmmcosta.deliveringflowers.data.delivery.entities.Delivery;
import com.rmmcosta.deliveringflowers.service.AnotherDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/another-delivery")
public class AnotherDeliveryController {
    @Autowired
    AnotherDeliveryService anotherDeliveryService;

    @PostMapping
    public ResponseEntity<Long> scheduleDelivery(@RequestBody Delivery delivery) {
        return new ResponseEntity<>(anotherDeliveryService.save(delivery), HttpStatus.OK);
    }
}
