package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/props")
public class PropsServices {
	
	@GET
	@Path("/getHours")
	public String getHours() {
		String hours = PropsHelper.get("hours");
		return hours;

	}
	
	@GET
	@Path("/setHours")
	public String setHours(@QueryParam("starttime") String starttime, @QueryParam("endtime") String endtime) {
		String val = starttime+","+endtime;
		PropsHelper.set("hours",val);
		return val;

	}
}
