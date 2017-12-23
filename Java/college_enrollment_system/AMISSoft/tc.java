
import java.sql.*;

public class tc { static public void main (String args[]) 
{ 
Connection con = null; 

try 
{ 
//Class.forName("MySql ODBC 3.51 Driver"); 
//jdbc:odbc:dsn_name;UID=your_uid;PWD=your_pwd 
//Class.forName("jdbc:odbc:); 

//Class.forName("jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb)};DBQ="+file;"); 
Class.forName("org.gjt.mm.mysql.Driver");      
} 

catch (Exception e) 
{ 
System.err.println("Unable to load driver."); 
e.printStackTrace(); 
return; 
} 

try { 

con = DriverManager.getConnection("jdbc:mysql://192.168.1.37:3306/dbmaster"); 
//con = DriverManager.getConnection("jdbc:odbc:DBSRC;UID=root;PWD="); 
System.out.println("Connection successful!"); 
System.out.println("I AM IN THE TEST DATABASE!"); } 

catch(SQLException e) 
{ e.printStackTrace(); } 

finally { if (con != null ) 
{ 
try { con.close(); 
System.out.println("I HAVE CLOSED THE CONNECTION TO THE DATABASE!"); 
} 
catch (SQLException e ) 
{ e.printStackTrace(); } 
} //if 
} //finally 
} //main 
} //tcdb 
