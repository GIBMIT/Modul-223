package ch.gibm.facade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.PersistenceException;

import ch.gibm.dao.EntityManagerHelper;
import ch.gibm.dao.LanguageDAO;
import ch.gibm.dao.PersonDAO;
import ch.gibm.entity.Language;
import ch.gibm.entity.Person;

public class PersonFacade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private PersonDAO personDAO = new PersonDAO();
	private LanguageDAO languageDAO = new LanguageDAO();

	public void createPerson(Person person) {
		EntityManagerHelper.beginTransaction();
		try {
			personDAO.save(person);
			EntityManagerHelper.commit();			
		}catch(PersistenceException e) {
			if(EntityManagerHelper.isActive()) {
				EntityManagerHelper.rollback();
			}
		}finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public void updatePerson(Person person) {
		EntityManagerHelper.beginTransaction();
		try {
			Person persistedPerson = personDAO.find(person.getId());
			persistedPerson.setName(person.getName());
			persistedPerson.setNachname(person.getNachname());
			EntityManagerHelper.commit();			
		}catch(PersistenceException e) {
			if(EntityManagerHelper.isActive()) {
				EntityManagerHelper.rollback();
			}
		}finally {
			EntityManagerHelper.closeEntityManager();
		}
	}
	
	public void deletePerson(Person person){
		EntityManagerHelper.beginTransaction();
		try {
			Person persistedPersonWithIdOnly = personDAO.findReferenceOnly(person.getId());
			personDAO.delete(persistedPersonWithIdOnly);
			EntityManagerHelper.commit();			
		}catch(PersistenceException e) {
			if(EntityManagerHelper.isActive()) {
				EntityManagerHelper.rollback();
			}
		}finally {
			EntityManagerHelper.closeEntityManager();
		}
		
	}

	public Person findPerson(int personId) {
		EntityManagerHelper.beginTransaction();
		Person person = personDAO.find(personId);
		try {
			EntityManagerHelper.commit();			
		}catch(PersistenceException e) {
			if(EntityManagerHelper.isActive()) {
				EntityManagerHelper.rollback();
			}
		}finally {
			EntityManagerHelper.closeEntityManager();
			
		}
		return person;
		
	}

	public List<Person> listAll() {
		EntityManagerHelper.beginTransaction();
		List<Person> result = personDAO.findAll();
		try {
			EntityManagerHelper.commit();			
		}catch(PersistenceException e) {
			if(EntityManagerHelper.isActive()) {
				EntityManagerHelper.rollback();
			}
		}finally {
			EntityManagerHelper.closeEntityManager();
		}
		
		return result;
	}

	public Person findPersonWithAllLanguages(int personId) {
		EntityManagerHelper.beginTransaction();
		Person person = personDAO.findPersonWithAllLanguages(personId);
		try {
			EntityManagerHelper.commit();			
		}catch(PersistenceException e) {
			if(EntityManagerHelper.isActive()) {
				EntityManagerHelper.rollback();
			}
		}finally {
			EntityManagerHelper.closeEntityManager();
		}
		
		return person;
	}

	public void addLanguageToPerson(int languageId, int personId) {
		EntityManagerHelper.beginTransaction();
		try {
			Language language = languageDAO.find(languageId);
			Person person = personDAO.find(personId);
			person.getLanguages().add(language);
			language.getPersons().add(person);
			EntityManagerHelper.commit();			
		}catch(PersistenceException e) {
			if(EntityManagerHelper.isActive()) {
				EntityManagerHelper.rollback();
			}
		}finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public void removeLanguageFromPerson(int languageId, int personId) {
		EntityManagerHelper.beginTransaction();
		try {
			Language language = languageDAO.find(languageId);
			Person person = personDAO.find(personId);
			person.getLanguages().remove(language);
			language.getPersons().remove(person);
			EntityManagerHelper.commit();			
		}catch(PersistenceException e) {
			if(EntityManagerHelper.isActive()) {
				EntityManagerHelper.rollback();
			}
		}finally {
			EntityManagerHelper.closeEntityManager();
		}

	}
}