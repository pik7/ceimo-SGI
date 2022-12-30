package com.ceimo.gestion.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class InscrireDTO {
	private Long idInscription;
	private double montant;
	private String mois;
	private boolean bouffer;
	private MembreSimplifieDTO membre;
	private Long idTontine;
}
