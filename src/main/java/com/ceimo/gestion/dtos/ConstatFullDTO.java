package com.ceimo.gestion.dtos;

import com.ceimo.gestion.entity.discipline.Sanction;
import com.ceimo.gestion.entity.membre.Membre;
import com.ceimo.gestion.entity.seance.Seance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConstatFullDTO {

	private Long idConstat;
	private boolean paye;
	private SeanceSimpleDTO seance;
	private MembreSimplifieDTO membre;
	private SanctionDTO sanction;
}
