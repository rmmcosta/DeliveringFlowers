package com.rmmcosta.deliveringflowers.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmmcosta.deliveringflowers.data.inventory.entities.Plant;
import com.rmmcosta.deliveringflowers.service.PlantService;
import com.rmmcosta.deliveringflowers.view.Views;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/plant")
public class PlantController {
    @Autowired
    PlantService plantService;

    @GetMapping
    public ResponseEntity<List<PlantDTO>> getPlants() {
        return new ResponseEntity<>(plantService.getPlants().stream().map(this::plantToPlantDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PlantDTO> getPlant(@PathVariable Long id) {
        return new ResponseEntity<>(plantToPlantDTO(plantService.getPlant(id)), HttpStatus.OK);
    }

    private PlantDTO plantToPlantDTO(Plant plant) {
        var plantDTO = new PlantDTO();
        BeanUtils.copyProperties(plant, plantDTO);
        return plantDTO;
    }
}
