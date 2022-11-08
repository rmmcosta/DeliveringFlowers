package com.rmmcosta.deliveringflowers.controller;

import com.rmmcosta.deliveringflowers.data.delivery.DeliveryManager;
import com.rmmcosta.deliveringflowers.data.delivery.entities.Delivery;
import com.rmmcosta.deliveringflowers.data.delivery.entities.DeliveryDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery_manager")
public class DeliveryManagerController {
    @Autowired
    DeliveryManager deliveryManager;

    @GetMapping("{id}")
    public ResponseEntity<Delivery> getDelivery(@PathVariable Long id) {
        return new ResponseEntity<>(deliveryManager.find(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveDelivery(@RequestBody DeliveryDTO delivery) {
        deliveryManager.persist(deliveryDTO2Delivery(delivery));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Delivery> updateDelivery(@RequestBody Delivery delivery) {
        return new ResponseEntity<>(deliveryManager.merge(delivery), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteDelivery(@PathVariable Long id) {
        deliveryManager.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Delivery deliveryDTO2Delivery(DeliveryDTO deliveryDTO) {
        Delivery delivery = new Delivery();
        BeanUtils.copyProperties(deliveryDTO, delivery);
        return delivery;
    }
}
