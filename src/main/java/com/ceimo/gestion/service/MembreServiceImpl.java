package com.ceimo.gestion.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceimo.gestion.dtos.DemissionDTO;
import com.ceimo.gestion.dtos.DemissionDTOLight;
import com.ceimo.gestion.dtos.ElireDTO;
import com.ceimo.gestion.dtos.ElireDTOLight;
import com.ceimo.gestion.dtos.InscriptionDTO;
import com.ceimo.gestion.dtos.MembreBureauDTO;
import com.ceimo.gestion.dtos.MembrePagineDTO;
import com.ceimo.gestion.dtos.MembreSimplifieDTO;
import com.ceimo.gestion.dtos.ResponsabiliteDTO;
import com.ceimo.gestion.entity.compte.enums.StatutCompte;
import com.ceimo.gestion.entity.membre.Demission;
import com.ceimo.gestion.entity.membre.Elire;
import com.ceimo.gestion.entity.membre.Inscription;
import com.ceimo.gestion.entity.membre.Membre;
import com.ceimo.gestion.entity.membre.Poste;
import com.ceimo.gestion.entity.membre.Responsabilite;
import com.ceimo.gestion.entity.seance.Exercice;
import com.ceimo.gestion.mappers.MembreModuleMapper;
import com.ceimo.gestion.repository.membre.DemissionRepository;
import com.ceimo.gestion.repository.membre.ElireRepository;
import com.ceimo.gestion.repository.membre.InscriptionRepository;
import com.ceimo.gestion.repository.membre.MembreRepository;
import com.ceimo.gestion.repository.membre.ResponsabiliteRepository;
import com.ceimo.gestion.repository.seance.ExerciceRepository;
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

@Service
@Transactional
@AllArgsConstructor
public class MembreServiceImpl implements MembreService {

	private MembreRepository membreRepository;
	private ResponsabiliteRepository responsabiliteRepository;
	private DemissionRepository demissionRepository;
	private ElireRepository elireRepository;
	private ExerciceRepository exerciceRepository;
	private InscriptionRepository inscriptionRepository;
	private MembreModuleMapper meMapper;
	private CompteService compteService;
	private Environment environment;

	public List<MembreSimplifieDTO> listMembres() {
		List<Membre> membres = membreRepository.findAll();
		List<MembreSimplifieDTO> membreSimplifieDTOS = membres.stream().map(membre -> meMapper.fromMembre(membre))
				.collect(Collectors.toList());
		return membreSimplifieDTOS;

	}
	
	@Override
	public MembrePagineDTO getAllMembrePagine(int page, int size) {
		Page<Membre> membres = membreRepository.findAll(PageRequest.of(page, size));
		
		
		
		MembrePagineDTO membrePagineDTO = new MembrePagineDTO();
		membrePagineDTO.setCurrentPage(page);
		membrePagineDTO.setPageSize(size);
		membrePagineDTO.setTotalPages(membres.getTotalPages());
		membrePagineDTO.setMembres(membres.getContent().stream()
				.map(membre ->meMapper.fromMembre(membre)).collect(Collectors.toList()));
		
		return membrePagineDTO;
	}
	
	public MembreSimplifieDTO getMembreById(Long idMembre) throws MembreNotFoundException {
		Membre membre = membreRepository.findById(idMembre)
				.orElseThrow(() -> new MembreNotFoundException("Membre non trouvé"));
		return meMapper.fromMembre(membre);
	}

	public List<MembreSimplifieDTO> searchMembre(String mc) {
		List<Membre> membres = membreRepository.searchMembre(mc, mc);
		List<MembreSimplifieDTO> membreSimplifieDTOS = membres.stream().map(membre -> meMapper.fromMembre(membre))
				.collect(Collectors.toList());
		return membreSimplifieDTOS;
	}

	public boolean isOnBureau(Membre membre, Long idExercice) {
		List<Elire> roles = membre.getMesRoles();
		boolean isInBureau = false;
		for (Elire role : roles) {
			if ((role.getExercice().getIdExercice() == idExercice) && role.getResponsabilite().getPoste() != "MEMBRE")
				isInBureau = true;
		}
		return isInBureau;
	}

	@Override
	public List<MembreBureauDTO> listMembresBureau(Long idExercice) {
		List<Membre> membres = membreRepository.findAll();
		List<Membre> membresBureau = membres.stream().filter(membre -> isOnBureau(membre, idExercice))
				.collect(Collectors.toList());
		List<MembreBureauDTO> membreBureauDTOS = membresBureau.stream().map(membre -> meMapper.fromMembreBureau(membre))
				.collect(Collectors.toList());
		return membreBureauDTOS;
	}

	@Override
	public List<DemissionDTO> getDemissionnaire() {
		List<Demission> demissions = demissionRepository.findAll();
		List<DemissionDTO> demissionDTOS = demissions.stream().map(demission -> meMapper.fromDemission(demission))
				.collect(Collectors.toList());
		return demissionDTOS;
	}

	@Override
	public MembreSimplifieDTO saveMembre(MembreSimplifieDTO membreDTO) throws MembreDejeExistantException, MembreNotFoundException {
		Membre membre = meMapper.fromMembreSimplifieDTO(membreDTO);
		membre.setNomMembre(membre.getNomMembre().toUpperCase());
		if(membre.getPrenomMembre() != null) {
			membre.setPrenomMembre(membre.getPrenomMembre().toUpperCase());
		}
		if (membreDTO.getIdMembre() == null) {
			Membre membreTest = membreRepository.findByNomMembreAndPrenomMembreAndDateNaissanceOrLogin(
					membre.getNomMembre(), membre.getPrenomMembre(), membre.getDateNaissance(), membre.getLogin());
			if (membreTest == null) {
				membre = membreRepository.save(membre);
				compteService.createComptesByMembre(membre.getIdMembre());
				return meMapper.fromMembre(membre);
			} else {
				throw new MembreDejeExistantException("Le membre ou login deja existant");
			}
		} else {
			return meMapper.fromMembre(membreRepository.save(membre));
		}

	}
	

	@Override
	public ResponsabiliteDTO saveResponsabilite(ResponsabiliteDTO responsabiliteDTO)
			throws ResponsabiliteDejeExistantException {
		Responsabilite responsabilite = meMapper.fromResponsabiliteDTO(responsabiliteDTO);
		if (responsabilite.getIdresponsabilite() == null) {
			if (responsabiliteRepository.findByPoste(responsabilite.getPoste()) != null) {
				return meMapper.fromResponsabilite(responsabiliteRepository.save(responsabilite));
			} else {
				throw new ResponsabiliteDejeExistantException("");
			}
		} else {
			return meMapper.fromResponsabilite(responsabiliteRepository.save(responsabilite));
		}
	}

	@Override
	public ElireDTO nommerMembre(ElireDTOLight elireDTOLight)
			throws MembreNotFoundException, ExerciceNotFoundException, PosteDejaOccupeException {

		Elire el = elireRepository.findByExerciceIdExerciceAndResponsabilitePoste(elireDTOLight.getIdExercice(),
				elireDTOLight.getPoste());
		if (el != null) {
			throw new PosteDejaOccupeException("Le poste est deja occupé");
		} else {
			Membre membre = membreRepository.findById(elireDTOLight.getIdMembre())
					.orElseThrow(() -> new MembreNotFoundException("Membre non trouvé dans le système"));
			Exercice exercice = exerciceRepository.findById(elireDTOLight.getIdExercice())
					.orElseThrow(() -> new ExerciceNotFoundException("Exercice non trouvé dans le système"));
			Responsabilite resp = responsabiliteRepository.findByPoste(elireDTOLight.getPoste());
			Elire elire = new Elire();
			elire.setDateadhesion(elireDTOLight.getDateNomination());
			elire.setExercice(exercice);
			elire.setResponsabilite(resp);
			elire.setMembre(membre);
			elireRepository.save(elire);
			return meMapper.fromElire(elireRepository.save(elire));
		}

	}

	@Override
	public DemissionDTO demissionner(DemissionDTOLight demissionDTOLight)
			throws MembreNotFoundException, DemissionDejaEnregistreException {
		Membre membre = membreRepository.findById(demissionDTOLight.getIdMembre())
				.orElseThrow(() -> new MembreNotFoundException("Membre non present dans le système"));
		if (demissionRepository.findByDemissionnaireIdMembre(demissionDTOLight.getIdMembre()) == null) {
			throw new DemissionDejaEnregistreException(membre.getNomMembre());
		}
		Demission demission = new Demission();
		demission.setDatedemission(demissionDTOLight.getDatedemission());
		demission.setLettredemission(demissionDTOLight.getLettredemission());
		demission.setDemissionnaire(membre);
		return meMapper.fromDemission(demissionRepository.save(demission));
	}

	@Override
	public void deleteMembre(Long idMembre) throws MembreNotFoundException {
		Membre membre = membreRepository.findById(idMembre)
				.orElseThrow(() -> new MembreNotFoundException("Membre non present dans le système"));
		membreRepository.deleteById(idMembre);
	}

	@Override
	public void deleteResponsabilite(Long idResponsabilite) throws ResponsabiliteNotFoundException {
		Responsabilite responsabilite = responsabiliteRepository.findById(idResponsabilite)
				.orElseThrow(() -> new ResponsabiliteNotFoundException(""));
		elireRepository.deleteByResponsabiliteIdresponsabilite(idResponsabilite);
		responsabiliteRepository.deleteById(idResponsabilite);
	}

	@Override
	public void deleteDemission(Long idDemission) throws DemissionNotFoundException {
		Demission demission = demissionRepository.findById(idDemission)
				.orElseThrow(() -> new DemissionNotFoundException(""));
		demissionRepository.deleteById(idDemission);

	}

	@Override
	public void exclureDuBureau(Long idMembre) throws MembreNotFoundException, ExerciceNotFoundException {
		Membre membre = membreRepository.findById(idMembre)
				.orElseThrow(() -> new MembreNotFoundException("Membre non present dans le système"));
		Exercice exercice = exerciceRepository.findByStatut(true);
		if (exercice == null)
			throw new ExerciceNotFoundException("Aucun Exercice en cours ");
		else {

			if (isOnBureau(membre, exercice.getIdExercice())) {
				membre.getMesRoles().forEach(role -> {
					if (role.getResponsabilite().getPoste() != "MEMBRE") {
						elireRepository.deleteByResponsabiliteIdresponsabiliteAndMembreIdMembreAndExerciceIdExercice(
								role.getResponsabilite().getIdresponsabilite(), idMembre, exercice.getIdExercice());
					}
				});
			}
		}

	}

	@Override
	public List<ResponsabiliteDTO> getResponsabilite() {
		List<Responsabilite> responsabilites = responsabiliteRepository.findAll();
		List<ResponsabiliteDTO> responsabiliteDTOS = responsabilites.stream()
				.map(responsabilite -> meMapper.fromResponsabilite(responsabilite)).collect(Collectors.toList());
		return responsabiliteDTOS;

	}

	
	@Override
	public InscriptionDTO inscrireMembre(Long idMembre, Long idExercice, int montant)
			throws MembreNotFoundException, ExerciceNotFoundException, InscriptionDejaExixtanteException {
		Membre membre = membreRepository.findById(idMembre)
				.orElseThrow(() -> new MembreNotFoundException("Membre non present dans le système"));

		Exercice exercice = exerciceRepository.findById(idExercice)
				.orElseThrow(() -> new ExerciceNotFoundException("Exercice non trouvé dans le système"));

		Inscription inscription = inscriptionRepository.findByMembreIdMembreAndExerciceIdExercice(idMembre, idExercice);
		if (inscription != null) {
			Inscription insc = new Inscription();
			insc.setMontant(montant);
			insc.setMembre(membre);
			insc.setExercice(exercice);
			return meMapper.fromInscription(inscriptionRepository.save(insc));
		}else {
			throw new InscriptionDejaExixtanteException("Ce membre est déjà inscrit pour cette exercice");
		}

	}

	@Override
	public void deleteInscriptionMembre(Long idMembre, Long idExercice) {
		
		Inscription inscription = inscriptionRepository.findByMembreIdMembreAndExerciceIdExercice(idMembre, idExercice);
		if(inscription != null) {
			inscriptionRepository.deleteById(inscription.getIdInscription());
		}
		
	}
	
}
