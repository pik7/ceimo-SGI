package com.ceimo.gestion.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceimo.gestion.dtos.ConstatDTO;
import com.ceimo.gestion.dtos.ConstatFullDTO;
import com.ceimo.gestion.dtos.SanctionDTO;
import com.ceimo.gestion.dtos.VersementSanctionDTO;
import com.ceimo.gestion.service.DisciplineService;

import lombok.AllArgsConstructor;

@RestController @CrossOrigin("*")
@AllArgsConstructor
public class DisciplineController {

	private DisciplineService disciplineService;
	
	
	@PostMapping("/disciplines/sanctions")
	public SanctionDTO saveSanction(@RequestBody SanctionDTO sanctionDTO) throws Exception {
		return disciplineService.saveSanction(sanctionDTO);
	}
	
	@PostMapping("discipline/constats")
	public ConstatDTO sanctionner(@RequestBody ConstatDTO constatDTO) throws Exception {
		return disciplineService.saveConstat(constatDTO);
	}
	
	@PostMapping("/discipline/reglementamande")
	public VersementSanctionDTO reglerAmende(@RequestBody VersementSanctionDTO versementSanctionDTO) throws Exception {
		return disciplineService.savePaiementAmande(versementSanctionDTO);
	}
	
	@GetMapping("/discipline/sanctions")
	public List<SanctionDTO> getAll(){
		return disciplineService.getAllSanction();
	}
	
	@GetMapping("/discipline/sanctions/{id}")
	public SanctionDTO getSanction(@PathVariable(name="id") Long idSanction) throws Exception {
		return disciplineService.getSanction(idSanction);
	}
	
	@GetMapping("/disciplines/casindiscipline/versements/list")
	public List<VersementSanctionDTO>  getVersements(Long idConstat){
		return disciplineService.listerVersementParConstat(idConstat);
	}
	
	
	@GetMapping("/disciplines/casindiscipline/seance/{id}")
	public List<ConstatFullDTO>  getConstatSeance(@PathVariable(name="id") Long idSeance){
		return disciplineService.listerConstatSeance(idSeance);
	}
	
	
	@GetMapping("/disciplines/impayes")
	public List<ConstatFullDTO> getAmandeNonPaye(Long idConstat){
		return disciplineService.listerAmandesNonReglees();
	}
	
	@GetMapping("discipline/casindiscipline/{id}")
	public ConstatFullDTO getCasIndiscipline(@PathVariable(name="id") Long idConstat) throws Exception {
		return disciplineService.getConstat(idConstat);
	}
	
	
	@DeleteMapping("/discipline/sanction/{id}")
	public void deleteSanction(@PathVariable(name="id") Long idSanction) {
		disciplineService.deleteSanction(idSanction);
	}
	
	@DeleteMapping("/discipline/casindiscipline/{id}")
	public void deleteCasIndiscipline(@PathVariable(name="id") Long idConstat) {
		disciplineService.deleteConstat(idConstat);
	}
	
	@DeleteMapping("/discipline/reglementamande/{id}")
	public void deletePaiement(@PathVariable(name="id") Long idVersement) {
		disciplineService.deleteVersementSanction(idVersement);
	}
	
	
}
