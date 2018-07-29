package services;

import java.sql.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Customer;
import entity.Project;
import manager.ManagerHelper;

/**
 * Use this class to route the request to the corresponding project manager function .
 */
@Path("/project")
public class ProjectServices {

	@GET
	@Path("/get")
	public Project getProject(@QueryParam("id") int id) {
		return ManagerHelper.getProjectManager().get(id);

	}

	@GET
	@Path("/getByProjectName")
	public List<Project> getByProjectName(@QueryParam("projectname") String projectname) {
		return ManagerHelper.getProjectManager().getByProjectName(projectname);
	}
	
	@GET
	@Path("/getAllProjects")
	public List<Project> getAllProjects() {
		return ManagerHelper.getProjectManager().getAllProjects();
	}
	
	@GET
	@Path("/getActiveProjects")
	public List<Project> getActiveProjects() {
		return ManagerHelper.getProjectManager().getActiveProjects();
	}
	
	@GET
	@Path("/getCustomerActiveProjects")
	public List<Project> getCustomerActiveProjects(@QueryParam("userid") int userid) {
		return ManagerHelper.getProjectManager().getCustomerActiveProjects(userid);
	}
	
	@GET
	@Path("/getProjectsAboutToFinish")
	public List<Project> getProjectsAboutToFinish() {
		return ManagerHelper.getProjectManager().getProjectsAboutToFinish();
	}
	
	@GET
	@Path("/getProjectsByEmployee")
	public List<Project> getProjectsByEmployee(@QueryParam("userid") int userid) {
		return ManagerHelper.getProjectManager().getProjectsByEmployee(userid);
	}
	
	@GET
	@Path("/getProjectsByCustomer")
	public List<Project> getProjectsByCustomer(@QueryParam("userid") int userid) {
		return ManagerHelper.getProjectManager().getProjectsByCustomer(userid);
	}
	
	@GET
	@Path("/createProject")
	public Project createProject(@QueryParam("projectname") String projectname, @QueryParam("customer") int customer, @QueryParam("customerprojectmanager") String customerprojectmanager, @QueryParam("projectmanageremail") String projectmanageremail, @QueryParam("projectmanagerephone") String projectmanagerephone, @QueryParam("startdate") String startdate, @QueryParam("enddate") String enddate) {
		return ManagerHelper.getProjectManager().createProject(projectname, customer, customerprojectmanager, projectmanageremail, projectmanagerephone, startdate, enddate);
	}
	
	@GET
	@Path("/updateProject")
	public Reply updateProject(@QueryParam("id") int id, @QueryParam("projectname") String projectname, @QueryParam("customer") int customer, @QueryParam("customerprojectmanager") String customerprojectmanager, @QueryParam("projectmanageremail") String projectmanageremail, @QueryParam("projectmanagerphone") String projectmanagerphone, @QueryParam("startdate") String startdate, @QueryParam("enddate") String enddate) {
		return ManagerHelper.getProjectManager().updateProject(id, projectname, customer, customerprojectmanager, projectmanageremail, projectmanagerphone, startdate, enddate);
	}
	
	@GET
	@Path("/deleteProject")
	public Reply deleteProject(@QueryParam("id") int id) {
		return ManagerHelper.getProjectManager().deleteProject(id);
	}
}
