package com.ceimo.gestion.entity.tontine;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TONTINE")
@Getter
@Setter
@NoArgsConstructor @DynamicUpdate
public class Tontine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TONTINE")
	private Long idTontine;
	@Column(name = "LIBELLE_TONTINE",nullable = false)
	private String libelleTontine;
	@Column(name = "DATE_DEBUT",nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	@Column(name = "DATE_FIN",nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	@Column(name = "ETAT",nullable = false)
	private boolean etat;

	@Override
	public String toString() {
		return "Tontine [idTontine=" + idTontine + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin
				+ ", montantMinimal=]";
	}
	
	
	
	
}
