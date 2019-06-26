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
		User userAdmin = userFacade.getUserByName("admin");
		if (userAdmin == null) {
			userAdmin = new User();
			userAdmin.setRole("ADMIN");
			userAdmin.setPassword("admin");
			userAdmin.setUsername("admin");

			userFacade.createUser(userAdmin);
		}
		User userBjoern = userFacade.getUserByName("bjoern.pfoster");
		if (userBjoern == null) {
			userBjoern = new User();
			userBjoern.setRole("USER");
			userBjoern.setPassword("bjoern");
			userBjoern.setUsername("bjoern.pfoster");

			userFacade.createUser(userBjoern);
		}
		User userNicola = userFacade.getUserByName("nicola.hasler");
		if (userNicola == null) {
			userNicola = new User();
			userNicola.setRole("USER");
			userNicola.setPassword("nicola");
			userNicola.setUsername("nicola.hasler");

			userFacade.createUser(userNicola);
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
	public static boolean isActive() {
		return getEntityManager().getTransaction().isActive();
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
