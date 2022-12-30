package com.ceimo.gestion.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestReclassementDTO {

	private Long idTontine; 
	private Long idMembre;
}
