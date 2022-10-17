package com.ceimo.gestion.service.exceptions;

public class DemissionDejaEnregistreException extends Exception {
	public DemissionDejaEnregistreException(String nom) {
		super("Demission de "+nom+" déjà enrégistrée");
	}
}
