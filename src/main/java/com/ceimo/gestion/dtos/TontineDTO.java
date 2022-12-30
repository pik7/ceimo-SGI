package com.ceimo.gestion.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class TontineDTO {

	private Long idTontine;
	private String libelleTontine;
	private Date dateDebut;
	private Date dateFin;
	private boolean etat;
	private int montantTontine;
}
