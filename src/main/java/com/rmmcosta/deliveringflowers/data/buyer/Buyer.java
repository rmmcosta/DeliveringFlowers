package com.rmmcosta.deliveringflowers.data.buyer;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Buyer {
    @Embedded
    @Id
    private BuyerPK id;
    @Column(length = 500)
    private String address;
    private LocalDate birthdate;
    @OneToOne(mappedBy = "buyer")
    private Outfit outfit;
}
