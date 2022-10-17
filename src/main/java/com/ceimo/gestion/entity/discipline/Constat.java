package com.ceimo.gestion.entity.discipline;

import java.util.Date;
import java.util.Set;

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
import com.ceimo.gestion.entity.seance.Seance;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor @DynamicUpdate
public class Constat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idConstat;
	private boolean paye;

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "ID_SEANCE", nullable = false)
	private Seance seance;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MEMBRE", nullable = false)
	private Membre membre;

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "ID_SANCTION", nullable = false)
	private Sanction sanction;

}
