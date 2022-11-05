package com.rmmcosta.deliveringflowers.data.buyer.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class SharedOutfit {
    @Id
    @GeneratedValue
    private Long id;
    private String hat;
    private String gloves;
    private String shoes;
    private String legs;
    private String top;
    @ManyToOne
    Humanoid humanoid;
}
