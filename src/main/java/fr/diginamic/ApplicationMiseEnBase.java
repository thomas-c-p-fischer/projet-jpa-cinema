/** Package où se trouve la class */
package fr.diginamic;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import fr.diginamic.Entities.Role;
import fr.diginamic.Entities.Acteur;
import fr.diginamic.Entities.Film;
import fr.diginamic.Entities.Genre;
import fr.diginamic.Entities.Langue;
import fr.diginamic.Entities.LieuNaissance;
import fr.diginamic.Entities.Pays;
import fr.diginamic.Entities.Realisateur;
import fr.diginamic.service.LecteurCsv;

/** Nom de la classe */
public class ApplicationMiseEnBase {

	/** Ici nous mettons en BDD les données des fichiers CSV
	 * @param args
	 */
	public static void main(String[] args) {
	
		// J'instancie mon lecteur de fichier CSV
		LecteurCsv lecteurCsv = new LecteurCsv();
		// Instanciation de entityManager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		// Mise en base des pays :
		List<Pays> pays = lecteurCsv.parsePays("pays.csv");
		for(Pays p : pays) {
			em.persist(p);
		}
		
		// Mise en base des langues :
		List<Langue> langues = lecteurCsv.parseLangue("films.csv");
		for(Langue l : langues) {
			em.persist(l);
		}
		
		// Mise en base des genres :
		List<Genre> genres = lecteurCsv.parseGenre("films.csv");
		for(Genre g : genres) {
			em.persist(g);
		}
		
		// Mise en base des lieuNaissance :
		List<LieuNaissance> lieuxNaissance = lecteurCsv.parseLieuNaissance("realisateurs.csv", "acteurs.csv");
		for(LieuNaissance l : lieuxNaissance) {	
			em.persist(l);
		}
		
		// Mise en base des films :
		List<Film> films = lecteurCsv.parseFilm("films.csv", pays, langues, genres);
		for(Film f : films) {
			em.persist(f);
		}
		
		// Mise en base des acteurs :
		List<Acteur> acteurs = lecteurCsv.parseActeur("acteurs.csv", lieuxNaissance);
		for(Acteur a : acteurs) {
			em.persist(a);
		}
		
		// Mise en base des realisateurs :
		List<Realisateur> realisateurs = lecteurCsv.parseRealisateur("realisateurs.csv", lieuxNaissance);
		for(Realisateur r : realisateurs) {
			em.persist(r);
		}
		
		lecteurCsv.parseCastingPrincipal("castingPrincipal.csv", films, acteurs);
		lecteurCsv.parseFilmRealisateur("film_realisateurs.csv", films, realisateurs);
		
		// Mise en base des rôles :
		List<Role> roles = lecteurCsv.parseRole("roles.csv", films, acteurs);
		for(Role r : roles) {
			em.persist(r);
		}
		lecteurCsv.parseCastingPrincipal("castingPrincipal.csv", films, acteurs);
		lecteurCsv.parseFilmRealisateur("film_realisateurs.csv", films, realisateurs);
		em.getTransaction().commit();
	}
}