package com.ceimo.gestion.repository.seance;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceimo.gestion.entity.seance.Exercice;
import com.ceimo.gestion.entity.tontine.Tontine;

@Repository
public interface ExerciceRepository extends JpaRepository<Exercice, Long> {

	Exercice findByStatut(boolean etat);
	Exercice findByDateDebutExerciceAndDateFinExercice(Date debut, Date fin);
}
