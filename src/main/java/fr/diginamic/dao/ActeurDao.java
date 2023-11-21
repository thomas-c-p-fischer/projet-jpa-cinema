/** Package où se trouve l'interface */
package fr.diginamic.dao;

import java.util.List;

import fr.diginamic.Entities.Acteur;
import fr.diginamic.Entities.Film;

/** Interface Dao pour l'entité Acteur */
public interface ActeurDao {
	public List<Acteur> getActeurByFilm(Film film1, Film film2);
	public Acteur getActeur(String nomActeur);
	public List<Acteur> getActeurByFilm(Film film);
}