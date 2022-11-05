package com.rmmcosta.deliveringflowers.data.buyer;

import com.rmmcosta.deliveringflowers.data.buyer.entities.Buyer;
import com.rmmcosta.deliveringflowers.data.buyer.entities.BuyerPK;
import org.springframework.data.repository.CrudRepository;

public interface BuyerRepository extends CrudRepository<Buyer, BuyerPK> {
}
