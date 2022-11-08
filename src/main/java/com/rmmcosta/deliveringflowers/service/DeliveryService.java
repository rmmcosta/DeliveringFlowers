package com.rmmcosta.deliveringflowers.service;

import com.rmmcosta.deliveringflowers.data.delivery.DeliveryRepository;
import com.rmmcosta.deliveringflowers.data.delivery.entities.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {
    @Autowired
    private DeliveryRepository deliveryRepository;

    public List<Delivery> getDeliveries() {
        return (List<Delivery>) deliveryRepository.findAll();
    }

    public void deleteDelivery(Long id) {
        deliveryRepository.deleteById(id);
    }
}
