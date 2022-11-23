package com.ceimo.gestion.entity.membre;

import java.util.*;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

import com.ceimo.gestion.entity.emprunt.Emprunt;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MEMBRE")
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
public class Membre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_MEMBRE")
	private Long idMembre;
	@Column(name = "NOM_MEMBRE", length = 50, nullable = false)
	private String nomMembre;
	@Column(name = "PRENOM_MEMBRE", length = 50, nullable = true)
	private String prenomMembre;
	@Column(name = "SEXE_MEMBRE", nullable = false)
	private char sexeMembre;
	@Column(name = "DATE_NAISSANCE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	@Column(name = "EMAIL", nullable = false)
	private String email;
	@Column(name = "DATE_FIRST_INSCRIPTION", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateFirstInscription;
	@Column(name = "PHOTO", nullable = true, length = 100)
	private String photo;
	@Column(name = "STATUTGEOGRAPHIQUE", nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private StatutGeo statutGeo;
	private boolean demissionnaire;
	
	@Column(name = "LOGIN")
	private String login;
	@Column(name = "PASSWORD")
	private String password;
	
	@OneToMany(mappedBy = "membre", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private List<Elire> mesRoles;
	
	public void addPoste(Elire elire) {
		mesRoles.add(elire);
		elire.setMembre(this);
	}

	@Override
	public String toString() {
		return "Membre";
	}

}
