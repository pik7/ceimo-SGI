package com.ceimo.gestion.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class TransfertDTO {

	private Long idCompteEmetteur;
	private Long idCompteDestinataire;
	private double montant;
	private String description;
	private Long idSeance;
}
