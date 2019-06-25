package ch.gibm.facade;

import java.util.List;

import javax.persistence.PersistenceException;

import ch.gibm.dao.EntityManagerHelper;
import ch.gibm.dao.UserDAO;
import ch.gibm.entity.User;

public class UserFacade {
	private static final long serialVersionUID = 1L;

	private UserDAO userDAO = new UserDAO();
	
	
	public User getUserByName(String name) {
		EntityManagerHelper.beginTransaction();
		User user = this.userDAO.findByName(name);
		try {
			EntityManagerHelper.commit();			
		}catch(PersistenceException e) {
			if(EntityManagerHelper.isActive()) {
				EntityManagerHelper.rollback();
			}
		}finally {
			EntityManagerHelper.closeEntityManager();
		}
		return user;
		
	}

	public void createUser(User user) {
		EntityManagerHelper.beginTransaction();
		try {
			this.userDAO.save(user);
			EntityManagerHelper.commit();			
		}catch(PersistenceException e) {
			if(EntityManagerHelper.isActive()) {
				EntityManagerHelper.rollback();
			}
		}finally {
			EntityManagerHelper.closeEntityManager();
		}
		
	}

	public void updateUser(User user) {
		EntityManagerHelper.beginTransaction();
		try {
			this.userDAO.update(user);
			EntityManagerHelper.commit();			
		}catch(PersistenceException e) {
			if(EntityManagerHelper.isActive()) {
				EntityManagerHelper.rollback();
			}
		}finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	public void deleteUser(User user) {
		EntityManagerHelper.beginTransaction();
		try {
			User persistedUserWithIdOnly = this.userDAO.findReferenceOnly(user.getId());
			this.userDAO.delete(persistedUserWithIdOnly);
			EntityManagerHelper.commit();			
		}catch(PersistenceException e) {
			if(EntityManagerHelper.isActive()) {
				EntityManagerHelper.rollback();
			}
		}finally {
			EntityManagerHelper.closeEntityManager();
		}

	}

	public User findUser(int userId) {
		EntityManagerHelper.beginTransaction();
		User user = this.userDAO.find(userId);
		try {
			EntityManagerHelper.commit();			
		}catch(PersistenceException e) {
			if(EntityManagerHelper.isActive()) {
				EntityManagerHelper.rollback();
			}
		}finally {
			EntityManagerHelper.closeEntityManager();
		}
		
		return user;
	}

	public List<User> listAll() {
		EntityManagerHelper.beginTransaction();
		List<User> result = this.userDAO.findAll();
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
