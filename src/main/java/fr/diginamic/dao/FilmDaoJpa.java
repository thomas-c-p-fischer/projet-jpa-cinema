/** Package où se trouve la class */
package fr.diginamic.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import fr.diginamic.Entities.Acteur;
import fr.diginamic.Entities.Film;

/** Classe avec les méthodes SQL concernant les Films */
public class FilmDaoJpa implements FilmDao {

	/** emf */
	private EntityManagerFactory emf;
	
	/** Constructeur Dao */
	public FilmDaoJpa(EntityManagerFactory emf) {
		this.emf = emf;
	}
	@Override
	public List<Film> getFilmByActeur(Acteur acteur) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f JOIN f.acteurs a WHERE a = :acteur", Film.class);
			query.setParameter("acteur", acteur);
			List<Film> listFilmByActeur = query.getResultList();
			return listFilmByActeur;
		} finally {
			em.getTransaction().commit();
			em.close();
		}	
	}
	
	@Override
	public Film getFilmByName(String nom) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f WHERE f.nom = :nom", Film.class);
			query.setParameter("nom", nom);
			List<Film> films = query.getResultList();
			if (films.isEmpty()) {
				return null;
			} else {
				return films.get(0);
			}
		} finally {
			em.getTransaction().commit();
			em.close();
		}
	}
	
	@Override
	public List<Film> getFilmByAnnee(String debut, String fin) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f WHERE f.annee BETWEEN :debut AND :fin",
					Film.class);
			query.setParameter("debut", debut);
			query.setParameter("fin", fin);
			List<Film> filmsByAnnee = query.getResultList();
			return filmsByAnnee;
		} finally {
			em.getTransaction().commit();
			em.close();
		}
	}
	
	@Override
	public List<Film> getFilmByActeurs(Acteur acteur1, Acteur acteur2) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			TypedQuery<Film> query = em.createQuery(
					"SELECT f FROM Film f JOIN f.roles r1 JOIN f.roles r2 WHERE r1.acteur = :acteur1 AND r2.acteur = :acteur2",
					Film.class);
			query.setParameter("acteur1", acteur1);
			query.setParameter("acteur2", acteur2);
			List<Film> listFilmByActeurCommun = query.getResultList();
			return listFilmByActeurCommun;
		} finally {
			em.getTransaction().commit();
			em.close();
		}
	}
	
	@Override
	public List<Film> getFilmByAnneeActeur(String debut, String fin, Acteur acteur) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			TypedQuery<Film> query = em.createQuery(
					"SELECT DISTINCT f FROM Film f JOIN f.roles r WHERE f.annee BETWEEN :debut AND :fin AND r.acteur = :acteur",
					Film.class);
			query.setParameter("debut", debut);
			query.setParameter("fin", fin);
			query.setParameter("acteur", acteur);
			List<Film> filmsByAnneeActeur = query.getResultList();
			return filmsByAnneeActeur;
		} finally {
			em.getTransaction().commit();
			em.close();
		}
	}	
}