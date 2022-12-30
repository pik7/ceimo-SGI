package com.ceimo.gestion.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.ceimo.gestion.entity.membre.Membre;
import com.ceimo.gestion.entity.membre.StatutGeo;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmpruntDTO {
	
	private Long idEmprunt;
	private Date dateEmprunt;
	private Date dateLimite;
	private float montant;
	private double tauxInteret;
	private boolean etat;
	private double resteAPayer;
	private Long idEmprunteur;
	private List<Long> idAvalistes;
	
}
