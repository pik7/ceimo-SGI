package com.ceimo.gestion.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class InscriptionRequestDTO {

	private Long idInscription;
	private int montant;
	private Long idMembre;
	private Long idExercice;
}
