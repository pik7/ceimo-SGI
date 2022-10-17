package com.ceimo.gestion.repository.compte;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceimo.gestion.entity.compte.Operation;
import com.ceimo.gestion.entity.tontine.Tontine;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

}
