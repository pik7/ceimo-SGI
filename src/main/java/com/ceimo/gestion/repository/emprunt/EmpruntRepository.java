package com.ceimo.gestion.repository.emprunt;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ceimo.gestion.entity.emprunt.Emprunt;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

	/*
	 * @EntityGraph(value = "Emprunt.emprunteur") List<Emprunt> findAll();
	 */
	
}
