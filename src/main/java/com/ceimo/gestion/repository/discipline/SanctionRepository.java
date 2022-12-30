package com.ceimo.gestion.repository.discipline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceimo.gestion.entity.discipline.Sanction;
import com.ceimo.gestion.entity.tontine.Tontine;

@Repository
public interface SanctionRepository extends JpaRepository<Sanction, Long> {

	Sanction findByLibelleSanction(String libelle);
}
