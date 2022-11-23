package com.ceimo.gestion.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceimo.gestion.dtos.CompteCollationDTO;
import com.ceimo.gestion.dtos.CompteDTO;
import com.ceimo.gestion.dtos.CompteEpargneDTO;
import com.ceimo.gestion.dtos.CompteFondDTO;
import com.ceimo.gestion.dtos.CompteRoulementDTO;
import com.ceimo.gestion.dtos.HistoriqueCompteDTO;
import com.ceimo.gestion.dtos.OperationDTO;
import com.ceimo.gestion.dtos.SoldesCompteMembreDTO;
import com.ceimo.gestion.entity.compte.Collation;
import com.ceimo.gestion.entity.compte.Compte;
import com.ceimo.gestion.entity.compte.Epargne;
import com.ceimo.gestion.entity.compte.Fond;
import com.ceimo.gestion.entity.compte.Operation;
import com.ceimo.gestion.entity.compte.Roulement;
import com.ceimo.gestion.entity.compte.enums.StatutCompte;
import com.ceimo.gestion.entity.compte.enums.TypeOperation;
import com.ceimo.gestion.entity.membre.Membre;
import com.ceimo.gestion.entity.seance.Seance;
import com.ceimo.gestion.mappers.MembreModuleMapper;
import com.ceimo.gestion.repository.compte.CompteRepository;
import com.ceimo.gestion.repository.compte.OperationRepository;
import com.ceimo.gestion.repository.membre.MembreRepository;
import com.ceimo.gestion.repository.seance.SeanceRepository;
import com.ceimo.gestion.service.exceptions.CompteNotFoundException;
import com.ceimo.gestion.service.exceptions.MembreNotFoundException;
import com.ceimo.gestion.service.exceptions.SeanceNotFoundException;
import com.ceimo.gestion.service.exceptions.SoldeInsuffisantException;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CompteServiceImpl implements CompteService {
	
	private CompteRepository compteRepository;
	private MembreRepository membreRepository;
	private OperationRepository operationRepository;
	private SeanceRepository seanceRepository;
	private MembreModuleMapper meMapper;
	
	
	@Override
	public CompteEpargneDTO saveCompteEpargne(double solde, double tauxInteret, Long idMembre,
			StatutCompte statutCompte) throws MembreNotFoundException {
			Membre membre = membreRepository.findById(idMembre)
					.orElseThrow(() ->new MembreNotFoundException("Membre non existant"));
			
			Epargne epargne = new Epargne();
			epargne.setDateCreation(new Date());
			epargne.setSolde(solde);
			epargne.setTauxInteret(tauxInteret);
			epargne.setStatutCompte(StatutCompte.ACTIF);
			epargne.setMembre(membre);
		return meMapper.fromCompteEpargne(compteRepository.save(epargne));
	}

	@Override
	public CompteFondDTO saveCompteFond(double solde, double soldeMax, Long idMembre, StatutCompte statutCompte) throws MembreNotFoundException {
		Membre membre = membreRepository.findById(idMembre)
				.orElseThrow(() ->new MembreNotFoundException("Membre non existant"));
		Fond fond = new Fond();
		fond.setDateCreation(new Date());
		fond.setSolde(solde);
		fond.setSoldeMax(soldeMax);
		fond.setStatutCompte(StatutCompte.ACTIF);
		fond.setMembre(membre);
	return meMapper.fromCompteFond(compteRepository.save(fond));
	}

	@Override
	public CompteCollationDTO saveCompteCollation(double solde, double montantCollation, Long idMembre,
			StatutCompte statutCompte) throws MembreNotFoundException {
		Membre membre = membreRepository.findById(idMembre)
				.orElseThrow(() ->new MembreNotFoundException("Membre non existant"));
		Collation coll = new Collation();
		coll.setDateCreation(new Date());
		coll.setSolde(solde);
		coll.setMontantCollation(montantCollation);
		coll.setStatutCompte(StatutCompte.ACTIF);
		coll.setMembre(membre);
		return meMapper.fromCompteCollation(compteRepository.save(coll));
	}

	@Override
	public CompteRoulementDTO saveCompteRoulement(double solde, double montantMinimum, Long idMembre,
			StatutCompte statutCompte) throws MembreNotFoundException {
		Membre membre = membreRepository.findById(idMembre)
				.orElseThrow(() ->new MembreNotFoundException("Membre non existant"));
		Roulement roul = new Roulement();
		roul.setDateCreation(new Date());
		roul.setSolde(solde);
		roul.setMontantMin(montantMinimum);
		roul.setStatutCompte(StatutCompte.ACTIF);
		roul.setMembre(membre);
		return meMapper.fromCompteRoulement(compteRepository.save(roul));
	}

	@Override
	public CompteDTO getCompte(Long idCompte) throws CompteNotFoundException {
		
		Compte compte = compteRepository.findById(idCompte).orElseThrow(()-> new CompteNotFoundException("Compte non existant"));
		if(compte instanceof Epargne) {
			Epargne epargne = (Epargne) compte;
			return meMapper.fromCompteEpargne(epargne);
		}else if(compte instanceof Fond) {
			Fond fond = (Fond) compte;
			return meMapper.fromCompteFond(fond);
		}else if(compte instanceof Roulement) {
			Roulement roul = (Roulement) compte;
			return meMapper.fromCompteRoulement(roul);
		}else {
			Collation coll = (Collation) compte;
			return meMapper.fromCompteCollation(coll);
		}
	}

	@Override
	public List<CompteDTO> listCompte() {
		List<Compte> comptes = compteRepository.findAll();
		List<CompteDTO> compteDTOs = comptes.stream().map(compte->{
			if(compte instanceof Epargne) {
				Epargne epargne = (Epargne) compte;
				return meMapper.fromCompteEpargne(epargne);
			}else if(compte instanceof Fond) {
				Fond fond = (Fond) compte;
				return meMapper.fromCompteFond(fond);
			}else if(compte instanceof Roulement) {
				Roulement roul = (Roulement) compte;
				return meMapper.fromCompteRoulement(roul);
			}else {
				Collation coll = (Collation) compte;
				return meMapper.fromCompteCollation(coll);
			}
		}).collect(Collectors.toList());
		return compteDTOs;
	}

	@Override
	public void debit(Long idCompte, double montant, String description, Long idSeance) throws CompteNotFoundException, SeanceNotFoundException {
		
		Compte compte = compteRepository.findById(idCompte).orElseThrow(()->new CompteNotFoundException("Compte non existant"));
		Seance seance = seanceRepository.findById(idSeance).orElseThrow(()-> new SeanceNotFoundException("Seance non existante"));
		compte.setSolde(compte.getSolde()-montant);
		Operation operation = new Operation();
		operation.setIdOperation(UUID.randomUUID().toString());
		operation.setCompte(compte);
		operation.setSeance(seance);
		operation.setDateOperation(new Date());
		operation.setDescription(description);
		operation.setMontant(montant);
		operation.setType(TypeOperation.DEBIT);
		operationRepository.save(operation);
		compteRepository.save(compte);
		
	}

	@Override
	public void credit(Long idCompte, double montant, String description, Long idSeance) throws CompteNotFoundException, SeanceNotFoundException {
		Compte compte = compteRepository.findById(idCompte).orElseThrow(()->new CompteNotFoundException("Compte non existant"));
		Seance seance = seanceRepository.findById(idSeance).orElseThrow(()-> new SeanceNotFoundException("Seance non existante"));
		compte.setSolde(compte.getSolde()+montant);
		Operation operation = new Operation();
		operation.setIdOperation(UUID.randomUUID().toString());
		operation.setCompte(compte);
		operation.setSeance(seance);
		operation.setDateOperation(new Date());
		operation.setDescription(description);
		operation.setMontant(montant);
		operation.setType(TypeOperation.CREDIT);
		operationRepository.save(operation);
		compteRepository.save(compte);
		
	}

	@Override
	public void transfert(Long idCompteEmetteur, Long idCompteDestinataire, double montant, String description, Long idSeance) throws CompteNotFoundException, SeanceNotFoundException, SoldeInsuffisantException {
		Compte compteEmetteur = compteRepository.findById(idCompteEmetteur).orElseThrow(()->new CompteNotFoundException("Compte non existant"));
		Compte compteDestinataire = compteRepository.findById(idCompteDestinataire).orElseThrow(()->new CompteNotFoundException("Compte non existant"));
		if(compteEmetteur.getSolde() >= montant) {
			debit(compteEmetteur.getIdCompte(), montant, description, idSeance);
			credit(compteDestinataire.getIdCompte(), montant, description, idSeance);
		}else {
			throw new SoldeInsuffisantException("Solde du compte emetteur insuffisant");
		}
		
	}

	@Override
	public HistoriqueCompteDTO getCompteHistory(Long idCompte, int page, int size) throws CompteNotFoundException {
		
		Compte compte = compteRepository.findById(idCompte)
				.orElseThrow(()-> new CompteNotFoundException("Compte non existant"));
		Page<Operation> operations = operationRepository
				.findByCompteIdCompteOrderByDateOperationDesc(idCompte,  PageRequest.of(page, size));
		
		HistoriqueCompteDTO historique = new HistoriqueCompteDTO();
		historique.setCurrentPage(page);
		historique.setPageSize(size);
		historique.setSolde(compte.getSolde());
		historique.setIdCompte(compte.getIdCompte());
		historique.setTotalPages(operations.getTotalPages());
		
		List<OperationDTO> operationDTOs = operations.getContent().stream()
				.map(op ->meMapper.fromOperation(op))
				.collect(Collectors.toList());
		historique.setOperationDTOs(operationDTOs);
		return historique;
	}

	public SoldesCompteMembreDTO getSoldesComptesMembre(Long idMembre) throws MembreNotFoundException {
		Membre membre = membreRepository.findById(idMembre)
				.orElseThrow(()-> new MembreNotFoundException("Membre non existant"));
		List<Compte> comptes = compteRepository.findByMembreIdMembre(membre.getIdMembre());
		List<CompteDTO> compteDTOs = comptes.stream().map(compte ->{
			if(compte instanceof Epargne) {
				Epargne epargne = (Epargne) compte;
				return meMapper.fromCompteEpargne(epargne);
			}else if(compte instanceof Fond) {
				Fond fond = (Fond) compte;
				return meMapper.fromCompteFond(fond);
			}else if(compte instanceof Roulement) {
				Roulement roul = (Roulement) compte;
				return meMapper.fromCompteRoulement(roul);
			}else {
				Collation coll = (Collation) compte;
				return meMapper.fromCompteCollation(coll);
			}
		}).collect(Collectors.toList());
		
		SoldesCompteMembreDTO soldesComptesDTO = new SoldesCompteMembreDTO();
		soldesComptesDTO.setIdMembre(membre.getIdMembre());
		soldesComptesDTO.setNomMembre(membre.getNomMembre());
		soldesComptesDTO.setPrenomMembre(membre.getPrenomMembre());
		soldesComptesDTO.setCompteDTOs(compteDTOs);
		return soldesComptesDTO;
	}
	
	public boolean findCompteEpargne(Long idMembre) throws MembreNotFoundException {
		Membre membre = membreRepository.findById(idMembre)
				.orElseThrow(()-> new MembreNotFoundException("Membre non existant"));
		List<Compte> comptes = compteRepository.findByMembreIdMembre(membre.getIdMembre());
		for(Compte cpt : comptes) {
			if(cpt instanceof Epargne) {
				return true;
			}
		}
		return false;
	}
	
	public boolean findCompteCollation(Long idMembre) throws MembreNotFoundException {
		Membre membre = membreRepository.findById(idMembre)
				.orElseThrow(()-> new MembreNotFoundException("Membre non existant"));
		List<Compte> comptes = compteRepository.findByMembreIdMembre(membre.getIdMembre());
		for(Compte cpt : comptes) {
			if(cpt instanceof Collation) {
				return true;
			}
		}
		return false;
	}
	
	public boolean findCompteFond(Long idMembre) throws MembreNotFoundException {
		Membre membre = membreRepository.findById(idMembre)
				.orElseThrow(()-> new MembreNotFoundException("Membre non existant"));
		List<Compte> comptes = compteRepository.findByMembreIdMembre(membre.getIdMembre());
		for(Compte cpt : comptes) {
			if(cpt instanceof Fond) {
				return true;
			}
		}
		return false;
	}
	
	public boolean findCompteRoulement(Long idMembre) throws MembreNotFoundException {
		Membre membre = membreRepository.findById(idMembre)
				.orElseThrow(()-> new MembreNotFoundException("Membre non existant"));
		List<Compte> comptes = compteRepository.findByMembreIdMembre(membre.getIdMembre());
		for(Compte cpt : comptes) {
			if(cpt instanceof Roulement) {
				return true;
			}
		}
		return false;
	}
	
	
	public void transactionTousLesComptesMemeType(Long idSeance, double montant, String description, Compte compte, String typeOperation) throws CompteNotFoundException, SeanceNotFoundException {
		List<Membre> membres = membreRepository.findByDemissionnaireOrderByNomMembre(false);
		for(Membre membre : membres) {
			List<Compte> comptes = compteRepository.findByMembreIdMembre(membre.getIdMembre());
			for(Compte cpte : comptes) {
				if(cpte.getClass().getName() == compte.getClass().getName() ) {
					if(typeOperation.equalsIgnoreCase("DEBIT")) {
						debit(cpte.getIdCompte(),montant, description,idSeance);
					}else {
						credit(cpte.getIdCompte(),montant, description,idSeance);
					}
				}
			}
		}
	}
	
	public void createComptesByMembre(Long idMembre) throws MembreNotFoundException {
		Membre membre = membreRepository.findById(idMembre)
				.orElseThrow(() ->new MembreNotFoundException("Membre non existant"));
		saveCompteEpargne(0, 11.5, membre.getIdMembre(),StatutCompte.ACTIF);
		saveCompteFond(0,	20000,membre.getIdMembre(),StatutCompte.ACTIF);
		saveCompteRoulement(0,1000,membre.getIdMembre(),StatutCompte.ACTIF);
		saveCompteCollation(0, 2000,membre.getIdMembre(),StatutCompte.ACTIF);
	}
	
	
	
}
