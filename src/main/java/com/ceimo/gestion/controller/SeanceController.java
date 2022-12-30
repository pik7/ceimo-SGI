package com.ceimo.gestion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceimo.gestion.dtos.AssisterDTO;
import com.ceimo.gestion.dtos.ExerciceDTO;
import com.ceimo.gestion.dtos.MembrePagineDTO;
import com.ceimo.gestion.dtos.MembreSimplifieDTO;
import com.ceimo.gestion.dtos.SeanceFullDTO;
import com.ceimo.gestion.dtos.SeanceSimpleDTO;
import com.ceimo.gestion.service.SeanceService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class SeanceController {

	
	private SeanceService seanceService; 
	
	@PostMapping("/seances")
	public SeanceSimpleDTO saveSeance(@RequestBody SeanceSimpleDTO seanceSimpleDTO) throws Exception {
		return seanceService.saveSeance(seanceSimpleDTO);
	}
	
	
	@DeleteMapping("seances/{id}")
	public void suppSeance(@PathVariable Long id) throws Exception {
		seanceService.deleteSeance(id);
	}
	
	@PostMapping("/seances/assister")
	public void marquerPresent(@RequestBody AssisterDTO assisterDTO) throws Exception {
		seanceService.appelSeance(assisterDTO.getIdMembre(), assisterDTO.getIdSeance());
	}
	
	@GetMapping("/seances/{idSeance}")
	public SeanceSimpleDTO getSeance(@PathVariable Long idSeance) throws Exception {
		return seanceService.getSeance(idSeance);
	}
	
	@GetMapping("/seances/{idSeance}/presents")
	public SeanceFullDTO lesPresents(@PathVariable Long idSeance) throws Exception {
		return seanceService.getListeDesPresents(idSeance);
	}
	
	@GetMapping("/seances/{idSeance}/absents")
	public List<MembreSimplifieDTO> lesAbsents(@PathVariable Long idSeance) throws Exception {
		return seanceService.listerAbsents(idSeance);
	}
	
	@DeleteMapping("/seances/exercices/{id}")
	public void deleteExercice(@PathVariable(name="id") Long idExercice) throws Exception {
		seanceService.deleteExercice(idExercice);
	}
	
	@PostMapping("/seances/exercices")
	public ExerciceDTO saveExercice(@RequestBody ExerciceDTO exerciceDTO) throws Exception {
		return seanceService.saveExercice(exerciceDTO);
	}
	
	
	@GetMapping("/seances/exercices/{id}")
	public ExerciceDTO getExercice(@PathVariable Long id) throws Exception {
		return seanceService.getExercice(id);
	}
	
}
