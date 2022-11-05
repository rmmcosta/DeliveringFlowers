package com.rmmcosta.deliveringflowers.data.delivery;

import com.rmmcosta.deliveringflowers.data.delivery.entities.Delivery;
import org.springframework.data.repository.CrudRepository;

public interface DeliveryRepository extends CrudRepository<Delivery, Long> {
}
