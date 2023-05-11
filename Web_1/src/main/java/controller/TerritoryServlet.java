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
import java.util.List;

import dao.ConnectionProperty;
//import dao.RegionDAO;
import dao.EmpConnBuilder;
//import dao.TerritoryDAO;
import domain.Territory;
import domain.Region;


/**
 * Servlet implementation class TerritoryServlet
 */
@WebServlet("/territory")
public class TerritoryServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	String select_all_territorys = "SELECT id, regionid, terdesc FROM territorys";
	String select_all_regions = "SELECT id, regionname, regiondesc FROM regions";
	String insert_territory = "INSERT INTO territorys(regionid,terdesc) VALUES (?, ?)";
	ArrayList<Region> regions= new ArrayList<Region>();
	ArrayList<Territory> territorys= new ArrayList<Territory>();
       
  
    public TerritoryServlet() throws FileNotFoundException, IOException{
    	prop = new ConnectionProperty();
    }
    
    private Region FindById(Long id, ArrayList<Region> regions) {
    	if(regions != null) {
    		 for(Region reg: regions) {
    			 if((reg.getRegionID()).equals(id)) {
    				 return reg;
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
    		ResultSet rs = stmt.executeQuery(select_all_regions);
    		if(rs != null) {
    			regions.clear();
	    		while (rs.next()) {
		    			regions.add(new Region(rs.getLong("id"),rs.getString("regionname"),rs.getString("regiondesc")));
	    		}
	    		rs.close();
	    		request.setAttribute("regions", regions);
    			}
    		else {
    			System.out.println("Ошибка загрузки");
    		}
    		long id;
    		stmt = conn.createStatement();
    		rs = stmt.executeQuery(select_all_territorys);
    		if(rs != null) {
    			territorys.clear();
    			while (rs.next()) {
    				id = rs.getLong("regionid");
    				territorys.add(new Territory(rs.getLong("id"),rs.getString("terdesc"), id, FindById(id, regions)));
    			}
    			rs.close();
    			request.setAttribute("territorys", territorys);
    		}
    		} catch (Exception e) {
    			System.out.println(e);
    		} 
    		getServletContext().getRequestDispatcher("/jspf/Territory.jsp").forward(request, response);
    	
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.setContentType("text/html");
		EmpConnBuilder builder = new EmpConnBuilder();
		try (Connection conn = builder.getConnection()){
			String terdesc = request.getParameter("terDesc");
			String reg = request.getParameter("region");
			int index1 = reg.indexOf('=');
			int index2 = reg.indexOf(","); 
			String r1 = reg.substring(index1+1, index2);
			Long regId = Long.parseLong(r1.trim());
			System.out.println(reg);
			
			Territory terr = new Territory(terdesc,regId);
			try (PreparedStatement preparedStatement = conn.prepareStatement(insert_territory)){
					preparedStatement.setLong(1, terr.getRegionID());
					preparedStatement.setString(2, terr.getTerDesc());
					
					
					int result = preparedStatement.executeUpdate();
					} catch (Exception e) {
						System.out.println(e);
					}
					
			
		} catch (SQLException e) {
			e.printStackTrace();
			String path = "/jspf/Territory.jsp"; 
			ServletContext servletContext = getServletContext(); 
			RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path); 
			requestDispatcher.forward(request, response); 
		}
			
		doGet(request, response);
		
		
	}

}
