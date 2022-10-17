package com.ceimo.gestion.repository.seance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceimo.gestion.entity.seance.Seance;
import com.ceimo.gestion.entity.tontine.Tontine;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {

}
