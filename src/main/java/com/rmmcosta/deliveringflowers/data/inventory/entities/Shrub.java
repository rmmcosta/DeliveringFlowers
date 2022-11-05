package com.rmmcosta.deliveringflowers.data.inventory.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "shrubbery")
@Data
public class Shrub extends Plant {
    private int height;
    private int width;
}
