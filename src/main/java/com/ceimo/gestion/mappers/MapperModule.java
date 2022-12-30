package com.ceimo.gestion.mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceimo.gestion.dtos.CompteCollationDTO;
import com.ceimo.gestion.dtos.CompteEpargneDTO;
import com.ceimo.gestion.dtos.CompteFondDTO;
import com.ceimo.gestion.dtos.CompteRoulementDTO;
import com.ceimo.gestion.dtos.ConstatDTO;
import com.ceimo.gestion.dtos.ConstatFullDTO;
import com.ceimo.gestion.dtos.ContributionDTO;
import com.ceimo.gestion.dtos.ContributionFullDTO;
import com.ceimo.gestion.dtos.DemissionDTO;
import com.ceimo.gestion.dtos.ElireDTO;
import com.ceimo.gestion.dtos.EmpruntDTO;
import com.ceimo.gestion.dtos.ExerciceDTO;
import com.ceimo.gestion.dtos.InscriptionDTO;
import com.ceimo.gestion.dtos.InscrireDTO;
import com.ceimo.gestion.dtos.MembreBureauDTO;
import com.ceimo.gestion.dtos.MembreSimplifieDTO;
import com.ceimo.gestion.dtos.OperationDTO;
import com.ceimo.gestion.dtos.ResponsabiliteDTO;
import com.ceimo.gestion.dtos.SanctionDTO;
import com.ceimo.gestion.dtos.SeanceFullDTO;
import com.ceimo.gestion.dtos.SeanceSimpleDTO;
import com.ceimo.gestion.dtos.TontineDTO;
import com.ceimo.gestion.dtos.VersementSanctionDTO;
import com.ceimo.gestion.entity.compte.Collation;
import com.ceimo.gestion.entity.compte.Epargne;
import com.ceimo.gestion.entity.compte.Fond;
import com.ceimo.gestion.entity.compte.Operation;
import com.ceimo.gestion.entity.compte.Roulement;
import com.ceimo.gestion.entity.discipline.Constat;
import com.ceimo.gestion.entity.discipline.Sanction;
import com.ceimo.gestion.entity.discipline.VersementSanction;
import com.ceimo.gestion.entity.emprunt.Emprunt;
import com.ceimo.gestion.entity.membre.Demission;
import com.ceimo.gestion.entity.membre.Elire;
import com.ceimo.gestion.entity.membre.Inscription;
import com.ceimo.gestion.entity.membre.Membre;
import com.ceimo.gestion.entity.membre.Responsabilite;
import com.ceimo.gestion.entity.seance.Exercice;
import com.ceimo.gestion.entity.seance.Seance;
import com.ceimo.gestion.entity.tontine.Contribution;
import com.ceimo.gestion.entity.tontine.Inscrire;
import com.ceimo.gestion.entity.tontine.Tontine;
import com.ceimo.gestion.repository.discipline.ConstatRepository;
import com.ceimo.gestion.repository.discipline.SanctionRepository;
import com.ceimo.gestion.repository.discipline.VersementSanctionRepository;
import com.ceimo.gestion.repository.membre.MembreRepository;
import com.ceimo.gestion.repository.seance.ExerciceRepository;
import com.ceimo.gestion.repository.seance.SeanceRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class MapperModule {
	
	private MembreRepository membreRepository;
	private ExerciceRepository exerciceRepository;
	private ConstatRepository constatRepository; 
	private SanctionRepository sanctionRepository;
	private SeanceRepository seanceRepository;
	private VersementSanctionRepository versementSanctionRepository;
	

	//****************** Mapping de Membre ***********************************
	public MembreSimplifieDTO fromMembre(Membre membre) {

		MembreSimplifieDTO membreSimplifieDTO = new MembreSimplifieDTO();
		BeanUtils.copyProperties(membre, membreSimplifieDTO);
		return membreSimplifieDTO;
	}

	public Membre fromMembreSimplifieDTO(MembreSimplifieDTO membreSimplifieDTO) {

		Membre membre = new Membre();
		BeanUtils.copyProperties(membreSimplifieDTO, membre);
		return membre;
	}
	
	//****************** Fin Mapping de Membre ***********************************
	
	//****************** Mapping de Responsabilite ***********************************
	
	public ResponsabiliteDTO fromResponsabilite(Responsabilite responsabilite) {
		ResponsabiliteDTO responsabiliteDTO = new ResponsabiliteDTO();
		BeanUtils.copyProperties(responsabilite, responsabiliteDTO);
		return responsabiliteDTO;
	}
	
	public Responsabilite fromResponsabiliteDTO(ResponsabiliteDTO responsabiliteDTO) {
		Responsabilite responsabilite = new Responsabilite();
		BeanUtils.copyProperties(responsabiliteDTO, responsabilite);
		return responsabilite;
	}
	
	//****************** Fin Mapping de Responsabilite ***********************************
	
	//****************** Mapping de Exercice ***********************************
	
	public ExerciceDTO fromExercice(Exercice exercice) {
		ExerciceDTO exerciceDTO = new ExerciceDTO();
		BeanUtils.copyProperties(exercice, exerciceDTO);
		return exerciceDTO;
	}
	
	public Exercice fromExerciceDTO(ExerciceDTO exerciceDTO) {
		Exercice exercice = new Exercice();
		BeanUtils.copyProperties(exerciceDTO, exercice);
		return exercice;
	}
	
	//****************** Fin Mapping de Exercice ***********************************
	
	//****************** Mapping de Elire ***********************************
	
	public ElireDTO fromElire(Elire elire) {
		ElireDTO elireDTO = new ElireDTO();
		BeanUtils.copyProperties(elire, elireDTO);
		elireDTO.setMembre(fromMembre(elire.getMembre()));
		elireDTO.setResponsabilite(fromResponsabilite(elire.getResponsabilite()));
		elireDTO.setExercice(fromExercice(elire.getExercice()));
		return elireDTO;
	}
	
	public Elire fromElireDTO(ElireDTO elireDTO) {
		Elire elire = new Elire();
		BeanUtils.copyProperties(elireDTO, elire);
		elire.setMembre(fromMembreSimplifieDTO(elireDTO.getMembre()));
		elire.setResponsabilite(fromResponsabiliteDTO(elireDTO.getResponsabilite()));
		elire.setExercice(fromExerciceDTO(elireDTO.getExercice()));
		return elire;
	}
	
	//****************** Fin Mapping de Exercice ***********************************
	
	//****************** Mapping de Demission ***********************************
	
	public DemissionDTO fromDemission(Demission demission) {
		DemissionDTO demissionDTO = new DemissionDTO();
		BeanUtils.copyProperties(demission, demissionDTO);
		demissionDTO.setDemissionnaire(fromMembre(demission.getDemissionnaire()));
		return demissionDTO;
	}
	
	public Demission fromDemissionDTO(DemissionDTO demissionDTO) {
		Demission demission = new Demission();
		BeanUtils.copyProperties(demissionDTO, demission);
		demission.setDemissionnaire(fromMembreSimplifieDTO(demissionDTO.getDemissionnaire()));
		return demission;
	}
	
	//****************** Fin Mapping de Demission ***********************************
	
	//****************** Mapping de Membre Bureau ***********************************
	
	public MembreBureauDTO fromMembreBureau(Membre membre) {
		MembreBureauDTO membreBureauDTO = new MembreBureauDTO();
		BeanUtils.copyProperties(membre, membreBureauDTO);
		List<Responsabilite> responsabilites = new ArrayList<Responsabilite>();
		membre.getMesRoles().forEach(role ->{ responsabilites.add(role.getResponsabilite()); });
		List<ResponsabiliteDTO> responsabiliteDTOs = responsabilites.stream().map(resp ->fromResponsabilite(resp)).collect(Collectors.toList());
		membreBureauDTO.setPostes(responsabiliteDTOs);
		return membreBureauDTO;
	}
	
	public InscriptionDTO fromInscription(Inscription inscription) {
		InscriptionDTO inscriptionDTO = new InscriptionDTO();
		BeanUtils.copyProperties(inscription, inscriptionDTO);
		inscriptionDTO.setMembre(fromMembre(inscription.getMembre()));	
		inscriptionDTO.setExercice(fromExercice(inscription.getExercice()));
		return inscriptionDTO;
	}
	
	public Inscription fromInscriptionDTO(InscriptionDTO inscriptionDTO) {
		Inscription inscription = new Inscription();
		BeanUtils.copyProperties(inscriptionDTO, inscription);
		inscription.setMembre(fromMembreSimplifieDTO(inscriptionDTO.getMembre()));	
		inscription.setExercice(fromExerciceDTO(inscriptionDTO.getExercice()));
		return inscription;
	}
	
	
	public CompteEpargneDTO fromCompteEpargne(Epargne epargne) {
		
		CompteEpargneDTO compteEpargneDTO = new CompteEpargneDTO();
		BeanUtils.copyProperties(epargne, compteEpargneDTO);
		compteEpargneDTO.setMembre(fromMembre(epargne.getMembre()));
		compteEpargneDTO.setType(epargne.getClass().getSimpleName());
		return compteEpargneDTO;
	}
	
	public Epargne fromCompteEpargneDTO(CompteEpargneDTO compteEpargneDTO) {
		
		Epargne epargne = new Epargne();
		BeanUtils.copyProperties(compteEpargneDTO, epargne);
		epargne.setMembre(fromMembreSimplifieDTO(compteEpargneDTO.getMembre()));
		return epargne;
	}
	
	public CompteFondDTO fromCompteFond(Fond fond) {
		
		CompteFondDTO compteFondDTO = new CompteFondDTO();
		BeanUtils.copyProperties(fond, compteFondDTO);
		compteFondDTO.setMembre(fromMembre(fond.getMembre()));
		compteFondDTO.setType(fond.getClass().getSimpleName());
		return compteFondDTO;
	}
	
	public Fond fromCompteFondDTO(CompteFondDTO compteFondDTO) {
		
		Fond fond = new Fond();
		BeanUtils.copyProperties(compteFondDTO, fond);
		fond.setMembre(fromMembreSimplifieDTO(compteFondDTO.getMembre()));
		return fond;
	}
	
	
	public CompteCollationDTO fromCompteCollation(Collation collation) {
		
		CompteCollationDTO compteCollationDTO = new CompteCollationDTO();
		BeanUtils.copyProperties(collation, compteCollationDTO);
		compteCollationDTO.setMembre(fromMembre(collation.getMembre()));
		compteCollationDTO.setType(collation.getClass().getSimpleName());
		return compteCollationDTO;
	}
	
	public Collation fromCompteCollationDTO(CompteCollationDTO compteCollationDTO) {
		
		Collation collation = new Collation();
		BeanUtils.copyProperties(compteCollationDTO, collation);
		collation.setMembre(fromMembreSimplifieDTO(compteCollationDTO.getMembre()));
		return collation;
	}
	
	public CompteRoulementDTO fromCompteRoulement(Roulement roulement) {
		
		CompteRoulementDTO compteRoulementDTO = new CompteRoulementDTO();
		BeanUtils.copyProperties(roulement, compteRoulementDTO);
		compteRoulementDTO.setMembre(fromMembre(roulement.getMembre()));
		compteRoulementDTO.setType(roulement.getClass().getSimpleName());
		return compteRoulementDTO;
	}
	
	public Roulement fromCompteRoulementDTO(CompteRoulementDTO compteRoulementDTO) {
		
		Roulement roulement = new Roulement();
		BeanUtils.copyProperties(compteRoulementDTO, roulement);
		roulement.setMembre(fromMembreSimplifieDTO(compteRoulementDTO.getMembre()));
		return roulement;
	}
	
	
	public OperationDTO fromOperation(Operation operation) {
		OperationDTO operationDTO = new OperationDTO();
		BeanUtils.copyProperties(operation, operationDTO);
		return operationDTO;
	}
	
	public Operation fromOperationDTO(OperationDTO operationDTO) {
		Operation operation = new Operation();
		BeanUtils.copyProperties(operationDTO, operation);
		return operation;
	}
	
	
	public Tontine fromTontineDTO(TontineDTO tontineDTO) {
		Tontine tontine = new Tontine();
		BeanUtils.copyProperties(tontineDTO, tontine);
		return tontine;
	}
	
	public TontineDTO fromTontine(Tontine tontine) {
		TontineDTO tontineDTO = new TontineDTO();
		BeanUtils.copyProperties(tontine, tontineDTO);
		return tontineDTO;
	}
	
	
	public ContributionDTO fromContribution(Contribution contribution) {
		ContributionDTO contributionDTO = new ContributionDTO();
		BeanUtils.copyProperties(contribution, contributionDTO);
		contributionDTO.setIdMembre(contribution.getMembre().getIdMembre());
		contributionDTO.setIdSeance(contribution.getSeance().getIdSeance());
		contributionDTO.setIdTontine(contribution.getTontine().getIdTontine());
		return contributionDTO;
	}
	
	public ContributionFullDTO fromContributionFull(Contribution contribution) {
		ContributionFullDTO contributionFullDTO = new ContributionFullDTO();
		BeanUtils.copyProperties(contribution, contributionFullDTO);
		contributionFullDTO.setMembre(fromMembre(contribution.getMembre()));
		contributionFullDTO.setTontine(fromTontine(contribution.getTontine()));
		contributionFullDTO.setIdSeance(contribution.getSeance().getIdSeance());
		return contributionFullDTO;
	}
	
	
	public InscrireDTO fromInscrire(Inscrire inscrire) {
		InscrireDTO inscrireDTO = new InscrireDTO();
		BeanUtils.copyProperties(inscrire, inscrireDTO);
		inscrireDTO.setMembre(fromMembre(inscrire.getMembre()));
		inscrireDTO.setIdTontine(inscrire.getTontine().getIdTontine());
		return inscrireDTO;
	}
	
	
	public Emprunt fromEmpruntDTO(EmpruntDTO empruntDTO) throws Exception { 
		Emprunt emprunt = new Emprunt();
		BeanUtils.copyProperties(empruntDTO, emprunt);
		Membre membre = membreRepository.findById(empruntDTO.getIdEmprunteur()).orElseThrow(()-> new Exception(""));
		emprunt.setEmprunteur(membre);
		List<Membre> lesAvalistes = new ArrayList<Membre>();
		for(Long idAval : empruntDTO.getIdAvalistes()) {
			Membre unAvaliste = membreRepository.findById(idAval).orElseThrow(()-> new Exception(""));
			lesAvalistes.add(unAvaliste);
		}
		emprunt.setAvalistes(lesAvalistes);
		return emprunt;
	}
	
	public SeanceSimpleDTO fromSeance(Seance seance) {
		SeanceSimpleDTO seanceSimpleDTO = new SeanceSimpleDTO();
		BeanUtils.copyProperties(seance, seanceSimpleDTO);
		seanceSimpleDTO.setPresident(seance.getPresident().getIdMembre());
		seanceSimpleDTO.setExercice(seance.getExercice().getIdExercice());
		return seanceSimpleDTO;	
	}
	
	public Seance fromSeanceSimpleDTO(SeanceSimpleDTO seanceDTO) throws Exception {
		Seance seance = new Seance();
		try{
			BeanUtils.copyProperties(seanceDTO, seance);
		}catch(BeansException be) {
			throw new  Exception("Conversion de bean echouée");
		}
		
		Membre president = membreRepository.findById(seanceDTO.getPresident()).orElseThrow(()-> new Exception("Membre non trouvé"));
		Exercice exercice = exerciceRepository.findById(seanceDTO.getExercice()).orElseThrow(()-> new Exception("Exercice non trouvé"));
		seance.setPresident(president);
		seance.setExercice(exercice);
		return seance;	
	}
	
	public SeanceFullDTO fromFullSeance(Seance seance) {
		SeanceFullDTO seanceFullDTO = new SeanceFullDTO();
		BeanUtils.copyProperties(seance, seanceFullDTO);
		seanceFullDTO.setPresident(fromMembre(seance.getPresident()));
		seanceFullDTO.setExercice(fromExercice(seance.getExercice()));
		seanceFullDTO.setListesdespresents(seance.getListesdespresents().stream()
				.map(membre ->fromMembre(membre))
				.collect(Collectors.toList())
				);
		seanceFullDTO.setAccueillants(seance.getAccueillants().stream()
				.map(membre ->fromMembre(membre))
				.collect(Collectors.toList())
				);
		return seanceFullDTO;	
	}
	
	
	public SanctionDTO fromSanction(Sanction sanction) {
		SanctionDTO sanctionDTO = new SanctionDTO();
		BeanUtils.copyProperties(sanction, sanctionDTO);
		return sanctionDTO;
	}
	
	public Sanction fromSanctionDTO(SanctionDTO sanctionDTO) {
		Sanction sanction = new Sanction();
		BeanUtils.copyProperties(sanctionDTO, sanction);
		return sanction;
	}
	
	public ConstatFullDTO fromConstatToConstatFullDTO(Constat constat) {
		ConstatFullDTO constatDTO = new ConstatFullDTO();
		BeanUtils.copyProperties(constat, constatDTO);
		constatDTO.setMembre(fromMembre(constat.getMembre()));
		constatDTO.setSeance(fromSeance(constat.getSeance()));
		constatDTO.setSanction(fromSanction(constat.getSanction()));
		return constatDTO;
	}
	
	public Constat fromConstatFullDTOToConstat(ConstatFullDTO constatDTO) throws Exception {
		Constat constat = new Constat();
		BeanUtils.copyProperties(constatDTO, constat);
		constat.setMembre(fromMembreSimplifieDTO(constatDTO.getMembre()));
		constat.setSeance(fromSeanceSimpleDTO(constatDTO.getSeance()));
		constat.setSanction(fromSanctionDTO(constatDTO.getSanction()));
		return constat;
	}
	
	
	public ConstatDTO fromConstatToConstatDTO(Constat constat) {
		ConstatDTO constatDTO = new ConstatDTO();
		BeanUtils.copyProperties(constat, constatDTO);
		constatDTO.setIdMembre(constat.getMembre().getIdMembre());
		constatDTO.setIdSanction(constat.getSanction().getIdSanction());
		constatDTO.setIdSeance(constat.getSeance().getIdSeance());
		return constatDTO;
	}
	
	public Constat fromConstatDTOToConstat(ConstatDTO constatDTO) throws Exception {
		Constat constat = new Constat();
		BeanUtils.copyProperties(constatDTO, constat);
		Membre m = membreRepository.findById(constatDTO.getIdMembre()).orElseThrow(()-> new Exception(""));
		constat.setMembre(m);
		Sanction s = sanctionRepository.findById(constatDTO.getIdSanction()).orElseThrow(()-> new Exception(""));
		constat.setSanction(s);
		Seance se = seanceRepository.findById(constatDTO.getIdSeance()).orElseThrow(()-> new Exception(""));
		constat.setSeance(se);
		return constat;
	}
	
	
	public VersementSanctionDTO fromVersementSanctionToVersementSanctionDTO(VersementSanction versementSanction) {
		VersementSanctionDTO versementSanctionDTO = new VersementSanctionDTO();
		BeanUtils.copyProperties(versementSanction, versementSanctionDTO);
		versementSanctionDTO.setIdMembre(versementSanction.getMembre().getIdMembre());
		versementSanctionDTO.setIdConstat(versementSanction.getConstat().getIdConstat());
		return versementSanctionDTO;
	}
	
	public VersementSanction fromVersementSanctionDTOToVersementSanction(VersementSanctionDTO versementSanctionDTO) throws Exception {
		VersementSanction versementSanction = new VersementSanction();
		BeanUtils.copyProperties(versementSanctionDTO, versementSanction);
		Membre m = membreRepository.findById(versementSanctionDTO.getIdMembre()).orElseThrow(()-> new Exception(""));
		Constat c = constatRepository.findById(versementSanctionDTO.getIdConstat()).orElseThrow(()-> new Exception(""));
		versementSanction.setMembre(m);
		versementSanction.setConstat(c);
		return versementSanction;
	}
	
}
