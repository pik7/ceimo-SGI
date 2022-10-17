package com.ceimo.gestion;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ceimo.gestion.dtos.MembreBureauDTO;
import com.ceimo.gestion.dtos.MembreSimplifieDTO;
import com.ceimo.gestion.entity.emprunt.Emprunt;
import com.ceimo.gestion.entity.membre.Elire;
import com.ceimo.gestion.entity.membre.Membre;
import com.ceimo.gestion.entity.membre.Poste;
import com.ceimo.gestion.entity.membre.Responsabilite;
import com.ceimo.gestion.entity.membre.StatutGeo;
import com.ceimo.gestion.entity.seance.Exercice;
import com.ceimo.gestion.repository.membre.MembreRepository;
import com.ceimo.gestion.repository.membre.ResponsabiliteRepository;
import com.ceimo.gestion.repository.seance.ExerciceRepository;
import com.ceimo.gestion.service.EmpruntServiceImpl;
import com.ceimo.gestion.service.MembreService;
import com.ceimo.gestion.service.MembreServiceImpl;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;


@SpringBootApplication
public class CeimoApplication implements CommandLineRunner {
	
	@Autowired
	private MembreService membreService;
	
	public static void main(String[] args) {
		SpringApplication.run(CeimoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*List<MembreBureauDTO> membreBureauDTOS =  membreService.listMembresBureau(1L);
		membreBureauDTOS.forEach(membrebureauDTO ->{
			System.out.println(membrebureauDTO.getNomMembre());
			System.out.println(membrebureauDTO.getPrenomMembre());
			membrebureauDTO.getPostes().forEach(poste ->{
				System.out.println(poste.getPoste());
			});
		});
		
		MembreSimplifieDTO membreSimplifieDTO = new MembreSimplifieDTO();
		membreSimplifieDTO.setNomMembre("NGOMNGUE");
		membreSimplifieDTO.setPrenomMembre("SERGE ALBERT");
		membreSimplifieDTO.setDateFirstInscription(new Date());
		membreSimplifieDTO.setDateNaissance(new Date());
		membreSimplifieDTO.setDemissionnaire(false);
		membreSimplifieDTO.setLogin("man");
		membreSimplifieDTO.setPassword("1234");
		membreSimplifieDTO.setSexeMembre('M');
		membreSimplifieDTO.setStatutGeo(StatutGeo.RESIDENT);
		membreService.saveMembre(membreSimplifieDTO);*/
		
		//membreService.nommerMembre(3L, 1L, Poste.TRESORIER, new Date(), 0);
		membreService.exclureDuBureau(3L, 1L);
		
	}
	
	//bean de configuration pour que les propriétés des proxy hibernate soit null
	@Bean
	public Hibernate5Module nullifierProxyHibernate() {
		return new Hibernate5Module();
	}

}
