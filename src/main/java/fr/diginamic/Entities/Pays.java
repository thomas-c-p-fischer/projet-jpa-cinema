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
@Table(name="PAYS")
public class Pays {
	
	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/** nom */
	@Column(name = "NOM", length = 200, nullable = false, unique = true)
	private String nom;
	
	/** url */
	@Column(name = "URL", length = 250, nullable = false, unique = true)
	private String url;
	
	/** films */
	@OneToMany(mappedBy = "pays")
	private List<Film> films = new ArrayList<>();

	/** Constructeur
	 * 
	 */
	public Pays() {
	}

	/** Constructeur pour la liste des pays
	 * @param nom
	 * @param url
	 */
	public Pays(String nom, String url) {
		super();
		this.nom = nom;
		this.url = url;
	}

	@Override
	public String toString() {
		return "Pays [id=" + id + ", nom=" + nom + ", url=" + url + ", films=" + films + "]";
	}
	
	/** Méthode de recherche du pays par son nom
	 * @param List<Pays> listPays
	 * @param String nom
	 * @return Pays pays
	 */
	public Pays rechercheParNom(List<Pays> listPays, String nom) {
		Pays pays = null;
		for(Pays p : listPays) {
			if(p.getNom().equals(nom)) {
				pays = p;
				break;
			}
		}
		return pays;
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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/** Setter
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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