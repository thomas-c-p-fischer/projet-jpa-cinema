/** Package où se trouve l'interface */
package fr.diginamic.dao;

import java.util.List;

import fr.diginamic.Entities.Langue;
import fr.diginamic.Entities.Pays;

/** Interface Dao pour l'entité Langue */
public interface LangueDao {
	boolean existe(List<Langue> langues);
}