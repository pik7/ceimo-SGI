package com.ceimo.gestion.entity.discipline;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;
import com.ceimo.gestion.entity.membre.Membre;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor @DynamicUpdate
public class VersementSanction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_VERSEMENT")
	private String idVersement;
	@Temporal(TemporalType.DATE)
	private Date dateVersement;
	private int montantVerse;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MEMBRE", nullable = false)
	private Membre membre;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_CONSTAT", nullable = false)
	private Constat constat;
}
