//package dao;
//
//import java.util.List;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import domain.Region;
//import domain.Territory;
//
//public class TerritoryDAO extends ConnectionProperty{
//	
//	
//	
//	public TerritoryDAO() {
//		
//	}
//	
//	
//	private static final String select_all_territorys = "SELECT id, regionid, terdesc FROM territorys";
//	private static final String select_all_regions = "SELECT id, regionname, regiondesc FROM regions";
//	
//	
//	
//	private Region findById(Long id, List<Region> regions) {
//    	if(regions != null) {
//    		 for(Region reg: regions) {
//    			 if((reg.getRegionID()).equals(id)) {
//    				 return reg;
//    			 }
//    		 }
//    	}
//    	else {
//    		 return null;
//    	}
//    	 return null;
//    }
//	public List<Territory> selectAllTerritory() {
//		List<Region> regions= new ArrayList<Region>();
//		List<Territory> territorys= new ArrayList<Territory>();
////		EmpConnBuilder builder = new EmpConnBuilder();
//		try (Connection connection = getConnection()){
//			
//			PreparedStatement preparedStatement = connection.prepareStatement(select_all_regions);
//			ResultSet rs = preparedStatement.executeQuery();
//			while (rs.next()) {
//    			regions.add(new Region(rs.getLong("id"),rs.getString("regionname"),rs.getString("regiondesc")));
//    			
//			}
//			long id;
//
//			preparedStatement = connection.prepareStatement(select_all_territorys);
//			rs = preparedStatement.executeQuery();
//			while (rs.next()) {
//				id = rs.getLong("regionid");
//				territorys.add(new Territory(rs.getLong("id"),rs.getString("terdesc"), id, findById(id, regions)));
//				
//			}
//		}catch (SQLException e) {
//			System.out.println(e);
//		}
//		return territorys;
//	
//	}
//}

