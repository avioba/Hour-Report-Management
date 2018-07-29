package manager;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Employee;
import entity.User;
import services.Reply;

/**
 * Use this class to make user query's for the database .
 */
public class UserManager {

	private final EntityManager entityManager;

	public UserManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(User user) {
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
	}

	public User create(User user) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(user);
			entityManager.getTransaction().commit();
			return user;
		} catch (Exception e) {
			return null;
		}

	}

	public void delete(User user) {
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit();
	}

	public User get(int id) {

		return entityManager.find(User.class, id);
	}

	public User getUser(String username, String password) {
		System.out.println("username" + " " + username);
		System.out.println("password" + " " + password);
		String sql = "select * from user where username = '";
		String sql1 = " and password = '";
		System.out.println(sql);
		System.out.println(sql1);
		try {
			System.out.println(sql + username + "'" + sql1 + password + "'");
			return (User) entityManager.createNativeQuery(sql + username + "'" + sql1 + password + "'", User.class)
					.getSingleResult();
		} catch (Exception e) {
			System.out.println("null");
			return null;
		}

	}

	public Reply forgotPassword(String username) {

		String sql = "select * from user  where username = '" + username + "' ";
		User user2 = (User) entityManager.createNativeQuery(sql, User.class).getSingleResult();

		if (user2.getType().equals("employee")) {

			String employeeForgot = "select * from employee e inner join user u on e.user=u.id where u.username ='"
					+ username + "' ";

			Employee employee = (Employee) entityManager.createNativeQuery(employeeForgot, Employee.class)
					.getSingleResult();

			try {
				MailHelper.sendMail(employee.getEmail(), "Password To Hourreport Site",
						"User Name : " + user2.getUsername() + " , " + " Password : " + user2.getPassword());

			} catch (MessagingException e) {
				e.getMessage();
			}
		} else if (user2.getType().equals("customer")) {

			String customerForgot = "select * from customer c inner join user u on c.user=u.id where u.username ='"
					+ username + "' ";

			Customer customer = (Customer) entityManager.createNativeQuery(customerForgot, Customer.class)
					.getSingleResult();

			try {

				MailHelper.sendMail(customer.getEmail(), "Password To Hourreport Site",
						"User Name : " + user2.getUsername() + " , " + " Password :" + user2.getPassword());
			} catch (MessagingException e) {
				e.getMessage();
			}
		} else {
			try {
				MailHelper.sendMail("avioba89@gmail.com", "Password To Hourreport Site",
						user2.getUsername() + " , " + user2.getPassword());
			} catch (MessagingException e) {
				e.getMessage();
			}
		}

		return new Reply();
	}

}