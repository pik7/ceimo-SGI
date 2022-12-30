package com.ceimo.gestion.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceimo.gestion.dtos.CompteCollationDTO;
import com.ceimo.gestion.dtos.CompteDTO;
import com.ceimo.gestion.dtos.CompteEpargneDTO;
import com.ceimo.gestion.dtos.CompteFondDTO;
import com.ceimo.gestion.dtos.CompteRoulementDTO;
import com.ceimo.gestion.dtos.CreditDTO;
import com.ceimo.gestion.dtos.DebitDTO;
import com.ceimo.gestion.dtos.HistoriqueCompteDTO;
import com.ceimo.gestion.dtos.OperationCommuneDTO;
import com.ceimo.gestion.dtos.RequestCreateCollationDTO;
import com.ceimo.gestion.dtos.RequestCreateEpargneDTO;
import com.ceimo.gestion.dtos.RequestCreateFondDTO;
import com.ceimo.gestion.dtos.RequestCreateRoulementDTO;
import com.ceimo.gestion.dtos.SoldesCompteMembreDTO;
import com.ceimo.gestion.dtos.TransfertDTO;
import com.ceimo.gestion.entity.compte.Collation;
import com.ceimo.gestion.entity.compte.Epargne;
import com.ceimo.gestion.entity.compte.Fond;
import com.ceimo.gestion.entity.compte.Roulement;
import com.ceimo.gestion.service.CompteService;
import com.ceimo.gestion.service.exceptions.CompteDejaCreeException;
import com.ceimo.gestion.service.exceptions.CompteNotFoundException;
import com.ceimo.gestion.service.exceptions.MembreNotFoundException;
import com.ceimo.gestion.service.exceptions.OperationNotFoundException;
import com.ceimo.gestion.service.exceptions.SeanceNotFoundException;
import com.ceimo.gestion.service.exceptions.SoldeInsuffisantException;

import lombok.AllArgsConstructor;

@RestController @CrossOrigin("*")
@AllArgsConstructor
public class CompteController {

	private CompteService compteService;
	
	@PostMapping("/comptes/epargne")
	public CompteEpargneDTO createCompteEpargne(@RequestBody RequestCreateEpargneDTO compte) throws MembreNotFoundException, CompteDejaCreeException {
		if(!compteService.findCompteEpargne(compte.getIdMembre())) {
			return compteService.saveCompteEpargne(
					compte.getSolde(), compte.getTauxInteret(), compte.getIdMembre(), compte.getStatutCompte());
		}else {
			throw new CompteDejaCreeException("Compte epargne deja existant pour ce membre");
		}
	}
	
	@PostMapping("/comptes/fond")
	public CompteFondDTO createCompteFond(@RequestBody RequestCreateFondDTO compte) throws MembreNotFoundException, CompteDejaCreeException {
		if(!compteService.findCompteFond(compte.getIdMembre())) {
			return compteService.saveCompteFond(
					compte.getSolde(), compte.getSoldeMax(), compte.getIdMembre(), compte.getStatutCompte());
		}else {
			throw new CompteDejaCreeException("Compte Fond de caisse deja existant pour ce membre");
		}
	}
	
	@PostMapping("/comptes/roulement")
	public CompteRoulementDTO createCompteRoulement(@RequestBody RequestCreateRoulementDTO compte) throws MembreNotFoundException, CompteDejaCreeException {
		if(!compteService.findCompteRoulement(compte.getIdMembre())) {
			return compteService.saveCompteRoulement(
					compte.getSolde(), compte.getMontantMin(), compte.getIdMembre(), compte.getStatutCompte());
		}else {
			throw new CompteDejaCreeException("Compte roulement deja existant pour ce membre");
		}
	}
	
	@PostMapping("/comptes/collation")
	public CompteCollationDTO createCompteCollation(@RequestBody RequestCreateCollationDTO compte) throws MembreNotFoundException, CompteDejaCreeException {
		if(!compteService.findCompteCollation(compte.getIdMembre())) {
			return compteService.saveCompteCollation(
					compte.getSolde(), compte.getMontantCollation(), compte.getIdMembre(), compte.getStatutCompte());
		}else {
			throw new CompteDejaCreeException("Compte collation deja existant pour ce membre");
		}
	}
	
	@PostMapping("/comptes/debit")
	public DebitDTO debiter(@RequestBody DebitDTO debitDTO) throws CompteNotFoundException, SeanceNotFoundException {
		compteService.debit(debitDTO.getIdCompte(), debitDTO.getMontant(), debitDTO.getDescription(), debitDTO.getIdSeance());
		return debitDTO;
	}
	
	@PostMapping("/comptes/credit")
	public CreditDTO crediter(@RequestBody CreditDTO creditDTO) throws CompteNotFoundException, SeanceNotFoundException {
		compteService.credit(creditDTO.getIdCompte(), creditDTO.getMontant(), creditDTO.getDescription(), creditDTO.getIdSeance());
		return creditDTO;
	}
	
	@PostMapping("/comptes/transfert")
	public TransfertDTO transfert(@RequestBody TransfertDTO transfertDTO) throws CompteNotFoundException, SeanceNotFoundException, SoldeInsuffisantException {
		compteService.transfert(transfertDTO.getIdCompteEmetteur(), 
				transfertDTO.getIdCompteDestinataire(), transfertDTO.getMontant(), 
				transfertDTO.getDescription(), transfertDTO.getIdSeance());
		return transfertDTO;
	}
	
	@GetMapping("/comptes/historique/{id}")
	public HistoriqueCompteDTO getHistory(@PathVariable(name = "id") Long idCompte,
			@RequestParam(name = "page", defaultValue = "0") int page, 
			@RequestParam(name = "size", defaultValue = "5") int size) throws CompteNotFoundException {
		return compteService.getCompteHistory(idCompte, page, size);
	}
	
	@GetMapping("/comptes/soldes/{id}")
	public SoldesCompteMembreDTO getSoldesComptes(@PathVariable(name = "id") Long idMembre) throws MembreNotFoundException {
		return compteService.getSoldesComptesMembre(idMembre);
	}
	
	@GetMapping("/comptes/list")
	public List<CompteDTO> getAllCompte() {
		return compteService.listCompte();
	}
	
	@PostMapping("/comptes/fond/debits")
	public void debiterTousFond(@RequestBody OperationCommuneDTO operationCommuneDTO) throws CompteNotFoundException, SeanceNotFoundException {
		compteService.transactionTousLesComptesMemeType(
				operationCommuneDTO.getIdSeance(), 
				operationCommuneDTO.getMontant(), 
				operationCommuneDTO.getDescription(), 
				new Fond(), "DEBIT");
	}
	
	@PostMapping("/comptes/fond/credits")
	public void crediterTousFond(@RequestBody OperationCommuneDTO operationCommuneDTO) throws CompteNotFoundException, SeanceNotFoundException {
		compteService.transactionTousLesComptesMemeType(
				operationCommuneDTO.getIdSeance(), 
				operationCommuneDTO.getMontant(), 
				operationCommuneDTO.getDescription(), 
				new Fond(), "CREDIT");
	}
	
	@PostMapping("/comptes/roulement/debits")
	public void debiterTousRoulement(@RequestBody OperationCommuneDTO operationCommuneDTO) throws CompteNotFoundException, SeanceNotFoundException {
		compteService.transactionTousLesComptesMemeType(
				operationCommuneDTO.getIdSeance(), 
				operationCommuneDTO.getMontant(), 
				operationCommuneDTO.getDescription(), 
				new Roulement(), "DEBIT");
	}
	
	@PostMapping("/comptes/roulement/credits")
	public void crediterTousRoulement(@RequestBody OperationCommuneDTO operationCommuneDTO) throws CompteNotFoundException, SeanceNotFoundException {
		compteService.transactionTousLesComptesMemeType(
				operationCommuneDTO.getIdSeance(), 
				operationCommuneDTO.getMontant(), 
				operationCommuneDTO.getDescription(), 
				new Roulement(), "CREDIT");
	}
	
	@PostMapping("/comptes/collation/debits")
	public void debiterTousCollation(@RequestBody OperationCommuneDTO operationCommuneDTO) throws CompteNotFoundException, SeanceNotFoundException {
		compteService.transactionTousLesComptesMemeType(
				operationCommuneDTO.getIdSeance(), 
				operationCommuneDTO.getMontant(), 
				operationCommuneDTO.getDescription(), 
				new Collation(), "DEBIT");
	}
	
	@PostMapping("/comptes/collation/credits")
	public void crediterTousCollation(@RequestBody OperationCommuneDTO operationCommuneDTO) throws CompteNotFoundException, SeanceNotFoundException {
		compteService.transactionTousLesComptesMemeType(
				operationCommuneDTO.getIdSeance(), 
				operationCommuneDTO.getMontant(), 
				operationCommuneDTO.getDescription(), 
				new Collation(), "CREDIT");
	}
	
	@DeleteMapping("/comptes{id}")
	public void supprimerCompte(@PathVariable Long id) throws CompteNotFoundException {
		compteService.deleteCompte(id);
	}
	
	@PostMapping("/comptes/anuller/operation{id}")
	public void annulerOperation(@PathVariable String id) throws OperationNotFoundException, CompteNotFoundException {
		compteService.avoidOperation(id);
	}
	
	
	
	
	
}
