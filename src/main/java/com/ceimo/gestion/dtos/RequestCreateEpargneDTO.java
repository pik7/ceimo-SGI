package com.ceimo.gestion.dtos;

import com.ceimo.gestion.entity.compte.enums.StatutCompte;

import lombok.Data;

@Data
public class RequestCreateEpargneDTO {

	private Long idMembre;
	private double solde;
	private double tauxInteret;
	private StatutCompte statutCompte;
}
