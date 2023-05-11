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
import domain.EmployeeTerritory;
import domain.Territory;

/**
 * Servlet implementation class DeleteEmpTerrServlet
 */
@WebServlet("/deleteempterr")
public class DeleteEmpTerrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	String select_all_territorys = "SELECT id, regionid, terdesc FROM territorys";
	String select_employees_name = "SELECT id, firstname, secondname, lastname, title, phone, addres, email, birchdate  FROM employees";
	String select_all_empter = "SELECT id, empid,terid FROM empter";
	String select_empter_BY_id = "SELECT id, empid,terid FROM empter WHERE id = ?";
	String delete_empter = "DELETE FROM empter WHERE id = ?";
	ArrayList<Territory> territorys= new ArrayList<Territory>();
	ArrayList<EmployeeTerritory> deleteempterr= new ArrayList<EmployeeTerritory>();
	ArrayList<Employee> employees= new ArrayList<Employee>();
	ArrayList<EmployeeTerritory> empterr= new ArrayList<EmployeeTerritory>();
       
    
    public DeleteEmpTerrServlet() throws FileNotFoundException, IOException {
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
						deleteempterr.clear();
						while (rs.next()) {
							Long id3 = rs.getLong("empid");
		    				Long id4 = rs.getLong("terid");
							deleteempterr.add(new EmployeeTerritory(rs.getLong("id"),id3,id4,FindEmpById(id3,employees),FindTerById(id4,territorys)));
						}
						rs.close();
						request.setAttribute("empterrDelete", deleteempterr);
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
		String path = "/jspf/deleteempterr.jsp"; 
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
			try (PreparedStatement prep = conn.prepareStatement(delete_empter)){
				prep.setLong(1,id);
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
