package com.rmmcosta.deliveringflowers.data.inventory.entities;

import com.rmmcosta.deliveringflowers.data.delivery.entities.Delivery;
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
    private Delivery delivery;
}
