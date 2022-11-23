package com.ceimo.gestion.dtos;

import java.util.Date;

import com.ceimo.gestion.entity.compte.enums.TypeOperation;

import lombok.Data;

@Data
public class OperationDTO {

	private String idOperation;
	private Date dateOperation;
	private double montant;
	private TypeOperation type;
	private String description;
}
