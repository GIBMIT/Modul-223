package ch.gibm.dao;

import java.util.HashMap;
import java.util.Map;

import ch.gibm.entity.User;

public class UserDAO extends GenericDAO<User> {

	private static final long serialVersionUID = 1L;

	public UserDAO() {
		super(User.class);
	}
	
	public User findByName(String username) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("username", username);
		
		return super.findOneResult(User.FIND_BY_USERNAME, parameters);
	}

	public void delete(User user) {
		super.delete(user.getId(), User.class);
	}
}
