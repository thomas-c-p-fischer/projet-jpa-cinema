/** Package où se trouve la class */
package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import fr.diginamic.Entities.Acteur;
import fr.diginamic.Entities.LieuNaissance;

/** Classe avec les méthodes SQL concernant les Acteurs */
public class ActeurDaoJpa implements ActeurDao {

	/** emf */
	private EntityManagerFactory emf;
	
	/** Constructeur Dao */
	public ActeurDaoJpa(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public boolean existe(List<Acteur> acteurs) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
