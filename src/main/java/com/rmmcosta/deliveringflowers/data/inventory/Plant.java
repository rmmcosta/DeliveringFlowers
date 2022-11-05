package com.rmmcosta.deliveringflowers.data.inventory;

import com.rmmcosta.deliveringflowers.data.delivery.Delivery;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Plant {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(precision = 12, scale = 4)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery_id;
}
