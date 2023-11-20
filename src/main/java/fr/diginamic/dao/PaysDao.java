/** Package où se trouve l'interface */
package fr.diginamic.dao;

import java.util.List;

import fr.diginamic.Entities.Pays;

/** Interface Dao pour l'entité Pays */
public interface PaysDao {
	boolean existe(List<Pays> pays);
}