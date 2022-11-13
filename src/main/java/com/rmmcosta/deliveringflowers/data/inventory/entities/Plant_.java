package com.rmmcosta.deliveringflowers.data.inventory.entities;

import com.rmmcosta.deliveringflowers.data.delivery.entities.Delivery;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Delivery.class)
public abstract class Plant_ {
    public static volatile SingularAttribute<Plant, Long> id;
    public static volatile SingularAttribute<Plant, String> name;
    public static volatile SingularAttribute<Plant, BigDecimal> price;
    public static volatile SingularAttribute<Plant, Delivery> delivery;
}
