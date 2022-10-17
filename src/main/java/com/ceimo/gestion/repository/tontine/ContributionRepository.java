package com.ceimo.gestion.repository.tontine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceimo.gestion.entity.tontine.Contribution;
import com.ceimo.gestion.entity.tontine.Tontine;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, Long> {

}
