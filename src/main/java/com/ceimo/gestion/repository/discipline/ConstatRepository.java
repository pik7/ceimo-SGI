package com.ceimo.gestion.repository.discipline;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceimo.gestion.entity.discipline.Constat;
import com.ceimo.gestion.entity.tontine.Tontine;

@Repository
public interface ConstatRepository extends JpaRepository<Constat, Long> {

	List<Constat> findBySeanceIdSeance(Long idSeance);
	List<Constat> findByPaye(boolean paye);
}
