package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.EmployeeProject;
import entity.Project;
import services.Reply;

/**
 * Use this class to make employeeProject query's for the database .
 */
public class EmployeeProjectManager {

	private final EntityManager entityManager;

	public EmployeeProjectManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(EmployeeProject employeeProject) {
		entityManager.getTransaction().begin();
		entityManager.merge(employeeProject);
		entityManager.getTransaction().commit();
	}

	public void create(EmployeeProject employeeProject) {
		entityManager.getTransaction().begin();
		entityManager.persist(employeeProject);
		entityManager.getTransaction().commit();
	}

	public void delete(EmployeeProject employeeProject) {
		entityManager.getTransaction().begin();
		entityManager.remove(employeeProject);
		entityManager.getTransaction().commit();
	}

	public EmployeeProject get(Integer id) {
		return entityManager.find(EmployeeProject.class, id);
	}
	
	public EmployeeProject createEmployeeProject(int employeeid, int projectid){
		EmployeeProject employeeProject = new EmployeeProject();
		employeeProject.setEmployeeid(employeeid);
		employeeProject.setProjectid(projectid);
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(employeeProject);
			entityManager.getTransaction().commit();
			return employeeProject;
		} catch (Exception e) {
			
			return null;
		}
	}
	
	public Reply updateEmployeeProject(int id, int employeeid, int projectid){
		Reply r = new Reply();
		EmployeeProject employeeProject = new EmployeeProject();
		employeeProject.setId(id);
		employeeProject.setEmployeeid(employeeid);
		employeeProject.setProjectid(projectid);
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(employeeProject);
			entityManager.getTransaction().commit();
			return r;
		} catch (Exception e) {
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}
	
	public Reply deleteEmployeeProject(int id){
		System.out.println("----> manager EmployeeProject");
		EmployeeProject employeeProject = get(id);
		try{
			entityManager.getTransaction().begin();
			entityManager.remove(employeeProject);
			entityManager.getTransaction().commit();
		return new Reply();
		} catch(Exception e ){
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r ;
		}
	}

}