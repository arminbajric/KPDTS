

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.sql.DriverManager;

public class BazaPodataka {
	public String imeBaze,imeKonekcije;
	public final String USERNAME = "admin";
	public final String PASSWORD = "admin";
	
	public final String CONN_STRING = "jdbc:mysql://localhost:3306/Napomene?useLegacyDatetimeCode=false&serverTimezone=UTC";
	public Date datum;
	
	
BazaPodataka() throws SQLException, ClassNotFoundException
{
	
		
}
void UpdateRow(int ID,String zaDatum,String napomena,String datumIzrade) throws SQLException 
{
	Connection con=null;
	String query1 = "UPDATE `podsjetnici` SET `Datum_izrade` = '"+datumIzrade+"', `Napomena` = '"+napomena+"', `ZaDatum` = '"+zaDatum+"' WHERE `podsjetnici`.`ID` = "+ID+" ";
	// napraviti novi ResultSet
	ResultSet rs = null;

	try (Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			// java.sql.Statement
			Statement statement = connection.createStatement();) {

		// izvrsiti UPDATE query
		statement.executeUpdate(query1);

		// izvrsiti SELECT query
		
	}catch (SQLException ex) {
	    // handle any errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());}
	

}
void deleteRow(int ID) {
	Connection con=null;
	String query1 = "DELETE FROM `podsjetnici`  WHERE `podsjetnici`.`ID` = "+ID+" ";
	// napraviti novi ResultSet
	ResultSet rs = null;

	try (Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			// java.sql.Statement
			Statement statement = connection.createStatement();) {

		// izvrsiti UPDATE query
		statement.executeUpdate(query1);

		// izvrsiti SELECT query
		
	}catch (SQLException ex) {
	    // handle any errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());}
}
public ResultSet rSet() throws SQLException 
{
	final String USERNAME = "admin";
	final String PASSWORD = "admin";
	
	final String CONN_STRING = "jdbc:mysql://localhost:3306/Napomene?useLegacyDatetimeCode=false&serverTimezone=UTC";
	try (Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			// java.sql.Statement
			Statement statement = connection.createStatement();) {

		
		
	ResultSet rs=null;
	String query ="SELECT * FROM podsjetnici";
	rs = statement.executeQuery(query);
	return rs;
	
}}
void readRow() throws SQLException {
	try (Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			// java.sql.Statement
			Statement statement = connection.createStatement();) {

		
		
	ResultSet rs=null;
	String query ="SELECT * FROM podsjetnici";
	rs = statement.executeQuery(query);

	// postaviti kursor
	if(rs.first()==false)
	{
		System.out.println("Nemate podsjetnika!");
	}
	else
	{
		
		do {
			
			
			// print the result
			System.out.println("ID: " + rs.getInt("ID") + ", Izraðeno za datum: " + rs.getString("ZaDatum") + ", Napomena: "
					+ rs.getString("Napomena") + ", Izraðeno datuma: " + rs.getString("Datum_izrade") );
			rs.next();
		}while(rs.isAfterLast()==false);
			// zatvoriti ResultSet
			rs.close();
	}
	}
	
}
void insertRow(String zaDatum,String napomena,String datum)
{
	Connection con=null;
	String query1 = "INSERT INTO `podsjetnici` SET `Datum_izrade` = '"+datum+"', `Napomena` = '"+napomena+"', `ZaDatum` = '"+zaDatum+"'  ";
	// napraviti novi ResultSet
	ResultSet rs = null;

	try (Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			// java.sql.Statement
			Statement statement = connection.createStatement();) {

		// izvrsiti UPDATE query
		statement.executeUpdate(query1);

		// izvrsiti SELECT query
		
	}catch (SQLException ex) {
	    // handle any errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());}
	
}

}
