package com.rmmcosta.deliveringflowers.data.buyer;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Outfit {
    @Id
    @GeneratedValue
    private Long id;
    private String hat;
    private String gloves;
    private String shoes;
    private String legs;
    private String top;
    @OneToOne
    Buyer buyer;
}
