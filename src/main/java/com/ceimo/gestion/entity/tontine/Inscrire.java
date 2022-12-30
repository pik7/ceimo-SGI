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
import javax.persistence.Table;

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
@Table(name = "INSCRIRE_TONTINE")
public class Inscrire {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idInscription;
	
	private double montant;
	
	@Column(name="MOIS_GAIN", length = 10, nullable = true)
	private String mois;
	private double gain;
	
	@Column(name = "classer", nullable = false)
	private boolean classer;
	
	private int nombreEchec;
	
	private int ordre;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_MEMBRE")
	private Membre membre;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_TONTINE")
	private Tontine tontine;
	
}
