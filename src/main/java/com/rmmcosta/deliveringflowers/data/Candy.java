package com.rmmcosta.deliveringflowers.data;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Candy {
    private Long id;
    private String name;
    private BigDecimal price;
}
