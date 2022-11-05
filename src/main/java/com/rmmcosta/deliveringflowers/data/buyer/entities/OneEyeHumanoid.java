package com.rmmcosta.deliveringflowers.data.buyer.entities;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class OneEyeHumanoid extends Humanoid {
    private String oneEyeColor;
}
