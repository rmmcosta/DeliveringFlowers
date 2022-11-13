package com.rmmcosta.deliveringflowers.data.delivery.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipientAndPrice {
    private String name;
    private BigDecimal price;
}
