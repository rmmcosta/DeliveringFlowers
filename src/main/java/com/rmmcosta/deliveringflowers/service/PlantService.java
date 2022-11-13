package com.rmmcosta.deliveringflowers.service;

import com.rmmcosta.deliveringflowers.data.inventory.PlantRepository;
import com.rmmcosta.deliveringflowers.data.inventory.entities.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlantService {
    @Autowired
    PlantRepository plantRepository;

    public List<Plant> getPlants() {
        return (List<Plant>) plantRepository.findAll();
    }

    public Plant getPlant(Long id) {
        return plantRepository.findById(id).orElseThrow(PlantNotFoundException::new);
    }

    public Plant savePlant(Plant plant) {
        return plantRepository.save(plant);
    }

    public void deletePlant(Long id) {
        plantRepository.deleteById(id);
    }

    public Long count() {
        return plantRepository.count();
    }

    public boolean hasBeenDelivered(Long plantId) {
        var isCompleted = plantRepository.findIsCompletedTrueById(plantId);
        return isCompleted;
    }

    public List<Plant> getAllPlantsCheaperThan(BigDecimal maxPrice) {
        return plantRepository.findAllByPriceLessThan(maxPrice);
    }
}
