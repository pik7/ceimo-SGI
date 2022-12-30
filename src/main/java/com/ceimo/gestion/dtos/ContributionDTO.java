package com.ceimo.gestion.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ContributionDTO {

	private Long idContribution;
	private double montantContribution;
	private String mois;
	private Long idMembre;
	private Long idTontine;
	private Long idSeance;
}
