package manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Employee;
import entity.Hourreport;
import entity.Project;

/**
 * Use this class to make hour report query's for the database .
 */
public class HourreportManager {
	
private final EntityManager entityManager;
	
	public HourreportManager(EntityManager entityManager){
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}
	
	public void update(Hourreport hourreport) {
		entityManager.getTransaction().begin();
		entityManager.merge(hourreport);
		entityManager.getTransaction().commit();
	}
	
	public void create(Hourreport hourreport) {
		entityManager.getTransaction().begin();
		entityManager.persist(hourreport);
		entityManager.getTransaction().commit();
	}
	
	public void delete(Hourreport hourreport) {
		entityManager.getTransaction().begin();
		entityManager.remove(hourreport);
		entityManager.getTransaction().commit();
	}

	public Hourreport get(Integer id) {
		return entityManager.find(Hourreport.class, id);
	}
	
	public List<Hourreport> getByHour(String name) {
		String sql = "select * from hourreport where description like '";
		return (List)entityManager.
				createNativeQuery(sql+name+"'", Hourreport.class).getResultList();
	}
	
	public Hourreport createHourreport(int userid, int project, String date, String starthour, String endhour, String description) {
		
		String sql = "select * from employee e where e.user="+ userid;
		
		Employee employee = (Employee) entityManager.createNativeQuery(sql, Employee.class).getSingleResult();
		
		Project p = ManagerHelper.getProjectManager().get(project);
		
		Hourreport hourreport = new Hourreport();
		hourreport.setEmployee(employee);
		hourreport.setProject(p);
		hourreport.setDate(date);
		hourreport.setStarthour(starthour);
		hourreport.setEndhour(endhour);
		hourreport.setDescription(description);
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(hourreport);
			entityManager.getTransaction().commit();
			ManagerHelper.getHourreportManager().create(hourreport);
			return  hourreport;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public List<Hourreport> getHourReportByEmployeeUser(int userid) {
		String sql = "SELECT h.id, h.project, h.date, h.starthour, h.endhour, h.description FROM hourreport h "
				+"inner join employee e on h.employee = e.id "
				+"inner join user u on e.user = u.id "
				+"where u.id=";
		return (List)entityManager.
				createNativeQuery(sql+userid, Hourreport.class).getResultList();
	}
	
	public List<Hourreport> getHourReportByEmployee(int employee) {
		String sql = "SELECT * FROM projectmanager.hourreport where employee = '";
		return (List)entityManager.
				createNativeQuery(sql+employee+"'", Hourreport.class).getResultList();
	}
	
	public List<Hourreport> getHourReportByCustomerUser(int userid) {
		String sql = "SELECT h.id, h.employee, h.project, h.date , h.starthour, h.endhour, h.description FROM hourreport h "
				+"inner join project p on h.project = p.id "
				+"inner join customer c on p.customer = c.id "
				+"inner join user u on c.user = u.id "
				+"where u.id=";
		return (List)entityManager.
				createNativeQuery(sql+userid, Hourreport.class).getResultList();
	}
	
	public List<Hourreport> getHourReportByCustomer(int customer) {
		String sql = "SELECT * FROM hourreport h"
			+"	inner join project p on h.project = p.id"
			+"	where p.customer ='";
		return (List)entityManager.
				createNativeQuery(sql+customer+"'", Hourreport.class).getResultList();
	}
	
	public List<Hourreport> getAllHourreports() {
		String sql = "SELECT id, employee, project, date, starthour, endhour, description FROM hourreport";
		return (List)entityManager.
				createNativeQuery(sql, Hourreport.class).getResultList();
	}
	
	public List<Hourreport> getHourreport(String yearAndMonth, int employeeId, int projectId, int customerId) {
		
		return new ArrayList<Hourreport>();
	}
	
	public List<Hourreport> getHourReportByDate( int userid ){
		String sql = "SELECT h.id, e.id, u.id, h.employee, h.project, h.date, h.starthour, h.endhour, h.description FROM projectmanager.hourreport h  "
			+ " inner join employee e on h.employee = e.id "
       		+ " inner join user u on e.user = u.id "
      		+ " where date >= curdate() - INTERVAL DAYOFWEEK(curdate())+6 DAY "
					+ " AND date < curdate() - INTERVAL DAYOFWEEK(curdate())-7 DAY and u.id=" + userid ; 
		System.out.println(sql);
		return (List)entityManager.
				createNativeQuery(sql, Hourreport.class).getResultList();
	}
	
	public List<Hourreport> getHourReportByYearAndMonth(String yearandmonth, int employee, int project, int customer) {
		
		String sql = "select h.id, e.firstname, p.projectname, h.date, h.starthour, h.endhour, h.description from hourreport h "
				+ " inner join employee e on e.id = h.employee"
				+ " inner join project p on p.id = h.project"
				+ " inner join customer c on c.id = p.customer"
				+ " where month(h.date) = month('"+yearandmonth+"')"
				+ " and year(h.date) = year('"+yearandmonth+"')";
				
				if(employee != 0){
					sql += " and h.employee = " + employee;
				}
				
				if( project != 0){
					sql +=" and  p.id =  " + project;
				}
				
				 if(customer != 0){
					sql += " and c.id =  " + customer;
				}
		
				sql += " order by h.date desc";
				
							
		return (List)entityManager.createNativeQuery(sql, Hourreport.class).getResultList();
	}
	
	public List<Hourreport> getYearAndMonthByEmployee(int userid, String yearandmonth, int project) {
			
			String sql = "select h.id, e.firstname, p.projectname, h.date, h.starthour, h.endhour, h.description from hourreport h "
					+ " inner join employee e on e.id = h.employee"
					+ " inner join project p on p.id = h.project"
					+ " inner join user u on u.id = e.user"
					+ " where month(h.date) = month('"+yearandmonth+"')"
					+ " and year(h.date) = year('"+yearandmonth+"')"
					+ " and u.id = "+userid;
					if( project != 0){
						sql +=" and  p.id =  " + project;
					}
					
					sql += " order by h.date desc";
					
								
		return (List)entityManager.createNativeQuery(sql, Hourreport.class).getResultList();
	}

	public List<Hourreport> getYearAndMonthByCustomer(int userid, String yearandmonth, int project) {
		
		String sql = "select h.id, c.companyname, p.projectname, h.date, h.starthour, h.endhour, h.description from hourreport h "
				+ " inner join project p on p.id = h.project"
				+ " inner join customer c on c.id = p.customer"
				+ " inner join user u on u.id = c.user"
				+ " where month(h.date) = month('"+yearandmonth+"')"
				+ " and year(h.date) = year('"+yearandmonth+"')"
				+ "and u.id = "+userid;
		
				if( project != 0){
					sql +=" and  p.id =  " + project;
				}
				
				sql += " order by h.date desc";
				
							
		return (List)entityManager.createNativeQuery(sql, Hourreport.class).getResultList();
	}


}
