package com.rmmcosta.deliveringflowers.data.buyer;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class PersonPK implements Serializable {
    private int heightCm;
    private String sockColor;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(this.getClass())) {
            return false;
        }
        PersonPK otherBuyerPK = (PersonPK) obj;
        return this.heightCm == otherBuyerPK.heightCm && this.sockColor.equals(otherBuyerPK.sockColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(heightCm, sockColor);
    }
}