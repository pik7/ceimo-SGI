package com.ceimo.gestion.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class HistoriqueCompteDTO {

	private Long idCompte;
	private double Solde;
	private int currentPage;
	private int totalPages;
	private int pageSize ;
	private List<OperationDTO> operationDTOs;
}
