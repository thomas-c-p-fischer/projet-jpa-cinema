/** Package où se trouve la class */
package fr.diginamic.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.Entities.Film;
import fr.diginamic.Entities.Genre;
import fr.diginamic.Entities.Langue;
import fr.diginamic.Entities.LieuNaissance;
import fr.diginamic.Entities.Pays;

/** Nom de la classe */
public class LecteurCsv {

	/** Méthode de parsing du fichier pays.csv
	 * @param nomFichier
	 * @return List<Pays>
	 */
	public List<Pays> parsePays(String nomFichier) {

		List<Pays> donneesPays = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(nomFichier);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + nomFichier);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String ligne;
			lecteur.readLine();
			while ((ligne = lecteur.readLine()) != null) {
				String[] colonnes = ligne.split(";");
				if (colonnes.length == 2) {
					String nom = colonnes[0].trim();
					String url = colonnes[1].trim();
					Pays pays = new Pays(nom, url);
					donneesPays.add(pays);
				} else {
					// Gérer les lignes incorrectes
					System.err.println("Ligne incorrecte dans le fichier CSV: " + ligne);
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return donneesPays;
	}

	/** Méthode de parsing du fichier films.csv pour récupérer les langues
	 * @param nomFichier
	 * @return List<Langue>
	 */
	public List<Langue> parseLangue(String nomFichier) {

		List<Langue> langues = new ArrayList<>();
		List<String> donneesLangue = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(nomFichier);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + nomFichier);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String ligne;
			lecteur.readLine();
			while ((ligne = lecteur.readLine()) != null) {
				String[] colonnes = ligne.split(";");
				String nom = colonnes[7];
				if (!nom.isEmpty()) {
					if (!donneesLangue.contains(nom)) {
						donneesLangue.add(nom);
						Langue langue = new Langue(nom);
						langues.add(langue);
					}
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return langues;
	}
	
	/** Méthode de parsing du fichier acteurs.csv et realisateurs.csv
	 * pour récupérer les lieux de naissances
	 * @param nomFichier
	 * @return List<LieuNaissance>
	 */
	public List<LieuNaissance> parseLieuNaissance(String nomFichier) {

		List<LieuNaissance> lieuNaissances = new ArrayList<>();
		List<String> donneesLieuNaissance = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(nomFichier);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + nomFichier);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String ligne;
			lecteur.readLine();
			while ((ligne = lecteur.readLine()) != null) {
				String[] colonnes = ligne.split(";");
				String lieux = colonnes[3];
				if (!lieux.isEmpty()) {
					if (!donneesLieuNaissance.contains(lieux)) {
						donneesLieuNaissance.add(lieux);
						LieuNaissance lieuNaissance = new LieuNaissance(lieux);
						lieuNaissances.add(lieuNaissance);
					}
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return lieuNaissances;
	}
	
	/** Méthode de parsing du fichier films.csv pour récupérer les langues
	 * @param nomFichier
	 * @return List<Genre>
	 */
	public List<Genre> parseGenre(String nomFichier) {

        List<Genre> genres = new ArrayList<>();
        List<String> genreUnicite = new ArrayList<>();
        ClassLoader cl = getClass().getClassLoader();
        InputStream is = cl.getResourceAsStream(nomFichier);

        if (is == null) {
            throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + nomFichier);
        }

        try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
            String line;
            lecteur.readLine();
            while ((line = lecteur.readLine()) != null) {
                String[] colonnes = line.split(";");
                String nomGenre = colonnes[6];
                String[] arrayGenre = nomGenre.split(",");
                for (String nomGenres : arrayGenre) {
                    if (!nomGenres.isEmpty()) {
                        if (!genreUnicite.contains(nomGenres)) {
                        	genreUnicite.add(nomGenres);
                            Genre genre = new Genre(nomGenres);
                            genres.add(genre);
                        }
                    }
                }
            }

        } catch (

        IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
        }
        return genres;
    }

//	public List<Film> parseFilm(String nomFichier) {
//
//        List<Film> donneesFilms = new ArrayList<>();
//        ClassLoader cl = getClass().getClassLoader();
//        InputStream is = cl.getResourceAsStream(nomFichier);
//
//        if (is == null) {
//            throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + nomFichier);
//        }
//
//        try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
//            String line;
//            lecteur.readLine();
//            while ((line = lecteur.readLine()) != null) {
//                String[] tokens = line.split(";");
//                System.out.println(line);
//                
//                if (tokens.length > 10) {
//                    tokens[8] = tokens[8] + tokens[9];
//                    tokens[9] = tokens[10];
//                    System.out.println(tokens[8]);
//                }
//

//                if (tokens.length == 9) {
//
//                    pays = "Pas de pays";
//
//                } else {
//
//                    pays = tokens[9];
//                }
//
//                String idImdb = tokens[0];
//                System.out.println(idImdb);
//                String nom = tokens[1];
//                String annee = tokens[2];
//                String rating = tokens[3];
//                String url = tokens[4];
//                String lieuTournage = tokens[5];
//                String genre = tokens[6];
//                String langue = tokens[7];
//                String resume = tokens[8];
//
//                Film actuelFilm = new Film(idImdb, nom, annee, rating, url, lieuTournage, resume);
//
//                donneesFilms.add(actuelFilm);
//            }
//
//        } catch (
//
//        IOException e) {
//            System.err.println(e.getMessage());
//            throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
//        }
//        return donneesFilms;
//    }
}