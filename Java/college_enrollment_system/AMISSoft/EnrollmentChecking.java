
package mes.gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.print.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.Date;
import javax.swing.Timer;
import java.util.*;
import java.text.*;
import java.text.SimpleDateFormat;
import javax.swing.text.MaskFormatter;

import mes.dbase.*;
import mes.gui.*;
import mes.util.*;

import java.awt.print.*;



public class EnrollmentChecking extends MESFrame implements ActionListener
{
    
    Timer timer;
    Date date;
    SimpleDateFormat dateFormat=new SimpleDateFormat("MM-dd-yy");
    JTextField timeText;
    JTextField dateToday;
    
    MainFrame parent;
    showStudentList showstudentlist;
    MESTable questionTable, answerTable;
    MESTable table;
    PrinterJob job;
    PageFormat landscape;
    
    String url="jdbc:mysql://192.168.1.37/dbmaster";
    String driver = "org.gjt.mm.mysql.Driver";
    String username="root";
    String password="rose";

    Connection connection;
    Statement statement;
    ResultSet rs;
//    PrinterJob job;
//    PageFormat landscape;
 
    String qNumber;
    String rowCount="";
    int rowcount=0;
    JTextField EnrollmentField;
   
    JTextField CivilStatus,SYField,SemesterField,DateField,IDField,FirstNameField,LastNameField,AddressField,GenderField,CourseField,YearLevelField,SubjectField,UnitsField, totalLabUnits;
    JTextField totalSubjectField;
    public String strStatus,strSY,strSem,strID,strFName,strLName,strAddress,strGender,strCourse,strYearLevel,strERDATE;
    public EnrollmentChecking(MainFrame main ,showStudentList paramstudentlist, String IDCode,String FNameCode,String LNameCode,String AddressCode,String GenderCode,String CourseCode,String YearLevelCode,String SemCode,String SYCode,String ErDateCode,String StatusCode)
    {
        super(600,410,"", new String[] {"Print Report","Add Subject","Drop Subject","Close"});
        strID = IDCode;
        strFName=FNameCode;
        strLName =LNameCode;
        strAddress=AddressCode;
        strGender=GenderCode;
        strCourse=CourseCode;
        strYearLevel=YearLevelCode;
        strSem=SemCode;
        strSY=SYCode;    
        strERDATE=ErDateCode;
        strStatus=StatusCode;
        parent = main;
        setTitle("Student Enrollment File");
        init();
        fillTable();
        setSize(580,390);
        setVisible(true);
    }
    void init(){
        addButtonActionListener(this);
        
        Calendar calendar=Calendar.getInstance();
        Calendar calen2=Calendar.getInstance();
        date=calendar.getTime();
        
        timeText=new JTextField();
        //MAINPANEL.add(timeText=new JTextField(dateFormat.format(date)+""));
        timeText=new JTextField(""+dateFormat.format(date));
        timeText.setBounds(220,36,100,20);
      //  timeText.setForeground(Color.white);
        timeText.setFont(new Font("Verdana",Font.PLAIN,13));
      //  timeText.setBackground(new Color(0,128,192));
      //  timeText.setEditable(false);
      //  timeText.setVisible(false);
        timer=new Timer(990,new clockHandler());
       // timer.start();
      
        String timeViewer=timeText.getText();
        dateToday=new JTextField(""+timeViewer);
      
        MAINPANEL.add(dateToday).setBounds(650,90,70,24);
        dateToday.setText(timeViewer);
        dateToday.setFont(new Font("Verdana",Font.PLAIN,13));
      
        MAINPANEL.setLayout(null);
        //MAINPANEL.setBackground(new Color(213,229,219));

        JLabel t1=new JLabel("Student Enrollment File");
        MAINPANEL.add(t1).setBounds(290,5,200,25);
        t1.setFont(new Font("Verdana",Font.BOLD,14));
        
        JLabel id = new JLabel("IDNumber",JLabel.LEFT);
        MAINPANEL.add(id).setBounds(20,30,100,24);
        id.setFont(new Font("Verdana",Font.PLAIN,13));
        
        JLabel firstname= new JLabel("FirstName", JLabel.LEFT);
        MAINPANEL.add(firstname).setBounds(20,60,100,24);
        firstname.setFont(new Font("Verdana",Font.PLAIN,13));
       
        JLabel lastname= new JLabel("LastName", JLabel.LEFT);
        MAINPANEL.add(lastname).setBounds(20,90,100,24);
        lastname.setFont(new Font("Verdana",Font.PLAIN,13));
       
        JLabel address= new JLabel("Address",JLabel.LEFT);
        MAINPANEL.add(address).setBounds(20,120,100,24);
        address.setFont(new Font("Verdana",Font.PLAIN,13));
       
        JLabel gender=new JLabel("Gender",JLabel.LEFT);
        MAINPANEL.add(gender).setBounds(280,30,100,24);
        gender.setFont(new Font("Verdana",Font.PLAIN,13));
       
        JLabel course=new JLabel("Course",JLabel.LEFT);
        MAINPANEL.add(course).setBounds(450,30,100,24);
        course.setFont(new Font("Verdana",Font.PLAIN,13));
       
        JLabel yearlevel=new JLabel("Year",JLabel.LEFT);
        MAINPANEL.add(yearlevel).setBounds(450,60,100,24);
        yearlevel.setFont(new Font("Verdana",Font.PLAIN,13));
       
        JLabel dateenrolled=new JLabel("Date",JLabel.LEFT);
        dateenrolled.setFont(new Font("Verdana",Font.PLAIN,14));

        JLabel semester=new JLabel("Sem.",JLabel.LEFT);
        MAINPANEL.add(semester).setBounds(450,120,100,24);
        semester.setFont(new Font("Verdana",Font.PLAIN,13));
       
        JLabel sy=new JLabel("S.Y.",JLabel.LEFT);
        MAINPANEL.add(sy).setBounds(580,30,100,24);
        sy.setFont(new Font("Verdana",Font.PLAIN,13));
   
        JLabel enrollmentStatus=new JLabel("Status",JLabel.LEFT);
        MAINPANEL.add(enrollmentStatus).setBounds(580,60,100,24);
        enrollmentStatus.setFont(new Font("Verdana",Font.PLAIN,13));
   
        
       
        JLabel class_section=new JLabel("Section",JLabel.LEFT);
        MAINPANEL.add(class_section).setBounds(450,90,100,24);
        class_section.setFont(new Font("Verdana",Font.PLAIN,13));
       
       // enrollmentdate.setForeground(Color.red.darker());
        
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
       
        
      
        MAINPANEL.add(dateenrolled).setBounds(580,90,100,24);
        dateenrolled.setFont(new Font("Verdana",Font.PLAIN,13));

      

       
        EnrollmentField=new JTextField(strERDATE);
        MAINPANEL.add(EnrollmentField).setBounds(510,90,60,24);
   
        MAINPANEL.add(lbltotalsubjects).setBounds(20,330,200,21);
       // lbltotalsubjects.setFont(new Font("Verdana",Font.BOLD,10));
      
        MAINPANEL.add(lbltotunits).setBounds(20,360,200,21);
      //  lbltotunits.setFont(new Font("Verdana",Font.BOLD,10));
      
       
        MAINPANEL.add(lbltotalunits).setBounds(420,330,200,21);
       // lbltotalunits.setFont(new Font("Verdana",Font.BOLD,10));
        
        MAINPANEL.add(labUnits).setBounds(420,360,200,21);
      //  labUnits.setFont(new Font("Verdana",Font.BOLD,10));
        
     
      
      
      
        IDField = new JTextField(strID);
      //  IDField.setFont(new Font("Verdana",Font.BOLD,11));
        FirstNameField=new JTextField(strFName);
       // FirstNameField.setFont(new Font("Verdana",Font.BOLD,11));
        LastNameField = new JTextField(strLName);
       // LastNameField.setFont(new Font("Verdana",Font.BOLD,11));
       
        AddressField = new JTextField(strAddress);
       // AddressField.setFont(new Font("Verdana",Font.BOLD,11));
       
        GenderField=new JTextField(strGender);
       // GenderField.setFont(new Font("Verdana",Font.BOLD,11));
        
        DateField=new JTextField(strERDATE);
       // DateField.setFont(new Font("Verdana",Font.BOLD,11));
        
        SemesterField=new JTextField(strSem);
       // SemesterField.setFont(new Font("Verdana",Font.BOLD,11));
        
        
        CourseField=new JTextField(strCourse);
       // CourseField.setFont(new Font("Verdana",Font.BOLD,11));

        SYField=new JTextField(strSY);
       // SYField.setFont(new Font("Verdana",Font.BOLD,11));
        
        CivilStatus=new JTextField(strStatus);
        MAINPANEL.add(CivilStatus).setBounds(650,60,70,24);
        
        YearLevelField=new JTextField(strYearLevel);
       /// YearLevelField.setFont(new Font("Verdana",Font.BOLD,11));

      
        SubjectField=new JTextField();
        //SubjectField.setFont(new Font("Verdana",Font.BOLD,10));

        UnitsField=new JTextField();
       // UnitsField.setFont(new Font("Verdana",Font.BOLD,10));
        
        totalLabUnits = new JTextField();
       // totalLabUnits.setFont(new Font("Verdana",Font.BOLD,10));
     
        totalSubjectField=new JTextField();
       // totalSubjectField.setFont(new Font("Verdana",Font.BOLD,10));
           

        IDField.setEditable(false);
        LastNameField.setEditable(false);
        FirstNameField.setEditable(false);
        AddressField.setEditable(false);
        GenderField.setEditable(false);
        CourseField.setEditable(false);
        YearLevelField.setEditable(false);
        totalLabUnits.setEditable ( false );
        UnitsField.setEditable(false);
        totalLabUnits.setEditable(false);
        SubjectField.setEditable(false);
        totalSubjectField.setEditable(false);
        SemesterField.setEditable(false);
        EnrollmentField.setEditable(false);
        SYField.setEditable(false);
        CivilStatus.setEditable(false);
        dateToday.setEditable(false);
       
        MAINPANEL.add(IDField).setBounds(130,30,140,24);
        MAINPANEL.add(FirstNameField).setBounds(130,60,310,24);
        MAINPANEL.add(LastNameField).setBounds(130,90,310,24);
        MAINPANEL.add(AddressField).setBounds(130,120,310,24);
        MAINPANEL.add(GenderField).setBounds(340,30,100,24);
        MAINPANEL.add(CourseField).setBounds(510,30,60,24);
        
        MAINPANEL.add(SYField).setBounds(650,30,70,24);
        
        MAINPANEL.add(YearLevelField).setBounds(510,60,60,24);

        MAINPANEL.add(SemesterField).setBounds(510,120,60,24);

        MAINPANEL.add(SubjectField).setBounds(170,330,70,24);
        MAINPANEL.add(UnitsField).setBounds(560,330,70,24);
        MAINPANEL.add(totalLabUnits).setBounds(560,360,70,24);
        MAINPANEL.add(totalSubjectField).setBounds(170,360,70,21);
    
        questionTable = new MESTable("",new String[] {"ClassNo","SubjectCode","Title","LecUnit","LabUnit","Days","TSlot","Room"});
        MAINPANEL.add(questionTable).setBounds(2,150,735,170);
        questionTable.setColumnSize(new int[]{80,100,250,50,50,50,70,60});
        questionTable.useDefaultRenderer(new int[] {-1});
       
          questionTable.getTable().addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent event){
            }
        }
      );
        
    }
    Hashtable getQuestion(String qno, String exNo){
        DataCollection cols = parent.getCollection("enrollment_data");
        Hashtable question = null;
        for(int i = 0; i < cols.size(); i++){
            Hashtable q = (Hashtable)cols.get(i);
            if(q.get("ClassNo").toString().equals(qno.trim()) && q.get("IDNumber").equals(exNo)){
                question = q;
                break;
            }
        }
        return  question;
    }
    void fillTable(){
        DataCollection col = parent.getCollection("enrollment_data");
        questionTable.getTableModel().setRowCount(0);
        questionTable.getTableModel().getRowCount();
        for(int i = 0; i < col.size(); i++){
            Hashtable h = (Hashtable)col.get(i);
            if(h.get("IDNumber").toString().equals(strID)){
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
            JOptionPane.showMessageDialog(parent, "PLEASE SELECT A SUBJECT TO DROP", "CONFIRM INTENTION", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int del = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this question?", "Delete",
                                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
                                        );
        if(del ==0 ){    
            Hashtable question = getQuestion(questionTable.getValueAtSelectedRow(0).trim(), strID);
            qNumber = question.get("ClassNo").toString();
            String sql = "DELETE  FROM enrollment_data WHERE IDNumber='"+strID+"' AND ClassNo='"+qNumber+"'";
            int delete = parent.database.executeUpdate(sql);
            if(delete == 0)
            {
                DataCollection col = parent.getCollection("enrollment_data");
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
//start of printing
  //\\\\\\\\\\\\\\\\\\\\\\\\PRINTING AND PAGESETUP\\\\\\\\\\\\\\\\//
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
       g2d.drawString("Student Enrollment Report",165,40);
       g2d.drawString("________________________________________________________________________________________________________",10,130);
       g2d.drawString("ClassNo",20,143);
       g2d.drawString("SubjectCode",70,143);
       g2d.drawString("Title",120,143);
       g2d.drawString("Lec",285,143);
       g2d.drawString("Lab",310,143);
       g2d.drawString("Days",340,143);
       g2d.drawString("Time",370,143);
       g2d.drawString("Room",420,143);
       g2d.drawString("________________________________________________________________________________________________________",10,145);
 
      
       String strTotalSubjects=UnitsField.getText();
       String strTotalUnits=SubjectField.getText();
       
       String strTotalLec=totalSubjectField.getText();
       String strTotalLab=totalLabUnits.getText();
       
       g2d.drawString("________________________________________________________________________________________________________",10,320);
       g2d.drawString("Total No. of Subjects:",20,335);
       g2d.drawString("Total No. of Units:",20,350);
       
       g2d.drawString("Lecture   :",270,335);
       g2d.drawString("Laboratory:",270,350);
       
       g2d.drawString(strTotalSubjects,100,350);
       g2d.drawString(strTotalUnits,100,335);
       
       g2d.drawString(strTotalLec,315,335);
       g2d.drawString(strTotalLab,315,350);
       
       g2d.drawString("________________________________________________________________________________________________________",10,355);
      
       //===================JDBC DATA=====================//
       try
         {
         Class.forName(driver); 
         connection=DriverManager.getConnection(url,username,password);
         statement=connection.createStatement();
         String query="SELECT IDNumber,ClassNo,SubjectCode,Title,LecUnit,LabUnit,Days,TSlot,Room FROM enrollment_data WHERE IDNumber='"+strID+"'";
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
          String h=rs.getString(8);
          String i=rs.getString(9);
         
          
          g2d.drawString("Student No:",20,80);
		  g2d.drawString("FirstName:",20,95);
		  g2d.drawString("LastName:",20,110);
		  g2d.drawString("Address:",20,125);
    	  g2d.drawString("Gender:",150,80);
    	  g2d.drawString("Course:",230,80);
    	  g2d.drawString("Enrollment Date:",310,80);
    	  g2d.drawString("Year Level:",230,100);
          g2d.drawString("Semester:",150,100);
          g2d.drawString("School Year:",310,100);
         
          
		  g2d.drawString("Approved by:_________________",20,380);
          g2d.drawString("Recieved by:_________________",20,410);
          
          g2d.drawString("Assessed by:_________________",320,380);
          g2d.drawString("Date       :_________________",320,410);
          
          
          String strID=IDField.getText();
          String strFName=FirstNameField.getText();
          String strLName=LastNameField.getText();
          String strAddres=AddressField.getText();
          String strGender= GenderField.getText();
          String strCourse= CourseField.getText();
          String strYearLevel= YearLevelField.getText();
          String strSem= SemesterField.getText();
          String dateenrolled=EnrollmentField.getText();
          String schoolyear=SYField.getText();
          
         //=========================Part II======================//
          double side=pf.getImageableX();
          g2d.drawString(a,63,80);
          g2d.drawString(strFName,63,95);
          g2d.drawString(strLName,63,110);
          g2d.drawString(strAddress,63,125);
          g2d.drawString(strGender,180,80);
          g2d.drawString(strCourse,260,80);
          g2d.drawString(strYearLevel,275,100);
          g2d.drawString(strSem,190,100);
          g2d.drawString(dateenrolled,380,80);
          g2d.drawString(schoolyear,380,100);
        
          g2d.drawString(b,(int)side-52,j);
          g2d.drawString(c,(int)side+1,j);
          g2d.drawString(d,(int)side+50,j);
          g2d.drawString(e,(int)side+212,j);
          g2d.drawString(f,(int)side+240,j);
          g2d.drawString(gg,(int)side+267,j);
          g2d.drawString(h,(int)side+300,j);
          g2d.drawString(i,(int)side+350,j);

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
 public class clockHandler implements ActionListener{
          public void actionPerformed(ActionEvent e)
          {
          Calendar clockCal=Calendar.getInstance();
          date=clockCal.getTime();
          timeText.setText(dateFormat.format(date)+"");	
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
         parent.addFrame4(new EnlistSubject(parent, this));
         }  
         else if(act.equals("Drop Subject"))
         {
         remove_fromList();	
         }
         else if(act.equals("Print Report"))
         {
         OnPrint();         	
         }
        else
        {
        fillTable();	
        }
    }

}//\\\\\\\\\\\\\\\\\\\END OF CLASS EnrollmentChecking.java\\\\\\\\\\\\\//
