package com.ceimo.gestion.repository.seance;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceimo.gestion.entity.seance.Seance;
import com.ceimo.gestion.entity.tontine.Tontine;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {
	List<Seance> findByExerciceIdExercice(Long idExercice);
	Seance findByDateSeance(Date dateSeance);
}
