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
import domain.Region;
import domain.Territory;

/**
 * Servlet implementation class EditTerritoryServlet
 */
@WebServlet("/editterritory")
public class EditTerritoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	String select_all_territorys = "SELECT id, regionid, terdesc FROM territorys ORDER BY id ASC";
	String select_all_regions = "SELECT id, regionname, regiondesc FROM regions ORDER BY id ASC";
	String select_territory_BY_id = "SELECT id, regionid, terdesc FROM territorys WHERE id = ?";
	String edit_territory = "UPDATE territorys SET terdesc = ?, regionid = ?  WHERE id = ?";
	ArrayList<Region> regions= new ArrayList<Region>();
	ArrayList<Territory> territorys= new ArrayList<Territory>();
	ArrayList<Territory> editterr= new ArrayList<Territory>();
    
	
	
    public EditTerritoryServlet() throws FileNotFoundException, IOException {
        super();
        // TODO Auto-generated constructor stub
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpConnBuilder builder = new EmpConnBuilder();
    	try (Connection conn = builder.getConnection()) {
    		String terId = request.getParameter("id");
    		Long tid = Long.getLong(terId);
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
    		
    		stmt = conn.createStatement();
    		rs = stmt.executeQuery(select_all_territorys);
    		if(rs != null) {
    			territorys.clear();
    			while (rs.next()) {
    				Long Id = rs.getLong("regionid");
    				territorys.add(new Territory(rs.getLong("id"),rs.getString("terdesc"), Id, FindById(Id, regions)));
    			}
    			rs.close();
    			request.setAttribute("territorys", territorys);
    		}
    		String Id1 = request.getParameter("id");
			Long id = null;  
			if(Id1 != null) {
				id = Long.parseLong(Id1); 
			}
			try (PreparedStatement preparedStatement = conn.prepareStatement(select_territory_BY_id)) {
					preparedStatement.setLong(1, id);
					rs = preparedStatement.executeQuery();
					if(rs != null) {
						editterr.clear();
						while (rs.next()) {
							editterr.add(new Territory(rs.getLong("id"),rs.getString("terdesc"), rs.getLong("regionid")));
						}
						rs.close();
						request.setAttribute("territoryEdit", editterr);
					}
					else
					{
						System.out.println("Ошибка загрузки регионов");
					}
			} catch (Exception e) { 
				System.out.println(e);
			}
		} catch (Exception e) { 
			System.out.println(e);
		}
		String path = "/jspf/editterritory.jsp"; 
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
			String terdesc = request.getParameter("terDesc");
			String reg = request.getParameter("region");
			int index1 = reg.indexOf('=');
			int index2 = reg.indexOf(","); 
			String r1 = reg.substring(index1+1, index2);
			Long regId = Long.parseLong(r1.trim());
			
			
			try (PreparedStatement prep = conn.prepareStatement(edit_territory)){
				
				prep.setString(1, terdesc);
				prep.setLong(2, regId);
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
