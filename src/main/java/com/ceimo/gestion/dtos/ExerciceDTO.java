package com.ceimo.gestion.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ExerciceDTO {

	private Long idexercice;
	private Date datedebutexercice;
	private Date datefinexercice;
	
	
}
