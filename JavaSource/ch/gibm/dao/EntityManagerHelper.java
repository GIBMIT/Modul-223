package ch.gibm.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ch.gibm.entity.User;
import ch.gibm.facade.UserFacade;

public class EntityManagerHelper {

	private static EntityManagerFactory emf = null;
	private static final ThreadLocal<EntityManager> tLocal = new ThreadLocal<EntityManager>();

	public static void init() {
		emf = Persistence.createEntityManagerFactory("JSFAppPU");
		
		EntityManagerHelper.loadDefaultUsers();
	}

	private static void loadDefaultUsers() {
		UserFacade userFacade = new UserFacade();
		User user = userFacade.getUserByName("admin");
		if (user == null) {
			user = new User();
			user.setRole("ADMIN");
			user.setPassword("admin");
			user.setUsername("admin");

			userFacade.createUser(user);
		}
	}

	public static EntityManager getEntityManager() {
		EntityManager em = tLocal.get();

		if (em == null) {
			em = emf.createEntityManager();
			tLocal.set(em);
		}
		return em;
	}

	public static void beginTransaction() {
		getEntityManager().getTransaction().begin();
	}

	public static void rollback() {
		getEntityManager().getTransaction().rollback();
	}

	public static void commit() {
		getEntityManager().getTransaction().commit();
	}

	public static void commitAndCloseTransaction() {
		commit();
		closeEntityManager();
	}

	public static void closeEntityManager() {
		EntityManager em = tLocal.get();
		if (em != null) {
			em.close();
			tLocal.set(null);
		}
	}

	public static void closeEntityManagerFactory() {
		emf.close();
	}
}
