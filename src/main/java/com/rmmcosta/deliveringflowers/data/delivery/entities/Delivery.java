package com.rmmcosta.deliveringflowers.data.delivery.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rmmcosta.deliveringflowers.data.inventory.entities.Plant;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "address_full", length = 500)
    private String address;
    private LocalDate deliveryDate;
    private LocalTime deliveryTime;
    @Type(type = "yes_no")
    private boolean isCompleted;

    //fetch lazy by default
    @OneToMany(mappedBy = "delivery", cascade = CascadeType.REMOVE)
    @JsonIgnore
    List<Plant> plants;
}
