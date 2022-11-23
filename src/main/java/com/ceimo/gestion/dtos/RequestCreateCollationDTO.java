package com.ceimo.gestion.dtos;

import com.ceimo.gestion.entity.compte.enums.StatutCompte;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RequestCreateCollationDTO {

	private Long idMembre;
	private double solde;
	private double montantCollation;
	private StatutCompte statutCompte;
}
