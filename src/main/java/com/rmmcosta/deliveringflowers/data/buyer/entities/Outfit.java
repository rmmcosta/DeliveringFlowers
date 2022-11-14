package com.rmmcosta.deliveringflowers.data.buyer.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Outfit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hat;
    private String gloves;
    private String shoes;
    private String legs;
    private String top;
    @OneToOne
    Buyer buyer;
}
