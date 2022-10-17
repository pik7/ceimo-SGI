package com.ceimo.gestion.entity.membre;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;
import com.ceimo.gestion.entity.seance.Exercice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
public class Elire {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ELIRE")
	private Long id;
	@Column(name = "DATE_ADHESION")
	@Temporal(TemporalType.DATE)
	private Date dateadhesion;
	@Column(name = "MONTANT_INSCRIPTION", nullable = true)
	private int montantinscription;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MEMBRE", nullable = false)
	private Membre membre;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_RESPONSABILITE", nullable = false)
	private Responsabilite responsabilite;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_EXERCICE", nullable = false)
	private Exercice exercice;
	
	@Override
	public String toString() {
		return "Elire [id=" + id + ", dateadhesion=" + dateadhesion + ", montantinscription=" + montantinscription
				+ "]";
	}
	
	
}
