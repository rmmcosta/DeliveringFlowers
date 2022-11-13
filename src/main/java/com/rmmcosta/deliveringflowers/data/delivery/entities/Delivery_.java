package com.rmmcosta.deliveringflowers.data.delivery.entities;

import com.rmmcosta.deliveringflowers.data.inventory.entities.Plant;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Delivery.class)
public abstract class Delivery_ {
    public static volatile SingularAttribute<Delivery, Long> id;
    public static volatile SingularAttribute<Delivery, String> name;
    public static volatile ListAttribute<Delivery, Plant> plants;
}
