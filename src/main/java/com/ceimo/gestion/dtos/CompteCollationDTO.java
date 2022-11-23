package com.ceimo.gestion.dtos;

import java.util.Date;

import com.ceimo.gestion.entity.compte.enums.StatutCompte;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CompteCollationDTO extends CompteDTO{

	private Long idCompte;
	private double solde;
	private StatutCompte statutCompte;
	private Date dateCreation;
	private double montantCollation;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private MembreSimplifieDTO membre;
}
