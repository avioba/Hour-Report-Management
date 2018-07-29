package services;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Employee;
import entity.Hourreport;
import entity.Project;
import manager.ManagerHelper;

/**
 * Use this class to route the request to the corresponding hour report manager function .
 */
@Path("/hourreport")
public class HourreportServices {
	
	

	@GET
	@Path("/get")
	public Hourreport getHourreport(@QueryParam("id") int id) {
		return ManagerHelper.getHourreportManager().get(id);

	}

	@GET
	@Path("/getByHour")
	public List<Hourreport> getByHour(@QueryParam("Hour") String name) {
		return ManagerHelper.getHourreportManager().getByHour(name);
	}
	
	@GET
	@Path("/createHourreport")
	public Hourreport createHourreport(@QueryParam("employee")int userid, @QueryParam("project")int project, @QueryParam("date")String date, @QueryParam("starthour")String starthour, @QueryParam("endhour")String endhour, @QueryParam("description")String description) {
		return ManagerHelper.getHourreportManager().createHourreport(userid, project, date, starthour, endhour, description);
	}
	
	@GET
	@Path("/getHourReportByEmployeeUser")
	public List<Hourreport> getHourReportByEmployeeUser(@QueryParam("userid")int userid) {
		
		return ManagerHelper.getHourreportManager().getHourReportByEmployeeUser(userid);
	}
	
	@GET
	@Path("/getHourReportByEmployee")
	public List<Hourreport> getHourReportByEmployee(@QueryParam("employee")int employee) {
		
		return ManagerHelper.getHourreportManager().getHourReportByEmployee(employee);
	}
	
	@GET
	@Path("/getHourReportByCustomerUser")
	public List<Hourreport> getHourReportByCustomerUser(@QueryParam("userid")int userid) {
		
		return ManagerHelper.getHourreportManager().getHourReportByCustomerUser(userid);
	}
	
	@GET
	@Path("/getHourReportByCustomer")
	public List<Hourreport> getHourReportByCustomer(@QueryParam("customer")int customer) {
		
		return ManagerHelper.getHourreportManager().getHourReportByCustomer(customer);
	}
	
	@GET
	@Path("/getAllHourreports")
	public List<Hourreport> getAllHourreports() {
		
		return ManagerHelper.getHourreportManager().getAllHourreports();
	}
	
	@GET
	@Path("/getHourreport")
	public List<Hourreport> getHourreport(@QueryParam("yearAndMonth")String yearAndMonth, @QueryParam("employeeId")int employeeId, @QueryParam("projectId")int projectId, @QueryParam("customerId")int customerId) {
		return ManagerHelper.getHourreportManager().getHourreport(yearAndMonth, employeeId, projectId, customerId);
	}
	
	@GET
	@Path("/getHourReportByDate")
	public List<Hourreport> getHourReportByDate(@QueryParam("userid")int userid) {
		return ManagerHelper.getHourreportManager().getHourReportByDate(userid);
	}
	
	@GET
	@Path("/getHourReportByYearAndMonth")
	public List<Hourreport> getHourReportByYearAndMonth(@QueryParam("yearandmonth")String yearandmonth, @QueryParam("employee")int employee, @QueryParam("project")int project, @QueryParam("customer")int customer) {
		return ManagerHelper.getHourreportManager().getHourReportByYearAndMonth(yearandmonth, employee, project, customer);
	}
	
	@GET
	@Path("/getYearAndMonthByEmployee")
	public List<Hourreport> getYearAndMonthByEmployee(@QueryParam("userid")int userid, @QueryParam("yearandmonth")String yearandmonth, @QueryParam("project")int project) {
		return ManagerHelper.getHourreportManager().getYearAndMonthByEmployee(userid,yearandmonth, project);
	}
	
	@GET
	@Path("/getYearAndMonthByCustomer")
	public List<Hourreport> getYearAndMonthByCustomer(@QueryParam("userid")int userid, @QueryParam("yearandmonth")String yearandmonth, @QueryParam("project")int project) {
		return ManagerHelper.getHourreportManager().getYearAndMonthByCustomer(userid,yearandmonth, project);
	}
}
