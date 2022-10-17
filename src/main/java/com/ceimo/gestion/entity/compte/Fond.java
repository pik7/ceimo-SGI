package com.ceimo.gestion.entity.compte;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@DiscriminatorValue("FOND")
public class Fond extends Compte{
	private double soldeMax;
}
