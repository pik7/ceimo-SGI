package com.ceimo.gestion.entity.membre;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ceimo.gestion.entity.seance.Exercice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class Inscription {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idInscription;
	private int montant;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MEMBRE", nullable = false)
	private Membre membre; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_EXERCICE", nullable = false)
	private Exercice exercice;
}
