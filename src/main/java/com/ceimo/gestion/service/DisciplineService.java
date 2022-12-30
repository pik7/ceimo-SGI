package com.ceimo.gestion.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ceimo.gestion.dtos.ConstatDTO;
import com.ceimo.gestion.dtos.ConstatFullDTO;
import com.ceimo.gestion.dtos.SanctionDTO;
import com.ceimo.gestion.dtos.VersementSanctionDTO;
import com.ceimo.gestion.entity.discipline.Constat;
import com.ceimo.gestion.entity.discipline.Sanction;
import com.ceimo.gestion.entity.discipline.VersementSanction;

public interface DisciplineService {

	public SanctionDTO saveSanction(SanctionDTO sanctionDTO) throws Exception ;
	
	public void deleteSanction(Long idSanction);
	
	public ConstatDTO saveConstat(ConstatDTO constatDTO) throws Exception;
	
	public VersementSanctionDTO savePaiementAmande(VersementSanctionDTO versementSanctionDTO ) throws Exception ;
	
	public List<ConstatFullDTO> listerConstatSeance(Long idSeance);
	
	public List<ConstatFullDTO> listerAmandesNonReglees();
	
	public List<VersementSanctionDTO> listerVersementParConstat(Long idConstat);
	
	public List<SanctionDTO> getAllSanction();
	
	public SanctionDTO getSanction(Long idSanction) throws Exception ;

	public ConstatFullDTO getConstat(Long idConstat) throws Exception;

	void deleteConstat(Long idConstat);

	void deleteVersementSanction(Long id);
}
