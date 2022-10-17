package com.ceimo.gestion.repository.membre;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ceimo.gestion.entity.membre.Membre;
import com.ceimo.gestion.entity.membre.StatutGeo;

@Repository
public interface MembreRepository extends JpaRepository<Membre, Long>{
	
	Membre findByDemissionnaire(boolean d);
	Membre findByNomMembre(String nom);
	
	@Query( "SELECT m FROM Membre m WHERE m.nomMembre LIKE ?1 or m.prenomMembre LIKE ?2 ORDER BY m.nomMembre")
	List<Membre> searchMembre(String motCle, String motCle2);
	
	
	//@Query("SELECT m FROM Membre m WHERE m.nommembre = ?1 AND m.prenommembre = ?2 AND m.datenaissance = ?3")
	Membre findByNomMembreAndPrenomMembreAndDateNaissanceOrLogin(String nom, String prenom, Date datenaissance, String login);
	
	
	Membre findByLogin(String login);
	
	
	
	
	/*
	 * Iterable<Product> findByCategoriesName(String name);
	 * 
	 * @Query("FROM Product WHERE name = ?1") public Iterable<Product>
	 * findByNameJPQL(String name);
	 * 
	 * @Query(value = "SELECT * FROM produit WHERE cout = :cout", nativeQuery =
	 * true) public Iterable<Product> findByCostNative(@Param("cout") Integer cost);
	 */
	
}
