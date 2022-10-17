package com.ceimo.gestion.dtos;

import java.util.Date;

import com.ceimo.gestion.entity.seance.Exercice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ElireDTO {

	private Long id;
	
	private Date dateAdhesion;
	
	private int montantInscription;
	
	private MembreSimplifieDTO membre;
	
	private ResponsabiliteDTO responsabilite;
	
	private ExerciceDTO exercice;
	
}
