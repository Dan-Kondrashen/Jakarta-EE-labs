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

import dao.ConnectionProperty;
import dao.EmpConnBuilder;
import domain.Employee;
import domain.Territory;
import domain.EmployeeTerritory;

/**
 * Servlet implementation class EditEmpTerrServlet
 */
@WebServlet("/editempterr")
public class EditEmpTerrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	String select_all_territorys = "SELECT id, regionid, terdesc FROM territorys";
	String select_employees_name = "SELECT id, firstname, secondname, lastname, title, phone, addres, email, birchdate  FROM employees";
	String select_all_empter = "SELECT id, empid,terid FROM empter";
	String select_empter_BY_id = "SELECT id, empid,terid FROM empter WHERE id = ?";
	String edit_empter = "UPDATE empter SET empid = ?, terid = ?  WHERE id = ?";
	ArrayList<Territory> territorys= new ArrayList<Territory>();
	ArrayList<EmployeeTerritory> editempterr= new ArrayList<EmployeeTerritory>();
	ArrayList<Employee> employees= new ArrayList<Employee>();
	ArrayList<EmployeeTerritory> empterr= new ArrayList<EmployeeTerritory>();
       
	
    public EditEmpTerrServlet() throws FileNotFoundException, IOException {
        super();
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
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpConnBuilder builder = new EmpConnBuilder();
		try (Connection conn = builder.getConnection()) {
			Statement stmt = conn.createStatement();
//    		ResultSet rs = stmt.executeQuery(select_all_empter);
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
			stmt = conn.createStatement();
    		rs = stmt.executeQuery(select_all_empter);
    		if(rs != null) {
    			empterr.clear();
    			while (rs.next()) {
    				Long id1 = rs.getLong("empid");
    				Long id2 = rs.getLong("terid");
    				empterr.add(new EmployeeTerritory(rs.getLong("id"),id1,id2,FindEmpById(id1,employees),FindTerById(id2,territorys)));
//    				employeesTer.add(new EmployeeTerritory(rs.getLong("id"),rs.getLong("empid"),rs.getLong("terid")));
    			}
    			rs.close();
    			request.setAttribute("empter", empterr);
    		}
    		String Id1 = request.getParameter("id");
			Long id = null;  
			if(Id1 != null) {
				id = Long.parseLong(Id1); 
			}
			
			try (PreparedStatement preparedStatement = conn.prepareStatement(select_empter_BY_id)) {
					preparedStatement.setLong(1, id);
					rs = preparedStatement.executeQuery();
					if(rs != null) {
						editempterr.clear();
						while (rs.next()) {
							editempterr.add(new EmployeeTerritory(rs.getLong("id"),rs.getLong("empid"),rs.getLong("terid")));
						}
						rs.close();
						request.setAttribute("empterrEdit", editempterr);
					}
					else
					{
						System.out.println("Ошибка загрузки регионов");
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = "/jspf/editempterr.jsp"; 
		ServletContext servletContext = getServletContext(); 
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path); 
		requestDispatcher.forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpConnBuilder builder = new EmpConnBuilder();
		try (Connection conn = builder.getConnection()) { 
			String Id = request.getParameter("id");
			Long id = Long.parseLong(Id);
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
			try (PreparedStatement prep = conn.prepareStatement(edit_empter)){
				prep.setLong(1, empid);
				prep.setLong(2, terid);
				prep.setLong(3, id);
				int result = prep.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		doGet(request, response);
	}

}
