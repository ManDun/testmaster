package mes.gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.List;

import mes.dbase.*;
import mes.util.*;
import mes.gui.*;
import mes.util.*;
import mes.util.print.*;

public class showClassPrograms_Archieves extends MESFrame implements ActionListener{

    MainFrame parent;
    MESTable table;
    showStudentList_Archieves showArchieves;
    JTextField keyMaster;
    public showClassPrograms_Archieves(MainFrame main)
    {
    	super(700,300,"",new String[] {"Locate Record","Print","Class Schedules","Register Archieve Students","Close"}  );
        setTitle("Student Class Schedule Archieves");
        parent=main;
        init();
        setSize(700,300);
        setBounds(10,10,500,400);
        setVisible(true);
      } 
    void init(){
        addButtonActionListener(this);
        
        MAINPANEL.setLayout(new BorderLayout());
        
        table = new MESTable("",new String[] {"ClassNo","SubjectCode","Title","Lec","Lab","Days","TimeSlot","RoomNo","Limits","Ins_ID","CourseID"});
        table.setColumnSize(new int[] {85,120,270,60,60,60,100,70,75,160,90});
        MAINPANEL.add(table);
        table.useDefaultRenderer(new int[] {-1});
        table.getTable().addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent event){
                String select =  table.getValueAtSelectedRow(2);
                if(select == null)
                {
                    return;
                }else{
                    BUTTONS[0].setEnabled(true);
                    select.trim();
                }
            }
        
        });
        //BUTTONS[0].setEnabled(false);
        fillTable();
    }
    void fillTable()
    {
        DataCollection col = parent.getCollection("class_schedule");
        table.getTableModel().setRowCount(0);
        
        for(int i = 0; i < col.size(); i++){
            Hashtable data  = (Hashtable)col.get(i);
            Object[] row = { new JLabel(data.get("ClassNo")+""),
                             new JLabel(data.get("SubjectCode")+""),
                             new JLabel(data.get("Title")+""),
                             new JLabel(data.get("Lec")+""),
                             new JLabel(data.get("Lab")+""),
                             new JLabel(data.get("Days")+""),
                             new JLabel(data.get("TimeSlot")+""),
                             new JLabel(data.get("RoomNo")+""),
                             new JLabel(data.get("Limits")+""),
                             new JLabel(data.get("Ins_ID")+""),
                             new JLabel(data.get("CourseID")+""),
                           };
            table.getTableModel().addRow(row);
        }
    }
    public void actionPerformed(ActionEvent event )
    {
        String act = event.getActionCommand();
        if(act.equals("Close"))
        {
        dispose();	
        }
        else if(act.equals("Modify"))
        {
            if(table.getTable().getSelectedRow() != -1)
            {
      
            }
        }
 //       else if(act.equals("Register"))
  //      {
  //         if(table.getTable().getSelectedRow()!=-1)
//           {
           
    //       	parent.addFrame(new ClassProgramChecking_Archieves(parent,this,table.getValueAtSelectedRow(0),table.getValueAtSelectedRow(1),table.getValueAtSelectedRow(2),table.getValueAtSelectedRow(3),table.getValueAtSelectedRow(4),table.getValueAtSelectedRow(5),table.getValueAtSelectedRow(6),table.getValueAtSelectedRow(7),table.getValueAtSelectedRow(8),table.getValueAtSelectedRow(9),table.getValueAtSelectedRow(10) ) );
     //      }  	
      //  }
        else if(act.equals("Register Archieve Students"))
        {
        	if(table.getTable().getSelectedRow()!=-1)
        	{
          	parent.addFrame(new ClassProgramChecking_Archieves(parent,this,table.getValueAtSelectedRow(0),table.getValueAtSelectedRow(1),table.getValueAtSelectedRow(2),table.getValueAtSelectedRow(3),table.getValueAtSelectedRow(4),table.getValueAtSelectedRow(5),table.getValueAtSelectedRow(6),table.getValueAtSelectedRow(7),table.getValueAtSelectedRow(8),table.getValueAtSelectedRow(9),table.getValueAtSelectedRow(10) ) );
        	}
        }
        else if(act.equals("Locate Record"))
        {
        	//CODE PATCH 07/10/2006
        	String recNum=JOptionPane.showInputDialog("TYPE CLASS NUMBER TO SEARCH");
			try {
			    
			    System.out.println ( "ssss" );	
	        	ResultSet rs = parent.getResultSet();
	        	List<String> idno2 = new ArrayList<String>();
	        	while ( rs.next() ) {
	        		idno2.add ( rs.getString( 1 ) );
	        		//System.out.println ( rs.getString( 1 ) );
	        	}
	        	
	        	int index = 0;
	        	for ( int i = 0; i < idno2.size(); i++ ) {
	        		if ( idno2.get(i).equalsIgnoreCase ( recNum ) ) {
	        			index = i;
	        			break;
	        		}
	        	}
	        	
	        	table.getTable().changeSelection ( index, 0, false, false );
	        	
	        } catch ( SQLException sqle ) {
	        	System.out.println ( sqle.getMessage() );
	        }
        	
        }
        else if(act.equals("Print"))
         {
         JLabel titleLabel = new JLabel(createGroundMessage());
         titleLabel.setBounds(20, 3, getSize().width-30, 40);
         new PrintPreviewFrame(table.getTable(),titleLabel,"Student Master List");
         }
         else{
                JOptionPane.showMessageDialog(parent, "Select the examination.", "Update Questionnaire", JOptionPane.WARNING_MESSAGE);
            }
 }       
  private String createGroundMessage(){
        String title="Student Master List";
        String html =   "<html>"+

                        "<head>"+
                        "</head>"+
                        "<body>"+
                       // "<p align=left><b><font size=3>"+table.getValueAtSelectedRow(3)+"</font></b></p>"+
                       // "<p align=left><font size=2>"+table.getValueAtSelectedRow(0)+" - "+table.getValueAtSelectedRow(1)+"</p>"+
                       // "<p align=left><font size=2>"+table.getValueAtSelectedRow(0)+ "</p>"+
                        "<p align=left><font size=3>"+title+"</p>"+
                        "</body>"+
                        "</html>";
                        
        

        return  html ;
    }    
   /*
    void edit(){
        int del = JOptionPane.showConfirmDialog(this, "Are you sure you want to "+BUTTONS[0].getText().toLowerCase()+" this examination?", "Options", 
                                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
                                        );
        if(del ==0 ){    
            String sno = table.getValueAtSelectedRow(0);
            String sql = "UPDATE EXAM SET STATUS = '"+BUTTONS[0].getText()+"'WHERE EXAM_NO = '"+sno+"'";
            int delete = parent.database.executeUpdate(sql);
            
            if(delete == 0){
                DataCollection col = parent.getCollection("ActivityMaster");
                Hashtable empInfo = col.getHashtable("exam_no", sno);
                empInfo.put("status", BUTTONS[0].getText());
               //fillTable();
            }
        }
        
    }
    void delete(){
        if(table.getTable().getSelectedRow() == -1){
            JOptionPane.showMessageDialog(parent, "Select Activity to Delete.", "Delete Activity", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int del = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete Activity?", "Delete", 
                                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
                                        );
        if(del ==0 ){    
            String actCode = table.getValueAtSelectedRow(0);
            String sql = "DELETE FROM ActivityMaster WHERE ActCode = '"+actCode+"'";
            int delete = parent.database.executeUpdate(sql);
           // parent.database.executeUpdate("DELETE FROM QUESTION WHERE EXAM_NO = '"+sno+"'");
            if(delete == 0){
                DataCollection col = parent.getCollection("ActivityMaster");
                Hashtable empInfo = col.getHashtable("ActCode",actCode);
                col.remove(empInfo);
                //fillTable();
            }
        }
        
    }*/
    
}