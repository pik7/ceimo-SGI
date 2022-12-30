package com.ceimo.gestion.service;

import java.util.List;

import com.ceimo.gestion.dtos.CompteCollationDTO;
import com.ceimo.gestion.dtos.CompteDTO;
import com.ceimo.gestion.dtos.CompteEpargneDTO;
import com.ceimo.gestion.dtos.CompteFondDTO;
import com.ceimo.gestion.dtos.CompteRoulementDTO;
import com.ceimo.gestion.dtos.HistoriqueCompteDTO;
import com.ceimo.gestion.dtos.SoldesCompteMembreDTO;
import com.ceimo.gestion.entity.compte.Compte;
import com.ceimo.gestion.entity.compte.enums.StatutCompte;
import com.ceimo.gestion.service.exceptions.CompteNotFoundException;
import com.ceimo.gestion.service.exceptions.MembreNotFoundException;
import com.ceimo.gestion.service.exceptions.OperationNotFoundException;
import com.ceimo.gestion.service.exceptions.SeanceNotFoundException;
import com.ceimo.gestion.service.exceptions.SoldeInsuffisantException;

public interface CompteService {
	
	CompteEpargneDTO saveCompteEpargne(double solde,  double tauxInteret, Long idMembre, StatutCompte statutCompte) 
			throws MembreNotFoundException;
	
	CompteFondDTO saveCompteFond(double solde,  double soldeMax, Long idMembre, StatutCompte statutCompte) 
			throws MembreNotFoundException;
	
	CompteCollationDTO saveCompteCollation(double solde,  double montantCollation, Long idMembre, StatutCompte statutCompte) 
			throws MembreNotFoundException;
	
	CompteRoulementDTO saveCompteRoulement(double solde,  double montantMinimum, Long idMembre, StatutCompte statutCompte) 
			throws MembreNotFoundException;
	
	CompteDTO getCompte(Long idCompte) throws CompteNotFoundException;
	
	List<CompteDTO> listCompte();
	
	void debit(Long idCompte, double montant, String description, Long idSeance) 
			throws CompteNotFoundException, SeanceNotFoundException;
	
	void credit(Long idCompte, double montant, String description, Long idSeance) 
			throws CompteNotFoundException, SeanceNotFoundException;
	
	void transfert(Long idCompteEmetteur, Long idCompteDestinataire, double montant, String description, Long idSeance) 
			throws CompteNotFoundException, SeanceNotFoundException, SoldeInsuffisantException;
	
	HistoriqueCompteDTO getCompteHistory(Long idCompte, int page, int size) throws CompteNotFoundException ;
	
	public SoldesCompteMembreDTO getSoldesComptesMembre(Long idMembre) throws MembreNotFoundException;
	
	public boolean findCompteEpargne(Long idMembre) throws MembreNotFoundException;
	public boolean findCompteFond(Long idMembre) throws MembreNotFoundException;
	public boolean findCompteRoulement(Long idMembre) throws MembreNotFoundException;
	public boolean findCompteCollation(Long idMembre) throws MembreNotFoundException;
	
	public void transactionTousLesComptesMemeType(Long idSeance, double montant, String description, Compte compte, String typeOperation) throws CompteNotFoundException, SeanceNotFoundException;
	public void createComptesByMembre(Long idMembre) throws MembreNotFoundException;
	
	public void deleteCompte(Long idCompte) throws CompteNotFoundException;
	public void avoidOperation(String idOperation) throws OperationNotFoundException, CompteNotFoundException;
}
