package com.ceimo.gestion.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DemissionDTOLight {

	private Long iddemission;
	private Date datedemission;
	private String lettredemission;
	private Long 	idMembre;
}
