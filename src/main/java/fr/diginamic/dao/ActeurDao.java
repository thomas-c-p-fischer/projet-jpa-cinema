/** Package où se trouve l'interface */
package fr.diginamic.dao;

import java.util.List;
import fr.diginamic.Entities.Acteur;

/** Interface Dao pour l'entité Acteur */
public interface ActeurDao {
	boolean existe(List<Acteur> acteurs);
}