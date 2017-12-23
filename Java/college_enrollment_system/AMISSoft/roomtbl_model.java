package mes.gui;

import javax.swing.table.*;
import java.util.*;
import java.sql.*;
import javax.swing.*;

public  class roomtbl_model extends AbstractTableModel{
	String columnName[] = { "ClassNo","SubjectCode","Title","Days","TimeSlot","Ins_ID" };
	Object value [][] = { { "...","...","...","...","...","...","..."} };
	Connection connection;
	int totalRows;
	int totalPopulation;
	public roomtbl_model ( Connection con ) {
		connection=con;
//		totalPopulation=pRows;
		value = new Object [ 0 ][ columnName.length ];
	}
	public void getList ( String branchCode) {
		try {
			Statement statement = connection.createStatement();
		    String query="SELECT ClassNo,SubjectCode,Title,Days,TimeSlot,Ins_ID FROM class_schedule WHERE RoomNo='"+branchCode+"'";
		    ResultSet resultSet = statement.executeQuery ( query );			
		    totalPopulation = getTotalRows ( branchCode );
			value = new Object [ totalPopulation ][ columnName.length ];
			for ( int i = 0; resultSet.next(); i++ ) {
				value [ i ] [ 0 ] = resultSet.getString ( 1 );
				value [ i ] [ 1 ] = resultSet.getString ( 2 );
				value [ i ] [ 2 ] = resultSet.getString ( 3 );
				value [ i ] [ 3 ] = resultSet.getString ( 4 );
				value [ i ] [ 4 ] = resultSet.getString ( 5 );
				value [ i ] [ 5 ] = resultSet.getString ( 6 );
				
			}
			statement.close();
		}
		catch ( SQLException sqle ){
			System.out.println ( "redefinedModel: " + sqle.getMessage() );
      	}
		fireTableStructureChanged();
	}
	public int getTotalRows ( String branchCode ) {
		int totalRows = 0;
		try {
			Statement statement = connection.createStatement(); 
		    String query="SELECT count(*) FROM class_schedule  WHERE RoomNo='"+branchCode+"'";
		    ResultSet resultSet = statement.executeQuery ( query );
		    resultSet.next();
			totalRows = resultSet.getInt ( 1 );
			statement.close();
		    }
		catch ( SQLException sqle )
		{
		System.out.println ( "redefinedModel: " + sqle.getMessage() );
		}
		
		return totalRows;
	}

 	public String getColumnName ( int column ) {
		return columnName [ column ];
	}
	
	public int getColumnCount() {
		return columnName.length;
	}
	
	public int getRowCount() {
		return value.length;
	}
	
	public Object getValueAt ( int row, int col ) {
		return value [ row ] [ col ];
	} 		
}	