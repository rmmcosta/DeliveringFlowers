package com.rmmcosta.deliveringflowers.data.buyer.entities;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.List;

@Entity
@IdClass(PersonPK.class)
@Data
public class Person {
    @Id
    private int heightCm;
    @Id
    private String sockColor;

    @ElementCollection
    private List<EmbeddableOutfit> embeddableOutfitList;

    public PersonPK getId() {
        PersonPK id = new PersonPK();
        id.setHeightCm(heightCm);
        id.setSockColor(sockColor);
        return id;
    }

    public void setId(PersonPK id) {
        this.heightCm = id.getHeightCm();
        this.sockColor = id.getSockColor();
    }
}
