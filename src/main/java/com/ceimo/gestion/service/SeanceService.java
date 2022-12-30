package com.ceimo.gestion.service;

import java.util.List;

import com.ceimo.gestion.dtos.ExerciceDTO;
import com.ceimo.gestion.dtos.MembreSimplifieDTO;
import com.ceimo.gestion.dtos.SeanceFullDTO;
import com.ceimo.gestion.dtos.SeanceSimpleDTO;
import com.ceimo.gestion.entity.seance.Seance;

public interface SeanceService {

	public  List<SeanceSimpleDTO> getSeances(Long idExercice);
	public SeanceSimpleDTO getSeance(Long idSeance) throws Exception ;
	public void appelSeance(Long idMembre, Long idSeance) throws Exception;
	public SeanceSimpleDTO saveSeance(SeanceSimpleDTO seanceDTO) throws Exception  ;
	public SeanceFullDTO getListeDesPresents(Long idSeance) throws Exception;
	public List<MembreSimplifieDTO> listerAbsents(Long idSeance) throws Exception;
	public void deleteSeance(Long idSeance) throws Exception;
	public ExerciceDTO saveExercice(ExerciceDTO exerciceDTO) throws Exception ;
	public void deleteExercice(Long idExercice) throws Exception ;
	public ExerciceDTO getExercice(Long idExercice) throws Exception ;
}
