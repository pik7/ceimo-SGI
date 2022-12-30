package com.ceimo.gestion.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceimo.gestion.dtos.ExerciceDTO;
import com.ceimo.gestion.dtos.MembreSimplifieDTO;
import com.ceimo.gestion.dtos.SeanceFullDTO;
import com.ceimo.gestion.dtos.SeanceSimpleDTO;
import com.ceimo.gestion.entity.membre.Inscription;
import com.ceimo.gestion.entity.membre.Membre;
import com.ceimo.gestion.entity.seance.Exercice;
import com.ceimo.gestion.entity.seance.Seance;
import com.ceimo.gestion.mappers.MapperModule;
import com.ceimo.gestion.repository.membre.InscriptionRepository;
import com.ceimo.gestion.repository.membre.MembreRepository;
import com.ceimo.gestion.repository.seance.ExerciceRepository;
import com.ceimo.gestion.repository.seance.SeanceRepository;
import com.ceimo.gestion.service.exceptions.MembreNotFoundException;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class SeanceServiceImpl implements SeanceService {
	
	private SeanceRepository seanceRepository;
	private ExerciceRepository exerciceRepository;
	private MembreRepository membreRepository;
	private InscriptionRepository inscriptionRepository;
	private MapperModule meMapper;
	
	@Override
	public  List<SeanceSimpleDTO> getSeances(Long idExercice) {
		List<Seance> seances = seanceRepository.findByExerciceIdExercice(idExercice);
		return seances.stream()
				.map(seance ->meMapper.fromSeance(seance))
				.collect(Collectors.toList());
	}
	
	@Override
	public SeanceSimpleDTO getSeance(Long idSeance) throws Exception {
		Seance seance = seanceRepository.findById(idSeance).orElseThrow(()-> new Exception("Seance non trouvé"));
		return meMapper.fromSeance(seance);
	}
	
	@Override
	public void appelSeance(Long idMembre, Long idSeance) throws Exception {
		Membre membre = membreRepository.findById(idMembre).orElseThrow(() -> new MembreNotFoundException(""));
		Seance seance =  seanceRepository.findById(idSeance).orElseThrow(()-> new Exception("Seance non trouvé"));
		List<Membre> presents = seance.getListesdespresents();
		presents.add(membre);
		seance.setListesdespresents(presents);
	}
	
	@Override
	
	public SeanceSimpleDTO saveSeance(SeanceSimpleDTO seanceDTO) throws Exception {
		Seance seance = meMapper.fromSeanceSimpleDTO(seanceDTO);
		if (seanceDTO.getIdSeance() == null) {
			Seance s = seanceRepository.findByDateSeance(seanceDTO.getDateSeance());
			if (s != null)
				throw new Exception("Séance deja existante");
		}
		return meMapper.fromSeance(seanceRepository.save(seance));

	}
	 
	
	
	@Override
	public SeanceFullDTO getListeDesPresents(Long idSeance) throws Exception {
		Seance seance =  seanceRepository.findById(idSeance).orElseThrow(()-> new Exception("Seance non trouvé"));
		return meMapper.fromFullSeance(seance);
	}
	
	@Override
	public List<MembreSimplifieDTO> listerAbsents(Long idSeance) throws Exception {
		boolean dedans;
		Seance seance =  seanceRepository.findById(idSeance).orElseThrow(()-> new Exception("Seance non trouvé"));
		List<Inscription> inscrits = inscriptionRepository.findByExerciceIdExercice(seance.getExercice().getIdExercice());
		List<Membre> membreAbsents = new ArrayList<>();
		for (Inscription ins : inscrits) {
			dedans = false;
			for(Membre present : seance.getListesdespresents()) {
				if(present.equals(ins.getMembre()))  dedans = true;
			}
			if(dedans) membreAbsents.add(ins.getMembre());
		}
		return membreAbsents.stream()
				.map(mbr -> meMapper.fromMembre(mbr))
				.collect(Collectors.toList());
	}
	
	@Override
	public void deleteSeance(Long idSeance) throws Exception {
		Seance seance =  seanceRepository.findById(idSeance).orElseThrow(()-> new Exception("Seance non trouvé"));
		seanceRepository.deleteById(idSeance);
	}
	
	@Override
	public ExerciceDTO saveExercice(ExerciceDTO exerciceDTO) throws Exception {
		
		Exercice exo = meMapper.fromExerciceDTO(exerciceDTO);
		exo.setStatut(true);
		if(exo.getIdExercice() == null) {
			Exercice exercice = exerciceRepository.findByDateDebutExerciceAndDateFinExercice(exo.getDateDebutExercice(), exo.getDateFinExercice());
			if(exercice!= null)
				throw new Exception("Exercie deja créé");
		}
		return meMapper.fromExercice(exerciceRepository.save(exo));
	}
	
	@Override
	public void deleteExercice(Long idExercice) throws Exception {
		Exercice exercice =  exerciceRepository.findById(idExercice).orElseThrow(()-> new Exception("Exercice non trouvé"));
		exerciceRepository.deleteById(idExercice);
	}
	
	@Override
	public ExerciceDTO getExercice(Long idExercice) throws Exception {
		Exercice exercice = exerciceRepository.findById(idExercice).orElseThrow(()-> new Exception("Exercice non trouvé"));
		return meMapper.fromExercice(exercice);
	}
}
