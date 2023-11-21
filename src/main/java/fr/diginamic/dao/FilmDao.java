/** Package où se trouve l'interface */
package fr.diginamic.dao;

import java.util.List;
import fr.diginamic.Entities.Acteur;
import fr.diginamic.Entities.Film;

/** Interface Dao pour l'entité Acteur */
public interface FilmDao {
	public List<Film> getFilmByAnneeActeur(String debut, String fin, Acteur acteur);
	public List<Film> getFilmByActeurs(Acteur acteur1, Acteur acteur2);
	public List<Film> getFilmByAnnee(String debut, String fin);
	public List<Film> getFilmByActeur(Acteur acteur);
	public Film getFilmByName(String nom);
}