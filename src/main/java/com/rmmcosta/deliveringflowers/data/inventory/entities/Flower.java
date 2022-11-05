package com.rmmcosta.deliveringflowers.data.inventory.entities;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Flower extends Plant {
    private String color;
}
