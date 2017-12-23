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

import java.awt.print.*;


public class ClassProgramChecking extends MESFrame implements ActionListener{

    MainFrame parent;
    
    showClassPrograms showclassprograms;
    
    MESTable questionTable, answerTable;
    MESTable attendancetable;
    
    String url="jdbc:mysql://192.168.1.37/dbmaster";
    String driver = "org.gjt.mm.mysql.Driver";
    String username="root";
    String password="rose";
    
    Connection connection;
    Statement statement;
    ResultSet rs;
    PrinterJob job;
    PageFormat landscape;
    String rowCount="";
    int countStudent=0;
    double countLimit=0.0;
    
    String strlnOpen="",strlnClose="";
      
    JTextField CourseIDField,IDField,FirstNameField,LastNameField,AddressField,GenderField,CourseField,SubjectField,UnitsField, totalLabUnits,daysField,timeField,roomField,limitField,instructorField;
    JTextField totalSubjectField;
    public String strID,strFName,strLName,strAddress,strGender,strCourse,strLec,strLab,strDays,strTime,strRoom,strLimit,strInstructor,strCourseID;
    public ClassProgramChecking(MainFrame main ,showClassPrograms paramshowPrograms, String IDCode,String FNameCode,String LNameCode,String LecID,String LabID,String daysID,String timeID,String roomID,String limitID,String instructorID,String CourseID){
    	
    	super(600,410,"", new String[] {"Print Report","Register Student","Drop Subject","Close"});
        strID = IDCode;
        strFName=FNameCode;
        strLName =LNameCode;
   		strLec=LecID;
   	    strLab=LabID;
   	    strDays=daysID;
   	    strTime=timeID;
   	    strRoom=roomID;
   	    strLimit=limitID;
   	    strInstructor=instructorID;
   	    strCourseID=CourseID;
   	    parent = main;
        setTitle("Class Attendance Report");
        init();
        fillTable();
        setSize(580,390);
        setVisible(true);
    }
    void init(){
        addButtonActionListener(this);
        
        MAINPANEL.setLayout(null);
        //MAINPANEL.setBackground(new Color(213,229,219));
      
        JLabel t1=new JLabel("Class Attendance Report");
        MAINPANEL.add(t1).setBounds(280,3,200,25);
        t1.setFont(new Font("Verdana",Font.BOLD,14));
        
        JLabel id = new JLabel("Class No",JLabel.LEFT);
        //id.setFont(new Font("Verdana",Font.PLAIN,14));
      
        JLabel firstname= new JLabel("Subject Code", JLabel.LEFT);
        JLabel lastname= new JLabel("Description", JLabel.LEFT);
        JLabel address= new JLabel("Instructor",JLabel.LEFT);
        JLabel gender=new JLabel("Lec. Unit(s)",JLabel.LEFT);
        JLabel course=new JLabel("Lab Unit(s)",JLabel.LEFT);
        JLabel lbltotalsubjects=new JLabel("No. of Students",JLabel.LEFT);
        lbltotalsubjects.setFont(new Font("Verdana",Font.BOLD,14));
        JLabel lbltotunits=new JLabel("Population Limit");
        lbltotunits.setFont(new Font("Verdana",Font.PLAIN,12));
        
       //new------------------------------
        
        JLabel days=new JLabel("Class Days",JLabel.LEFT);
        MAINPANEL.add(days).setBounds(273,60,100,24);
        days.setFont(new Font("Verdana",Font.PLAIN,13));
        
        
        daysField=new JTextField(strDays);
        MAINPANEL.add(daysField).setBounds(360,60,100,24);  
        daysField.setFont(new Font("Verdana",Font.PLAIN,13));
        daysField.setEditable(false);
        
        JLabel time=new JLabel("Time",JLabel.LEFT);
        MAINPANEL.add(time).setBounds(480,60,100,24);
        time.setFont(new Font("Verdana",Font.PLAIN,13));
        
         
        timeField=new JTextField(strTime);
        MAINPANEL.add(timeField).setBounds(560,60,100,24);  
        timeField.setFont(new Font("Verdana",Font.PLAIN,13));
        timeField.setEditable(false);
      
        JLabel room=new JLabel("Room#",JLabel.LEFT);
        MAINPANEL.add(room).setBounds(480,90,100,24);
        room.setFont(new Font("Verdana",Font.PLAIN,13));
        
       
        roomField=new JTextField(strRoom);
        MAINPANEL.add(roomField).setBounds(560,90,100,24);  
        roomField.setFont(new Font("Verdana",Font.PLAIN,13));
        roomField.setEditable(false);
        
        JLabel courseID=new JLabel("Course",JLabel.LEFT);
        MAINPANEL.add(courseID).setBounds(480,120,100,24);
        courseID.setFont(new Font("Verdana",Font.PLAIN,13));
        
        
        CourseIDField=new JTextField(strCourseID);
        MAINPANEL.add(CourseIDField).setBounds(560,120,100,24);  
        CourseIDField.setFont(new Font("Verdana",Font.PLAIN,13));
        CourseIDField.setEditable(false);
        
       //new------------------------------
        
        //## code patch 07/10/2006
        ///begin!
        JLabel labUnits = new JLabel ("Class Status", JLabel.LEFT );
        labUnits.setFont(new Font("Verdana",Font.PLAIN,13));
        
        //end
       
        MAINPANEL.add(id).setBounds(20,30,100,24);
        id.setFont(new Font("Verdana",Font.PLAIN,13));
        
        MAINPANEL.add(firstname).setBounds(20,60,100,24);
        firstname.setFont(new Font("Verdana",Font.PLAIN,13));
        
        MAINPANEL.add(lastname).setBounds(20,90,100,24);
        lastname.setFont(new Font("Verdana",Font.PLAIN,13));
        
        MAINPANEL.add(address).setBounds(20,120,100,24);
        address.setFont(new Font("Verdana",Font.PLAIN,13));
        
        MAINPANEL.add(gender).setBounds(273,30,100,24);
        gender.setFont(new Font("Verdana",Font.PLAIN,13));
        
        MAINPANEL.add(course).setBounds(470,30,100,24);
        course.setFont(new Font("Verdana",Font.PLAIN,13));
        
        MAINPANEL.add(lbltotalsubjects).setBounds(20,330,200,21);
        lbltotalsubjects.setFont(new Font("Verdana",Font.PLAIN,13));
        
        
        MAINPANEL.add(lbltotunits).setBounds(20,360,200,21);
        lbltotunits.setFont(new Font("Verdana",Font.PLAIN,13));
        
        
        MAINPANEL.add(labUnits).setBounds(420,360,200,21);
        labUnits.setFont(new Font("Verdana",Font.PLAIN,13));
        
     
        IDField = new JTextField(strID);
        IDField.setFont(new Font("Verdana",Font.PLAIN,13));
        FirstNameField=new JTextField(strFName);
        FirstNameField.setFont(new Font("Verdana",Font.PLAIN,13));
        
        
        LastNameField = new JTextField(strLName);
        LastNameField.setFont(new Font("Verdana",Font.PLAIN,13));
        
        AddressField = new JTextField(strInstructor);
        AddressField.setFont(new Font("Verdana",Font.PLAIN,13));
        
        GenderField=new JTextField(strLec);
        GenderField.setFont(new Font("Verdana",Font.PLAIN,13));
        
        CourseField=new JTextField(strLab);
        CourseField.setFont(new Font("Verdana",Font.PLAIN,13));
        
        SubjectField=new JTextField();
        SubjectField.setFont(new Font("Verdana",Font.PLAIN,13));
        
      
        UnitsField=new JTextField();
        UnitsField.setFont(new Font("Verdana",Font.PLAIN,13));
        
        
        totalLabUnits = new JTextField();//Subject Status
        totalLabUnits.setFont(new Font("Verdana",Font.PLAIN,13));
        
        totalSubjectField=new JTextField(strLimit);//Population Limit
        totalSubjectField.setFont(new Font("Verdana",Font.PLAIN,13));
        
        
        IDField.setEditable(false);
        LastNameField.setEditable(false);
        FirstNameField.setEditable(false);
        AddressField.setEditable(false);
        GenderField.setEditable(false);
        CourseField.setEditable(false);
        totalLabUnits.setEditable (false);
        UnitsField.setEditable(false);
        totalLabUnits.setEditable(false);
        SubjectField.setEditable(false);
        totalSubjectField.setEditable(false);
        
        MAINPANEL.add(IDField).setBounds(130,30,140,24);
        MAINPANEL.add(FirstNameField).setBounds(130,60,140,24);
        MAINPANEL.add(LastNameField).setBounds(130,90,330,24);
        MAINPANEL.add(AddressField).setBounds(130,120,330,24);
        MAINPANEL.add(GenderField).setBounds(360,30,100,24);
        MAINPANEL.add(CourseField).setBounds(560,30,100,24);
        MAINPANEL.add(SubjectField).setBounds(170,330,70,24);//total Subjects
        MAINPANEL.add(totalLabUnits).setBounds(560,360,70,24);//Status
        MAINPANEL.add(totalSubjectField).setBounds(170,360,70,21);
    
        questionTable = new MESTable("",new String[] {"IDNumber","FirstName","LastName","Address","Gender","YearLevel","Course"});
        MAINPANEL.add(questionTable).setBounds(2,150,735,175);
        
        questionTable.setColumnSize(new int[]{130,170,190,240,60,90,90} );
        questionTable.useDefaultRenderer(new int[] {-1});
       
          questionTable.getTable().addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent event){
            }
        }
      );
    }
    Hashtable getQuestion(String qno, String exNo){
        DataCollection cols = parent.getCollection("classAttendance_data");
        Hashtable question = null;
        for(int i = 0; i < cols.size(); i++){
            Hashtable q = (Hashtable)cols.get(i);
            if(q.get("IDNumber").toString().equals(qno.trim()) && q.get("ClassNo").equals(exNo)){
                question = q;
                break;
            }
        }
        return  question;
    }
    void fillTable(){
        DataCollection col = parent.getCollection("classAttendance_data");
        questionTable.getTableModel().setRowCount(0);
        questionTable.getTableModel().getRowCount();
        for(int i = 0; i < col.size(); i++){
            Hashtable h = (Hashtable)col.get(i);
            if(h.get("ClassNo").toString().equals(strID)){
                Object row[] = {
                                new JLabel(""+h.get("IDNumber")+""),
                                new JLabel(""+h.get("FirstName")+""),
                                new JLabel(""+h.get("LastName")+""),
                                new JLabel(""+h.get("Address")+""),
                                new JLabel(""+h.get("Gender")+""),
                                new JLabel(""+h.get("YearLevel")+""),
                                new JLabel(""+h.get("Course")+""),
                                };
                questionTable.getTableModel().addRow(row);
     		    //---get total no. of subjects
     		 	//UnitsField.setText ( i + " "  );
//                  cntrRow=questionTable.getTableModel(); 
             }
        }
        //-- code patch 07/10/2006 
        //## begin!
        double noOfLecUnits = 0.0;
        double noOfLabUnits = 0.0;
        double totalSubjects= 0.0;
        double noOfUnits    = 0.0;
        int rowCount        = 0;
        for (int  i = 0; i < questionTable.getTableModel().getRowCount(); i++ )
        {
        //  noOfUnits += Integer.parseInt ( questionTable.getTableModel().getValueAt ( i, 2 ).toString() );
        	try
        	{
	 		//	JLabel tempLec = ( JLabel ) questionTable.getTable().getValueAt ( i, 3 );
	 	//		JLabel tempLab = ( JLabel ) questionTable.getTable().getValueAt ( i, 4 );
	 			
	 	//		noOfLecUnits += Double.parseDouble ( tempLec.getText() );
	 	//		noOfLabUnits += Double.parseDouble ( tempLab.getText() );
	 	//	    totalSubjects = noOfLecUnits+noOfLabUnits;
  	            rowCount=questionTable.getTableModel().getRowCount();
 
          SubjectField.setText(""+rowCount);//No. of Students enrolled
   	      
   	      countStudent=Integer.parseInt(SubjectField.getText());
   		  countLimit=Integer.parseInt(totalSubjectField.getText());
   	
   	        }
   	        catch ( NumberFormatException npe )
	 		{
	 		

	 		}	     

   		  if( countStudent<countLimit)
   		  {
   		  	 strlnOpen="Open";
   		  	 totalLabUnits.setText(strlnOpen);
   		  	 totalLabUnits.setFont(new Font("Verdana",Font.BOLD,12));
   		  	 totalLabUnits.setForeground(Color.blue.darker());
   		  	  
   		  }
   		  else if( countStudent==countLimit)
   		  {
   		    strlnClose="Close";
   		    totalLabUnits.setText(strlnClose);
   		    totalLabUnits.setFont(new Font("Verdana",Font.BOLD,12));
   		    totalLabUnits.setForeground(Color.red.darker());
   		  }
   		  else
   		  {
   		    strlnOpen="Overload";
   		    totalLabUnits.setText(strlnOpen);
   		    totalLabUnits.setFont(new Font("Verdana",Font.BOLD,13));
   		    totalLabUnits.setForeground(Color.red.darker());
   		  }

   	  	}
   	   
   	   // UnitsField.setText ( noOfLecUnits + "" );
       // totalLabUnits.setText ( noOfLabUnits + "" );
        //totalSubjectField.setText(totalSubjects + "");
      //   SubjectField.setText(""+rowCount);
   	    
        //## end!
        if(strlnClose=="Close")
       {
       	JTextArea outputArea=new JTextArea();
       	String output="";
       	output+="SUBJECT IS CLOSED\n";
       	output+="PLEASE SEE THE ACADEMIC HEAD\nFOR FURTHER INSTRUCTION\n";
       	output+="THANK YOU!!!\n";
       	output+="-------------------------------------------------------\n";
       	output+="By:ACAD";
       	outputArea.setText(output);
       	outputArea.setBackground(Color.red);
       	outputArea.setForeground(Color.white);
       	outputArea.setFont(new Font("Times New Roman",Font.BOLD,12));
       	outputArea.setEditable(false);
       JOptionPane.showMessageDialog(null,outputArea,"SUBJECT CLOSED",JOptionPane.WARNING_MESSAGE);
       }
       
  }
  public void actionPerformed(ActionEvent event){
        String act = event.getActionCommand();
        if(act.equals("Close"))
        {
            dispose();
        }
         if(act.equals("Print Report"))
         {
         OnPrint();         	
         }
        else if(act.equals("Register Student"))
         {
         parent.addFrame4(new enlist_students_to_subject(parent, this));
         }  
         else if(act.equals("Drop Subject"))
         {
         delete();	
         }
     }
   void delete(){
        if(questionTable.getTable().getSelectedRow() == -1){
            JOptionPane.showMessageDialog(parent, "Select the question to delete.", "Delete question", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int del = JOptionPane.showConfirmDialog(this, "DROP THE SUBJECT NOW?", "DROP SUBJECT", 
                                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
                                        );
        if(del ==0 ){    
            Hashtable question = getQuestion(questionTable.getValueAtSelectedRow(0).trim(), strID);
            String qNumber = question.get("IDNumber").toString();
            String sql = "DELETE  FROM  classAttendance_data WHERE ClassNo = '"+strID+"' AND IDNumber = '"+qNumber+"'";
            int delete = parent.database.executeUpdate(sql);
            if(delete == 0){
                DataCollection col = parent.getCollection("classAttendance_data");
                col.remove(question);
                fillTable();
            }
        }
    }

 void OnPrint()
   {
   job=PrinterJob.getPrinterJob();
   landscape=new PageFormat();
   landscape.setOrientation(PageFormat.LANDSCAPE);
   job.setPrintable(new Printing());
     if(job.printDialog())
     {
       try{
          job.print();
          }
          catch(Exception ex)
          {
          ex.printStackTrace();
          }
     } 
  }
   private class Printing implements Printable
   {
     public Font font=new Font("Times New Roman",Font.PLAIN,12);
     public int print(Graphics g, PageFormat pf, int pageIndex)
     {
       Graphics2D g2d=(Graphics2D)g;
       if(pageIndex==0)
       {
       g2d.translate((int)(pf.getImageableX()),(int)(pf.getImageableY()));
       g2d.setFont(font);
       g2d.setColor(Color.black);
       g2d.drawString("Systems Technology Institute(STI)",150,10); 
       g2d.drawString("STI-Digos",190,25);
       g2d.drawString("Student Class Registration",165,40);
       g2d.drawString("________________________________________________________________________________________________________",10,130);
       g2d.drawString("Student#",20,143);
       g2d.drawString("FirstName",80,143);
       g2d.drawString("LastName",170,143);
       g2d.drawString("Gender",270,143);
       g2d.drawString("Course",340,143);
       g2d.drawString("YearLevel",390,143);
       g2d.drawString("________________________________________________________________________________________________________",10,150);
      
       String strTotalSubjects=UnitsField.getText();
       String strTotalUnits=SubjectField.getText();
    //   String strTotalStudents=SubjectField
       
       String strTotalLec=totalSubjectField.getText();
       String strTotalLab=totalLabUnits.getText();
       g2d.drawString("________________________________________________________________________________________________________",10,520);
       g2d.drawString("Total No. of Students:",20,540);
       g2d.drawString("Population Limit:",20,555);
       g2d.drawString("Subject Status:",230,540);
       g2d.drawString(strTotalUnits,100,540);//population
       g2d.drawString(strTotalLec,100,555);//population limit
       g2d.drawString(strTotalLab,290,540);//
       
       g2d.drawString("________________________________________________________________________________________________________",10,560);
      
       //===================JDBC DATA=====================//
       try
         {
         Class.forName(driver); 
         connection=DriverManager.getConnection(url,username,password);
         statement=connection.createStatement();
         String query="SELECT ClassNo,IDNumber,FirstName,LastName,Gender,Course,YearLevel FROM classattendance_data WHERE ClassNo='"+strID+"'";
         rs=statement.executeQuery(query);
         double top=pf.getImageableY();
         double bottom=pf.getImageableHeight();
         for(int j=(int)top+95;j<(int)bottom;j+=11)
         {
          rs.next();
     
          String a=rs.getString(1);
          String b=rs.getString(2);
          String c=rs.getString(3);
          String d=rs.getString(4);
          String e=rs.getString(5);
          String f=rs.getString(6);
          String gg=rs.getString(7);
          
          g2d.drawString("Class No:",20,80);
		  g2d.drawString("SubjectCode:",20,95);
		  g2d.drawString("Title:",20,110);
		  g2d.drawString("Instructor:",20,125);
    	  g2d.drawString("Room:",240,80);
    	  g2d.drawString("Days:",240,95);
    	  g2d.drawString("Lec Unit(s):",140,80);
     	  g2d.drawString("Lab Unit(s):",140,95);
    	  g2d.drawString("Time:",300,80);
    	  g2d.drawString("Course:",300,95);
		 // g2d.drawString("Approved by:_________________",20,380);
         // g2d.drawString("Recieved by:_________________",20,410);
        //  g2d.drawString("Assessed by:_________________",320,380);
        ///  g2d.drawString("Date       :_________________",320,410);

          
          String strID=IDField.getText();
          String strFName=FirstNameField.getText();
          String strLName=LastNameField.getText();
          String strAddress=AddressField.getText();
          String strGender=GenderField.getText();
          String strCourse=CourseField.getText();
          String strRoom=roomField.getText();
          String strDays=daysField.getText();
          String strCourseID=CourseIDField.getText();
        
          //=========================Part II======================//
          double side=pf.getImageableX();
        //  g2d.drawString(a,63,80);
          g2d.drawString(strID,70,80);
          g2d.drawString(strFName,70,95);
          g2d.drawString(strLName,70,110);
          g2d.drawString(strAddress,70,125);
          g2d.drawString(strGender,185,80);//lect unit
          g2d.drawString(strCourse,185,95);//lab unit
          g2d.drawString(strRoom,265,80);//room
          g2d.drawString(strDays,265,95);//room
          g2d.drawString(strTime,323,80);//room
          g2d.drawString(strCourseID,330,95);//room
         
          g2d.drawString(b,(int)side-52,j);
          g2d.drawString(c,(int)side+11,j);
          g2d.drawString(d,(int)side+95,j);
          g2d.drawString(e,(int)side+200,j);
          g2d.drawString(f,(int)side+267,j);
          g2d.drawString(gg,(int)side+320,j);
         
          } 
       }
          catch(ClassNotFoundException ex)
          {
           System.err.println(ex);
         }
          catch(SQLException ql)
          {
           System.err.println(ql);
          }
       return Printable.PAGE_EXISTS;
       }     
       return Printable.NO_SUCH_PAGE;
       }
  }
  
    
}//\\\\\\\\\\\\\\\\\\\END OF CLASS DSAChecking.java\\\\\\\\\\\\\//
