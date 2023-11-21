/** Package où se trouve la class */
package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import fr.diginamic.Entities.Acteur;
import fr.diginamic.Entities.Film;

/** Classe avec les méthodes SQL concernant les Acteurs */
public class ActeurDaoJpa implements ActeurDao {

	/** emf */
	private EntityManagerFactory emf;
	
	/** Constructeur Dao */
	public ActeurDaoJpa(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public Acteur getActeur(String identite) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			TypedQuery<Acteur> query = em.createQuery("SELECT a FROM Acteur a WHERE a.identite = :identite", Acteur.class);
			query.setParameter("identite", identite);
			List<Acteur> acteurs = query.getResultList();
			if (acteurs.isEmpty()) {
				return null;
			} else {
				return acteurs.get(0);
			}
		} finally {
			em.getTransaction().commit();
			em.close();
		}
	}

	@Override
	public List<Acteur> getActeurByFilm(Film film) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			TypedQuery<Acteur> query = em.createQuery("SELECT a FROM Acteur a JOIN a.films f WHERE f = :film", Acteur.class);
			query.setParameter("film", film);
			List<Acteur> listActeurByFilm = query.getResultList();
			return listActeurByFilm;
		} finally {
			em.getTransaction().commit();
			em.close();
		}
	}

	@Override
	public List<Acteur> getActeurByFilm(Film film1, Film film2) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			TypedQuery<Acteur> query = em.createQuery(
					"SELECT a FROM Acteur a JOIN a.roles r1 JOIN a.roles r2 WHERE r1.film = :film1 AND r2.film = :film2",
					Acteur.class);
			query.setParameter("film1", film1);
			query.setParameter("film2", film2);
			List<Acteur> listActeurCommunByFilm = query.getResultList();
			return listActeurCommunByFilm;
		} finally {
			em.getTransaction().commit();
			em.close();
		}
	}
}