package com.ceimo.gestion.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ContributionFullDTO {

	private Long idContribution;
	private double montantContribution;
	private String mois;
	private MembreSimplifieDTO membre;
	private TontineDTO tontine;
	private Long idSeance;
}
