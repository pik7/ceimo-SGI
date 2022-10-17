package com.ceimo.gestion.entity.compte;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.ceimo.gestion.entity.compte.enums.StatutCompte;
import com.ceimo.gestion.entity.membre.Elire;
import com.ceimo.gestion.entity.membre.Membre;
import com.ceimo.gestion.entity.membre.StatutGeo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter
@Setter
@NoArgsConstructor @DynamicUpdate
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",length = 4, discriminatorType = DiscriminatorType.STRING)
public abstract class Compte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_COMPTE")
	private Long idCompte;
	private double solde;
	private StatutCompte statutCompte;
	private Date dateCreation;

	@ManyToOne
	private Membre membre;
	@OneToMany(mappedBy = "compte", fetch = FetchType.LAZY)
	private List<Operation> listOperation ;
	
	
}
