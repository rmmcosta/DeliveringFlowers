package com.rmmcosta.deliveringflowers.data;

import java.util.List;

public interface CandyDAO {
    List<Candy> getAllCandy();

    void addCandyToDelivery(Long candyId, Long deliveryId);

    List<Candy> getAllCandyByDeliveryId(Long deliveryId);

    void saveCandy(Candy candy);
}
