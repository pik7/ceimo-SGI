package com.ceimo.gestion.repository.membre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceimo.gestion.entity.membre.Elire;
import com.ceimo.gestion.entity.membre.Inscription;
import com.ceimo.gestion.entity.membre.Poste;
import com.ceimo.gestion.entity.tontine.Tontine;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
	
	Inscription findByMembreIdMembreAndExerciceIdExercice(Long idMembre, Long idExercice);
}
