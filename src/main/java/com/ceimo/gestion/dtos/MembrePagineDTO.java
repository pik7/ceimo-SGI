package com.ceimo.gestion.dtos;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class MembrePagineDTO {

	private int currentPage;
	private int totalPages;
	private int pageSize;
	private List<MembreSimplifieDTO> membres;
}
