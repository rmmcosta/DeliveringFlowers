package com.rmmcosta.deliveringflowers.data.buyer;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
public abstract class Humanoid {
    @Id
    @GeneratedValue
    private Long Id;

    @OneToMany(mappedBy = "humanoid")
    Set<SharedOutfit> outfits;
}
