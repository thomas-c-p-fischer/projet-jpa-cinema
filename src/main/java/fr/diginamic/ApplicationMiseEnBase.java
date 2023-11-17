/** Package où se trouve la class */
package fr.diginamic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
		// Création des mes divers listes d'entités
		List<Pays> arrayPays = lecteurCsv.parsePays("pays.csv");
		List<Langue> arrayLangue = lecteurCsv.parseLangue("films.csv");
		List<Genre> arrayGenre = lecteurCsv.parseGenre("films.csv");
		List<LieuNaissance> arrayLieuNaissanceRea = lecteurCsv.parseLieuNaissance("realisateurs.csv");
		List<LieuNaissance> arrayLieuNaissanceAct = lecteurCsv.parseLieuNaissance("acteurs.csv");
		List<Role> arrayRole = lecteurCsv.parseRole("roles.csv");
		List<Realisateur> arrayRealisateur = lecteurCsv.parseRealisateur("realisateurs.csv");//, fichierFilmRealisateur
		List<Acteur> arrayActeur = lecteurCsv.parseActeur("acteurs.csv");
		System.out.println(arrayActeur);
//		List<Film> arrayFilms = lecteurCsv.parseFilm(fichierFilm);
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cinema");
//		EntityManager em = emf.createEntityManager();
//		EntityTransaction transaction = em.getTransaction();
	}
}