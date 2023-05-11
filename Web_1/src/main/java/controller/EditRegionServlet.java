package controller;

import java.io.FileNotFoundException; import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement; import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest; import jakarta.servlet.http.HttpServletResponse;
import dao.ConnectionProperty; 
import dao.EmpConnBuilder; 
import domain.Region;



/**
 * Servlet implementation class EditRegionSevlet
 */

@WebServlet("/editregion")
public class EditRegionServlet extends HttpServlet implements Servlet{
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	String select_regions_BY_id = "SELECT id, regionname, regiondesc FROM regions WHERE id = ?";
	String select_all_regions = "SELECT id, regionname, regiondesc FROM regions ORDER BY regionname ASC";
	String edit_region = "UPDATE regions SET regionname = ?, regiondesc = ? WHERE id = ?";
	ArrayList<Region> regions = new ArrayList<Region>();
	ArrayList<Region> editregions = new ArrayList<Region>();
	
    
    public EditRegionServlet() throws FileNotFoundException, IOException {
        super();
        // TODO Auto-generated constructor stub
        prop = new ConnectionProperty();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
			String Id = request.getParameter("id");
			Long id = null;  
			if(Id != null) {
				id = Long.parseLong(Id); 
			}
			
			try (PreparedStatement preparedStatement = conn.prepareStatement(select_regions_BY_id)) {
					preparedStatement.setLong(1, id);
					rs = preparedStatement.executeQuery();
					if(rs != null) {
						editregions.clear();
						while (rs.next()) {
							editregions.add(new Region(rs.getLong("id"),rs.getString("regionname"),rs.getString("regiondesc")));
						}
						rs.close();
						request.setAttribute("regionEdit", editregions);
					}
					else
					{
						System.out.println("Ошибка загрузки регионов");
					}
			}catch (Exception e) { 
				System.out.println(e);
			}
		}catch (Exception e) { 
			System.out.println(e);
		}
		String path = "/jspf/editregion.jsp"; 
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
			String regionName = request.getParameter("regionName");
			String regionDesc = request.getParameter("regionDesc");
			try (PreparedStatement prep = conn.prepareStatement(edit_region)){
				prep.setString(1,regionName);
				prep.setString(2,regionDesc);
				prep.setLong(3,id);
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
