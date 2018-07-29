package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Employee;
import entity.User;
import services.Reply;

/**
 * Use this class to make employee query's for the database .
 */
public class EmployeeManager {
	
	private final EntityManager entityManager;
	
	public EmployeeManager(EntityManager entityManager){
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}
	
	public void update(Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.merge(employee);
		entityManager.getTransaction().commit();
	}
	
	public void create(Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
	}
	
	public void delete(Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.remove(employee);
		entityManager.getTransaction().commit();
	}

	public Employee get(Integer id) {
		return entityManager.find(Employee.class, id);
	}
	
	public List<Employee> getAllEmployees() {
		String sql = "select * from employee";
		return (List)entityManager.
				createNativeQuery(sql, Employee.class).getResultList();
	}
	
	public List<Employee> getByName(String name) {
		String sql = "select * from employee where firstname like '";
		return (List)entityManager.
				createNativeQuery(sql+name+"'", Employee.class).getResultList();
	}
	
	public Employee createEmployee(String firstname, String lastname, String email, String phone, String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setType("employee");
		user = ManagerHelper.getUserManager().create(user);
		Employee employee = new Employee();
		employee.setFirstname(firstname);
		employee.setLastname(lastname);
		employee.setEmail(email);
		employee.setPhone(phone);
		employee.setUser(user.getId());
		
		try{
			entityManager.getTransaction().begin();
			entityManager.persist(employee);
			entityManager.getTransaction().commit();
		return employee;
		}catch (Exception e) {
			
			return null;
		}
		
	}
	
	public Reply updateEmployee(int id, String firstname, String lastname, String email, String phone, int user){
		Reply r = new Reply();
		Employee employee = new Employee();
		employee.setId(id);
		employee.setFirstname(firstname);
		employee.setLastname(lastname);
		employee.setEmail(email);
		employee.setPhone(phone);
		employee.setUser(user);
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(employee);
			entityManager.getTransaction().commit();
		return r ;	
		} catch (Exception e) {
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}
	
	public Reply deleteEmployee(int id){
		
		Employee employee = get(id);
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(employee);
			entityManager.getTransaction().commit();
		return new Reply() ;	
		} catch (Exception e) {
			e.printStackTrace();
			Reply r = new Reply();
			r.setId(Reply.FAIL_ID);
			r.setMsg(e.getMessage());
			return r;
		}
	}

}
