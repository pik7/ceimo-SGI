package com.ceimo.gestion.entity.seance;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="EXERCICE")
@Getter
@Setter @DynamicUpdate
@NoArgsConstructor
public class Exercice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_EXERCICE")
	private Long idExercice;
	@Column(name="DATE_DEBUT_EXERCICE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateDebutExercice;
	@Column(name="DATE_FIN_EXERCICE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateFinExercice;
	
	private boolean statut;
	@OneToMany(mappedBy = "exercice", fetch = FetchType.LAZY)
	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private List<Seance> seances = new ArrayList<>();
	
}
