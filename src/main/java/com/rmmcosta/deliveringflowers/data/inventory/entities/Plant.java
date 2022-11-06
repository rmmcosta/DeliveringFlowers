package com.rmmcosta.deliveringflowers.data.inventory.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.rmmcosta.deliveringflowers.data.delivery.entities.Delivery;
import com.rmmcosta.deliveringflowers.view.Views;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Plant {
    @Id
    @GeneratedValue
    @JsonView(Views.Private.class)
    private Long id;
    @JsonView(Views.Public.class)
    private String name;
    @JsonView(Views.Public.class)
    @Column(precision = 12, scale = 4)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    @JsonView(Views.Private.class)
    private Delivery delivery;
}
