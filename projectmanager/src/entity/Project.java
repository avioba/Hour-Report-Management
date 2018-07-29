package entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.openjpa.persistence.jdbc.ElementForeignKey;
import org.apache.openjpa.persistence.jdbc.Unique;

/**
 * Use this class to create a project.
 */
@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String projectname;
	@ManyToOne
	@JoinColumn(name="customer")
	private Customer customer;
	private String customerprojectmanager;
	private String projectmanageremail;
	private String projectmanagerphone;
	private String startdate;
	private String enddate; 
	
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getCustomerprojectmanager() {
		return customerprojectmanager;
	}
	public void setCustomerprojectmanager(String customerprojectmanager) {
		this.customerprojectmanager = customerprojectmanager;
	}
	public String getProjectmanageremail() {
		return projectmanageremail;
	}
	public void setProjectmanageremail(String projectmanageremail) {
		this.projectmanageremail = projectmanageremail;
	}
	public String getProjectmanagerphone() {
		return projectmanagerphone;
	}
	public void setProjectmanagerphone(String projectmanagerphone) {
		this.projectmanagerphone = projectmanagerphone;
	}
	
}
