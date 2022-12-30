package com.ceimo.gestion.service;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "compte")
@Data
public class CustomsProperties {
	public String tauxInteret;
	public String soldeMaxFond;
	public String montantCollation;
	public String montantMinRoulement;
}


