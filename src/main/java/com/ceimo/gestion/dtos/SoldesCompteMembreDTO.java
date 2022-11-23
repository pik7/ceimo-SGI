package com.ceimo.gestion.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class SoldesCompteMembreDTO {

	private Long idMembre;
	private String nomMembre;
	private String prenomMembre;
	private List<CompteDTO> compteDTOs;
}
