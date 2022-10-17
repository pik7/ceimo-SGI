package com.ceimo.gestion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ceimo.gestion.entity.emprunt.Emprunt;
import com.ceimo.gestion.repository.emprunt.EmpruntRepository;
import com.ceimo.gestion.service.EmpruntServiceImpl;

@RestController @CrossOrigin("*")
public class EmpruntController {

}
