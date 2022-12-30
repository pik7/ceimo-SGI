package com.ceimo.gestion.service;

import java.util.ArrayList;
import java.util.List;

import com.ceimo.gestion.dtos.ContributionDTO;
import com.ceimo.gestion.dtos.ContributionFullDTO;
import com.ceimo.gestion.dtos.GainTontineDTO;
import com.ceimo.gestion.dtos.InscrireDTO;
import com.ceimo.gestion.dtos.MembreSimplifieDTO;
import com.ceimo.gestion.dtos.TontineDTO;
import com.ceimo.gestion.service.exceptions.MembreNotFoundException;
import com.ceimo.gestion.service.exceptions.SeanceNotFoundException;

public interface TontineService {

	public TontineDTO saveTontine(TontineDTO tontineDTO);
	public void deleteTontine(Long idTontine) throws TontineNotFoundException ;
	
	public ContributionDTO cotiser(ContributionDTO contributionDTO) 
			throws MembreNotFoundException, TontineNotFoundException, SeanceNotFoundException; 
	public void inscrireATontine(Long idMembre, Long idTontine, double montant, int ordre) 
			throws MembreNotFoundException, TontineNotFoundException;
	
	public void classement(Long idTontine) throws TontineNotFoundException;
	public GainTontineDTO getAyantCotises(String mois, Long idTontine) throws TontineNotFoundException;
	public List<InscrireDTO> getAllGainMonth(Long idTontine);
	List<MembreSimplifieDTO> nonCotises(Long idTontine, String mois) throws TontineNotFoundException;
	void deleteContribution(Long idContribution) throws Exception;
	void reclasserApresEchec(Long idTontine, Long idMembre);
	public void removeFromTontine(Long idMembre, Long idTontine) throws MembreNotFoundException, TontineNotFoundException ;
}
