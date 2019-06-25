package ch.gibm.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
@NamedQuery(name = "User.findByUsername", query = "select u from User u where u.username = :username")
public class User {
	private static final long serialVersionUID = 1L;
	public static final String FIND_BY_USERNAME = "User.findByUsername";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String role;
	private String email;

	@Version
	private long version;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<Person> people = null;

	public boolean isAdmin() {
		return this.role.equalsIgnoreCase("Admin");
	}

	public boolean isUser() {
		return this.role.equalsIgnoreCase("User");
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void addPerson(Person person) {
		if(people == null){
			people = new ArrayList<Person>();
		}
		this.people.add(person);
		person.setUser(this);
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}

	public List<Person> getPeople() {
		return this.people; 
	}
	
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
