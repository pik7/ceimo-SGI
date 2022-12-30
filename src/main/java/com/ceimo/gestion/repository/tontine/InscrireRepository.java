package com.ceimo.gestion.repository.tontine;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ceimo.gestion.entity.tontine.Inscrire;
import com.ceimo.gestion.entity.tontine.Tontine;

@Repository
public interface InscrireRepository extends JpaRepository<Inscrire, Long> {

	List<Inscrire> findByTontineIdTontineOrderByMontantAsc(Long idTontine);
	List<Inscrire> findByTontineIdTontine(Long idTontine);
	List<Inscrire> findByTontineIdTontineOrderByOrdreAsc(Long idTontine);
	@Query("select i from Inscrire i where i.tontine.idTontine = ?1 and i.classer = ?2")
	List<Inscrire> getNonClasser(Long idTontine, boolean classer);
	Inscrire findBymoisAndTontineIdTontine(String mois, Long idTontine);
	void deleteByMembreIdMembreAndTontineIdTontine(Long idMembre, Long idTontine);
	Inscrire findByMembreIdMembreAndTontineIdTontine(Long idMembre, Long idTontine);
}
