package com.rmmcosta.deliveringflowers.data.inventory;

import com.rmmcosta.deliveringflowers.data.inventory.entities.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Long> {
    @Query("Select d.isCompleted from Plant p join p.delivery d where p.id = :plantId")
    public boolean findIsCompletedTrueById(Long plantId);

    public List<Plant> findAllByPriceLessThan(BigDecimal maxPrice);
}
