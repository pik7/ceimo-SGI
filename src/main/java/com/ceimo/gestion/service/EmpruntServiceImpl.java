package com.ceimo.gestion.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceimo.gestion.entity.emprunt.Emprunt;
import com.ceimo.gestion.entity.membre.Membre;
import com.ceimo.gestion.entity.membre.StatutGeo;
import com.ceimo.gestion.repository.emprunt.EmpruntRepository;
import com.ceimo.gestion.repository.membre.MembreRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class EmpruntServiceImpl implements EmpruntService {

	
}
