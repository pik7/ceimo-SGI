package com.ceimo.gestion.dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ceimo.gestion.entity.membre.StatutGeo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MembreSimplifieDTO {

	private Long idMembre;
	private String nomMembre;
	private String prenomMembre;
	private char sexeMembre;
	private Date dateNaissance;
	private String email;
	private Date dateFirstInscription;
	private String photo;
	@Enumerated(EnumType.STRING)
	private StatutGeo statutGeo;
	private boolean demissionnaire;
	private String login;
	private String password;
	
}
