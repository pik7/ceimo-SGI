package com.ceimo.gestion.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class InscriptionDTO {

	private Long idInscription;
	private int montant;
	private MembreSimplifieDTO membre;
	private ExerciceDTO exercice;
}
