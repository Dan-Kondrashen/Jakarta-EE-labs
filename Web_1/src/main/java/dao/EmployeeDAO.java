//package dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import domain.Employee;
//
//public class EmployeeDAO extends ConnectionProperty{
//	private static final String select_all_employee = "SELECT id, title, secondname, firstname, lastname, phone, email, addres, birchdate FROM employees";
//
//	public List<Employee> selectAllEmployees() {
//		List<Employee> employee= new ArrayList<Employee>();
////		EmpConnBuilder builder = new EmpConnBuilder();
//		try (Connection connection = getConnection()){
//			
//			PreparedStatement preparedStatement = connection.prepareStatement(select_all_employee);
//			ResultSet rs = preparedStatement.executeQuery();
//			while (rs.next()) {
//				employee.add(new Employee(rs.getLong("id"),rs.getString("title"),rs.getString("secondname"),rs.getString("firstname"),rs.getString("lastname"),
//						 rs.getString("phone"),rs.getString("email"),rs.getDate("birchdate"),rs.getString("addres")));
//			}
//
//		}catch (SQLException e) {
//			System.out.println(e);
//		}
//		
//		return employee;
//	}
//}
