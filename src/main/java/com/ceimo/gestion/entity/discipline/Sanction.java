package com.ceimo.gestion.entity.discipline;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.ceimo.gestion.entity.membre.Elire;
import com.ceimo.gestion.entity.membre.StatutGeo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SANCTION")
@Getter
@Setter
@NoArgsConstructor @DynamicUpdate
public class Sanction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_SANCTION")
	private Long idSanction;
	@Column(name = "LIBELLE_SANCTION",length = 100)
	private String libelleSanction;
	@Column(name = "AMENDE")
	private int amende;
	
	
}
