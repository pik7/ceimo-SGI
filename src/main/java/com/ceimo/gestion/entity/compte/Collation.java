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
@DiscriminatorValue("COLL")
public class Collation extends Compte{
	private double montantCollation;
}
