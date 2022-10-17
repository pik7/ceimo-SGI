package com.ceimo.gestion.service;

import java.util.Date;
import java.util.List;

import com.ceimo.gestion.dtos.DemissionDTO;
import com.ceimo.gestion.dtos.ElireDTO;
import com.ceimo.gestion.dtos.MembreBureauDTO;
import com.ceimo.gestion.dtos.MembreSimplifieDTO;
import com.ceimo.gestion.dtos.ResponsabiliteDTO;
import com.ceimo.gestion.entity.membre.Membre;
import com.ceimo.gestion.entity.membre.Poste;
import com.ceimo.gestion.service.exceptions.DemissionDejaEnregistreException;
import com.ceimo.gestion.service.exceptions.DemissionNotFoundException;
import com.ceimo.gestion.service.exceptions.ExerciceNotFoundException;
import com.ceimo.gestion.service.exceptions.MembreDejeExistantException;
import com.ceimo.gestion.service.exceptions.MembreNotFoundException;
import com.ceimo.gestion.service.exceptions.PosteDejaOccupeException;
import com.ceimo.gestion.service.exceptions.ResponsabiliteDejeExistantException;
import com.ceimo.gestion.service.exceptions.ResponsabiliteNotFoundException;

public interface MembreService {

	public List<MembreSimplifieDTO> listMembres();
	
	public MembreSimplifieDTO getMembreById(Long idMembre) 
			throws MembreNotFoundException;
	
	public List<MembreSimplifieDTO> searchMembre(String mc);
	
	public List<MembreBureauDTO> listMembresBureau(Long idExercice);
	
	public List<DemissionDTO> getDemissionnaire();
	
	public MembreSimplifieDTO saveMembre(MembreSimplifieDTO membreDTO) 
			throws MembreDejeExistantException;
	
	public ResponsabiliteDTO saveResponsabilite(ResponsabiliteDTO responsabiliteDTO) 
			throws ResponsabiliteDejeExistantException;
	
	public ElireDTO nommerMembre(Long idMembre, Long idExercice, Poste poste, Date dateNomination, int montantInscription) 
			throws MembreNotFoundException, ExerciceNotFoundException, PosteDejaOccupeException;
	
	public DemissionDTO demissionner(Long idMembre, Date datedemission, String lettre) 
			throws MembreNotFoundException, DemissionDejaEnregistreException;
	
	public void deleteMembre(Long idMembre) throws MembreNotFoundException;
	
	public void deleteResponsabilite(Long idResponsabilite) throws ResponsabiliteNotFoundException;
	
	public void deleteDemission(Long idDemission) throws DemissionNotFoundException;
	
	public void exclureDuBureau(Long idMembre, Long idExercice) throws MembreNotFoundException, ExerciceNotFoundException;
}
