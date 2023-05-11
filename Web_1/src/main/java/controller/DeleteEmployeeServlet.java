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
import java.util.ArrayList;

import dao.ConnectionProperty;
import dao.EmpConnBuilder;
import domain.Employee;


@WebServlet("/deleteemployee")
public class DeleteEmployeeServlet extends HttpServlet implements Servlet{
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	String select_all_employees = "SELECT id, secondname, firstname, lastname, title, phone, addres, email, birchdate FROM employees ORDER BY id ASC";
	String select_employee_BY_id = "SELECT id, secondname, firstname, lastname, title, phone, addres, email, birchdate FROM employees WHERE id = ?";
	String delete_employee = "DELETE FROM employees WHERE id = ?";
	ArrayList<Employee> employees= new ArrayList<Employee>();
	ArrayList<Employee> deleteemp= new ArrayList<Employee>();
   
	
	
    public DeleteEmployeeServlet() throws FileNotFoundException, IOException {
        super();
        prop = new ConnectionProperty();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpConnBuilder builder = new EmpConnBuilder();
		try (Connection conn = builder.getConnection()) { 
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(select_all_employees);
			if(rs != null) {
				employees.clear();
				while (rs.next()) {
					employees.add(new Employee(rs.getLong("id"),rs.getString("secondname"),rs.getString("firstname"),rs.getString("lastname"),rs.getString("title"),
							 rs.getString("phone"),rs.getString("addres") ,rs.getString("email"),rs.getDate("birchdate")));
				}
				rs.close();
				request.setAttribute("employees", employees);
			}
			String Id = request.getParameter("id");
			Long id = null;  
			if(Id != null) {
				id = Long.parseLong(Id); 
			}
			try (PreparedStatement preparedStatement = conn.prepareStatement(select_employee_BY_id)) {
				preparedStatement.setLong(1, id);
				rs = preparedStatement.executeQuery();
				if(rs != null) {
					deleteemp.clear();
					while (rs.next()) {
						deleteemp.add(new Employee(rs.getLong("id"),rs.getString("secondname"),rs.getString("firstname"),rs.getString("lastname"),rs.getString("title"),
								 rs.getString("phone"),rs.getString("addres") ,rs.getString("email"),rs.getDate("birchdate")));
					}
					rs.close();
					request.setAttribute("employeeDelete", deleteemp);
				}
			
			} catch (Exception e) {
				System.out.println(e);
		} 
		} catch (Exception e) {
				System.out.println(e);
		} 
		String path = "/jspf/deleteemployee.jsp"; 
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
			try (PreparedStatement prep = conn.prepareStatement(delete_employee)){
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
