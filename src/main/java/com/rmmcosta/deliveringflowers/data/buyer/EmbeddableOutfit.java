package com.rmmcosta.deliveringflowers.data.buyer;

import lombok.Data;

import javax.persistence.*;

@Embeddable
@Data
public class EmbeddableOutfit {
    private String hat;
    private String gloves;
    private String shoes;
    private String legs;
    private String top;
}
