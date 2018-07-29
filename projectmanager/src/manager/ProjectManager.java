package manager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Employee;
import entity.Project;
import services.Reply;

/**
 * Use this class to make project query's for the database .
 */
public class ProjectManager {

	private final EntityManager entityManager;

	public ProjectManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(Project project) {
		entityManager.getTransaction().begin();
		entityManager.merge(project);
		entityManager.getTransaction().commit();
	}

	public void create(Project project) {
		entityManager.getTransaction().begin();
		entityManager.persist(project);
		entityManager.getTransaction().commit();
	}

	public void delete(Project project) {
		entityManager.getTransaction().begin();
		entityManager.remove(project);
		entityManager.getTransaction().commit();
	}

	public Project get(Integer id) {
		return entityManager.find(Project.class, id);
	}

	public List<Project> getByProjectName(String projectname) {
		String sql = "select * from project where projectname like '";
		return (List) entityManager.createNativeQuery(sql + projectname + "'", Project.class).getResultList();
	}
	
	public List<Project> getActiveProjects() {
		String sql = "select * from project where enddate >= current_date()";
		return (List) entityManager.createNativeQuery(sql, Project.class).getResultList();
	}
	
	public List<Project> getCustomerActiveProjects(int userid) {
		String sql = "select * from project p inner join customer c on c.id = p.customer inner join user u on c.user = u.id where u.id=";
		String sql1 = " and enddate >= current_date()";
		return (List) entityManager.createNativeQuery(sql + userid + sql1, Project.class).getResultList();
	}
	
	public List<Project> getProjectsAboutToFinish() {
		String sql = "SELECT * from project where enddate between now() and date_add(now(),interval 30 day )";
		return (List) entityManager.createNativeQuery(sql , Project.class).getResultList();
	}
	
	public List<Project> getProjectsByEmployee(int userid) {
		String sql = "SELECT p.id, p.projectname FROM employeeproject ep"
				+ " inner join employee e on e.id = ep.employeeid"
				+ " inner join project p on p.id = ep.projectid"
				+ " inner join user u on u.id = e.user"
				+ " where u.id = ";
		String sql1 = " group by p.projectname"
				+ " order by p.id asc";
		return (List) entityManager.createNativeQuery(sql + userid + sql1, Project.class).getResultList();
	}
	
	public List<Project> getProjectsByCustomer(int userid) {
		String sql = "SELECT p.id, p.projectname FROM employeeproject ep"
				+ " inner join project p on p.id = ep.projectid"
				+ " inner join customer c on c.id = p.customer"
				+ " inner join user u on u.id = c.user"
				+ " where u.id = ";
		String sql1 = " group by p.projectname"
				+ " order by p.id asc";
		return (List) entityManager.createNativeQuery(sql + userid + sql1, Project.class).getResultList();
	}

	public List<Project> getAllProjects() {
		String sql = "SELECT * FROM project";
		return (List) entityManager.createNativeQuery(sql , Project.class).getResultList();
	}
	
	public Project createProject(String projectname, int customer, String customerprojectmanager, String projectmanageremail, String projectmanagerephone, String startdate, String enddate) {
		
		Customer c = ManagerHelper.getCustomerManager().get(customer);
		
		Project project = new Project();
		project.setProjectname(projectname);
		project.setCustomer(c);
		project.setCustomerprojectmanager(customerprojectmanager);
		project.setProjectmanageremail(projectmanageremail);
		project.setProjectmanagerphone(projectmanagerephone);
		project.setStartdate(startdate);
		project.setEnddate(enddate);
		try{
			entityManager.getTransaction().begin();
			entityManager.persist(project);
			entityManager.getTransaction().commit();
		return project;
		}catch (Exception e) {
			
			return null;
		}
		
	}
	
	public Reply updateProject(int id, String projectname, int customer, String customerprojectmanager, String projectmanageremail, String projectmanagerphone, String startdate, String enddate) {
		
		Customer c = ManagerHelper.getCustomerManager().get(customer);
		
		Reply r = new Reply();
		Project project = new Project();
		project.setId(id);
		project.setProjectname(projectname);
		project.setCustomer(c);
		project.setCustomerprojectmanager(customerprojectmanager);
		project.setProjectmanageremail(projectmanageremail);
		project.setProjectmanagerphone(projectmanagerphone);
		project.setStartdate(startdate);
		project.setEnddate(enddate);
		try{
			entityManager.getTransaction().begin();
			entityManager.merge(project);
			entityManager.getTransaction().commit();
		return r;
		}catch (Exception e) {
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}	
	}
	
	public Reply deleteProject(int id){
		
		Project project = get(id);
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(project);
			entityManager.getTransaction().commit();
		return new Reply() ;	
		} catch (Exception e) {
			Reply r = new Reply();
			r.setId(Reply.FAIL_ID);
			r.setMsg(e.getMessage());
			return r;
		}
	}

}
