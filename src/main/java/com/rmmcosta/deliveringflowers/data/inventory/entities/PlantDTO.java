package com.rmmcosta.deliveringflowers.data.inventory.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlantDTO {
    private String name;
    private BigDecimal price;
}
