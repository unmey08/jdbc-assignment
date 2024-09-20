package JDBC;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class JDBCStatements {
	public static void main(String[] args) throws Exception {
		String user = "root";
		String password = "root1234";
		String connectionUrl = "jdbc:mysql://localhost:3306/Employee";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection(connectionUrl, user, password);
		
		PreparedStatement ps = con.prepareStatement("insert into emp values(?, ?, ?)");
		
		// Insert data using scanner
		Scanner employeeObj = new Scanner(System.in);
	    System.out.println("Enter Employee ID: ");
	    int empId = employeeObj.nextInt(); 
	    employeeObj.nextLine();
	    
	    System.out.println("Enter Employee name: ");
	    String empName = employeeObj.nextLine();
	   
	    System.out.println("Enter Employee Salary: ");
	    int empSalary = Integer.parseInt(employeeObj.nextLine());
		
	    ps.setInt(1, empId);
	    ps.setString(2, empName);
	    ps.setInt(3, empSalary);
	    
	    int result = ps.executeUpdate();
	    System.out.println(result + " record added");
	    
	    
	    // select all statement
	    
	    PreparedStatement selectAllStatement = con.prepareStatement("select * from emp");
		
		ResultSet myRs= selectAllStatement.executeQuery();  
		System.out.println("");
		System.out.println("Select All statement result: ");
		while (myRs.next()) {
            String Name = myRs.getString("employee_name");
            int id = myRs.getInt("employee_id");
            int salary = myRs.getInt("salary");
            System.out.println(Name + "     " + id + "     " + salary);
        }
		
		// select statement
	    
	    PreparedStatement selectStatement = con.prepareStatement("select * from emp where employee_id=6");
		
		ResultSet selectRs= selectStatement.executeQuery();  
		
		System.out.println("");
		System.out.println("Select statement result: ");
		if (selectRs.next()) {
            String Name = selectRs.getString("employee_name");
            int id = selectRs.getInt("employee_id");
            int salary = selectRs.getInt("salary");
            System.out.println(Name + "     " + id + "     " + salary);
        }
		else {
			System.out.println("No record found");
		}
		
		
		// Update statement
		
		Statement updateStatement = con.createStatement();
		updateStatement.executeUpdate("update emp set employee_name = 'Sameer' where employee_id = 2");
		ResultSet updateRs = updateStatement.executeQuery("select * from emp");
		
		System.out.println("Update statement result: ");
		while (updateRs.next()) {
            String Name = updateRs.getString("employee_name");
            int id = updateRs.getInt("employee_id");
            int salary = updateRs.getInt("salary");
            System.out.println(Name + "     " + id + "     " + salary);
        }
		
		// Delete statement
		
		Statement deleteStatement = con.createStatement();
		deleteStatement.executeUpdate("delete from emp where employee_id = 65");
		ResultSet deleteRs = deleteStatement.executeQuery("select * from emp");
		
		System.out.println("Delete statement result: ");
		while (deleteRs.next()) {
            String Name = deleteRs.getString("employee_name");
            int id = deleteRs.getInt("employee_id");
            int salary = deleteRs.getInt("salary");
            System.out.println(Name + "     " + id + "     " + salary);
        }
		
		con.close();
	}
}
