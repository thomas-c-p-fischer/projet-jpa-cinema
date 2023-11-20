/** Package où se trouve la class */
package fr.diginamic.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/** Nom de la classe */
@Entity
@Table(name="LANGUE")
public class Langue {
	
	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/** nom */
	@Column(name = "NOM", length = 200, nullable = false, unique = true)
	private String nom;
	
	/** films */
	@OneToMany(mappedBy = "langue")
	private List<Film> films = new ArrayList<>();

	/** Constructeur
	 * 
	 */
	public Langue() {
	}
	
	/** Constructeur pour la liste de langue
	 * @param nom
	 */
	public Langue(String nom) {
		super();
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "[nom=" + nom + "]";
	}
	
	/** Méthode de recherche de la langue par son nom
	 * @param List<Langue> langues
	 * @param String nom
	 * @return Langue langue
	 */
	public static Langue rechercheParNom(List<Langue> langues, String nom) {
		Langue langue = null;
		for(Langue l : langues) {
			if(l.getNom().equals(nom)) {
				langue = l;
				break;
			}
		}
		return langue;
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
	 * @return the films
	 */
	public List<Film> getFilms() {
		return films;
	}

	/** Setter
	 * @param films the films to set
	 */
	public void setFilms(List<Film> films) {
		this.films = films;
	}
}