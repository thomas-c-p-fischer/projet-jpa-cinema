/** Package de la class */
package fr.diginamic;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import fr.diginamic.Entities.Acteur;
import fr.diginamic.Entities.Film;
import fr.diginamic.dao.ActeurDaoJpa;
import fr.diginamic.dao.FilmDaoJpa;

/** nom de la classe */
public class ApplicationMenu {

	/** Ici nous affichons les données proposées par le menu 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        boolean quitter = false;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cinema");;
        ActeurDaoJpa acteurDao = new ActeurDaoJpa(emf);
        FilmDaoJpa filmDao = new FilmDaoJpa(emf);

        while (!quitter) {
            // Afficher le menu
            System.out.println("Menu :");
            System.out.println("1. Affichage de la filmographie d'un acteur donné");
            System.out.println("2. Affichage du casting d'un film donné");
            System.out.println("3. Affichage des films sortis entre 2 années données");
            System.out.println("4. Affichage des films communs à 2 acteurs/actrices donnés");
            System.out.println("5. Affichage des acteurs communs à 2 films donnés");
            System.out.println("6. Affichage des films sortis entre 2 années données avec un acteur donné au casting");
            System.out.println("7. Fin de l'application");

            System.out.print("Veuillez choisir une option (1-7) : ");
            int choix = sc.nextInt();
            sc.nextLine();

            // Traiter le choix de l'utilisateur
            switch (choix) {
                case 1:
                    // Code pour l'affichage de la filmographie d'un acteur
                	System.out.println("Veuillez entrer le nom d'un acteur : ");
                	String choixActeur = sc.nextLine();
                	Acteur nomActeur = acteurDao.getActeur(choixActeur);
                	List<Film> filmographie = filmDao.getFilmByActeur(nomActeur);
                	HashSet<Film> films = new HashSet<>(filmographie);
            		System.out.println("Voici la filmographie de " + nomActeur.getIdentite() + " : ");
                	for(Film film : films) {
                		System.out.println(film.getNom());
                	}
                    break;
                case 2:
                    // Code pour l'affichage du casting d'un film
                	System.out.println("Veuillez saisir le nom d'un film : ");
                	String choixFilm = sc.nextLine();
                	Film film = filmDao.getFilmByName(choixFilm);
                	List<Acteur> filmActeurs = acteurDao.getActeurByFilm(film);
                	HashSet<Acteur> acteurs = new HashSet<>(filmActeurs);
                	System.out.println("Voici le casting du film " + film.getNom() + " : ");
                	for(Acteur acteur : acteurs) {
                		System.out.println(acteur.getIdentite());
                	}
                    break;
                case 3:
                    // Code pour l'affichage des films entre 2 années données
                	System.out.println("1ère année : ");
                	String annee1 = sc.nextLine();
                	System.out.println("2ème année : ");
                	String annee2 = sc.nextLine();
                	List<Film> filmByAnnee = filmDao.getFilmByAnnee(annee1, annee2);
                	System.out.println("Voici la liste des films entre " + annee1 + " et " + annee2 + " :");
                	for (Film f : filmByAnnee) {
    					System.out.println(f.getNom());
    				}
                    break;
                case 4:
                    // Code pour l'affichage des films communs à 2 acteurs/actrices
                	System.out.println("1ér(e) acteur/actrice :");
                	String nomActeur1 = sc.nextLine();
                	System.out.println("2èm(e) acteur/actrice :");
                	String nomActeur2 = sc.nextLine();
                	Acteur acteur1 = acteurDao.getActeur(nomActeur1);
                	Acteur acteur2 = acteurDao.getActeur(nomActeur2);
                	List<Film> filmsCommun = filmDao.getFilmByActeurs(acteur1, acteur2);
                	System.out.println("Voici la filmographie commune de " + acteur1.getIdentite() + " et " + acteur2.getIdentite() + " :");
                	for(Film f : filmsCommun) {
                		System.out.println(f.getNom());
                	}
        			break;
                case 5:
                    // Code pour l'affichage des acteurs communs à 2 films
                	System.out.println("Nom du film 1 :");
                	String nomFilm1 = sc.nextLine();
                	System.out.println("Nom du film 2 :");
                	String nomFilm2 = sc.nextLine();
                	Film film1 = filmDao.getFilmByName(nomFilm1);
                	Film film2 = filmDao.getFilmByName(nomFilm2);
                	List<Acteur> acteursCommun = acteurDao.getActeurByFilm(film1, film2);
                	System.out.println("Voici les acteurs commun des films " + film1.getNom() + " et " + film2.getNom() + " :");
                	for(Acteur a : acteursCommun) {
                		System.out.println(a.getIdentite());
                	}
                    break;
                case 6:
                    // Code pour l'affichage des films entre 2 années données avec un acteur au casting
                	System.out.println("1ère année : ");
                	String annee01 = sc.nextLine();
                	System.out.println("2ème année : ");
                	String annee02 = sc.nextLine();
                	System.out.println("Acteur/actrice :");
                	String nomActeurAnnees = sc.nextLine();
                	Acteur identiteActeurAnnees = acteurDao.getActeur(nomActeurAnnees);
                	List<Film> filmsAnneesActeur = filmDao.getFilmByAnneeActeur(annee01, annee02, identiteActeurAnnees);
                	System.out.println("Les films sortis entre l'année : " + annee01 + " et " + annee02
    						+ " avec " + identiteActeurAnnees.getIdentite() + ", sont :");
    				for (Film f : filmsAnneesActeur) {
    					System.out.println(f.getNom());
    				}
                    break;
                case 7:
                    quitter = true;
                    System.out.println("Fin de l'application. Merci !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez choisir une option de 1 à 7.");
            }
        }
        sc.close();
	}
}