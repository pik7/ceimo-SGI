package com.ceimo.gestion.repository.compte;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceimo.gestion.entity.compte.Compte;
import com.ceimo.gestion.entity.tontine.Tontine;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {

	List<Compte> findByMembreIdMembre(Long idMembre);
}
