package com.ceimo.gestion.dtos;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GainTontineDTO{

	private Long idMembre;
	private String nom;
	private double gain;
	private List<ContributionFullDTO> contributions;	
}
