package ch.gibm.facade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.PersistenceException;

import ch.gibm.dao.EntityManagerHelper;
import ch.gibm.dao.LanguageDAO;
import ch.gibm.entity.Language;

public class LanguageFacade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private LanguageDAO languageDAO = new LanguageDAO();

	public void createLanguage(Language language) {
		EntityManagerHelper.beginTransaction();
		try {
			languageDAO.save(language);
			EntityManagerHelper.commit();			
		}catch(PersistenceException e) {
			if(EntityManagerHelper.isActive()) {
				EntityManagerHelper.rollback();
			}
		}finally {
			EntityManagerHelper.closeEntityManager();
		}

	}

	public void updateLanguage(Language language) {
		EntityManagerHelper.beginTransaction();
		try {
			Language persistedLng = languageDAO.find(language.getId());
			persistedLng.setName(language.getName());
			languageDAO.update(persistedLng);
			EntityManagerHelper.commit();			
		}catch(PersistenceException e) {
			if(EntityManagerHelper.isActive()) {
				EntityManagerHelper.rollback();
			}
		}finally {
			EntityManagerHelper.closeEntityManager();
		}

	}
	
	public void deleteLanguage(Language language) {
		EntityManagerHelper.beginTransaction();
		try {
			Language persistedLng = languageDAO.findReferenceOnly(language.getId());
			languageDAO.delete(persistedLng);
			EntityManagerHelper.commit();			
		}catch(PersistenceException e) {
			if(EntityManagerHelper.isActive()) {
				EntityManagerHelper.rollback();
			}
		}finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public Language findLanguage(int languageId) {
		EntityManagerHelper.beginTransaction();
		Language language = languageDAO.find(languageId);
		try {
			
			EntityManagerHelper.commit();			
		}catch(PersistenceException e) {
			if(EntityManagerHelper.isActive()) {
				EntityManagerHelper.rollback();
			}
		}finally {
			EntityManagerHelper.closeEntityManager();
		}

		return language;
	}

	public List<Language> listAll() {
		EntityManagerHelper.beginTransaction();
		List<Language> result = languageDAO.findAll();
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
}