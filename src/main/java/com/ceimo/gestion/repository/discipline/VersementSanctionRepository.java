package com.ceimo.gestion.repository.discipline;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceimo.gestion.entity.discipline.Sanction;
import com.ceimo.gestion.entity.discipline.VersementSanction;
import com.ceimo.gestion.entity.tontine.Tontine;

@Repository
public interface VersementSanctionRepository extends JpaRepository<VersementSanction, Long> {

	List<VersementSanction> findByConstatIdConstat(Long idConstat);
}
