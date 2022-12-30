package com.ceimo.gestion.entity.seance;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

import com.ceimo.gestion.entity.membre.Elire;
import com.ceimo.gestion.entity.membre.Membre;
import com.ceimo.gestion.entity.membre.StatutGeo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SEANCE")
@Getter
@Setter
@NoArgsConstructor @DynamicUpdate
public class Seance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_SEANCE")
	private Long idSeance;
	@Column(name = "DATE_SEANCE",nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateSeance;
	@Column(name = "ORDRE_DU_JOUR",nullable = false)
	private String ordreDuJour;
	@Column(name = "COMPTE_RENDU", length = 2000, nullable = true)
	private String compteRendu;
	@Column(name = "TYPE_SEANCE", length = 50)
	@Enumerated(EnumType.STRING)
	private TypeSeance typeSeance;
	@Enumerated(EnumType.STRING)
	private StatutSeance statutSeance;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PRESIDENT", nullable=false)
	private Membre president;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "PRESENTS", // Nom de la table de jointure
			joinColumns = @JoinColumn(name = "ID_SEANCE"), // champ de la table de jointure
			inverseJoinColumns = @JoinColumn(name = "ID_MEMBRE")// champ de la table de jointure
	)
	private List<Membre> listesdespresents = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_EXERCICE", nullable = false)
	private Exercice exercice;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ACCUEILLANTS", // Nom de la table de jointure
			joinColumns = @JoinColumn(name = "ID_SEANCE"), // champ de la table de jointure
			inverseJoinColumns = @JoinColumn(name = "ID_MEMBRE")// champ de la table de jointure
	)
	private List<Membre> accueillants = new ArrayList<>();
	

	@Override
	public String toString() {
		return "Seance [idSeance=" + idSeance + ", dateSeance=" + dateSeance + ", ordreDuJour=" + ordreDuJour
				+ ", compteRendu=" + compteRendu + "]";
	}
	
	
	
	
	
}
