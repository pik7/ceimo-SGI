package com.ceimo.gestion.dtos;

import lombok.Data;

@Data
public class OperationCommuneDTO {

	private Long idSeance;
	private double montant;
	private String description;
}
