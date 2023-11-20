/** Package contenant les Entités */
package fr.diginamic.Entities;

import java.time.LocalDate;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** Nom de la classe */
@Entity
@Table(name="REALISATEUR")
public class Realisateur {
	
	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/** id_imdb */
	@Column(name = "ID_IMDB", length = 50, nullable = false, unique = true)
	private String id_imdb;
	
	/** identite */
	@Column(name = "IDENTITE", length = 50, nullable = false)
	private String identite;
	
	@Column(name = "DATE_NAISSANCE")
	private LocalDate date_naissance;
	
	/** url */
	@Column(name = "URL", length = 250, nullable = false, unique = true)
	private String url;
	
	/** lieuNaissance */
	@ManyToOne
	@JoinColumn(name = "ID_LIEU_NAISSANCE")
	private LieuNaissance lieuNaissance;
	
	/** films */
	@ManyToMany
	@JoinTable(name = "FILM_REALISATEUR",
				joinColumns = @JoinColumn(name="ID_REALISATEUR", referencedColumnName = "ID"),
				inverseJoinColumns = @JoinColumn(name="ID_FILM", referencedColumnName = "ID"))
	private List<Film> films = new ArrayList<>();

	/** Constructeur
	 * 
	 */
	public Realisateur() {
	}
	
	/** Constructeur
	 * @param id_imdb
	 * @param identite
	 * @param date_naissance
	 * @param url
	 * @param lieuNaissance
	 */
	public Realisateur(String id_imdb, String identite, LocalDate date_naissance, String url,
			LieuNaissance lieuNaissance) {
		super();
		this.id_imdb = id_imdb;
		this.identite = identite;
		this.date_naissance = date_naissance;
		this.url = url;
		this.lieuNaissance = lieuNaissance;
	}



	@Override
	public String toString() {
		return "[id_imdb=" + id_imdb + ", identite=" + identite + ", date_naissance="
				+ date_naissance + ", url=" + url + ", lieuNaissance=" + lieuNaissance + "]";
	}
	
	/** Méthode de recherche d'acteur par l'id IMDB
	 * @param List<Realisateur> realisateurs
	 * @param String idImdb
	 * @return Realisateur realisateur
	 */
	public static Realisateur rechercheParImdb(List<Realisateur> realisateurs, String idImdb) {
		Realisateur realisateur = null;
		for(Realisateur r : realisateurs) {
			if(r.getId_imdb().equals(idImdb)) {
				realisateur = r;
				break;
			}
		}
		return realisateur;
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
	 * @return the id_imdb
	 */
	public String getId_imdb() {
		return id_imdb;
	}

	/** Setter
	 * @param id_imdb the id_imdb to set
	 */
	public void setId_imdb(String id_imdb) {
		this.id_imdb = id_imdb;
	}

	/** Getter
	 * @return the identite
	 */
	public String getIdentite() {
		return identite;
	}

	/** Setter
	 * @param identite the identite to set
	 */
	public void setIdentite(String identite) {
		this.identite = identite;
	}

	/** Getter
	 * @return the date_naissance
	 */
	public LocalDate getDate_naissance() {
		return date_naissance;
	}

	/** Setter
	 * @param date_naissance the date_naissance to set
	 */
	public void setDate_naissance(LocalDate date_naissance) {
		this.date_naissance = date_naissance;
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
	 * @return the lieuNaissance
	 */
	public LieuNaissance getLieuNaissance() {
		return lieuNaissance;
	}

	/** Setter
	 * @param lieuNaissance the lieuNaissance to set
	 */
	public void setLieuNaissance(LieuNaissance lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
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