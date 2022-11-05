package com.rmmcosta.deliveringflowers.data.buyer.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
public class BuyerPK implements Serializable {
    private String name;
    private String surname;

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(this.getClass())) {
            return false;
        }
        BuyerPK otherBuyerPK = (BuyerPK) obj;
        return this.name.equals(otherBuyerPK.name) && this.surname.equals(otherBuyerPK.surname);
    }
}
