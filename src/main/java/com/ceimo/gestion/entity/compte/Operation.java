package com.ceimo.gestion.entity.compte;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import com.ceimo.gestion.entity.compte.enums.TypeOperation;
import com.ceimo.gestion.entity.membre.Membre;

import com.ceimo.gestion.entity.seance.Seance;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@NoArgsConstructor @DynamicUpdate
@Data
public class Operation {

	
	@Id
	private String idOperation;
	private Date dateOperation;
	private double montant;
	@Enumerated(EnumType.STRING)
	private TypeOperation type;
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Compte compte;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Seance seance;
}
