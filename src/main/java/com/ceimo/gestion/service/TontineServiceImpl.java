package com.ceimo.gestion.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceimo.gestion.dtos.ContributionDTO;
import com.ceimo.gestion.dtos.ContributionFullDTO;
import com.ceimo.gestion.dtos.GainTontineDTO;
import com.ceimo.gestion.dtos.InscrireDTO;
import com.ceimo.gestion.dtos.MembreSimplifieDTO;
import com.ceimo.gestion.dtos.TontineDTO;
import com.ceimo.gestion.entity.membre.Membre;
import com.ceimo.gestion.entity.seance.Seance;
import com.ceimo.gestion.entity.tontine.Contribution;
import com.ceimo.gestion.entity.tontine.Inscrire;
import com.ceimo.gestion.entity.tontine.Tontine;
import com.ceimo.gestion.mappers.MapperModule;
import com.ceimo.gestion.repository.membre.MembreRepository;
import com.ceimo.gestion.repository.seance.SeanceRepository;
import com.ceimo.gestion.repository.tontine.ContributionRepository;
import com.ceimo.gestion.repository.tontine.InscrireRepository;
import com.ceimo.gestion.repository.tontine.TontineRepository;
import com.ceimo.gestion.service.exceptions.MembreNotFoundException;
import com.ceimo.gestion.service.exceptions.SeanceNotFoundException;
import com.ceimo.gestion.service.utilitaires.DifferenceDateMois;

import lombok.AllArgsConstructor;

@Service
@Transactional

public class TontineServiceImpl implements TontineService {
	@Autowired
	private TontineRepository tontineRepository;
	@Autowired
	private ContributionRepository contributionRepository;
	@Autowired
	private InscrireRepository inscrireRepository;
	@Autowired
	private MembreRepository membreRepository;
	@Autowired
	private SeanceRepository seanceRepository;
	@Autowired
	private MapperModule meMapper;
	
	
	
	/**************** LISTE DES MEMBRES AYANT ECHOUES LA TONTINE 
	 * @throws TontineNotFoundException ********************************/
	
	@Override
	public List<MembreSimplifieDTO> nonCotises(Long idTontine, String mois) throws TontineNotFoundException {
		boolean trouver = false;
		List<MembreSimplifieDTO> membreSimplifieDTOs = new ArrayList<MembreSimplifieDTO>();
		List<Inscrire> inscrires = inscrireRepository.findByTontineIdTontine(idTontine);
		GainTontineDTO gainTontineDTO = getAyantCotises(mois, idTontine);
		
		for(Inscrire inscris : inscrires) {
			trouver = false;
			for(ContributionFullDTO contrib : gainTontineDTO.getContributions()) {
				if(inscris.getMembre().getIdMembre() == contrib.getMembre().getIdMembre()) {
					trouver = true;
				}
			}
			if(!trouver)
				membreSimplifieDTOs.add(meMapper.fromMembre(inscris.getMembre()));
		}
		return membreSimplifieDTOs;	
	}
	/**************** FIN LISTE DES MEMBRES AYANT ECHOUES LA TONTINE ********************************/
	
	/**************** LISTE DES MOIS DE GAIN DE LA TONTINE ********************************/
	
	
	@Override
	public List<InscrireDTO> getAllGainMonth(Long idTontine){
		
		List<Inscrire> inscrires = inscrireRepository.findByTontineIdTontine(idTontine);
		List<InscrireDTO> inscrireDTOs = inscrires.stream()
				.map(inscris ->meMapper.fromInscrire(inscris))
				.collect(Collectors.toList());
		return inscrireDTOs;
	}
	
	/**************** FIN  LISTE DES MOIS DE GAIN DE LA TONTINE ********************************/
	
	/**************** LISTE DES CONTRIBUTIONS ENREGISTREES POUR UNE TONTINE DANS UN MOIS DONNE ********************************/
	@Override
	public GainTontineDTO getAyantCotises(String mois, Long idTontine) throws TontineNotFoundException {
		GainTontineDTO gainTontineDTO = new GainTontineDTO();
		Inscrire inscrire = inscrireRepository.findBymoisAndTontineIdTontine(mois, idTontine);
		gainTontineDTO.setIdMembre(inscrire.getMembre().getIdMembre());
		gainTontineDTO.setNom(inscrire.getMembre().getNomMembre()+" "+inscrire.getMembre().getPrenomMembre());
		List<Contribution> contributions = contributionRepository.findByMoisAndTontineIdTontine(mois, idTontine);
		List<ContributionFullDTO> contributionFullDTOs = contributions.stream()
				.map(contrib -> meMapper.fromContributionFull(contrib))
				.collect(Collectors.toList());
		gainTontineDTO.setContributions(contributionFullDTOs);
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Tontine tontine = tontineRepository.findById(idTontine)
				.orElseThrow(() -> new TontineNotFoundException("Tontine non existante"));
		long nbreMois = dureeTontine(idTontine);
		gainTontineDTO.setGain(inscrire.getMontant()*nbreMois);
		return gainTontineDTO;
	}
	/**************** FIN LISTE DES CONTRIBUTIONS ENREGISTREES POUR UNE TONTINE DANS UN MOIS DONNE ********************************/
	
	
	
	
	/**************** CREATION ET MISE A JOUR D'UNE TONTINE*****************************************************************/
	@Override
	public TontineDTO saveTontine(TontineDTO tontineDTO) {
		Tontine tontine = meMapper.fromTontineDTO(tontineDTO);
		return meMapper.fromTontine(tontineRepository.save(tontine));
	}
	/**************** FIN CREATION ET MISE A JOUR D'UNE TONTINE*****************************************************************/
	
	
	/**************** SUPPRESSION D'UNE TONTINE***********************************************************************************/
	@Override
	public void deleteTontine(Long idTontine) throws TontineNotFoundException {
		Tontine tontine = tontineRepository.findById(idTontine)
				.orElseThrow(() -> new TontineNotFoundException("Tontine non existante"));
		tontineRepository.delete(tontine);
	}
	/**************** FIN SUPPRESSION D'UNE TONTINE***********************************************************************************/
	

	/**************** ENREGISTREMENT LA CONTRIBUTION D'UN MEMBRE *****************************************************************/
	@Override
	public ContributionDTO cotiser(ContributionDTO contributionDTO)
			throws MembreNotFoundException, TontineNotFoundException, SeanceNotFoundException {
		Membre membre = membreRepository.findById(contributionDTO.getIdMembre())
				.orElseThrow(() -> new MembreNotFoundException("Membre non existant"));
		Tontine tontine = tontineRepository.findById(contributionDTO.getIdTontine())
				.orElseThrow(() -> new TontineNotFoundException("Tontine non existante"));
		Seance seance = seanceRepository.findById(contributionDTO.getIdSeance())
				.orElseThrow(() -> new SeanceNotFoundException("Seance non existante"));

		Contribution contrib = new Contribution();
		contrib.setMontantContribution(contributionDTO.getMontantContribution());
		contrib.setMembre(membre);
		contrib.setSeance(seance);
		contrib.setTontine(tontine);
		return meMapper.fromContribution(contributionRepository.save(contrib));

	}
	/**************** FIN ENREGISTREMENT LA CONTRIBUTION D'UN MEMBRE *****************************************************************/
	
	/**************** SUPPRESSION D'UNE CONTRIBUTION D'UN MEMBRE *****************************************************************/
	@Override
	public void deleteContribution(Long idContribution) throws Exception {
		Contribution contribution = contributionRepository.findById(idContribution)
				.orElseThrow(()-> new Exception("Contribution non  existante"));
		contributionRepository.delete(contribution);
	}
	
	/**************** FIN SUPPRESSION D'UNE LA CONTRIBUTION D'UN MEMBRE *****************************************************************/

	/**************** INSCRIPTION D'UN MEMBRE DANS UNE TONTINE***************************************************************************/
	public void inscrireATontine(Long idMembre, Long idTontine, double montant, int ordre)
			throws MembreNotFoundException, TontineNotFoundException {
		Membre membre = membreRepository.findById(idMembre)
				.orElseThrow(() -> new MembreNotFoundException("Membre non existant"));
		Tontine tontine = tontineRepository.findById(idTontine)
				.orElseThrow(() -> new TontineNotFoundException("Tontine non existante"));

		Inscrire inscription = new Inscrire();
		inscription.setMembre(membre);
		inscription.setMontant(montant);
		inscription.setTontine(tontine);
		inscription.setGain(0);
		inscription.setNombreEchec(0);
		inscription.setOrdre(ordre);
		inscription.setClasser(false);
		inscrireRepository.save(inscription);
	}
	/**************** FIN INSCRIPTION D'UN MEMBRE DANS UNE TONTINE*****************************************************************/
	
	/**************** SUPPRESSION D'UN MEMBRE DE LA TONTINE TONTINE
	 * @throws MembreNotFoundException 
	 * @throws TontineNotFoundException **************************************************************************/
	
	public void removeFromTontine(Long idMembre, Long idTontine) throws MembreNotFoundException, TontineNotFoundException {
		Membre membre = membreRepository.findById(idMembre)
				.orElseThrow(() -> new MembreNotFoundException("Membre non existant"));
		Tontine tontine = tontineRepository.findById(idTontine)
				.orElseThrow(() -> new TontineNotFoundException("Tontine non existante"));
		inscrireRepository.deleteByMembreIdMembreAndTontineIdTontine(idMembre, idTontine);
		contributionRepository.deleteByMembreIdMembreAndTontineIdTontine(idMembre, idTontine);
	}
	
	
	/**************** FIN SUPPRESSION D'UN MEMBRE DE LA TONTINE TONTINE**************************************************************************/
	
	
	/**************** ENREGISTREMENT DES MOIS DE GAIN DES MEMBRES DE LA TONTINE**************************************************/
	@Override
	public void classement(Long idTontine) throws TontineNotFoundException {
	
		Tontine tontine = tontineRepository.findById(idTontine)
				.orElseThrow(() -> new TontineNotFoundException("Tontine non existante"));
			
		List<Inscrire> inscrisTontine = inscrireRepository.findByTontineIdTontineOrderByOrdreAsc(idTontine);
		inscrisTontine.forEach(inscris ->{
			inscris.setClasser(false);
		});

		long nbreMois = dureeTontine(idTontine);
		double gainMensuelTotal = 0;
		for(Inscrire inscris : inscrisTontine) {
			gainMensuelTotal += inscris.getMontant();
		}
		
		Calendar gc = new GregorianCalendar();
		gc.setTime(tontine.getDateDebut());
		int moisDebut = gc.get(GregorianCalendar.MONTH);
		
		double montantEnCaisse = tontine.getMontantEnCaisse();
		int cptMembre = 0;
		
		for (int i = 1; i <= nbreMois; i++) {
			montantEnCaisse = gainMensuelTotal + montantEnCaisse;
			moisDebut+=1;
			if(moisDebut >12) moisDebut = 1;
			System.out.println("********** Mois " + i + " - Etat courant : " + montantEnCaisse);
					cptMembre = 0;
					while( cptMembre <= inscrisTontine.size()-1 ){
						
						if(!inscrisTontine.get(cptMembre).isClasser() && inscrisTontine.get(cptMembre).getMontant()*nbreMois <= montantEnCaisse) {
							System.out.println(i + " - " + inscrisTontine.get(cptMembre).getMembre().getNomMembre() 
									+ " "+"Cotise : "+inscrisTontine.get(cptMembre).getMontant() 
									+" Et bouffe :" + (inscrisTontine.get(cptMembre).getMontant() * nbreMois));
							System.out.println("Mois de gain : "+moisDebut);
							inscrisTontine.get(cptMembre).setClasser(true);
							inscrisTontine.get(cptMembre).setMois(DifferenceDateMois.mois(moisDebut));
							inscrisTontine.get(cptMembre).setGain(inscrisTontine.get(cptMembre).getMontant() * nbreMois);
							
							montantEnCaisse = montantEnCaisse - inscrisTontine.get(cptMembre).getMontant()*nbreMois;
						}
						cptMembre++;
					}
			
		}

	}
	/**************** FIN ENREGISTREMENT DES MOIS DE GAIN DES MEMBRES DE LA TONTINE**************************************************/
	
	@Override
	public void reclasserApresEchec(Long idTontine, Long idMembre) {
		int temp;
		Inscrire inscrire = inscrireRepository.findByMembreIdMembreAndTontineIdTontine(idMembre, idTontine);
		if(inscrire !=null) {
			inscrire.setNombreEchec(inscrire.getNombreEchec()+1);
			List<Inscrire> inscris =  inscrireRepository.findByTontineIdTontine(idTontine);
			for(Inscrire unInscrit : inscris) {
				if(unInscrit.getOrdre() > inscrire.getOrdre() && unInscrit.getMembre().getIdMembre()!= inscrire.getMembre().getIdMembre()) {
					temp = inscrire.getOrdre();
					inscrire.setOrdre(unInscrit.getOrdre());
					unInscrit.setOrdre(temp);
				}
			}
		
		}
	}
	
	public long dureeTontine(Long idTontine) throws TontineNotFoundException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Tontine tontine = tontineRepository.findById(idTontine)
				.orElseThrow(() -> new TontineNotFoundException("Tontine non existante"));

		return DifferenceDateMois.autreMethodeCalculDiffMois(sdf.format(tontine.getDateDebut()),
				sdf.format(tontine.getDateFin()));
	}


}
