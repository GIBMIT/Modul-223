package ch.gibm.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

@Entity
@NamedQuery(name = "Person.findPersonByIdWithLanguages", query = "select p from Person p left join fetch p.languages where p.id = :personId")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String FIND_PERSON_BY_ID_WITH_LANGUAGES = "Person.findPersonByIdWithLanguages";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String nachname;

	@Version
	private long version;
	
	@ManyToMany
	private List<Language> languages;
	
	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "person_creator_fk"))
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			Person person = (Person) obj;
			return person.getId() == id;
		}

		return false;
	}
}