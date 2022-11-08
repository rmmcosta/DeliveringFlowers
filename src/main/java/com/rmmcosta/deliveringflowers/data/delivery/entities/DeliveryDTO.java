package com.rmmcosta.deliveringflowers.data.delivery.entities;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class DeliveryDTO {
    private String name;
    private String address;
    private LocalDate deliveryDate;
    private LocalTime deliveryTime;
}
