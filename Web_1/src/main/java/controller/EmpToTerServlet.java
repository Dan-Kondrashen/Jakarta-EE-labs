package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import dao.TerritoryDAO;
//import dao.EmployeeDAO;
//import dao.EmpToTerDAO;
import dao.ConnectionProperty;
import dao.EmpConnBuilder;
//import dao.EmpToTerDAO;
import domain.Employee;
import domain.EmployeeTerritory;
import domain.Territory;


@WebServlet("/employeesterritory")
public class EmpToTerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	String select_all_territorys = "SELECT id, regionid, terdesc FROM territorys";
	String select_employees_name = "SELECT id, firstname, secondname, lastname, title, phone, addres, email, birchdate  FROM employees";
	String select_all_empter = "SELECT id, empid,terid FROM empter";
	String insert_empter = "INSERT INTO empter(empid, terid) VALUES (?, ?)";
	ArrayList<EmployeeTerritory> employeesTer= new ArrayList<EmployeeTerritory>();
	ArrayList<Territory> territorys= new ArrayList<Territory>();
	ArrayList<Employee> employees= new ArrayList<Employee>();
  
    public EmpToTerServlet() throws FileNotFoundException, IOException{
    	prop = new ConnectionProperty();
    }
    private Territory FindTerById(Long id, ArrayList<Territory> territory) {
    	if(territory != null) {
    		 for(Territory ter: territory) {
    			 if((ter.getTerID()).equals(id)) {
    				 return ter;
    			 }
    		 }
    	}
    	else {
    		 return null;
    	}
    	 return null;
    }
    private Employee FindEmpById(Long id, ArrayList<Employee> employee) {
    	if(employee != null) {
    		 for(Employee emp: employee) {
    			 if((emp.getId()).equals(id)) {
    				 return emp;
    			 }
    		 }
    	}
    	else {
    		 return null;
    	}
    	 return null;
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpConnBuilder builder = new EmpConnBuilder();
		try (Connection conn = builder.getConnection()) {
			Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery(select_employees_name);
    		if(rs != null) {
    			employees.clear();
    			while (rs.next()) {
    				employees.add(new Employee(rs.getLong("id"),rs.getString("secondname"),rs.getString("firstname"),rs.getString("lastname"),rs.getString("title") ,
   						 rs.getString("phone"),rs.getString("addres"),rs.getString("email"),rs.getDate("birchdate")));
    			}
    			rs.close();
    			request.setAttribute("employee", employees);
    		}
			else {
				System.out.println("Ошибка загрузки");
			}
    		rs = stmt.executeQuery(select_all_territorys);
			if(rs != null) {
				territorys.clear();
			while (rs.next()) {
				territorys.add(new Territory(rs.getLong("id"),rs.getString("terdesc"), rs.getLong("regionid")));
			}
			rs.close();
			request.setAttribute("territory", territorys);			
			}
			else {
    			System.out.println("Ошибка загрузки");
    		}
//
    		long id1;
    		long id2;
			stmt = conn.createStatement();
    		rs = stmt.executeQuery(select_all_empter);
    		if(rs != null) {
    			employeesTer.clear();
    			while (rs.next()) {
    				id1 =rs.getLong("empid");
    				id2 = rs.getLong("terid");
    				employeesTer.add(new EmployeeTerritory(rs.getLong("id"),id1,id2,FindEmpById(id1,employees),FindTerById(id2,territorys)));
//    				employeesTer.add(new EmployeeTerritory(rs.getLong("id"),rs.getLong("empid"),rs.getLong("terid")));
    			}
    			rs.close();
    			request.setAttribute("empter", employeesTer);
    		}
			} catch (Exception e) {
			System.out.println(e);
			} 
    		String path = "/jspf/EmpToTer.jsp"; 
    		ServletContext servletContext = getServletContext(); 
    		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path); 
    		requestDispatcher.forward(request, response); 
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpConnBuilder builder = new EmpConnBuilder();
		try (Connection conn = builder.getConnection()){
			String emp = request.getParameter("employee");
			String terr = request.getParameter("territory");
			int index1 = emp.indexOf('=');
			int index2 = emp.indexOf(","); 
			int index3 = terr.indexOf('=');
			int index4 = terr.indexOf(","); 
			String r1 = emp.substring(index1+1, index2);
			String r2 = terr.substring(index3+1, index4);
			
			Long empid = Long.parseLong(r1.trim());
			Long terid = Long.parseLong(r2.trim());
			try (PreparedStatement prep = conn.prepareStatement(insert_empter)){
				prep.setLong(1, empid);
				prep.setLong(2, terid);
				
				int rows = prep.executeUpdate();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			String path = "/jspf/EmpToTer.jsp"; 
    		ServletContext servletContext = getServletContext(); 
    		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path); 
    		requestDispatcher.forward(request, response); 
		}
		doGet(request, response);
	}

}
