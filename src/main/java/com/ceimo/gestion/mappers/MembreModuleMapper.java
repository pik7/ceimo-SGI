package com.ceimo.gestion.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ceimo.gestion.dtos.CompteCollationDTO;
import com.ceimo.gestion.dtos.CompteEpargneDTO;
import com.ceimo.gestion.dtos.CompteFondDTO;
import com.ceimo.gestion.dtos.CompteRoulementDTO;
import com.ceimo.gestion.dtos.DemissionDTO;
import com.ceimo.gestion.dtos.ElireDTO;
import com.ceimo.gestion.dtos.ExerciceDTO;
import com.ceimo.gestion.dtos.InscriptionDTO;
import com.ceimo.gestion.dtos.MembreBureauDTO;
import com.ceimo.gestion.dtos.MembreSimplifieDTO;
import com.ceimo.gestion.dtos.OperationDTO;
import com.ceimo.gestion.dtos.ResponsabiliteDTO;
import com.ceimo.gestion.entity.compte.Collation;
import com.ceimo.gestion.entity.compte.Epargne;
import com.ceimo.gestion.entity.compte.Fond;
import com.ceimo.gestion.entity.compte.Operation;
import com.ceimo.gestion.entity.compte.Roulement;
import com.ceimo.gestion.entity.membre.Demission;
import com.ceimo.gestion.entity.membre.Elire;
import com.ceimo.gestion.entity.membre.Inscription;
import com.ceimo.gestion.entity.membre.Membre;
import com.ceimo.gestion.entity.membre.Responsabilite;
import com.ceimo.gestion.entity.seance.Exercice;

@Service
public class MembreModuleMapper {
	
	
	//****************** Mapping de Membre ***********************************
	public MembreSimplifieDTO fromMembre(Membre membre) {

		MembreSimplifieDTO membreSimplifieDTO = new MembreSimplifieDTO();
		BeanUtils.copyProperties(membre, membreSimplifieDTO);
		return membreSimplifieDTO;
	}

	public Membre fromMembreSimplifieDTO(MembreSimplifieDTO membreSimplifieDTO) {

		Membre membre = new Membre();
		BeanUtils.copyProperties(membreSimplifieDTO, membre);
		return membre;
	}
	
	//****************** Fin Mapping de Membre ***********************************
	
	//****************** Mapping de Responsabilite ***********************************
	
	public ResponsabiliteDTO fromResponsabilite(Responsabilite responsabilite) {
		ResponsabiliteDTO responsabiliteDTO = new ResponsabiliteDTO();
		BeanUtils.copyProperties(responsabilite, responsabiliteDTO);
		return responsabiliteDTO;
	}
	
	public Responsabilite fromResponsabiliteDTO(ResponsabiliteDTO responsabiliteDTO) {
		Responsabilite responsabilite = new Responsabilite();
		BeanUtils.copyProperties(responsabiliteDTO, responsabilite);
		return responsabilite;
	}
	
	//****************** Fin Mapping de Responsabilite ***********************************
	
	//****************** Mapping de Exercice ***********************************
	
	public ExerciceDTO fromExercice(Exercice exercice) {
		ExerciceDTO exerciceDTO = new ExerciceDTO();
		BeanUtils.copyProperties(exercice, exerciceDTO);
		return exerciceDTO;
	}
	
	public Exercice fromExerciceDTO(ExerciceDTO exerciceDTO) {
		Exercice exercice = new Exercice();
		BeanUtils.copyProperties(exerciceDTO, exercice);
		return exercice;
	}
	
	//****************** Fin Mapping de Exercice ***********************************
	
	//****************** Mapping de Elire ***********************************
	
	public ElireDTO fromElire(Elire elire) {
		ElireDTO elireDTO = new ElireDTO();
		BeanUtils.copyProperties(elire, elireDTO);
		elireDTO.setMembre(fromMembre(elire.getMembre()));
		elireDTO.setResponsabilite(fromResponsabilite(elire.getResponsabilite()));
		elireDTO.setExercice(fromExercice(elire.getExercice()));
		return elireDTO;
	}
	
	public Elire fromElireDTO(ElireDTO elireDTO) {
		Elire elire = new Elire();
		BeanUtils.copyProperties(elireDTO, elire);
		elire.setMembre(fromMembreSimplifieDTO(elireDTO.getMembre()));
		elire.setResponsabilite(fromResponsabiliteDTO(elireDTO.getResponsabilite()));
		elire.setExercice(fromExerciceDTO(elireDTO.getExercice()));
		return elire;
	}
	
	//****************** Fin Mapping de Exercice ***********************************
	
	//****************** Mapping de Demission ***********************************
	
	public DemissionDTO fromDemission(Demission demission) {
		DemissionDTO demissionDTO = new DemissionDTO();
		BeanUtils.copyProperties(demission, demissionDTO);
		demissionDTO.setDemissionnaire(fromMembre(demission.getDemissionnaire()));
		return demissionDTO;
	}
	
	public Demission fromDemissionDTO(DemissionDTO demissionDTO) {
		Demission demission = new Demission();
		BeanUtils.copyProperties(demissionDTO, demission);
		demission.setDemissionnaire(fromMembreSimplifieDTO(demissionDTO.getDemissionnaire()));
		return demission;
	}
	
	//****************** Fin Mapping de Demission ***********************************
	
	//****************** Mapping de Membre Bureau ***********************************
	
	public MembreBureauDTO fromMembreBureau(Membre membre) {
		MembreBureauDTO membreBureauDTO = new MembreBureauDTO();
		BeanUtils.copyProperties(membre, membreBureauDTO);
		List<Responsabilite> responsabilites = new ArrayList<Responsabilite>();
		membre.getMesRoles().forEach(role ->{ responsabilites.add(role.getResponsabilite()); });
		List<ResponsabiliteDTO> responsabiliteDTOs = responsabilites.stream().map(resp ->fromResponsabilite(resp)).collect(Collectors.toList());
		membreBureauDTO.setPostes(responsabiliteDTOs);
		return membreBureauDTO;
	}
	
	public InscriptionDTO fromInscription(Inscription inscription) {
		InscriptionDTO inscriptionDTO = new InscriptionDTO();
		BeanUtils.copyProperties(inscription, inscriptionDTO);
		inscriptionDTO.setMembre(fromMembre(inscription.getMembre()));	
		inscriptionDTO.setExercice(fromExercice(inscription.getExercice()));
		return inscriptionDTO;
	}
	
	public Inscription fromInscriptionDTO(InscriptionDTO inscriptionDTO) {
		Inscription inscription = new Inscription();
		BeanUtils.copyProperties(inscriptionDTO, inscription);
		inscription.setMembre(fromMembreSimplifieDTO(inscriptionDTO.getMembre()));	
		inscription.setExercice(fromExerciceDTO(inscriptionDTO.getExercice()));
		return inscription;
	}
	
	
	public CompteEpargneDTO fromCompteEpargne(Epargne epargne) {
		
		CompteEpargneDTO compteEpargneDTO = new CompteEpargneDTO();
		BeanUtils.copyProperties(epargne, compteEpargneDTO);
		compteEpargneDTO.setMembre(fromMembre(epargne.getMembre()));
		compteEpargneDTO.setType(epargne.getClass().getSimpleName());
		return compteEpargneDTO;
	}
	
	public Epargne fromCompteEpargneDTO(CompteEpargneDTO compteEpargneDTO) {
		
		Epargne epargne = new Epargne();
		BeanUtils.copyProperties(compteEpargneDTO, epargne);
		epargne.setMembre(fromMembreSimplifieDTO(compteEpargneDTO.getMembre()));
		return epargne;
	}
	
	public CompteFondDTO fromCompteFond(Fond fond) {
		
		CompteFondDTO compteFondDTO = new CompteFondDTO();
		BeanUtils.copyProperties(fond, compteFondDTO);
		compteFondDTO.setMembre(fromMembre(fond.getMembre()));
		compteFondDTO.setType(fond.getClass().getSimpleName());
		return compteFondDTO;
	}
	
	public Fond fromCompteFondDTO(CompteFondDTO compteFondDTO) {
		
		Fond fond = new Fond();
		BeanUtils.copyProperties(compteFondDTO, fond);
		fond.setMembre(fromMembreSimplifieDTO(compteFondDTO.getMembre()));
		return fond;
	}
	
	
	public CompteCollationDTO fromCompteCollation(Collation collation) {
		
		CompteCollationDTO compteCollationDTO = new CompteCollationDTO();
		BeanUtils.copyProperties(collation, compteCollationDTO);
		compteCollationDTO.setMembre(fromMembre(collation.getMembre()));
		compteCollationDTO.setType(collation.getClass().getSimpleName());
		return compteCollationDTO;
	}
	
	public Collation fromCompteCollationDTO(CompteCollationDTO compteCollationDTO) {
		
		Collation collation = new Collation();
		BeanUtils.copyProperties(compteCollationDTO, collation);
		collation.setMembre(fromMembreSimplifieDTO(compteCollationDTO.getMembre()));
		return collation;
	}
	
	public CompteRoulementDTO fromCompteRoulement(Roulement roulement) {
		
		CompteRoulementDTO compteRoulementDTO = new CompteRoulementDTO();
		BeanUtils.copyProperties(roulement, compteRoulementDTO);
		compteRoulementDTO.setMembre(fromMembre(roulement.getMembre()));
		compteRoulementDTO.setType(roulement.getClass().getSimpleName());
		return compteRoulementDTO;
	}
	
	public Roulement fromCompteRoulementDTO(CompteRoulementDTO compteRoulementDTO) {
		
		Roulement roulement = new Roulement();
		BeanUtils.copyProperties(compteRoulementDTO, roulement);
		roulement.setMembre(fromMembreSimplifieDTO(compteRoulementDTO.getMembre()));
		return roulement;
	}
	
	
	public OperationDTO fromOperation(Operation operation) {
		OperationDTO operationDTO = new OperationDTO();
		BeanUtils.copyProperties(operation, operationDTO);
		return operationDTO;
	}
	
	public Operation fromOperationDTO(OperationDTO operationDTO) {
		Operation operation = new Operation();
		BeanUtils.copyProperties(operationDTO, operation);
		return operation;
	}
	
	
	
	
	
	
}
