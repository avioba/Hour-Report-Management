package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Employee;
import manager.ManagerHelper;

/**
 * Use this class to route the request to the corresponding employee manager function .
 */
@Path("/employee")
public class EmployeeServices {

	

	@GET
	@Path("/get")
	public Employee getEmployee(@QueryParam("id") int id) {
		return ManagerHelper.getEmployeeManager().get(id);

	}

	@GET
	@Path("/getByName")
	public List<Employee> getByName(@QueryParam("firstname") String name) {
		return ManagerHelper.getEmployeeManager().getByName(name);
	}
	
	@GET
	@Path("/getAllEmployees")
	public List<Employee> getAllEmployees() {
		return ManagerHelper.getEmployeeManager().getAllEmployees();
	}
	
	@GET
	@Path("/createEmployee")
	public Employee createEmployee(@QueryParam("firstname")String firstname,@QueryParam("lastname")String lastname,@QueryParam("email")String email,@QueryParam("phone")String phone,@QueryParam("username") String username,@QueryParam("password") String password){
		return ManagerHelper.getEmployeeManager().createEmployee(firstname, lastname, email, phone, username, password);
	}
	
	@GET
	@Path("/updateEmployee")
	public Reply updateEmployee(@QueryParam("id")int id,@QueryParam("firstname")String firstname,@QueryParam("lastname")String lastname,@QueryParam("email")String email,@QueryParam("phone")String phone,@QueryParam("user") int user){
		return ManagerHelper.getEmployeeManager().updateEmployee(id,firstname, lastname, email, phone, user);
	}
	
	@GET
	@Path("/deleteEmployee")
	public Reply deleteEmployee(@QueryParam("id")int id){
		return ManagerHelper.getEmployeeManager().deleteEmployee(id);
	}
}
