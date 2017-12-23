package mes.gui;


import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.awt.Color.*;
import java.awt.*;
public class classListener implements ActionListener
{
      Connection finalConnection;
	  redefinedModel finalRM;
     
      JTextField BranchCode;
	  JTextField BranchAddress;
	  JTextField TotalUnits;
	  JTextField CurriculumField;
	  JTextArea status;
      redefinedModel model; 
      JScrollPane resultsPane;
      ResultSet resultSet;
      String msg;
      MainFrame parent;
      classListener cls;
      JTextArea statusArea;
      String output="PLEASE CHECK IF YOUR TYPE THE COURSE CODE CORRECTLY\n"
                 +"OR ELSE YOUR LEAVE IT EMPTY?---[PLEASE TRY AGAIN]---\n";
	public classListener (Connection connection, redefinedModel rm, JTextField branchId, JTextField branchAddress,JTextField ptotalunits,JTextField pField)
	    {
	    finalConnection = connection;
		finalRM = rm;
        BranchCode=branchId;
	    BranchAddress=branchAddress;
	    TotalUnits=ptotalunits;   
	   	CurriculumField=pField;
	   	}
	public void actionPerformed( ActionEvent e )
	{
		try {
     			Statement statement = finalConnection.createStatement();
     			String query = "SELECT CourseCode,Title,TotalUnits,CurrID FROM Courses WHERE CourseCode='"+BranchCode.getText()+"'";
      			ResultSet resultSet = statement.executeQuery(query);
      			resultSet.next();         
      //----------------------------------Display Records-----------------------------------//
				BranchCode.setText(resultSet.getString(1));
				BranchAddress.setText(resultSet.getString(2));
		        TotalUnits.setText(resultSet.getString(3));
		        CurriculumField.setText(resultSet.getString(4));
		        finalRM.getList ( BranchCode.getText() );
				statement.close();
      	    }
       catch ( SQLException sqle )
       {
			JOptionPane.showMessageDialog(null,"ClassNo Not Found?","SEARCH CLASSNO",JOptionPane.WARNING_MESSAGE);
	      	finalRM.getList ( null );
	      	BranchCode.setText("");
	      	BranchCode.requestFocus();
	      	BranchAddress.setText("");
	      	TotalUnits.setText("");
	      	CurriculumField.setText("");
			//status.setText ( " " );
			//emptyfields();
			//ClassNo.setText("");
			//ClassNo.requestFocus();
      }
  
  }//END OF CONSTRUCTOR

}//END OF BRANCHLISTENER