package com.ceimo.gestion.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestInscrireATontineDTO {

	private Long idMembre;
	private Long idTontine; 
	private double montant; 
	private int ordre;
}
