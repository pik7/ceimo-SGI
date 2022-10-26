package com.ceimo.gestion.repository.membre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceimo.gestion.entity.membre.Elire;
import com.ceimo.gestion.entity.membre.Poste;
import com.ceimo.gestion.entity.tontine.Tontine;

@Repository
public interface ElireRepository extends JpaRepository<Elire, Long> {
	
	Elire findByExerciceIdExerciceAndResponsabilitePoste(Long IdExercice, String poste);
	void deleteByResponsabiliteIdresponsabilite(Long idResp);
	void deleteByResponsabiliteIdresponsabiliteAndMembreIdMembreAndExerciceIdExercice(Long idResp, Long idMembre, Long IdExercice);
}
