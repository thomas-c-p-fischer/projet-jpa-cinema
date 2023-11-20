/** Package où se trouve la class */
package fr.diginamic.dao;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import fr.diginamic.Entities.Langue;
import fr.diginamic.Entities.Pays;

/** Classe avec les méthodes SQL concernant les Langues */
public class LangueDaoJpa implements LangueDao {

	/** emf */
	private EntityManagerFactory emf;
	
	/** Constructeur Dao */
	public LangueDaoJpa(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public boolean existe(List<Langue> langues) {
		EntityManager em = emf.createEntityManager();
		try {
			List<String> nomsLangue = langues.stream().map(Langue::getNom).collect(Collectors.toList());
			Long count = em.createQuery("SELECT COUNT(p) FROM Pays p WHERE p.nomPays IN :nomsPays", Long.class)
					.setParameter("nom", nomsLangue).getSingleResult();
			return count > 0;
		} finally {
			em.close();
		}
	}
}
