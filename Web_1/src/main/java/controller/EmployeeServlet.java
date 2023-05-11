package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//import java.util.Date;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import dao.ConnectionProperty;
//import dao.EmpConnBuilder;
//import dao.EmpToTerDAO;
//import dao.EmployeeDAO;
//import dao.TerritoryDAO;
import dao.EmpConnBuilder;
import domain.Employee;
import domain.EmployeeTerritory;

/**
 * Servlet implementation class Employee
 */
@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	ConnectionProperty prop;
	String select_all_employee = "SELECT id, secondname, firstname, lastname, title, phone, addres, email, birchdate FROM employees";
	String insert_employee = "INSERT INTO employees(id, secondname, firstname, lastname, title, phone, addres, email, birchdate) VALUES (?, ?, ?, ?, ? ,? ,? ,? , ?)";
	ArrayList<Employee> employees= new ArrayList<Employee>();
	
	/**
	* @see HttpServlet#HttpServlet() */
	public EmployeeServlet () throws FileNotFoundException, IOException {
		prop = new ConnectionProperty();
	}

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpConnBuilder builder = new EmpConnBuilder();
		try (Connection conn = builder.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(select_all_employee);
			if(rs != null) {
				employees.clear();
			while (rs.next()) {
				employees.add(new Employee(rs.getLong("id"),rs.getString("secondname"),rs.getString("firstname"),rs.getString("lastname"),rs.getString("title"),
						 rs.getString("phone"),rs.getString("addres") ,rs.getString("email"),rs.getDate("birchdate")));
			}
			rs.close();
			request.setAttribute("employees", employees);
			}
			} catch (Exception e) {
			System.out.println(e);
			} 
    		String path = "/jspf/Employee.jsp"; 
    		ServletContext servletContext = getServletContext(); 
    		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path); 
    		requestDispatcher.forward(request, response); 
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	EmpConnBuilder builder = new EmpConnBuilder();
		try (Connection conn = builder.getConnection()){
			String id = request.getParameter("id");
			String secondname = request.getParameter("secondname");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String title = request.getParameter("title");
			String phone = request.getParameter("phone");
			String addres = request.getParameter("addres");
			String email = request.getParameter("email");
			String birchdate = request.getParameter("birchdate");
			LocalDate date = LocalDate.parse(birchdate);
			Date date1 =Date.valueOf(date);
			Long id2 = Long.parseLong(id);
			
			try (PreparedStatement prep =conn.prepareStatement(insert_employee)){
					prep.setLong(1, id2);
					prep.setString(2, secondname);
					prep.setString(3, firstname);
					prep.setString(4, lastname);
					prep.setString(5, title);
					prep.setString(6, phone);
					prep.setString(7, addres);
					prep.setString(8, email);
					prep.setDate(9, date1);
					int rows = prep.executeUpdate();
					
			} catch (Exception e) {
				System.out.println(e);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			String path = "/jspf/Employee.jsp"; 
    		ServletContext servletContext = getServletContext(); 
    		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path); 
    		requestDispatcher.forward(request, response); 
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	           doGet(request, response);
   	}
    	
}
