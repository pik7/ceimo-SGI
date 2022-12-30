package com.ceimo.gestion.repository.tontine;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceimo.gestion.entity.tontine.Contribution;
import com.ceimo.gestion.entity.tontine.Tontine;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, Long> {
	
	List<Contribution> findByMoisAndTontineIdTontine(String mois, Long idTontine);
	void deleteByMembreIdMembreAndTontineIdTontine(Long idMembre, Long idTontine);
}
