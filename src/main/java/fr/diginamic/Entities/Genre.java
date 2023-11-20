/** Package où se trouve la class */
package fr.diginamic.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/** Nom de la classe */
@Entity
@Table(name="GENRE")
public class Genre {
	
	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/** nom */
	@Column(name = "NOM", length = 200, nullable = false, unique = true)
	private String nom;
	
	/** films */
	@ManyToMany
	@JoinTable(name = "FILM_GENRE",
				joinColumns = @JoinColumn(name="ID_GENRE", referencedColumnName = "ID"),
				inverseJoinColumns = @JoinColumn(name="ID_FILM", referencedColumnName = "ID"))
	private List<Film> films = new ArrayList<>();

	/** Constructeur
	 * 
	 */
	public Genre() {
	}

	/** Constructeur
	 * @param nom
	 */
	public Genre(String nom) {
		super();
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "[nom=" + nom + "]";
	}
	
	/** Méthode de recherche du genre par son nom
	 * @param List<Genre> genres
	 * @param String nom
	 * @return Genre genre
	 */
	public static Genre rechercheParNom(List<Genre> genres, String nom) {
		Genre genre = null;
		for(Genre g : genres) {
			if(g.getNom().equals(nom)) {
				genre = g;
				break;
			}
		}
		return genre;
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