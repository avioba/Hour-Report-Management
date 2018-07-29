package manager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Employee;
import entity.User;
import services.Reply;

/**
 * Use this class to make customer query's for the database .
 */
public class CustomerManager {

	private final EntityManager entityManager;

	public CustomerManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 
	}

	public void update(Customer customer) {
		entityManager.getTransaction().begin();
		entityManager.merge(customer);
		entityManager.getTransaction().commit();
	}

	public void create(Customer customer) {
		entityManager.getTransaction().begin();
		entityManager.persist(customer);
		entityManager.getTransaction().commit();
	}

	public void delete(Customer customer) {
		entityManager.getTransaction().begin();
		entityManager.remove(customer);
		entityManager.getTransaction().commit();
	}

	public Customer get(Integer id) {
		return entityManager.find(Customer.class, id);
	}

	public List<Customer> getByContactName(String name) {
		String sql = "select * from customer where contactname like '";
		return (List) entityManager.createNativeQuery(sql + name + "'", Customer.class).getResultList();
	}

	public List<Customer> getActiveCustomer() {
		String sql = "SELECT c.companyname, c.companynumber, c.contactname, c.email, c.phone, true as 'isactive' FROM customer c where ( select count(p.id) from project p where p.customer = c.id and (current_date() between p.startdate and p.enddate) > 0 )";
		return (List) entityManager.createNativeQuery(sql, Customer.class).getResultList();
	}
	
	public List<Customer> getAllCustomer() {

		String sql = "SELECT c.id, c.contactname,(select count(p.id) from project p where p.customer = c.id and (current_date() between p.startdate and p.enddate)) > 0 as 'isactive' from customer c ";
		return (List) entityManager.createNativeQuery(sql, Customer.class).getResultList();
	}
	
	public List<Customer> getPopularCustomers(){
		String sql = " SELECT c.id, c.companyname ,(select count(projectname) from project p where p.customer=c.id) as 'projects' from customer c "
				+ "inner join project p on c.id = p.customer "
				+ "group by c.companyname "
				+ "having count(c.id) > 1 "
				+ "order by (select count(projectname) from projectmanager.project p where p.customer=c.id) desc ";
				return (List)entityManager.
						createNativeQuery(sql, Customer.class).getResultList();	
	}
	
	public Customer createCustomer(String companyname, String companynumber, String contactname, String email, String phone, String username, String password ){
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setType("customer");
		user = ManagerHelper.getUserManager().create(user);
		Customer customer = new Customer();
		customer.setCompanyname(companyname);
		customer.setCompanynumber(companynumber);
		customer.setContactname(contactname);
		customer.setEmail(email);
		customer.setPhone(phone);
		customer.setUser(user.getId());
		System.out.println("this is creare Customer manager Enter -->");

		try {
			entityManager.getTransaction().begin();
			entityManager.persist(customer);
			entityManager.getTransaction().commit();
			System.out.println("this is creare Customer manager Exit -->");

			return customer ;
			
		} catch (Exception e) {
			
			return null;
		}
		
	}
	
	public Reply updateCustomer(int id, String companyname, String companynumber, String contactname, String email, String phone, int user){
		Reply r = new Reply();
		Customer customer = new Customer();
		customer.setId(id);
		customer.setCompanyname(companyname);
		customer.setCompanynumber(companynumber);
		customer.setContactname(contactname);
		customer.setEmail(email);
		customer.setPhone(phone);
		customer.setUser(user);
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(customer);
			entityManager.getTransaction().commit();
		
			return r ;	
		} catch (Exception e) {
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
		
	}
	
	public Reply deleteCustomer(int id){
		
		Customer customer = get(id);
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(customer);
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