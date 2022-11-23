package com.ceimo.gestion.entity.compte;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.ceimo.gestion.entity.membre.Membre;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class MainLevee {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMainLevee;
	private String motif;
	private double montant;
	private Date dateCreation;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Membre> membres;
}
