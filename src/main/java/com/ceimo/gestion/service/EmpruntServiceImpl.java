package com.ceimo.gestion.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceimo.gestion.dtos.CompteDTO;
import com.ceimo.gestion.dtos.CompteEpargneDTO;
import com.ceimo.gestion.dtos.EmpruntDTO;
import com.ceimo.gestion.dtos.SoldesCompteMembreDTO;
import com.ceimo.gestion.entity.emprunt.Emprunt;
import com.ceimo.gestion.entity.membre.Membre;
import com.ceimo.gestion.entity.membre.StatutGeo;
import com.ceimo.gestion.mappers.MapperModule;
import com.ceimo.gestion.repository.compte.CompteRepository;
import com.ceimo.gestion.repository.emprunt.EmpruntRepository;
import com.ceimo.gestion.repository.membre.MembreRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class EmpruntServiceImpl implements EmpruntService {

	private EmpruntRepository empruntRepository;
	private MembreRepository membreRepository;
	private CompteRepository compteRepository;
	private CompteService compteService;
	private MapperModule meMapper;
	
	/*public EmpruntDTO saveEmprunt(EmpruntDTO empruntDTO ) throws Exception {
		Emprunt emprunt = meMapper.fromEmpruntDTO(empruntDTO);
		double solde = 0;
		List<Membre> membres = membreRepository.findByDemissionnaireOrderByNomMembre(false);
		for(Membre m : membres) {
			SoldesCompteMembreDTO compte  = compteService.getSoldesComptesMembre(m.getIdMembre());
			for(CompteDTO cdto : compte.getCompteDTOs()) {
				if(cdto instanceof CompteEpargneDTO) {
					solde+=((CompteEpargneDTO) cdto).getSolde();
				}
			}
		}
	
		
		empruntRepository.save(emprunt);
		
	}*/
	
	
}
