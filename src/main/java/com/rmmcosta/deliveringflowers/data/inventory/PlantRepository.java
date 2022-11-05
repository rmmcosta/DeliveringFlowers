package com.rmmcosta.deliveringflowers.data.inventory;

import com.rmmcosta.deliveringflowers.data.inventory.entities.Plant;
import org.springframework.data.repository.CrudRepository;

public interface PlantRepository extends CrudRepository<Plant, Long> {
}
