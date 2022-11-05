package com.rmmcosta.deliveringflowers.data.delivery;

import com.rmmcosta.deliveringflowers.data.inventory.Plant;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(name = "address_full", length = 500)
    private String address;
    private LocalDate deliveryDate;
    private LocalTime deliveryTime;
    @Type(type = "yes_no")
    private boolean isCompleted;

    //fetch lazy by default
    @OneToMany(mappedBy = "delivery")
    List<Plant> plants;
}
