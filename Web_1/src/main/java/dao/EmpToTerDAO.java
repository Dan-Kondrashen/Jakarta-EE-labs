//package dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.ArrayList;
//
//import domain.Employee;
//import domain.EmployeeTerritory;
//import domain.Territory;
//
//public class EmpToTerDAO extends ConnectionProperty{
//	
//	
//
//	public EmpToTerDAO()  {
//		
//	}
//
//	private static final String select_all_territorys = "SELECT id, regionid, terdesc FROM territorys";
//	private static final String select_employees_name = "SELECT * FROM employees";
//	private static final String select_all_empter = "SELECT id, empid,terid FROM empter";
//	
//	private Employee findEmpById(Long id, List<Employee> employees) {
//		if(employees != null) {
//			for(Employee r: employees) {
//				if((r.getId()).equals(id)) {
//					return r;
//				}
//			}
//		} else {
//			return null;
//		}
//		return null;
//	}
//	
//	private Territory findTerById(Long id, List<Territory> territory) {
//    	if(territory != null) {
//    		 for(Territory ter: territory) {
//    			 if((ter.getTerID()).equals(id)) {
//    				 return ter;
//    			 }
//    		 }
//    	}
//    	else {
//    		 return null;
//    	}
//    	 return null;
//    }
//
//	public List<EmployeeTerritory> selectAllLinked() {
//
//		List<EmployeeTerritory> employeesTer= new ArrayList<EmployeeTerritory>();
//		List<Territory> territorys= new ArrayList<Territory>();
//		List<Employee> employees= new ArrayList<Employee>();
////		EmpConnBuilder builder = new EmpConnBuilder();
//		try (Connection connection = getConnection()){
//
//			
//			PreparedStatement preparedStatement = connection.prepareStatement(select_employees_name);
//			
//			ResultSet rs = preparedStatement.executeQuery();
//
//			
//			while (rs.next()) {
//				employees.add(new Employee(rs.getLong("id"),rs.getString("title"),rs.getString("secondname"),rs.getString("firstname"),rs.getString("lastname"),
//  						 rs.getString("phone"),rs.getString("email"),rs.getDate("birchdate"),rs.getString("addres")));
//			}
//			
//			preparedStatement = connection.prepareStatement(select_all_territorys);
//			rs = preparedStatement.executeQuery();
//			while (rs.next()) {
//				territorys.add(new Territory(rs.getLong("id"),rs.getString("terdesc"), rs.getLong("regionid")));
//				
//			}
//			
//			long id1;
//			long id2;
//			preparedStatement = connection.prepareStatement(select_all_empter);
//			rs = preparedStatement.executeQuery();
//			while (rs.next()) {
//				id1 =rs.getLong("empid");
//				id2 = rs.getLong("terid");
//				employeesTer.add(new EmployeeTerritory(rs.getLong("id"),id1,id2,findEmpById(id1,employees),findTerById(id2,territorys)));
//				
//			}
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
//		return employeesTer;
//	}
//	
//
//}