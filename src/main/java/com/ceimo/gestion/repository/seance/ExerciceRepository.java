package com.ceimo.gestion.repository.seance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceimo.gestion.entity.seance.Exercice;
import com.ceimo.gestion.entity.tontine.Tontine;

@Repository
public interface ExerciceRepository extends JpaRepository<Exercice, Long> {

}
