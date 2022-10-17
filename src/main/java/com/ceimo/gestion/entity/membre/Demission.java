package com.ceimo.gestion.entity.membre;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DEMISSION")
@Getter
@Setter
@NoArgsConstructor @DynamicUpdate
public class Demission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_DEMISSION")
	private Long iddemission;
	@Column(name="DATE_DEMISSION", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date datedemission;
	@Column(name="LETTRE_DEMISSION", nullable = false, length = 50)
	private String lettredemission;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DEMISSIONNAIRE", nullable=false)
	private Membre 	demissionnaire;
}
