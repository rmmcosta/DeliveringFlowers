package com.rmmcosta.deliveringflowers.data.inventory;

import com.rmmcosta.deliveringflowers.data.inventory.entities.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Long> {
    public boolean existsPlantByIdAndDeliveryIsCompletedIsTrue(Long plantId);

    public List<Plant> findAllByPriceLessThan(BigDecimal maxPrice);
}
