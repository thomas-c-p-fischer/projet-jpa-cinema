/** Package où se trouve la class */
package fr.diginamic.Entities;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/** Nom de la classe */
@Entity
@Table(name="LIEU_NAISSANCE")
public class LieuNaissance {

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/** nom */
	@Column(name = "NOM", length = 500, unique = true)
	private String nom;
	
	/** acteurs */
	@OneToMany(mappedBy = "lieuNaissance")
	private List<Acteur> acteurs = new ArrayList<>();
	
	/** realisateurs */
	@OneToMany(mappedBy = "lieuNaissance")
	private List<Realisateur> realisateurs = new ArrayList<>();

	/** Constructeur
	 * 
	 */
	public LieuNaissance() {
	}

	/** Constructeur
	 * @param nom
	 */
	public LieuNaissance(String nom) {
		super();
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "[nom=" + nom + "]";
	}

	/** Méthode de recherche du lieu de naissance par son nom
	 * @param List<LieuNaissance> lieuNaissances
	 * @param String nom
	 * @return LieuNaissance lieuNaissance
	 */
	public static LieuNaissance rechercheParNom(List<LieuNaissance> lieuNaissances, String nom) {
		for(LieuNaissance l : lieuNaissances) {
			if(l.getNom().equals(nom)) {
					return l;
			}
		}
		return null;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LieuNaissance other = (LieuNaissance) obj;
		return Objects.equals(nom, other.nom);
	}

	/** Getter
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/** Setter
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/** Getter
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/** Setter
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/** Getter
	 * @return the acteurs
	 */
	public List<Acteur> getActeurs() {
		return acteurs;
	}

	/** Setter
	 * @param acteurs the acteurs to set
	 */
	public void setActeurs(List<Acteur> acteurs) {
		this.acteurs = acteurs;
	}

	/** Getter
	 * @return the realisateurs
	 */
	public List<Realisateur> getRealisateurs() {
		return realisateurs;
	}

	/** Setter
	 * @param realisateurs the realisateurs to set
	 */
	public void setRealisateurs(List<Realisateur> realisateurs) {
		this.realisateurs = realisateurs;
	}
}