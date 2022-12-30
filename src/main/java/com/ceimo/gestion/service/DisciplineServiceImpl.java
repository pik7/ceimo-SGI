package com.ceimo.gestion.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceimo.gestion.dtos.ConstatDTO;
import com.ceimo.gestion.dtos.ConstatFullDTO;
import com.ceimo.gestion.dtos.SanctionDTO;
import com.ceimo.gestion.dtos.VersementSanctionDTO;
import com.ceimo.gestion.entity.discipline.Constat;
import com.ceimo.gestion.entity.discipline.Sanction;
import com.ceimo.gestion.entity.discipline.VersementSanction;
import com.ceimo.gestion.mappers.MapperModule;
import com.ceimo.gestion.repository.discipline.ConstatRepository;
import com.ceimo.gestion.repository.discipline.SanctionRepository;
import com.ceimo.gestion.repository.discipline.VersementSanctionRepository;
import com.ceimo.gestion.repository.tontine.TontineRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class DisciplineServiceImpl implements DisciplineService {

	private SanctionRepository sanctionRepository;
	private ConstatRepository constatRepository;
	private VersementSanctionRepository versementSanctionRepository;
	private MapperModule meMapper;
	
	
	@Override
	public SanctionDTO saveSanction(SanctionDTO sanctionDTO) throws Exception {
		
		Sanction sanction = meMapper.fromSanctionDTO(sanctionDTO);
		if(sanction.getIdSanction() != null) {
			Sanction s = sanctionRepository.findById(sanction.getIdSanction()).orElseThrow(()-> new Exception("Sanction non Existante"));
			return meMapper.fromSanction(sanctionRepository.save(sanction));
		}else {
			Sanction s = sanctionRepository.findByLibelleSanction(sanction.getLibelleSanction());
			if(s != null) throw new Exception("Sanction avec le même libellé déjà existante");
			else return meMapper.fromSanction(sanctionRepository.save(sanction));
		}
	}
	
	@Override
	public SanctionDTO getSanction(Long idSanction) throws Exception {
		Sanction s = sanctionRepository.findById(idSanction).orElseThrow(()-> new Exception("Sanction non Existante"));
		return meMapper.fromSanction(s);
	}
	
	
	@Override
	public List<SanctionDTO> getAllSanction(){
		List<Sanction> lesSanctions = sanctionRepository.findAll();
		return lesSanctions.stream()
			.map(s -> meMapper.fromSanction(s))
			.collect(Collectors.toList());
	}
	
	@Override
	public void deleteSanction(Long idSanction) {
		sanctionRepository.deleteById(idSanction);
	}
	
	@Override
	public ConstatDTO saveConstat(ConstatDTO constatDTO) throws Exception{
		Constat constat = meMapper.fromConstatDTOToConstat(constatDTO);
		return meMapper.fromConstatToConstatDTO(constatRepository.save(constat));
	}
	
	@Override
	public ConstatFullDTO getConstat(Long idConstat) throws Exception {
		Constat c = constatRepository.findById(idConstat).orElseThrow(()-> new Exception("Constat disciplinaire non existant dans le système"));
		return meMapper.fromConstatToConstatFullDTO(c);
	}
	
	
	
	@Override
	public VersementSanctionDTO savePaiementAmande(VersementSanctionDTO versementSanctionDTO ) throws Exception {
		VersementSanction versement = meMapper.fromVersementSanctionDTOToVersementSanction(versementSanctionDTO) ;
		Optional<Integer> montantDejaRegle;
		Constat constat = constatRepository.findById(versementSanctionDTO.getIdConstat()).orElseThrow(()-> new Exception("Constat disciplinaire non existant dans le système"));
		//Sanction sanction = sanctionRepository.findById(constat.)
		if(constat.isPaye()) {
			throw new Exception("Amande déjà totalement payée pour ce cas d'indiscipline");
		}else {
			List<VersementSanction> lesVersements = versementSanctionRepository.findByConstatIdConstat(versementSanctionDTO.getIdConstat());
			montantDejaRegle = lesVersements.stream()
					.map(vers -> vers.getMontantVerse())
					.reduce(Integer::sum);
			
			if((montantDejaRegle.get()+versement.getMontantVerse())<= constat.getSanction().getAmende() ) {
				if((montantDejaRegle.get()+versement.getMontantVerse())== constat.getSanction().getAmende()) {
					constat.setPaye(true);
					constatRepository.save(constat);
				}
				return meMapper.fromVersementSanctionToVersementSanctionDTO(versementSanctionRepository.save(versement));
				
			}else {
				throw new Exception("Ce versement est supérieur au montant restant à régler pour cette amande");
			}
		}
		
	}
	
	@Override
	public List<ConstatFullDTO> listerConstatSeance(Long idSeance){
		List<Constat> constats = constatRepository.findBySeanceIdSeance(idSeance);
		List<ConstatFullDTO> lesConstats = constats.stream()
				.map(cst ->meMapper.fromConstatToConstatFullDTO(cst))
				.collect(Collectors.toList());
		return lesConstats;
	}
	
	@Override
	public List<ConstatFullDTO> listerAmandesNonReglees(){
		List<Constat> constats = constatRepository.findByPaye(false);
		return constats.stream()
				.map(cst ->meMapper.fromConstatToConstatFullDTO(cst))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<VersementSanctionDTO> listerVersementParConstat(Long idConstat){
		List<VersementSanction> lesVersements = versementSanctionRepository.findByConstatIdConstat(idConstat);
		return lesVersements.stream()
				.map(vsm ->meMapper.fromVersementSanctionToVersementSanctionDTO(vsm))
				.collect(Collectors.toList());
	}
	
	@Override
	public void deleteConstat(Long idConstat) {
		constatRepository.deleteById(idConstat);
	}
	
	@Override
	public void deleteVersementSanction(Long id) {
		versementSanctionRepository.deleteById(id);
	}
}


