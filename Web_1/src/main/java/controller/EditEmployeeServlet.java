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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.ConnectionProperty;
import dao.EmpConnBuilder;
import domain.Employee;
import domain.Region;

/**
 * Servlet implementation class EditEmployeeServlet
 */
@WebServlet("/editemployee")
public class EditEmployeeServlet extends HttpServlet implements Servlet{
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	String select_all_employees = "SELECT id, secondname, firstname, lastname, title, phone, addres, email, birchdate FROM employees ORDER BY id ASC";
	String select_employee_BY_id = "SELECT id, secondname, firstname, lastname, title, phone, addres, email, birchdate FROM employees WHERE id = ?";
	String edit_employee = "UPDATE employees SET secondname = ?, firstname = ?, lastname = ?, title = ?, phone = ?, addres = ?, email = ?, birchdate = ? WHERE id = ?";
	ArrayList<Employee> employees= new ArrayList<Employee>();
	ArrayList<Employee> editemp= new ArrayList<Employee>();
	
	
    public EditEmployeeServlet() throws FileNotFoundException, IOException{
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
					editemp.clear();
					while (rs.next()) {
						editemp.add(new Employee(rs.getLong("id"),rs.getString("secondname"),rs.getString("firstname"),rs.getString("lastname"),rs.getString("title"),
								 rs.getString("phone"),rs.getString("addres") ,rs.getString("email"),rs.getDate("birchdate")));
					}
					rs.close();
					request.setAttribute("employeeEdit", editemp);
				}
			
			} catch (Exception e) {
				System.out.println(e);
		} 
		} catch (Exception e) {
				System.out.println(e);
		} 
		String path = "/jspf/editemployee.jsp"; 
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
			
			try (PreparedStatement prep = conn.prepareStatement(edit_employee)){
				prep.setString(1, secondname);
				prep.setString(2, firstname);
				prep.setString(3, lastname);
				prep.setString(4, title);
				prep.setString(5, phone);
				prep.setString(6, addres);
				prep.setString(7, email);
				prep.setDate(8, date1);
				prep.setLong(9,id);
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
