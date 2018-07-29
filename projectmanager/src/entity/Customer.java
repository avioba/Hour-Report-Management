package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.openjpa.persistence.jdbc.Unique;

/**
 * Use this class to create a customer.
 */
@Entity
public class Customer {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int id;
	private String companyname;
	private String companynumber;
	private String contactname;
	private String email;
	private String phone;
	@Transient 
	private boolean isactive;
	private int projects;
	private int user;
	
	public boolean isactive() {
		return isactive;
	}
	public void setactive(boolean isactive) {
		this.isactive = isactive;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getCompanynumber() {
		return companynumber;
	}
	public void setCompanynumber(String companynumber) {
		this.companynumber = companynumber;
	}
	public String getContactname() {
		return contactname;
	}
	public void setContactname(String contactname) {
		this.contactname = contactname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getProjects() {
		return projects;
	}
	public void setProjects(int projects) {
		this.projects = projects;
	}
	
}
