package ch.gibm.facade;

import java.util.List;

import ch.gibm.dao.EntityManagerHelper;
import ch.gibm.dao.UserDAO;
import ch.gibm.entity.User;

public class UserFacade {
	private static final long serialVersionUID = 1L;

	private UserDAO userDAO = new UserDAO();
	private UserDAO UserDAO = new UserDAO();
	
	public User getUserByName(String name) {
		EntityManagerHelper.beginTransaction();
		User user = this.userDAO.findByName(name);
		EntityManagerHelper.commitAndCloseTransaction();
		
		return user;
	}

	public void createUser(User user) {
		EntityManagerHelper.beginTransaction();
		this.userDAO.save(user);
		EntityManagerHelper.commitAndCloseTransaction();
	}

	public void updateUser(User user) {
		EntityManagerHelper.beginTransaction();
		this.userDAO.update(user);
		EntityManagerHelper.commitAndCloseTransaction();
	}

	public void deleteUser(User user) {
		EntityManagerHelper.beginTransaction();
		User persistedUserWithIdOnly = this.userDAO.findReferenceOnly(user.getId());
		this.userDAO.delete(persistedUserWithIdOnly);
		EntityManagerHelper.commitAndCloseTransaction();

	}

	public User findUser(int userId) {
		EntityManagerHelper.beginTransaction();
		User user = this.userDAO.find(userId);
		EntityManagerHelper.commitAndCloseTransaction();
		
		return user;
	}

	public List<User> listAll() {
		EntityManagerHelper.beginTransaction();
		List<User> result = this.userDAO.findAll();
		EntityManagerHelper.commitAndCloseTransaction();

		return result;
	}
}
