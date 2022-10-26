package com.ceimo.gestion.entity.membre;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "RESPONSABILITE") @DynamicUpdate
public class Responsabilite {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RESPONSABILITE")
	private Long idresponsabilite;
	@Column(name="INTITULE_RESPONSABILITE", nullable = false, unique = true)
	private String poste;
	@Column(name="DUREE_MANDAT")
	private int dureemandat; 
	
	/*
	 * @OneToMany(mappedBy = "pk.responsabilite") private Set<Elire> lesElus = new
	 * HashSet<>();
	 */
}
