package com.ceimo.gestion.dtos;

import java.util.Date;

import com.ceimo.gestion.entity.membre.Poste;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class ElireDTOLight {

	private Long idMembre;
	private Long idExercice;
	private String poste;
	private Date dateNomination;
	private int montantIbscription;
}
