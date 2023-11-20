/** Package où se trouve l'interface */
package fr.diginamic.dao;

import java.util.List;
import fr.diginamic.Entities.LieuNaissance;

/** Interface Dao pour l'entité LieuNaissance */
public interface LieuNaissanceDao {
	boolean existe(List<LieuNaissance> lieuNaissances);
}