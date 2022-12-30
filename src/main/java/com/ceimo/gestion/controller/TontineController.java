package com.ceimo.gestion.controller;

import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceimo.gestion.dtos.ContributionDTO;
import com.ceimo.gestion.dtos.GainTontineDTO;
import com.ceimo.gestion.dtos.InscrireDTO;
import com.ceimo.gestion.dtos.MembreSimplifieDTO;
import com.ceimo.gestion.dtos.RequestInscrireATontineDTO;
import com.ceimo.gestion.dtos.RequestReclassementDTO;
import com.ceimo.gestion.dtos.ResquestTontineMoisDTO;
import com.ceimo.gestion.dtos.TontineDTO;
import com.ceimo.gestion.service.TontineNotFoundException;
import com.ceimo.gestion.service.TontineService;
import com.ceimo.gestion.service.exceptions.MembreNotFoundException;
import com.ceimo.gestion.service.exceptions.SeanceNotFoundException;

import lombok.AllArgsConstructor;

@RestController @CrossOrigin("*")
@AllArgsConstructor
public class TontineController {

	
	private TontineService tontineService;
	
	
	@PostMapping("/tontines")
	public TontineDTO saveTontine(@RequestBody TontineDTO tontineDTO) {
		return tontineService.saveTontine(tontineDTO);
	}
	
	
	@PostMapping("tontines/cotiser")
	public ContributionDTO cotiser(@RequestBody ContributionDTO contributionDTO) 
			throws MembreNotFoundException, TontineNotFoundException, SeanceNotFoundException {
		return tontineService.cotiser(contributionDTO);
	}
	
	
	@PostMapping("tontines/inscription")
	public void inscrire(@RequestBody RequestInscrireATontineDTO inscrire) throws MembreNotFoundException, TontineNotFoundException {
		tontineService.inscrireATontine(inscrire.getIdMembre(), 
				inscrire.getIdTontine(), inscrire.getMontant(), inscrire.getOrdre());
		
	}
	
	
	@PostMapping("/tontine/classement")
	public void classerTontine(@RequestBody Long id) throws TontineNotFoundException {
		tontineService.classement(id);
	}
	
	
	@PutMapping("/tontines/echec")
	public void reclasserPourEchec(@RequestBody RequestReclassementDTO requestDTO) {
		tontineService.reclasserApresEchec(requestDTO.getIdTontine(), requestDTO.getIdMembre());
	}
	
	
	
	@DeleteMapping("tontines/exclure")
	public void exclureDeLaTontine(@RequestBody RequestReclassementDTO requestDTO) throws MembreNotFoundException, TontineNotFoundException {
		tontineService.removeFromTontine(requestDTO.getIdMembre(), requestDTO.getIdTontine());
	}
	
	
	@DeleteMapping("/tontines/contribution/{id}")
	public void deleteContribution(@PathVariable Long id) throws Exception {
		tontineService.deleteContribution(id);
	}
	
	
	@DeleteMapping("/tontines/{id}")
	public void deleteTontine(@PathVariable(name = "id") Long idTontine) throws TontineNotFoundException {
		tontineService.deleteTontine(idTontine);
	}
	
	
	@GetMapping("/tontines/liste_echecs")
	public List<MembreSimplifieDTO> listerEchecsDuMois(ResquestTontineMoisDTO resquestDTO) throws TontineNotFoundException{
		return tontineService.nonCotises(resquestDTO.getIdTontine(), resquestDTO.getMois());
	}
	
	@GetMapping("/tontintes/classement/{id}")
	public List<InscrireDTO> getClassementTontine(@PathVariable(name = "id") Long idTontine){
		return tontineService.getAllGainMonth(idTontine);
	}
	
	
	
	@GetMapping("/tontines/Beneficiaire")
	public  GainTontineDTO getRecapTontineDuMois(ResquestTontineMoisDTO resquestDTO) throws TontineNotFoundException{
		return tontineService.getAyantCotises(resquestDTO.getMois(), resquestDTO.getIdTontine());
	}
	
	
	
	
	
	
	
	
	
}
