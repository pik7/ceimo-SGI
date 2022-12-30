package com.ceimo.gestion.dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.*;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.ceimo.gestion.entity.membre.Membre;
import com.ceimo.gestion.entity.seance.Exercice;
import com.ceimo.gestion.entity.seance.StatutSeance;
import com.ceimo.gestion.entity.seance.TypeSeance;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class SeanceFullDTO {
		
	private Long idSeance;	
	private Date dateSeance;
	private String ordreDuJour;	
	private String compteRendu;	
	private TypeSeance typeSeance;
	private StatutSeance statutSeance;
	private MembreSimplifieDTO president;
	private ExerciceDTO exercice;
	private List<MembreSimplifieDTO> listesdespresents = new ArrayList<>();
	private List<MembreSimplifieDTO> accueillants = new ArrayList<>();

}
