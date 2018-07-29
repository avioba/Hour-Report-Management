package manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Use this class to create entity manager .
 */
public class ManagerHelper {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projectmanager");

	public static EmployeeManager getEmployeeManager() {
		return new EmployeeManager(entityManagerFactory.createEntityManager());
	}

	public static CustomerManager getCustomerManager() {
		return new CustomerManager(entityManagerFactory.createEntityManager());
	}

	public static ProjectManager getProjectManager() {
		return new ProjectManager(entityManagerFactory.createEntityManager());
	}

	public static HourreportManager getHourreportManager() {
		return new HourreportManager(entityManagerFactory.createEntityManager());
	}

	public static UserManager getUserManager() {
		return new UserManager(entityManagerFactory.createEntityManager());
	}
	
	public static EmployeeProjectManager getEmployeeProjectManager() {
		return new EmployeeProjectManager(entityManagerFactory.createEntityManager());
	}

}
