package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import entity.User;
import manager.ManagerHelper;

/**
 * Use this class to route the request to the corresponding user manager function .
 */
@Path("/user")
public class UserServices {

	@GET
	@Path("/get")
	public User getUser(@QueryParam("id") int id) {
		return ManagerHelper.getUserManager().get(id);

	}

	@GET
	@Path("getUser")
	public User getUser(@QueryParam("username") String username, @QueryParam("password") String password) {
		System.out.println("service"+" "+username);
		System.out.println("service"+" "+password);
		return ManagerHelper.getUserManager().getUser(username, password);
	}
	
	@GET
	@Path("/forgotPassword")
	public Reply forgotPassword(@QueryParam("username") String username) {
		return  ManagerHelper.getUserManager().forgotPassword(username);

	}

}
