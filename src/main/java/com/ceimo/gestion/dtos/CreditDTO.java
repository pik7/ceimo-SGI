package com.ceimo.gestion.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreditDTO {
	private Long idCompte;
	private double montant;
	private String description;
	private Long idSeance;
}
