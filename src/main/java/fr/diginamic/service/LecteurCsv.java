/** Package où se trouve la class */
package fr.diginamic.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import fr.diginamic.Entities.Acteur;
import fr.diginamic.Entities.Film;
import fr.diginamic.Entities.Genre;
import fr.diginamic.Entities.Langue;
import fr.diginamic.Entities.LieuNaissance;
import fr.diginamic.Entities.Pays;
import fr.diginamic.Entities.Realisateur;
import fr.diginamic.Entities.Role;

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
					String nom = colonnes[0];
					String url = colonnes[1];
					Pays pays = new Pays(nom, url);
					donneesPays.add(pays);
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
	
	/** Méthode pour parseLieuNaissance
	 * @param fichierActeur
	 * @return List<String>
	 */
	public List<String> parseStringLieuNaissance(String fichierActeur) { 
		
		List<String> stringLieuNaissance = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(fichierActeur);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + fichierActeur);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] colonnes = line.split(";");
				String lieuNaissance = colonnes[3];
				lieuNaissance = lieuNaissance.replaceAll("é", "e");
				lieuNaissance = lieuNaissance.replaceAll("è", "e");
				lieuNaissance = lieuNaissance.replaceAll("Î", "I");
				lieuNaissance = lieuNaissance.replaceAll("ü", "u");
				lieuNaissance = lieuNaissance.trim();
				if(!lieuNaissance.isEmpty()) {
					lieuNaissance = lieuNaissance.substring(0, 1).toUpperCase() + lieuNaissance.substring(1);
				}
				if (!lieuNaissance.isEmpty()) {					
					if (!stringLieuNaissance.contains(lieuNaissance)) {
						stringLieuNaissance.add(lieuNaissance);
					}
				}
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return stringLieuNaissance;
	}

	/** Méthode de parsing du fichier acteurs.csv et realisateurs.csv pour récupérer
	 * les lieux de naissances
	 * @param fichierReal
	 * @param fichierAct
	 * @return
	 */
	public List<LieuNaissance> parseLieuNaissance(String fichierReal, String fichierAct) {
		
		List<LieuNaissance> lieuxNaissances = new ArrayList<>();
		List<String> donneesLieuNaissance = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(fichierReal);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + fichierReal);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String ligne;
			lecteur.readLine();
			while ((ligne = lecteur.readLine()) != null) {
				String[] colonnes = ligne.split(";");
				String nomLieu = colonnes[3];
				nomLieu = nomLieu.replaceAll("é", "e");
				nomLieu = nomLieu.replaceAll("è", "e");
				nomLieu = nomLieu.replaceAll("Î", "I");
				nomLieu = nomLieu.replaceAll("ü", "u");
				nomLieu = nomLieu.trim();
				if(!nomLieu.isEmpty()) {
					nomLieu = nomLieu.substring(0, 1).toUpperCase() + nomLieu.substring(1);
				}
				LecteurCsv lectureCsv = new LecteurCsv();
				donneesLieuNaissance = lectureCsv.parseStringLieuNaissance(fichierAct);
				if (!nomLieu.isEmpty()) {
					if (!donneesLieuNaissance.contains(nomLieu)) {
						
						donneesLieuNaissance.add(nomLieu);
					}
				}
			}
			System.out.println(donneesLieuNaissance);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		for(String lieuNaissance : donneesLieuNaissance) {
			LieuNaissance nouveauLieuNaissance = new LieuNaissance(lieuNaissance);
			lieuxNaissances.add(nouveauLieuNaissance);
		}
		return lieuxNaissances;
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

	/** Méthode de parsing du fichier role.csv
	 * @param fichierRole
	 * @param films
	 * @param acteurs
	 * @return
	 */
	public List<Role> parseRole(String fichierRole, List<Film> films, List<Acteur> acteurs) {

		List<Role> roles = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(fichierRole);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + fichierRole);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] colonnes = line.split(";", -1);

					String idmbFilm = colonnes[0];
					String idmbActeur = colonnes[1];
					String nomRole = colonnes[2];
					Film actuelFilm = Film.rechercheParImdb(films, idmbFilm);
					Acteur actuelActeur = Acteur.rechercheParImdb(acteurs, idmbActeur);
					Role role = new Role(nomRole);
					if(actuelFilm != null && actuelActeur != null) {
						role.setFilm(actuelFilm);
						role.setActeur(actuelActeur);
						roles.add(role);
					}			
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return roles;
	}

	//, String fichierFiReal
	/** Méthode de parsing du fichier realisateurs.csv
	 * @param fichierReal
	 * @param lieuxNaissances
	 * @return
	 */
	public List<Realisateur> parseRealisateur(String fichierReal, List<LieuNaissance> lieuxNaissances) {
		List<Realisateur> realisateurs = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(fichierReal);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + fichierReal);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] colonnes = line.split(";", -1);
				String imdb = colonnes[0];
				String identite = colonnes[1];
				LocalDate dateNaissance = null;
				if (!colonnes[2].isEmpty()) {
					try {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d yyyy ", Locale.ENGLISH);
						dateNaissance = LocalDate.parse(colonnes[2], formatter);
					} catch (Exception e) {

					}
				}
				String lieuNaissance = colonnes[3].trim();
				String url = colonnes[4];
				LieuNaissance lieuNaissanceActuel = LieuNaissance.rechercheParNom(lieuxNaissances,
						lieuNaissance);
				Realisateur actuelRealisateur = new Realisateur(imdb, identite, dateNaissance, url, lieuNaissanceActuel);
				realisateurs.add(actuelRealisateur);
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return realisateurs;
	}
	
	/** Méthode de parsing du fichier acteurs.csv
	 * @param fichierAct
	 * @param lieuxNaissances
	 * @return
	 */
	public List<Acteur> parseActeur(String fichierAct, List<LieuNaissance> lieuxNaissances) {
		List<Acteur> acteurs = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(fichierAct);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + fichierAct);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] colonnes = line.split(";", -1);
				String imdb = colonnes[0];
				String identite = colonnes[1];
				LocalDate dateNaissance = null;
				if (!colonnes[2].isEmpty()) {
					if(!colonnes[2].contains("-")){
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
						dateNaissance = LocalDate.parse(colonnes[2], formatter);
					}
				}
				String lieuNaissance = colonnes[3].trim();
				String url = colonnes[4];
			
				LieuNaissance lieuNaissanceActuel = LieuNaissance.rechercheParNom(lieuxNaissances,
						lieuNaissance);
				Acteur actuelActeur = new Acteur(imdb, identite, dateNaissance, url, lieuNaissanceActuel);
				acteurs.add(actuelActeur);
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return acteurs;
	}

	/** Méthode de parsing du fichier films.csv
	 * @param fichierFilm
	 * @param pays
	 * @param langues
	 * @param genres
	 * @return
	 */
	public List<Film> parseFilm(String fichierFilm, List<Pays> pays, List<Langue> langues,
			List<Genre> genres) {

		List<Film> films = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(fichierFilm);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + fichierFilm);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] colonnes = line.split(";");
				if (colonnes.length > 10) {
					colonnes[8] = colonnes[8] + colonnes[9];
					colonnes[9] = colonnes[10];
				}
				String nomPays = null;
				if (colonnes.length == 9) {
					nomPays = "Pas de pays";
				} else {
					nomPays = colonnes[9];
				}
				String idImdb = colonnes[0];
				String nom = colonnes[1];
				String annee = colonnes[2];
				String rating = colonnes[3];
				String url = colonnes[4];
				String lieuTournage = colonnes[5];
				String ligneGenre = colonnes[6];
				String langue = colonnes[7];
				String resume = colonnes[8];

				Pays actuelPays = Pays.rechercheParNom(pays, nomPays);
				Langue actuelLangue = Langue.rechercheParNom(langues, langue);

				List<Genre> arrayActuelGenre = new ArrayList<>();
				String[] listGenre = ligneGenre.split(",");
				for (String g : listGenre) {
					Genre actuelGenre = Genre.rechercheParNom(genres, g);
					arrayActuelGenre.add(actuelGenre);
				}

				Film actuelFilm = new Film(idImdb, nom, annee, rating, url, lieuTournage, resume);
				actuelFilm.setLangue(actuelLangue);
				actuelFilm.setGenres(arrayActuelGenre);
				actuelFilm.setPays(actuelPays);

				films.add(actuelFilm);
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return films;
	}
	
	/** Métode de parsing du fichier castingPrincipal.csv
	 * @param fichierCastingPrincipal
	 * @param films
	 * @param acteurs
	 */
	public void parseCastingPrincipal(String fichierCastingPrincipal, List<Film> films, List<Acteur> acteurs) {

		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(fichierCastingPrincipal);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + fichierCastingPrincipal);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] colonnes = line.split(";");
				String idbmFilm = colonnes[0];
				String idbmActeur = colonnes[1];

				Film actuelFilm = Film.rechercheParImdb(films, idbmFilm);

				Acteur actuelActeur = Acteur.rechercheParImdb(acteurs, idbmActeur);

				if (actuelFilm != null && actuelActeur != null) {
					actuelFilm.getActeurs().add(actuelActeur);
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
	}
	
	/** Méthode de parsingn du fichier film_realisateur.csv
	 * @param pathFile
	 * @param pathFilm
	 * @param pathRealisateur
	 */
	public void parseFilmRealisateur(String fichierFilmReal, List<Film> films, List<Realisateur> realisateurs) {

		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(fichierFilmReal);

		if (is == null) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + fichierFilmReal);
		}

		try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");
				String idbmFilm = tokens[0];
				String idbmRealisateur = tokens[1];

				Film actuelFilm = Film.rechercheParImdb(films, idbmFilm);

				Realisateur actuelRealisateur = Realisateur.rechercheParImdb(realisateurs, idbmRealisateur);

				if (actuelFilm != null && actuelRealisateur != null) {
					actuelFilm.getRealisateurs().add(actuelRealisateur);
				}

			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une erreur est survenue lors de la lecture du ficher .csv.");
		}
	}
}