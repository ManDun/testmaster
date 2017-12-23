
package mes.gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.print.*;
import java.sql.*;
import javax.swing.table.*;

import mes.dbase.*;
import mes.gui.*;
import mes.util.*;

public class FacultyChecking extends MESFrame implements ActionListener
{
    MainFrame parent;
    showFacultyList showfacultylist;
    MESTable questionTable, answerTable;
    MESTable table;
    String url="jdbc:odbc:DBMaster";
    String driver="sun.jdbc.odbc.JdbcOdbcDriver";
    Connection connection;
    Statement statement;
    ResultSet rs;
    PrinterJob job;
    PageFormat landscape;
    String rowCount="";
    int rowcount=0;
    JTextField IDField,FirstNameField,LastNameField,AddressField,GenderField,CourseField,SubjectField,UnitsField, totalLabUnits,contactField;
    JTextField totalSubjectField;
    public String strID,strFName,strLName,strAddress,strGender,strCourse,strcontactno;
    public FacultyChecking(MainFrame main ,showFacultyList showfacultylist, String IDCode,String FNameCode,String LNameCode,String AddressCode,String GenderCode,String CourseCode){
        super(600,410,"", new String[] {"Print","Add Subject","Drop Subject","Close"});
        strID = IDCode;
        strFName=FNameCode;
        strLName =LNameCode;
        strAddress=AddressCode;
        strGender=GenderCode;
        strCourse=CourseCode;
       // strcontactno=ContactCode;
        parent = main;
        setTitle("Faculty Loading File");
        init();
        fillTable();
        setSize(580,390);
        setVisible(true);
    }
    void init(){
        addButtonActionListener(this);
        
        MAINPANEL.setLayout(null);
        //MAINPANEL.setBackground(new Color(213,229,219));
      
        JLabel t1=new JLabel("Faculty Loading File");
        MAINPANEL.add(t1).setBounds(290,5,200,25);
        t1.setFont(new Font("Verdana",Font.BOLD,14));
        
        JLabel id = new JLabel("Employee No",JLabel.LEFT);
        JLabel firstname= new JLabel("FirstName", JLabel.LEFT);
        JLabel lastname= new JLabel("LastName", JLabel.LEFT);
        JLabel address= new JLabel("Address",JLabel.LEFT);
        JLabel gender=new JLabel("SSS No",JLabel.LEFT);
        JLabel course=new JLabel("TIN#",JLabel.LEFT);
        JLabel lbltotalsubjects=new JLabel("Total No. of Subjects:",JLabel.LEFT);
        lbltotalsubjects.setFont(new Font("Verdana",Font.PLAIN,12));
        JLabel lbltotalunits=new JLabel("Lec.Unit(s)",JLabel.LEFT);
        lbltotalunits.setFont(new Font("Verdana",Font.PLAIN,12));
        JLabel lbltotunits=new JLabel("Total No. of Unit(s)");
        lbltotunits.setFont(new Font("Verdana",Font.PLAIN,12));
        
        
        //## code patch 07/10/2006
        //begin!
        JLabel labUnits = new JLabel ("Lab. Unit(s)", JLabel.LEFT );
        labUnits.setFont(new Font("Verdana",Font.PLAIN,12));
        //end
       
        MAINPANEL.add(id).setBounds(20,30,100,24);
        id.setFont(new Font("Verdana",Font.BOLD,13));
        
        MAINPANEL.add(firstname).setBounds(20,60,100,24);
        firstname.setFont(new Font("Verdana",Font.BOLD,11));
      
        MAINPANEL.add(lastname).setBounds(20,90,100,24);
        lastname.setFont(new Font("Verdana",Font.BOLD,11));
      
        MAINPANEL.add(address).setBounds(20,120,100,24);
        address.setFont(new Font("Verdana",Font.BOLD,11));
      
        MAINPANEL.add(gender).setBounds(280,30,100,24);
        gender.setFont(new Font("Verdana",Font.BOLD,13));
        
        MAINPANEL.add(course).setBounds(450,30,100,24);
        course.setFont(new Font("Verdana",Font.BOLD,13));
      
        MAINPANEL.add(lbltotalsubjects).setBounds(20,330,200,21);
        lbltotalsubjects.setFont(new Font("Verdana",Font.BOLD,10));
      
        MAINPANEL.add(lbltotunits).setBounds(20,360,200,21);
        lbltotunits.setFont(new Font("Verdana",Font.BOLD,10));
      
       
        MAINPANEL.add(lbltotalunits).setBounds(420,330,200,21);
        lbltotalunits.setFont(new Font("Verdana",Font.BOLD,10));
        
        MAINPANEL.add(labUnits).setBounds(420,360,200,21);
        labUnits.setFont(new Font("Verdana",Font.BOLD,10));
        
     
        IDField = new JTextField(strID);
        IDField.setFont(new Font("Verdana",Font.BOLD,11));
        FirstNameField=new JTextField(strFName);
        FirstNameField.setFont(new Font("Verdana",Font.BOLD,11));
        LastNameField = new JTextField(strLName);
        LastNameField.setFont(new Font("Verdana",Font.BOLD,11));
       
        AddressField = new JTextField(strAddress);
        AddressField.setFont(new Font("Verdana",Font.BOLD,11));
       
        GenderField=new JTextField(strGender);
        GenderField.setFont(new Font("Verdana",Font.BOLD,11));

        CourseField=new JTextField(strCourse);
        CourseField.setFont(new Font("Verdana",Font.BOLD,11));

        SubjectField=new JTextField();
        SubjectField.setFont(new Font("Verdana",Font.BOLD,10));

        UnitsField=new JTextField();
        UnitsField.setFont(new Font("Verdana",Font.BOLD,10));
        
        totalLabUnits = new JTextField();
        totalLabUnits.setFont(new Font("Verdana",Font.BOLD,10));
     
        totalSubjectField=new JTextField();
        totalSubjectField.setFont(new Font("Verdana",Font.BOLD,10));
           

        IDField.setEditable(false);
        LastNameField.setEditable(false);
        FirstNameField.setEditable(false);
        AddressField.setEditable(false);
        GenderField.setEditable(false);
        CourseField.setEditable(false);
        totalLabUnits.setEditable ( false );
        UnitsField.setEditable(false);
        totalLabUnits.setEditable(false);
        SubjectField.setEditable(false);
        totalSubjectField.setEditable(false);
        
       
        MAINPANEL.add(IDField).setBounds(130,30,140,24);
        MAINPANEL.add(FirstNameField).setBounds(130,60,190,24);
        MAINPANEL.add(LastNameField).setBounds(130,90,190,24);
        MAINPANEL.add(AddressField).setBounds(130,120,270,24);
        MAINPANEL.add(GenderField).setBounds(340,30,100,24);
        MAINPANEL.add(CourseField).setBounds(510,30,100,24);
        MAINPANEL.add(SubjectField).setBounds(170,330,70,24);
        MAINPANEL.add(UnitsField).setBounds(560,330,70,24);
        MAINPANEL.add(totalLabUnits).setBounds(560,360,70,24);
        MAINPANEL.add(totalSubjectField).setBounds(170,360,70,21);
    
        questionTable = new MESTable("",new String[] {"ClassNo","SubjectCode","Title","LecUnit","LabUnit","Days","TSlot","Room"});
        MAINPANEL.add(questionTable).setBounds(2,150,735,165);
        questionTable.setColumnSize(new int[]{80,100,250,50,50,50,70,60});
        questionTable.useDefaultRenderer(new int[] {-1});
        questionTable.getTable().addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent event){
            }
        }
      );
        
    }
    Hashtable getQuestion(String qno, String exNo){
        DataCollection cols = parent.getCollection("facultyloading_data");
        Hashtable question = null;
        for(int i = 0; i < cols.size(); i++){
            Hashtable q = (Hashtable)cols.get(i);
            if(q.get("ClassNo").toString().equals(qno.trim()) && q.get("EmployeeNo").equals(exNo)){
                question = q;
                break;
            }
        }
        return  question;
    }
    void fillTable(){
        DataCollection col = parent.getCollection("facultyloading_data");
        questionTable.getTableModel().setRowCount(0);
        questionTable.getTableModel().getRowCount();
        for(int i = 0; i < col.size(); i++){
            Hashtable h = (Hashtable)col.get(i);
            if(h.get("EmployeeNo").toString().equals(strID)){
                Object row[] = {
                                new JLabel(""+h.get("ClassNo")+""),
                                new JLabel(""+h.get("SubjectCode")+""),
                                new JLabel(""+h.get("Title")+""),
                                new JLabel(""+h.get("LecUnit")+""),
                                new JLabel(""+h.get("LabUnit")+""),
                                new JLabel(""+h.get("Days")+""),
                                new JLabel(""+h.get("TSlot")+""),
       						    new JLabel(""+h.get("Room")+""),
       						    };
                questionTable.getTableModel().addRow(row);
     		    //---get total no. of subjects
     		 	//UnitsField.setText ( i + " "  );
                //double x=questionTable.getTableModel().  
  
             }
        }
        
        //-- code patch 07/10/2006 
        //## begin!
        double noOfLecUnits = 0.0;
        double noOfLabUnits = 0.0;
        double totalSubjects= 0.0;
        double rowCount     = 0.0;
        for (int  i = 0; i < questionTable.getTableModel().getRowCount(); i++ ) {
        	
        	//noOfUnits += Integer.parseInt ( questionTable.getTableModel().getValueAt ( i, 4 ).toString() );
        	try {
	 			JLabel tempLec = ( JLabel ) questionTable.getTable().getValueAt ( i, 3 );
	 			JLabel tempLab = ( JLabel ) questionTable.getTable().getValueAt ( i, 4 );
	 			
	 			noOfLecUnits += Double.parseDouble ( tempLec.getText() );
	 			noOfLabUnits += Double.parseDouble ( tempLab.getText() );
	 		    totalSubjects = noOfLecUnits+noOfLabUnits;
	 		    rowCount=questionTable.getTableModel().getRowCount();
       	
	 		} catch ( NumberFormatException npe ) {}
 			
        }
        UnitsField.setText ( noOfLecUnits + "" );
        totalLabUnits.setText ( noOfLabUnits + "" );
        totalSubjectField.setText(totalSubjects + "");
        SubjectField.setText(""+rowCount);
   		
        //## end!
  }

   void remove_fromList()
   {
        if(questionTable.getTable().getSelectedRow() == -1){
            JOptionPane.showMessageDialog(parent, "Select the question to delete.", "Delete question", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int del = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this question?", "Delete",
                                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
                                        );
        if(del ==0 ){    
            Hashtable question = getQuestion(questionTable.getValueAtSelectedRow(0).trim(), strID);
            String qNumber = question.get("ClassNo").toString();
            String sql = "DELETE  FROM facultyloading_data WHERE EmployeeNo='"+strID+"' AND ClassNo='"+qNumber+"'";
            int delete = parent.database.executeUpdate(sql);
            if(delete == 0)
            {
                DataCollection col = parent.getCollection("facultyloading_data");
                col.remove(question);
                fillTable();
            }
        }
    }
    public void actionPerformed(ActionEvent event){
        String act = event.getActionCommand();
        if(act.equals("Close"))
        {
            dispose();
        }
         else if(act.equals("Add Subject"))
         {
         parent.addFrame4(new EnlistFacultySubject(parent, this));
         }  
         else if(act.equals("Drop Subject"))
         {
         remove_fromList();	
         }
        else
        {
        fillTable();	
        }
    }

}//\\\\\\\\\\\\\\\\\\\END OF CLASS DSAChecking.java\\\\\\\\\\\\\//
