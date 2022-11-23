package com.ceimo.gestion.controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceimo.gestion.dtos.DemissionDTO;
import com.ceimo.gestion.dtos.DemissionDTOLight;
import com.ceimo.gestion.dtos.ElireDTO;
import com.ceimo.gestion.dtos.ElireDTOLight;
import com.ceimo.gestion.dtos.InscriptionDTO;
import com.ceimo.gestion.dtos.InscriptionRequestDTO;
import com.ceimo.gestion.dtos.MembreBureauDTO;
import com.ceimo.gestion.dtos.MembrePagineDTO;
import com.ceimo.gestion.dtos.MembreSimplifieDTO;
import com.ceimo.gestion.dtos.ResponsabiliteDTO;
import com.ceimo.gestion.service.MembreService;
import com.ceimo.gestion.service.exceptions.DemissionDejaEnregistreException;
import com.ceimo.gestion.service.exceptions.DemissionNotFoundException;
import com.ceimo.gestion.service.exceptions.ExerciceNotFoundException;
import com.ceimo.gestion.service.exceptions.InscriptionDejaExixtanteException;
import com.ceimo.gestion.service.exceptions.MembreDejeExistantException;
import com.ceimo.gestion.service.exceptions.MembreNotFoundException;
import com.ceimo.gestion.service.exceptions.PosteDejaOccupeException;
import com.ceimo.gestion.service.exceptions.ResponsabiliteDejeExistantException;
import com.ceimo.gestion.service.exceptions.ResponsabiliteNotFoundException;

import lombok.AllArgsConstructor;

@RestController @CrossOrigin("*")
@AllArgsConstructor
public class MembreController {
	
	private MembreService membreService;
	
	@GetMapping("/membres")
	public MembrePagineDTO listerMembres(
			@RequestParam(name = "page") int page, 
			@RequestParam(name = "size") int size) {
		return membreService.getAllMembrePagine(page, size);
	}
	
	@GetMapping("/membres/bureau/{idExercice}")
	public List<MembreBureauDTO> listerMembresDuBureau(@PathVariable Long idExercice){
		return membreService.listMembresBureau(idExercice);
	}
	
	@GetMapping("/membres/recherche")
	public List<MembreSimplifieDTO> searchMembre(@RequestParam(name = "mc", defaultValue = "") String mc){
		return membreService.searchMembre("%"+mc+"%");
	}
	
	@GetMapping("/membres/{idMembre}")
	public MembreSimplifieDTO unMembre(@PathVariable Long idMembre) throws MembreNotFoundException {
		return membreService.getMembreById(idMembre);
	}
	
	@GetMapping("/membres/demissionnaire")
	public List<DemissionDTO> listerDemissionnaire(){
		return membreService.getDemissionnaire();
	}
	
	@PostMapping("/membres")
	public MembreSimplifieDTO saveMembre(@RequestBody MembreSimplifieDTO membreSimplifieDTO) throws MembreDejeExistantException, MembreNotFoundException {
		return membreService.saveMembre(membreSimplifieDTO);
	}
	
	@PostMapping("/membres/responsabilites")
	public ResponsabiliteDTO saveResponsabilite(@RequestBody ResponsabiliteDTO responsabiliteDTO) throws ResponsabiliteDejeExistantException {
		return membreService.saveResponsabilite(responsabiliteDTO);
	}
	
	@PostMapping("/membres/nomination")
	public ElireDTO nommerMembre(@RequestBody ElireDTOLight elireDTOLight) throws MembreNotFoundException, ExerciceNotFoundException, PosteDejaOccupeException {
		return membreService.nommerMembre(elireDTOLight);
	}
	
	@PostMapping("/membres/demission")
	public DemissionDTO demissioner(@RequestBody DemissionDTOLight demissionDTOLight) throws MembreNotFoundException, DemissionDejaEnregistreException {
		return membreService.demissionner(demissionDTOLight);
	}
	
	@DeleteMapping("/membres/{idMembre}")
	public void deleteMembre(@PathVariable Long idMembre) throws MembreNotFoundException {
		membreService.deleteMembre(idMembre);
	}
	
	@DeleteMapping("/membres/poste/{id}")
	public void deletePoste(@PathVariable("id") Long idResponsabilite) throws ResponsabiliteNotFoundException {
		membreService.deleteResponsabilite(idResponsabilite);
	}
	
	@DeleteMapping("/membres/demission/{id}")
	public void deleteDemission(@PathVariable("id") Long idDemission) throws DemissionNotFoundException {
		membreService.deleteDemission(idDemission);
	}
	
	@DeleteMapping("/membres/exclure/{id}")
	public void exclureDuBureau(@PathVariable("id") Long idMembre) throws MembreNotFoundException, ExerciceNotFoundException {
		membreService.exclureDuBureau(idMembre);
	}
	
	@PutMapping("/membres/{id}")
	public MembreSimplifieDTO updateMembre(@PathVariable("id") Long idMembre, @RequestBody MembreSimplifieDTO membreSimplifieDTO) throws MembreDejeExistantException, MembreNotFoundException {
		membreSimplifieDTO.setIdMembre(idMembre);
		return membreService.saveMembre(membreSimplifieDTO);
	}
	
	@PutMapping("/membres/responsabilite/{id}")
	public ResponsabiliteDTO updateResponsabilite(@PathVariable("id") Long idResp, @RequestBody ResponsabiliteDTO responsabiliteDTO)
			throws ResponsabiliteDejeExistantException {
		responsabiliteDTO.setIdresponsabilite(idResp);	
		return membreService.saveResponsabilite(responsabiliteDTO);
	}
	
	@GetMapping("/membres/responsabilites")
	public List<ResponsabiliteDTO> listerresponsabilites(){
		return membreService.getResponsabilite();	
	}
	
	@PostMapping("/membres/inscription")
	public InscriptionDTO saveInscriptionMembre(@RequestBody InscriptionRequestDTO inscriptionRequestDTO)
			throws MembreNotFoundException, ExerciceNotFoundException, InscriptionDejaExixtanteException {
		return membreService.inscrireMembre(inscriptionRequestDTO.getIdMembre(), inscriptionRequestDTO.getIdExercice(), inscriptionRequestDTO.getMontant());
	}
}
