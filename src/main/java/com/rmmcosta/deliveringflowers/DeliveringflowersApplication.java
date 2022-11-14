package com.rmmcosta.deliveringflowers;

import com.rmmcosta.deliveringflowers.data.buyer.*;
import com.rmmcosta.deliveringflowers.data.buyer.entities.*;
import com.rmmcosta.deliveringflowers.data.delivery.entities.Delivery;
import com.rmmcosta.deliveringflowers.data.delivery.DeliveryRepository;
import com.rmmcosta.deliveringflowers.data.inventory.entities.Flower;
import com.rmmcosta.deliveringflowers.data.inventory.FlowerRepository;
import com.rmmcosta.deliveringflowers.data.inventory.entities.Shrub;
import com.rmmcosta.deliveringflowers.data.inventory.ShrubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
public class DeliveringflowersApplication {
	@Autowired
	DeliveryRepository deliveryRepository;
	@Autowired
	FlowerRepository flowerRepository;
	@Autowired
	ShrubRepository shrubRepository;

	@Autowired
	BuyerRepository buyerRepository;

	@Autowired
	OutfitRepository outfitRepository;

	@Autowired
	SharedOutfitRepository sharedOutfitRepository;

	@Autowired
	OneEyeHumanoidRepository oneEyeHumanoidRepository;

	@Autowired
	TwoEyesHumanoidRepository twoEyesHumanoidRepository;

	public static void main(String[] args) {
		SpringApplication.run(DeliveringflowersApplication.class, args);
	}

	//@Bean //disabled to use MySql database
	public void insertData() {
		Delivery delivery = new Delivery();
		delivery.setName("Ricardo Costa");
		delivery.setAddress("Rua Luciano Cordeiro, 9, 4 Drt, 1150-211 Lisboa, Portugal");
		delivery.setCompleted(true);
		delivery.setDeliveryDate(LocalDate.now());
		delivery.setDeliveryTime(LocalTime.MIDNIGHT);
		delivery = deliveryRepository.save(delivery);
		Flower flower = new Flower();
		flower.setName("Rosa");
		flower.setColor("Red");
		flower.setPrice(BigDecimal.valueOf(6.5));
		flower.setDelivery(delivery);
		flower = flowerRepository.save(flower);
		Shrub shrub = new Shrub();
		shrub.setHeight(100);
		shrub.setWidth(500);
		shrub.setName("Sebes");
		shrub.setPrice(BigDecimal.valueOf(500));
		shrub.setDelivery(delivery);
		shrubRepository.save(shrub);
		BuyerPK buyerPK = new BuyerPK();
		buyerPK.setName("Ricardo");
		buyerPK.setSurname("Costa");
		Buyer buyer = new Buyer();
		buyer.setId(buyerPK);
		buyer.setAddress("Rua Luciano Cordeiro, 9, 4 Drt, 1150-211 Lisboa");
		buyer.setBirthdate(LocalDate.of(1985, 10, 10));
		buyer = buyerRepository.save(buyer);
		Outfit outfit = new Outfit();
		outfit.setGloves("x");
		outfit.setHat("y");
		outfit.setTop("a");
		outfit.setLegs("b");
		outfit.setShoes("nike");
		outfit.setBuyer(buyer);
		outfitRepository.save(outfit);

		OneEyeHumanoid oneEyeHumanoid = new OneEyeHumanoid();
		oneEyeHumanoid.setOneEyeColor("Blue");
		oneEyeHumanoidRepository.save(oneEyeHumanoid);
		TwoEyesHumanoid twoEyesHumanoid = new TwoEyesHumanoid();
		twoEyesHumanoid.setTwoEyesColor("Red");
		twoEyesHumanoidRepository.save(twoEyesHumanoid);

		SharedOutfit sharedOutfit = new SharedOutfit();
		sharedOutfit.setGloves("x");
		sharedOutfit.setHat("y");
		sharedOutfit.setTop("a");
		sharedOutfit.setLegs("b");
		sharedOutfit.setShoes("nike");
		sharedOutfit.setHumanoid(oneEyeHumanoid);
		sharedOutfitRepository.save(sharedOutfit);

		SharedOutfit sharedOutfit2 = new SharedOutfit();
		sharedOutfit2.setGloves("x");
		sharedOutfit2.setHat("y");
		sharedOutfit2.setTop("a");
		sharedOutfit2.setLegs("b");
		sharedOutfit2.setShoes("adidas");
		sharedOutfit2.setHumanoid(oneEyeHumanoid);
		sharedOutfitRepository.save(sharedOutfit2);

		SharedOutfit sharedOutfit3 = new SharedOutfit();
		sharedOutfit3.setGloves("x");
		sharedOutfit3.setHat("y");
		sharedOutfit3.setTop("a");
		sharedOutfit3.setLegs("b");
		sharedOutfit3.setShoes("New Balance");
		sharedOutfit3.setHumanoid(twoEyesHumanoid);
		sharedOutfitRepository.save(sharedOutfit3);
	}
}
