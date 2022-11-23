package com.ceimo.gestion.dtos;

import com.ceimo.gestion.entity.compte.enums.StatutCompte;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RequestCreateFondDTO {

	private Long idMembre;
	private double solde;
	private double soldeMax;
	private StatutCompte statutCompte;
}
