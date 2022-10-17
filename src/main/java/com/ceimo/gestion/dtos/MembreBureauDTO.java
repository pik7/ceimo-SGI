package com.ceimo.gestion.dtos;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MembreBureauDTO {
	private Long idMembre;
	private String nomMembre;
	private String prenomMembre;
	private Date datePriseFonction;
	private List<ResponsabiliteDTO> postes;
}
