/** Package où se trouve la class */
package fr.diginamic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.diginamic.Entities.Film;
import fr.diginamic.Entities.Genre;
import fr.diginamic.Entities.Langue;
import fr.diginamic.Entities.LieuNaissance;
import fr.diginamic.Entities.Pays;
import fr.diginamic.service.LecteurCsv;

/** Nom de la classe */
public class ApplicationMiseEnBase {

	/** Ici nous mettons en BDD les données des fichiers CSV
	 * @param args
	 */
	public static void main(String[] args) {
		// Instantiation des fichiers CSV
		String fichierActeur = "acteurs.csv";
		String fichiercastingPrincipal = "castingPrincipal.csv";
		String fichierFilmRealisateur = "film_realisateurs.csv";
		String fichierFilm = "films.csv";
		String fichierPays = "pays.csv";
		String fichierRealisateur = "realisateurs.csv";
		String fichierRole = "roles.csv";
		// J'instancie mon lecteur de fichier CSV
		LecteurCsv lecteurCsv = new LecteurCsv();
		// Création des mes divers listes d'entités
		List<Pays> arrayPays = lecteurCsv.parsePays(fichierPays);
		List<Langue> arrayLangues = lecteurCsv.parseLangue(fichierFilm);
		List<Genre> arrayGenre = lecteurCsv.parseGenre(fichierFilm);
		List<LieuNaissance> arrayLieuNaissanceRea = lecteurCsv.parseLieuNaissance(fichierRealisateur);
		List<LieuNaissance> arrayLieuNaissanceAct = lecteurCsv.parseLieuNaissance(fichierActeur);

//		List<Film> arrayFilms = lecteurCsv.parseFilm(fichierFilm);
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cinema");
//		EntityManager em = emf.createEntityManager();
//		EntityTransaction transaction = em.getTransaction();
	}
}