package com.ceimo.gestion.dtos;

import java.util.Date;

import org.hibernate.annotations.DynamicUpdate;

import com.ceimo.gestion.entity.discipline.Constat;
import com.ceimo.gestion.entity.membre.Membre;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor @DynamicUpdate
public class VersementSanctionDTO {
	
	private String idVersement;
	private Date dateVersement;
	private int montantVerse;
	private Long idMembre;
	private Long idConstat;
}
