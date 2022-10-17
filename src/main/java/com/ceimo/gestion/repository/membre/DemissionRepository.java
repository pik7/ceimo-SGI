package com.ceimo.gestion.repository.membre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceimo.gestion.entity.membre.Demission;
import com.ceimo.gestion.entity.tontine.Tontine;

@Repository
public interface DemissionRepository extends JpaRepository<Demission, Long> {

	Demission findByDemissionnaireIdMembre(Long idMembre);
}
