package com.ceimo.gestion.dtos;

import java.util.Date;

import com.ceimo.gestion.entity.seance.StatutSeance;
import com.ceimo.gestion.entity.seance.TypeSeance;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class SeanceSimpleDTO {
		
	private Long idSeance;
	private Date dateSeance;
	private String ordreDuJour;
	private String compteRendu;
	private TypeSeance typeSeance;
	private StatutSeance statutSeance;
	private Long president;
	private Long exercice;

}
