package com.ceimo.gestion.entity.emprunt;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import com.ceimo.gestion.entity.membre.Elire;
import com.ceimo.gestion.entity.membre.Membre;
import com.ceimo.gestion.entity.membre.StatutGeo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "EMPRUNT")
@Getter
@Setter
@NoArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
//@NamedEntityGraph(name="Emprunt.emprunteur", attributeNodes = @NamedAttributeNode("emprunteur"))
@DynamicUpdate
public class Emprunt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EMPRUNT")
	private Long idEmprunt;
	@Column(name = "DATE_EMPRUNT",nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateEmprunt;
	@Column(name = "DATE_LIMITE",nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateLimite;
	@Column(name = "MONTANT",nullable = false)
	private float montant;
	@Column(name = "TAUX_INTERET",nullable = false)
	private double tauxInteret;
	private boolean etat;
	@Column(name = "RESTE_A_PAYER",nullable = false)
	private double resteAPayer;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_MEMBRE", nullable=false)
	private Membre emprunteur;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "AVALISER", // Nom de la table de jointure
			joinColumns = @JoinColumn(name = "ID_EMPRUNT"), // champ de la table de jointure
			inverseJoinColumns = @JoinColumn(name = "ID_MEMBRE")// champ de la table de jointure
	)
	private List<Membre> avalistes = new ArrayList<>();
	
	
	@Override
	public String toString() {
		return "Emprunt";
	}
	
	
	
	
	
}
