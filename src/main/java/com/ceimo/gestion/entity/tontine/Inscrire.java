package com.ceimo.gestion.entity.tontine;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;

import com.ceimo.gestion.entity.membre.Membre;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor @DynamicUpdate
public class Inscrire {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idInscription;
	
	@Column(name="MOIS_GAIN", length = 10)
	private String mois;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_MEMBRE")
	private Membre membre;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_TONTINE")
	private Tontine tontine;
	
}
