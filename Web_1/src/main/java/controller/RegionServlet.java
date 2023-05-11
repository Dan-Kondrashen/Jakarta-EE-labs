package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet; 
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest; 
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Employee;
import domain.Region;
import dao.EmpConnBuilder;
import java.sql.PreparedStatement;

import dao.ConnectionProperty;
//import dao.RegionDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;


@WebServlet("/region")
public class RegionServlet extends HttpServlet implements Servlet {

  
	private static final long serialVersionUID = 1L;

ConnectionProperty prop;
String select_all_regions = "SELECT id, regionname, regiondesc FROM regions";
String insert_regions = "INSERT INTO regions(id, regionname,regiondesc) VALUES (?, ?, ?)";
ArrayList<Region> regions= new ArrayList<Region>();

public RegionServlet () throws FileNotFoundException, IOException {
	prop = new ConnectionProperty();
}

 
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	EmpConnBuilder builder = new EmpConnBuilder();
	try (Connection conn = builder.getConnection()) {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(select_all_regions);
		if(rs != null) {
			regions.clear();
		while (rs.next()) {
			regions.add(new Region(rs.getLong("id"),rs.getString("regionname"),rs.getString("regiondesc")));
		}
		rs.close();
		request.setAttribute("regions", regions);
		}
		} catch (Exception e) {
		System.out.println(e);
		} 
	String path = "/jspf/Region.jsp"; 
	ServletContext servletContext = getServletContext(); 
	RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path); 
	requestDispatcher.forward(request, response); 
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpConnBuilder builder = new EmpConnBuilder();
		try (Connection conn = builder.getConnection()){
			String Id = request.getParameter("id");
			String regionName = request.getParameter("regionName");
			String regionDesc = request.getParameter("regionDesc");
			Long id = Long.parseLong(Id, 10);
			try (PreparedStatement prep = conn.prepareStatement(insert_regions)){
				prep.setLong(1, id);
				prep.setString(2, regionName);
				prep.setString(3, regionDesc);
				int rows = prep.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String path = "/jspf/Region.jsp"; 
			ServletContext servletContext = getServletContext(); 
			RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path); 
			requestDispatcher.forward(request, response); 
		}
	           doGet(request, response);
	     }
	}

