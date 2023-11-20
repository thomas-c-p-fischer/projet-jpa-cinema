/** Package où se trouve la class */
package fr.diginamic.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import fr.diginamic.Entities.Pays;

/** Classe avec les méthodes SQL concernant les Pays */
public class PaysDaoJpa implements PaysDao {
	
	/** emf */
	private EntityManagerFactory emf;
	
	/** Constructeur Dao */
	public PaysDaoJpa(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public boolean existe(List<Pays> pays) {
		EntityManager em = emf.createEntityManager();
		try {
			List<String> nomsPays = pays.stream().map(Pays::getNom).collect(Collectors.toList());
			Long count = em.createQuery("SELECT COUNT(p) FROM Pays p WHERE p.nomPays IN :nomsPays", Long.class)
					.setParameter("nom", nomsPays).getSingleResult();
			return count > 0;
		} finally {
			em.close();
		}
	}
}