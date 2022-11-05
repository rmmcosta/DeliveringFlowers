package com.rmmcosta.deliveringflowers.data.inventory;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Flower extends Plant {
    private String color;
}
