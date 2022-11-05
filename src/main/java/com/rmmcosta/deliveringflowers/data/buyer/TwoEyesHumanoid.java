package com.rmmcosta.deliveringflowers.data.buyer;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class TwoEyesHumanoid extends Humanoid {
    private String twoEyesColor;
}
