package com.ceimo.gestion.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceimo.gestion.dtos.DemissionDTO;
import com.ceimo.gestion.dtos.ElireDTO;
import com.ceimo.gestion.dtos.MembreBureauDTO;
import com.ceimo.gestion.dtos.MembreSimplifieDTO;
import com.ceimo.gestion.dtos.ResponsabiliteDTO;
import com.ceimo.gestion.entity.membre.Demission;
import com.ceimo.gestion.entity.membre.Elire;
import com.ceimo.gestion.entity.membre.Membre;
import com.ceimo.gestion.entity.membre.Poste;
import com.ceimo.gestion.entity.membre.Responsabilite;
import com.ceimo.gestion.entity.seance.Exercice;
import com.ceimo.gestion.mappers.MembreModuleMapper;
import com.ceimo.gestion.repository.membre.DemissionRepository;
import com.ceimo.gestion.repository.membre.ElireRepository;
import com.ceimo.gestion.repository.membre.MembreRepository;
import com.ceimo.gestion.repository.membre.ResponsabiliteRepository;
import com.ceimo.gestion.repository.seance.ExerciceRepository;
import com.ceimo.gestion.service.exceptions.DemissionDejaEnregistreException;
import com.ceimo.gestion.service.exceptions.DemissionNotFoundException;
import com.ceimo.gestion.service.exceptions.ExerciceNotFoundException;
import com.ceimo.gestion.service.exceptions.MembreDejeExistantException;
import com.ceimo.gestion.service.exceptions.MembreNotFoundException;
import com.ceimo.gestion.service.exceptions.PosteDejaOccupeException;
import com.ceimo.gestion.service.exceptions.ResponsabiliteDejeExistantException;
import com.ceimo.gestion.service.exceptions.ResponsabiliteNotFoundException;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class MembreServiceImpl implements MembreService{

	private MembreRepository membreRepository;
	private ResponsabiliteRepository responsabiliteRepository;
	private DemissionRepository demissionRepository;
	private ElireRepository elireRepository;
	private ExerciceRepository exerciceRepository;
	private MembreModuleMapper meMapper;

	
	public List<MembreSimplifieDTO> listMembres(){
		List<Membre> membres =  membreRepository.findAll();
		List<MembreSimplifieDTO> membreSimplifieDTOS = membres.stream()
				.map(membre ->meMapper.fromMembre(membre))
				.collect(Collectors.toList());
		return membreSimplifieDTOS;
		
	}
	
	public MembreSimplifieDTO getMembreById(Long idMembre) throws MembreNotFoundException {
		Membre membre = membreRepository.findById(idMembre).orElseThrow(
				() -> new MembreNotFoundException("Membre non trouvé"));
		return meMapper.fromMembre(membre);
	}
	
	public List<MembreSimplifieDTO> searchMembre(String mc) {
		List<Membre> membres = membreRepository.searchMembre(mc, mc);
		List<MembreSimplifieDTO> membreSimplifieDTOS = membres.stream()
				.map(membre ->meMapper.fromMembre(membre))
				.collect(Collectors.toList());
		return membreSimplifieDTOS;
	}
	
	public boolean isOnBureau(Membre membre, Long idExercice) {
		List<Elire> roles = membre.getMesRoles();
		boolean isInBureau = false;
		for(Elire role:roles) {
			if((role.getExercice().getIdExercice() == idExercice) && role.getResponsabilite().getPoste() != Poste.MEMBRE)
				isInBureau = true;
		}
		return isInBureau;
	}

	@Override
	public List<MembreBureauDTO> listMembresBureau(Long idExercice) {
		List<Membre> membres = membreRepository.findAll();
		List<Membre> membresBureau = membres. stream()
				.filter(membre -> isOnBureau(membre,idExercice))
				.collect(Collectors.toList());
		List<MembreBureauDTO> membreBureauDTOS = membresBureau.stream()
				.map(membre -> meMapper.fromMembreBureau(membre))
				.collect(Collectors.toList());
		return membreBureauDTOS;
	}

	@Override
	public List<DemissionDTO> getDemissionnaire() {
		List<Demission> demissions = demissionRepository.findAll();
		List<DemissionDTO> demissionDTOS = demissions.stream()
				.map(demission ->meMapper.fromDemission(demission))
				.collect(Collectors.toList());
		return demissionDTOS;
	}

	@Override
	public MembreSimplifieDTO saveMembre(MembreSimplifieDTO membreDTO) 
			throws MembreDejeExistantException {
		
		Membre membre = meMapper.fromMembreSimplifieDTO(membreDTO);
		Membre membreTest = membreRepository.findByNomMembreAndPrenomMembreAndDateNaissanceOrLogin(
				membre.getNomMembre(), membre.getPrenomMembre(), membre.getDateNaissance(), membre.getLogin());
		if(membreTest == null) {
			return meMapper.fromMembre(membreRepository.save(membre));
		}else {
			throw new MembreDejeExistantException("Le membre ou login deja existant");
		}
		
	}

	@Override
	public ResponsabiliteDTO saveResponsabilite(ResponsabiliteDTO responsabiliteDTO) 
			throws ResponsabiliteDejeExistantException {
		Responsabilite responsabilite = meMapper.fromResponsabiliteDTO(responsabiliteDTO);
		if(responsabiliteRepository.findByPoste(responsabilite.getPoste()) != null) {
			return meMapper.fromResponsabilite(responsabiliteRepository.save(responsabilite));
		}else {
			throw new ResponsabiliteDejeExistantException(""); 
		}
	}

	@Override
	public ElireDTO nommerMembre(Long idMembre, Long idExercice, Poste poste, Date dateNomination, int montantInscription) 
			throws MembreNotFoundException, ExerciceNotFoundException, PosteDejaOccupeException {
		
		Elire el = elireRepository.findByExerciceIdExerciceAndResponsabilitePoste(idExercice, poste);
		if(el != null) {
			throw new PosteDejaOccupeException("Le poste est deja occupé");
		}else{
			Membre membre = membreRepository.findById(idMembre).orElseThrow(
					() -> new MembreNotFoundException("Membre non trouvé dans le système"));
			Exercice exercice = exerciceRepository.findById(idExercice).orElseThrow(
					()-> new ExerciceNotFoundException("Exercice non trouvé dans le système"));
			Responsabilite resp = responsabiliteRepository.findByPoste(poste);
			Elire elire = new Elire();
			elire.setDateadhesion(dateNomination);
			elire.setMontantinscription(montantInscription);
			elire.setExercice(exercice);
			elire.setResponsabilite(resp);
			elire.setMembre(membre);
			elireRepository.save(elire);
			return meMapper.fromElire(elireRepository.save(elire));  
		}
		
		  
	}

	@Override
	public DemissionDTO demissionner(Long idMembre, Date datedemission, String lettre) throws MembreNotFoundException, DemissionDejaEnregistreException {
		Membre membre = membreRepository.findById(idMembre).orElseThrow(
				() -> new MembreNotFoundException("Membre non present dans le système")); 	
		if(demissionRepository.findByDemissionnaireIdMembre(idMembre) == null) {
			throw new DemissionDejaEnregistreException(membre.getNomMembre());
		}
		Demission demission = new Demission();
		demission.setDatedemission(datedemission);
		demission.setLettredemission(lettre);
		demission.setDemissionnaire(membre);
		return meMapper.fromDemission(demissionRepository.save(demission));
	}

	@Override
	public void deleteMembre(Long idMembre) throws MembreNotFoundException {
		Membre membre = membreRepository.findById(idMembre).orElseThrow(
				() -> new MembreNotFoundException("Membre non present dans le système")); 
		membreRepository.deleteById(idMembre);
	}

	@Override
	public void deleteResponsabilite(Long idResponsabilite) throws ResponsabiliteNotFoundException {
		Responsabilite responsabilite = responsabiliteRepository.findById(idResponsabilite).orElseThrow(
				()-> new ResponsabiliteNotFoundException(""));
		elireRepository.deleteByResponsabiliteIdresponsabilite(idResponsabilite);
		responsabiliteRepository.deleteById(idResponsabilite);
	}

	@Override
	public void deleteDemission(Long idDemission) throws DemissionNotFoundException {
		Demission demission = demissionRepository.findById(idDemission).orElseThrow(
				()-> new DemissionNotFoundException(""));
		demissionRepository.deleteById(idDemission);
		
	}

	@Override
	public void exclureDuBureau(Long idMembre, Long idExercice)
			throws MembreNotFoundException, ExerciceNotFoundException {
		Membre membre = membreRepository.findById(idMembre).orElseThrow(
				() -> new MembreNotFoundException("Membre non present dans le système"));
		Exercice exercice = exerciceRepository.findById(idExercice).orElseThrow(
				()-> new ExerciceNotFoundException("Exercice non trouvé dans le système"));
		if(isOnBureau(membre, idExercice)) {
			membre.getMesRoles().forEach(role ->{
				if(role.getResponsabilite().getPoste() != Poste.MEMBRE) {
					elireRepository.deleteByResponsabiliteIdresponsabiliteAndMembreIdMembreAndExerciceIdExercice(
							role.getResponsabilite().getIdresponsabilite(), idMembre, idExercice);
				}
			});
		}
		
	}
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * public Membre getMembreByNom(String nomMembre) { Membre membre =
	 * membreRepository.findByNomMembre(nomMembre);
	 * membre.getMesRoles().forEach(role ->{ role.getDateadhesion(); });
	 * 
	 * 
	 * 
	 * 
	 * return membre; }
	 * 
	 * // Liste des services offert pour les membres
	 * 
	 * public Iterable<Membre> findMembres(String motCle) { return
	 * membreRepository.searchMembre(motCle, motCle); }
	 * 
	 * 
	 * 
	 * public Membre saveMembre(Membre membre) { if(membre.getIdMembre()==null) {
	 * if(membreRepository.findByNomMembreAndPrenomMembreAndDateNaissance(membre.
	 * getNomMembre(), membre.getPrenomMembre(), membre.getDateNaissance()) == null)
	 * return membreRepository.save(membre); else throw new
	 * RuntimeException("Membre déja présent dans le système !"); }else { return
	 * membreRepository.save(membre); }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * public Responsabilite saveResponsability(Responsabilite responsabilite) {
	 * if(responsabilite.getIdresponsabilite()==null) {
	 * if(responsabiliteRepository.findByPoste(responsabilite.getPoste()) == null)
	 * return responsabiliteRepository.save(responsabilite); else throw new
	 * RuntimeException("Poste déja présent dans le système !"); }else { return
	 * responsabiliteRepository.save(responsabilite); } }
	 * 
	 * 
	 * 
	 * public Elire addMembreResponsability(Elire elire) {
	 * 
	 * return elireRepository.save(elire); }
	 * 
	 * public Demission demissioner(Demission demission) {
	 * if(!demission.getDemissionnaire().isDemissionnaire()) return
	 * demissionRepository.save(demission); else throw new
	 * RuntimeException("Membre déjà démissionnaire !"); }
	 * 
	 * public void deleteMembre(Long id) {
	 * 
	 * membreRepository.deleteById(id); }
	 * 
	 * public void deleteResponsability(Poste poste) {
	 * responsabiliteRepository.deleteByPoste(poste); }
	 * 
	 * public void deleteDemission(Long id) { demissionRepository.deleteById(id); }
	 * 
	 * public void removeMembreResponsability(Elire elire) {
	 * 
	 * elireRepository.deleteById(elire.getId()); }
	 * 
	 * public Iterable<Membre> getAllMembres() { Iterable<Membre> membres =
	 * membreRepository.findAll(); membres.forEach(membre->{
	 * membre.getMesRoles().forEach(role ->{ role.getResponsabilite().getPoste();
	 * }); }); return membres; }
	 * 
	 * public Iterable<Elire> getResponsabilityMembre() { List<Elire> elires =
	 * elireRepository.findAll(); elires.forEach(elire->{
	 * elire.getResponsabilite().getPoste(); elire.getMembre().getNomMembre(); });
	 * return elires; }
	 * 
	 * public List<Membre> getMembreDemissionnaire() { List<Membre> membres = new
	 * ArrayList<>(); List<Demission> demissionnaires =
	 * demissionRepository.findAll(); demissionnaires.forEach(demission ->{
	 * demission.getDemissionnaire().getIdMembre();
	 * membres.add(demission.getDemissionnaire()); }); return membres; }
	 */

}
