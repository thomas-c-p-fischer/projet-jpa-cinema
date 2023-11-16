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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/** Nom de la classe */
@Entity
@Table(name="FILM")
public class Film {
	
	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/** id_imdb */
	@Column(name = "ID_IMDB", length = 50, nullable = false, unique = true)
	private String id_imdb;
	
	/** nom */
	@Column(name = "NOM", length = 200, nullable = false)
	private String nom;
	
	/** annee */
	@Column(name = "ANNEE", length = 10, nullable = false)
	private String annee;
	
	/** rating */
	@Column(name = "RATING", length = 10, nullable = false)
	private String rating;
	
	/** url */
	@Column(name = "URL", length = 250, nullable = false, unique = true)
	private String url;
	
	/** lieu_tournage */
	@Column(name = "LIEU_TOURNAGE", length = 250, nullable = false)
	private String lieu_tournage;
	
	/** resume */
	@Column(name = "RESUME", length = 350, nullable = false)
	private String resume;
	
	/** roles */
	@OneToMany(mappedBy = "film")
	private List<Role> roles = new ArrayList<>();
	
	/** pays */
	@ManyToOne
	@JoinColumn(name = "ID_PAYS")
	private Pays pays;
	
	/** langue */
	@ManyToOne
	@JoinColumn(name = "ID_LANGUE")
	private Langue langue;
	
	/** acteurs */
	@ManyToMany
	@JoinTable(name = "CASTING_PRINCIPAL",
				joinColumns = @JoinColumn(name="ID_FILM", referencedColumnName = "ID"),
				inverseJoinColumns = @JoinColumn(name="ID_ACTEUR", referencedColumnName = "ID"))
	private List<Acteur> acteurs = new ArrayList<>();
	
	/** genres */
	@ManyToMany
	@JoinTable(name = "FILM_GENRE",
				joinColumns = @JoinColumn(name="ID_FILM", referencedColumnName = "ID"),
				inverseJoinColumns = @JoinColumn(name="ID_GENRE", referencedColumnName = "ID"))
	private List<Genre> genres = new ArrayList<>();
	
	/** films */
	@ManyToMany
	@JoinTable(name = "FILM_REALISATEUR",
				joinColumns = @JoinColumn(name="ID_FILM", referencedColumnName = "ID"),
				inverseJoinColumns = @JoinColumn(name="ID_REALISATEUR", referencedColumnName = "ID"))
	private List<Realisateur> realisateurs = new ArrayList<>();

	/** Constructeur
	 * 
	 */
	public Film() {
	}

	/** Constructeur
	 * @param id_imdb
	 * @param nom
	 * @param annee
	 * @param rating
	 * @param url
	 * @param lieu_tournage
	 * @param resume
	 */
	public Film(String id_imdb, String nom, String annee, String rating, String url, String lieu_tournage,
			String resume) {
		super();
		this.id_imdb = id_imdb;
		this.nom = nom;
		this.annee = annee;
		this.rating = rating;
		this.url = url;
		this.lieu_tournage = lieu_tournage;
		this.resume = resume;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", id_imdb=" + id_imdb + ", nom=" + nom + ", annee=" + annee + ", rating=" + rating
				+ ", url=" + url + ", lieu_tournage=" + lieu_tournage + ", resume=" + resume + ", roles=" + roles
				+ ", pays=" + pays + ", langue=" + langue + ", acteurs=" + acteurs + ", genres=" + genres
				+ ", realisateurs=" + realisateurs + "]";
	}
	
	/** Méthode de recherche de films par l'id IMDB
	 * @param List<Film> films
	 * @param String idImdb
	 * @return Film film
	 */
	public Film rechercheParImdb(List<Film> films, String idImdb) {
		Film film = null;
		for(Film f : films) {
			if(f.getId_imdb().equals(idImdb)) {
				film = f;
				break;
			}
		}
		return film;
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
	 * @return the annee
	 */
	public String getAnnee() {
		return annee;
	}

	/** Setter
	 * @param annee the annee to set
	 */
	public void setAnnee(String annee) {
		this.annee = annee;
	}

	/** Getter
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/** Setter
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
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
	 * @return the lieu_tournage
	 */
	public String getLieu_tournage() {
		return lieu_tournage;
	}

	/** Setter
	 * @param lieu_tournage the lieu_tournage to set
	 */
	public void setLieu_tournage(String lieu_tournage) {
		this.lieu_tournage = lieu_tournage;
	}

	/** Getter
	 * @return the resume
	 */
	public String getResume() {
		return resume;
	}

	/** Setter
	 * @param resume the resume to set
	 */
	public void setResume(String resume) {
		this.resume = resume;
	}

	/** Getter
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/** Setter
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/** Getter
	 * @return the pays
	 */
	public Pays getPays() {
		return pays;
	}

	/** Setter
	 * @param pays the pays to set
	 */
	public void setPays(Pays pays) {
		this.pays = pays;
	}

	/** Getter
	 * @return the langue
	 */
	public Langue getLangue() {
		return langue;
	}

	/** Setter
	 * @param langue the langue to set
	 */
	public void setLangue(Langue langue) {
		this.langue = langue;
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
	 * @return the genres
	 */
	public List<Genre> getGenres() {
		return genres;
	}

	/** Setter
	 * @param genres the genres to set
	 */
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
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