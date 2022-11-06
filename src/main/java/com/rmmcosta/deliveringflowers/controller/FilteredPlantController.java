package com.rmmcosta.deliveringflowers.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rmmcosta.deliveringflowers.service.PlantService;
import com.rmmcosta.deliveringflowers.view.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filtered_plant")
public class FilteredPlantController {
    @Autowired
    PlantService plantService;

    @GetMapping("{id}")
    public ResponseEntity<String> getPlant(@PathVariable Long id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String result = mapper
                .writerWithView(Views.Public.class)
                .writeValueAsString(plantService.getPlant(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
