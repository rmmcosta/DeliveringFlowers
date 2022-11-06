package com.rmmcosta.deliveringflowers.data.inventory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmmcosta.deliveringflowers.data.inventory.entities.Plant;
import com.rmmcosta.deliveringflowers.view.Views;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

public class PlantJsonViewTests {
    @Test
    public void whenUseJsonViewToSerialize_thenCorrect()
            throws JsonProcessingException {

        Plant plant = new Plant();
        plant.setId(5L);
        plant.setName("Xpto");
        plant.setPrice(BigDecimal.valueOf(33.33));

        ObjectMapper mapper = new ObjectMapper();

        String result = mapper
                .writerWithView(Views.Public.class)
                .writeValueAsString(plant);
        System.out.println(result);
        assertThat(result, containsString("Xpto"));
        assertThat(result, containsString("33.33"));
        assertThat(result, not(containsString("5")));
    }
}
