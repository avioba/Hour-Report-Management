package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Customer;
import manager.ManagerHelper;

/**
 * Use this class to route the request to the corresponding customer manager function .
 */
@Path("/customer")
public class CustomerServices {
	

	@GET
	@Path("/get")
	public Customer getCustomer(@QueryParam("id") int id) {
		return ManagerHelper.getCustomerManager().get(id);

	}

	@GET
	@Path("/getByContactName")
	public List<Customer> getByContactName(@QueryParam("contactname") String contactname) {
		return ManagerHelper.getCustomerManager().getByContactName(contactname);
	}
	
	@GET
	@Path("/getActiveCustomer")
	public List<Customer> getActiveCustomer(@QueryParam("status") boolean status) {
		if(status == true){
			return ManagerHelper.getCustomerManager().getActiveCustomer();
		}else{
			return ManagerHelper.getCustomerManager().getAllCustomer();
		}	
	}
	
	@GET
	@Path("/getAllCustomer")
	public List<Customer> getAllCustomer() {
		return ManagerHelper.getCustomerManager().getAllCustomer();
	}
	
	@GET
	@Path("createCustomer")
	public Customer createCustomer( @QueryParam("companyname") String companyname, @QueryParam("companynumber") String companynumber, @QueryParam("contactname") String contactname, @QueryParam("email") String email, @QueryParam("phone") String phone, @QueryParam("username") String username, @QueryParam("password") String password) {
		System.out.println("this is creare Customer service -->");
		return ManagerHelper.getCustomerManager().createCustomer(companyname, companynumber, contactname, email, phone, username, password);
	}
	
	@GET
	@Path("getPopularCustomers")
	public List<Customer> getPopularCustomers() {
		return ManagerHelper.getCustomerManager().getPopularCustomers();
	}
	
	@GET
	@Path("updateCustomer")
	public Reply updateCustomer(@QueryParam("id") int id, @QueryParam("companyname") String companyname, @QueryParam("companynumber") String companynumber, @QueryParam("contactname") String contactname, @QueryParam("email") String email, @QueryParam("phone") String phone, @QueryParam("user") int user) {
		return ManagerHelper.getCustomerManager().updateCustomer(id,companyname, companynumber, contactname, email, phone, user);
	}
	
	@GET
	@Path("deleteCustomer")
	public Reply deleteCustomer(@QueryParam("id") int id) {
		return ManagerHelper.getCustomerManager().deleteCustomer(id);
	}
	
}
