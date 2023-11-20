/** Package où se trouve la class */
package fr.diginamic.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import fr.diginamic.Entities.LieuNaissance;


/** Classe avec les méthodes SQL concernant les LieuNaissances */
public class LieuNaissanceDaoJpa implements LieuNaissanceDao {
	
	/** emf */
	private EntityManagerFactory emf;
	
	/** Constructeur Dao */
	public LieuNaissanceDaoJpa(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public boolean existe(List<LieuNaissance> lieuNaissances) {
		EntityManager em = emf.createEntityManager();
		try {
			List<String> nomslieuNaissance = lieuNaissances.stream().map(LieuNaissance::getNom).collect(Collectors.toList());
			Long count = em.createQuery("SELECT COUNT(l) FROM LieuNaissance l WHERE l.nom IN :nomslieuNaissance", Long.class)
					.setParameter("nomslieuNaissance", nomslieuNaissance).getSingleResult();
			return count > 0;
		} finally {
			em.close();
		}
	}
}