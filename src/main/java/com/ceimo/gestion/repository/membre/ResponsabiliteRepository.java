package com.ceimo.gestion.repository.membre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ceimo.gestion.entity.membre.Membre;
import com.ceimo.gestion.entity.membre.Poste;
import com.ceimo.gestion.entity.membre.Responsabilite;

@Repository
public interface ResponsabiliteRepository extends JpaRepository<Responsabilite, Long> {
	Responsabilite findByPoste(String poste);
}
