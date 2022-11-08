package com.rmmcosta.deliveringflowers.service;

import com.rmmcosta.deliveringflowers.data.delivery.entities.Delivery;
import com.rmmcosta.deliveringflowers.data.delivery.entities.DeliveryRepositoryWithEntityManager;
import com.rmmcosta.deliveringflowers.data.inventory.entities.Plant;
import com.rmmcosta.deliveringflowers.data.inventory.entities.PlantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnotherDeliveryService {
    @Autowired
    DeliveryRepositoryWithEntityManager deliveryRepository;

    @Autowired
    PlantService plantService;

    public Long save(Delivery delivery) {
        if (delivery.getPlants() != null)
            delivery.getPlants().forEach(plant -> plant.setDelivery(delivery));
        deliveryRepository.persist(delivery);
        if (delivery.getPlants() != null)
            delivery.getPlants().forEach(plant -> plantService.savePlant(plant));
        return delivery.getId();
    }

    public Delivery get(Long id) {
        return deliveryRepository.find(id);
    }

    public List<PlantDTO> getPlants(Long id) {
        return deliveryRepository.findPlants(id);
    }
}
