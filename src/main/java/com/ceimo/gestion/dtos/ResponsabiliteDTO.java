package com.ceimo.gestion.dtos;


import com.ceimo.gestion.entity.membre.Poste;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponsabiliteDTO {

	private Long idresponsabilite;
	private Poste poste;
	private int dureemandat; 
}
