package com.ceimo.gestion.entity.compte;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue("EPAR")
public class Epargne extends Compte{
	
	private double tauxInteret;

}
