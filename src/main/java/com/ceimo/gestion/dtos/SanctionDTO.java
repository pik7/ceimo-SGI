package com.ceimo.gestion.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanctionDTO {

	private Long idSanction;
	private String libelleSanction;
	private int amende;
}
