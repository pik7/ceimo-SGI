package com.ceimo.gestion.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceimo.gestion.repository.tontine.TontineRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class TontineServiceImpl implements TontineService {
	
	private TontineRepository tontineRepository;

}
