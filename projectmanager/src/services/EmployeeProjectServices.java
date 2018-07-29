package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.EmployeeProject;
import manager.ManagerHelper;

/**
 * Use this class to route the request to the corresponding employeeProject manager function .
 */
@Path("/employeeProject")
public class EmployeeProjectServices {
	

	@GET
	@Path("/deleteEmployeeProject")
	public Reply deleteEmployeeProject(@QueryParam("id") int id) {
		System.out.println("----> Service EmployeeProject");
		return  (Reply)ManagerHelper.getEmployeeProjectManager().deleteEmployeeProject(id);
	}
	
	@GET
	@Path("/createEmployeeProject")
	public EmployeeProject createEmployeeProject(@QueryParam("employeeid") int employeeid, @QueryParam("projectid") int projectid) {
		System.out.println("----> Service EmployeeProject");
		return  (EmployeeProject)ManagerHelper.getEmployeeProjectManager().createEmployeeProject(employeeid, projectid);
	}
	
	@GET
	@Path("/updateEmployeeProject")
	public Reply updateEmployeeProject(@QueryParam("id") int id, @QueryParam("employeeid") int employeeid, @QueryParam("projectid") int projectid) {
		return  (Reply)ManagerHelper.getEmployeeProjectManager().updateEmployeeProject(id, employeeid, projectid);
	}

}
