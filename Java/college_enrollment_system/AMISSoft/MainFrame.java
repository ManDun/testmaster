//Project Name:Student Monitoring System(SMS)
//Database:MS ACCESS
//Language:Java(SDK1.5.0)
//Owner:STI-DIGOS
//PLEASE READ THE INSTRUCTION CAREFULLY BEFORE STARTING
//Developer:Bernardo D. Tongcos Jr.
//Module Name:MainFrame.java
   
   package mes.gui;
   import javax.swing.*;
   import javax.swing.tree.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.sql.*;
   import javax.swing.JOptionPane;
   import javax.swing.border.*;
   import javax.swing.ImageIcon.*;
   import java.util.*;
   import java.io.*;
   
   import mes.dbase.*;
   import mes.util.*;
   
   import mes.clock.desktopClock;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

  import java.net.URL;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.GridLayout; 
   
     public class MainFrame extends JFrame implements ActionListener
     {
     	//---Classes and Data Structures
     	JDesktopPane desktop;
     	JMenuBar mbar;
     	
     	JTree AMISTree;
     	DefaultMutableTreeNode category,book=null;
     	
        Action action;
     
     	private JEditorPane htmlPane;
        private JTree tree;
        private URL helpURL;
        private static boolean DEBUG = false;

     	
    	private static boolean useSystemLookAndFeel = false;

     	String filename = File.separator+"tmp";
        JFileChooser fc;
        JFrame frame;
    
     	
     	JMenu studs_Archieves,query_menu,view_menu,file_menu,registration_menu,setting_menu,reports_menu,help_menu;
        JMenu showStudentMenu;
     	JMenuItem studentMasterList_Archieves,room_master_list_item,subject_item,schedule_item,employee_item,classlist_item,classmaster_list_item;
     	JMenu faculty_master_menu;
     	JMenuItem faculty_master_item;
        JMenuItem about_item,tutorial_item,documentation_item;
        JMenuItem administrative_item;
     	
     	JMenuItem signout_item,exit_item;
        JMenuItem studentmaster_item;
        JMenuItem facultyloading_item;

        JMenuItem yearl_levelItem,courseItem;
        JMenuItem Class_Schedules_Archieves,student_masterList,faculty_masterList,course_masterList,studentMasterRecord;

        JMenuItem query_builderItem;
        JMenuItem course_item,curriculum_item,room_item,grade_item,grade_report_item;
        JMenuItem short_term_courses_item;
      
        JMenuItem by_YearLevel,by_Sem,by_Sy,by_Birthdate,by_Faculty_Load,by_EnrollmentStatus;
       
        
        String StrBusinessTitle;
   	    JLabel BusinessTitleLabel = new JLabel();
   	  
   	    JButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11;
   	    desktopClock deskClock;
   	  
   	    IntroFrame introframe;
   	    StudentMaster_onLoad studMaster;
   	    Add_UserAccounts usr_account;
   	    
   	    JLabel ImageLabel;
    	String imagename = "steam.gif";
    	ImageIcon ii = new ImageIcon(imagename);
    	ResultSet resultSet_;
        public DatabaseManager database;
   		Hashtable DATABASE;
   	 	public MainFrame()
     	{
        setTitle("Admission and Management Information System(AMIS)-beta");
     	init();
        }
     	    void getCollection()
         	{
        	DATABASE = new Hashtable();
       		//database = new DatabaseManager(this,"mes/dbase/DBMaster.mdb");
         	database = new DatabaseManager(this,"AppServ/mysql/data/dbmaster");//,"c:/AppServ/mysql/data/dbmaster");
         try
           {
            DATABASE.put("studentmaster",database.executeQuery("SELECT * FROM studentmaster ORDER BY IDNumber ASC"));
            DATABASE.put("temp",database.executeQuery("SELECT * FROM temp ORDER BY IDNumber ASC"));
            DATABASE.put("FacultyMaster",database.executeQuery("SELECT * FROM FacultyMaster ORDER BY EmployeeNo ASC"));
            DATABASE.put("enrollment_data",database.executeQuery("SELECT * FROM enrollment_data ORDER BY IDNumber ASC"));
            DATABASE.put("facultyloading_data",database.executeQuery("SELECT * FROM facultyloading_data ORDER BY EmployeeNo ASC"));
            DATABASE.put("class_schedule",database.executeQuery("SELECT * FROM class_schedule ORDER BY ClassNo ASC"));
            DATABASE.put("Courses",database.executeQuery("SELECT * FROM Courses ORDER BY CourseCode ASC"));
            DATABASE.put("classAttendance_data",database.executeQuery("SELECT * FROM classAttendance_data ORDER BY ClassNo ASC"));
            
            
            }
            catch(Exception ex)    
            {
            System.out.println("DATABASE QUERY ERROR "+ex.getMessage());
           // System.err.println(ex);
            }
          }
        public DataCollection getCollection(String key)
        {
        DataCollection col = (DataCollection)DATABASE.get(key);
        if(col == null)
        {
            return new DataCollection();
        }
        return col;
    }
    //code patch 07/10/2006
    	public ResultSet getResultSet() {
    		ResultSet rs = null;
    		//try {
    			rs = database.getStockMasterResultSet2();
   		//} catch ( SQLException sqle ) {
    			//System.out.println ( sqle.getMessage() );
    		//}
    			
    		return rs;
    	}
 
    
 	public ResultSet getResultSet2() {
    		ResultSet rs = null;
    		//try {
    			rs = database.getStockMasterResultSet();
   		//} catch ( SQLException sqle ) {
    			//System.out.println ( sqle.getMessage() );
    		//}
    			
    		return rs;
    	}
 
    public ResultSet getResultSet3() {
    		ResultSet rs = null;
    		//try {
    			rs = database.getStockMasterResultSet3();
   		//} catch ( SQLException sqle ) {
    			//System.out.println ( sqle.getMessage() );
    		//}
    			
    		return rs;
    	}
 
 
     	void init()
     	{
     	Container c=this.getContentPane();
     	this.setLayout(null);
     
	  //  StrBusinessTitle = "AMIS Software Copyright (C) 2006";
		BusinessTitleLabel.setText(StrBusinessTitle);
		BusinessTitleLabel.setFont(new Font("Verdana",Font.PLAIN,12));
		BusinessTitleLabel.setHorizontalAlignment(JLabel.CENTER);

     	 
 	
 		Icon img1=new ImageIcon("ICON1.JPG");
        btn1=new JButton();
        btn1.setIcon(img1);
        btn1.addActionListener(this);
        btn1.setToolTipText("Student Enrollment File"); 
         
        Icon img2=new ImageIcon("ICON2.JPG");
        btn2=new JButton();
        btn2.setIcon(img2);
         
        Icon img3=new ImageIcon("ICON3.JPG");
        btn3=new JButton();
        btn3.setIcon(img3);
       
        Icon img4=new ImageIcon("ICON4.JPG");
        btn4=new JButton();
        btn4.setIcon(img4);
        	 
        Icon img5=new ImageIcon("ICON5.JPG");
        btn5=new JButton();
        btn5.setIcon(img5);
        
        Icon img6=new ImageIcon("ICON6.JPG");
        JButton btn6=new JButton();
        btn6.setIcon(img6);
        
        Icon img7=new ImageIcon("ICON7.JPG");
        JButton btn7=new JButton();
        btn7.setIcon(img7);
        
        Icon img8=new ImageIcon("ICON8.JPG");
        JButton btn8=new JButton();
        btn8.setIcon(img8);
        
        Icon img9=new ImageIcon("ICON9.JPG");
        JButton btn9=new JButton();
        btn9.setIcon(img9);
        
        Icon img10=new ImageIcon("ICON10.JPG");
        JButton btn10=new JButton();
        btn10.setIcon(img10);
       
        Icon img11=new ImageIcon("ICON11.JPG");
        btn11=new JButton();
        btn11.setIcon(img11);
        btn11.addActionListener(this);
       
        JToolBar toolbar=new JToolBar("Tools");
        c.add(toolbar);
        toolbar.setBounds(10,6,800,40);
        	 
        toolbar.add(btn1);
        toolbar.add(btn2);
        toolbar.add(btn3);
        toolbar.add(btn4);
        toolbar.add(btn5);
        toolbar.add(btn6);
        toolbar.add(btn7);
        toolbar.add(btn8);
        toolbar.add(btn9);
        toolbar.add(btn10);
        toolbar.add(btn11);
      
        toolbar.add(BusinessTitleLabel);
     	
     	//---Add Timer/Clock
     	
     	//Icon clockIcon=new ImageIcon("clock.jpg");
     //	JLabel clockLabel=new JLabel("",clockIcon,SwingConstants.CENTER);
    // 	c.add(clockLabel).setBounds(20,493,100,30);
		
     	deskClock=new desktopClock();
		c.add(deskClock);
		deskClock.setBounds(3,493,780,500);
		
		
		deskClock.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
	 //	deskClock.setBackground(new Color(0,128,192));
     	//deskClock.setForeground(Color.white);
     	//deskClock.setForeground(Color.black);
         	 
        //---JDesktopPane
      	desktop=new JDesktopPane();
        desktop.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        add(desktop);
        desktop.setBounds(0,50,800,440);
     	
     
     	JLabel t1=new JLabel("Admission and Management Information System(AMIS)");
     	t1.setFont(new Font("Times New Roman",Font.BOLD,17));
     	desktop.add(t1).setBounds(10,8,540,24);
        t1.setForeground(Color.white);
        
     	JLabel t2=new JLabel("Copyright (C) 2006 STI Digos,Alrights Reserved");
     	t2.setFont(new Font("Times New Roman",Font.PLAIN,12));
     	t2.setForeground(Color.white);
        desktop.add(t2).setBounds(10,25,400,24);
     	
     	JLabel t3=new JLabel("Designed & Developed by:Mr. Bernardo D. Tongcos Jr.");
     	t3.setFont(new Font("Times New Roman",Font.PLAIN,12));
     	t3.setForeground(Color.white);
        desktop.add(t3).setBounds(10,41,500,24);
     	
     	//ImageIcon imgIcon=new ImageIcon("stilogo_amis.jpg");
     	
     	//JLabel imageLabel=new JLabel("",imgIcon,SwingConstants.CENTER);
     	//desktop.add(imageLabel).setBounds(10,5,120,80);
     	//---JMenuBar
     	mbar=new JMenuBar();
     	setJMenuBar(mbar);
        setIconImage(new ImageIcon("chatters.gif").getImage() );
         	  	
     	//---JMenu
     	file_menu=new JMenu("File");
     	file_menu.setFont(new Font("Times New Roman",Font.PLAIN,13));
       	file_menu.setMnemonic('F');
       	
     	registration_menu=new JMenu("Registration");
     	registration_menu.setFont(new Font("Times New Roman",Font.PLAIN,13));
     	registration_menu.setMnemonic('n');
     
     	setting_menu=new JMenu("System");
     	setting_menu.setFont(new Font("Times New Roman",Font.PLAIN,13));
        setting_menu.setMnemonic('s');
     	
     	view_menu=new JMenu("List");
     	view_menu.setFont(new Font("Times New Roman",Font.PLAIN,13));
        view_menu.setMnemonic('i');
    
     	     	
     	help_menu=new JMenu("Help");
     	help_menu.setFont(new Font("Times New Roman",Font.PLAIN,13));
        help_menu.setMnemonic('H');
     	
     	
     	reports_menu=new JMenu("Query");
     	reports_menu.setFont(new Font("Times New Roman",Font.PLAIN,13));
     	reports_menu.setMnemonic('e');
     	
     	//---JMenuItem
     	about_item=new JMenuItem("About Software?");
     	about_item.setFont(new Font("Times New Roman",Font.PLAIN,13));
     	about_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
		about_item.setIcon(new ImageIcon("EXIT.PNG"));
		about_item.addActionListener(this);
				
		tutorial_item=new JMenuItem("Tutorials");
     	tutorial_item.setFont(new Font("Times New Roman",Font.PLAIN,13));
     	tutorial_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,ActionEvent.CTRL_MASK));
		tutorial_item.setIcon(new ImageIcon("EXPENSE.PNG"));
		tutorial_item.addActionListener(this);
		
    	documentation_item=new JMenuItem("Documentation");
     	documentation_item.setFont(new Font("Times New Roman",Font.PLAIN,13));
     	documentation_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
		documentation_item.setIcon(new ImageIcon("SAVE.PNG"));
		documentation_item.addActionListener(this);
	
     	
     	signout_item=new JMenuItem("Sign Out");
     	signout_item.setFont(new Font("Times New Roman",Font.PLAIN,13));
     	signout_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,ActionEvent.CTRL_MASK));
		signout_item.setIcon(new ImageIcon("lockapplication.png"));
		signout_item.addActionListener(this);
		
		exit_item=new JMenuItem("Exit");
     	exit_item.setFont(new Font("Times New Roman",Font.PLAIN,13));
     	exit_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		exit_item.setIcon(new ImageIcon("EXIT.PNG"));
		exit_item.addActionListener(this);
	
		studentmaster_item=new JMenuItem("Current Semester & School Year");//enrollment master file
		studentmaster_item.setFont(new Font("Times New Roman",Font.PLAIN,13));
		studentmaster_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        studentmaster_item.setIcon(new ImageIcon("RESET.PNG"));
		studentmaster_item.addActionListener(this);
	

		studentMasterList_Archieves=new JMenuItem("Student Archieves");//enrollment master file
		studentMasterList_Archieves.setFont(new Font("Times New Roman",Font.PLAIN,13));
		studentMasterList_Archieves.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
        studentMasterList_Archieves.setIcon(new ImageIcon("SUPPLIER.PNG"));
		studentMasterList_Archieves.addActionListener(this);

	
		
		schedule_item=new JMenuItem("Class Schedules");
		schedule_item.setFont(new Font("Times New Roman",Font.PLAIN,13));
		schedule_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,ActionEvent.CTRL_MASK));
        schedule_item.setIcon(new ImageIcon("RESET.png"));
		schedule_item.addActionListener(this);
			
        course_item=new JMenuItem("Course/Program");
        course_item.setFont(new Font("Times New Roman",Font.PLAIN,13));
		course_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        course_item.setIcon(new ImageIcon("customer.png"));
		course_item.addActionListener(this);
		
	    subject_item=new JMenuItem("Subjects");
        subject_item.setFont(new Font("Times New Roman",Font.PLAIN,13));
		subject_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        subject_item.setIcon(new ImageIcon("delete.png"));
		subject_item.addActionListener(this);
		
		room_item=new JMenuItem("Room Master");
		room_item.setFont(new Font("Times New Roman",Font.PLAIN,13));
		room_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
        room_item.setIcon(new ImageIcon("purchaseorder.png"));
		room_item.addActionListener(this);
		
		room_master_list_item=new JMenuItem("Daily Classes");
		room_master_list_item.setFont(new Font("Times New Roman",Font.PLAIN,13));
		room_master_list_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
        room_master_list_item.setIcon(new ImageIcon("purchaseorder.png"));
		room_master_list_item.addActionListener(this);
	
		studentMasterRecord=new JMenuItem("Student Master Record");
		studentMasterRecord.setFont(new Font("Times New Roman",Font.PLAIN,13));
		studentMasterRecord.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
        studentMasterRecord.setIcon(new ImageIcon("SalesRep.png"));
		studentMasterRecord.addActionListener(this);
	
		curriculum_item=new JMenuItem("Curriculum");
		curriculum_item.setFont(new Font("Times New Roman",Font.PLAIN,13));
		curriculum_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
        curriculum_item.setIcon(new ImageIcon("RECIEVE.png"));
		curriculum_item.addActionListener(this);
		
		administrative_item=new JMenuItem("User Accounts");
		administrative_item.setFont(new Font("Times New Roman",Font.PLAIN,13));
		administrative_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
        administrative_item.setIcon(new ImageIcon("SalesRep.png"));
		administrative_item.addActionListener(this);

		grade_item=new JMenuItem("Student Academic Report");
		grade_item.setFont(new Font("Times New Roman",Font.PLAIN,13));
		grade_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,ActionEvent.CTRL_MASK));
        grade_item.setIcon(new ImageIcon("SEARCH.png"));
		grade_item.addActionListener(this);
	
		grade_report_item=new JMenuItem("Class Room Schedule");
		grade_report_item.setFont(new Font("Times New Roman",Font.PLAIN,13));
		grade_report_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,ActionEvent.CTRL_MASK));
        grade_report_item.setIcon(new ImageIcon("newinvoice.png"));
		grade_report_item.addActionListener(this);
	
		employee_item=new JMenuItem("Academic Personnel Record");
		employee_item.setFont(new Font("Times New Roman",Font.PLAIN,13));
		employee_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,ActionEvent.CTRL_MASK));
        employee_item.setIcon(new ImageIcon("EXPENSE.PNG"));
		employee_item.addActionListener(this);
	
    	showStudentMenu=new JMenu("Query Student");
		showStudentMenu.setFont(new Font("Times New Roman",Font.PLAIN,13));
	    showStudentMenu.setIcon(new ImageIcon("categories.png"));
	
     	//short_term_courses_item=new JMenuItem("Short Term Courses");
	//	short_term_courses_item.setFont(new Font("Verdana",Font.PLAIN,13));
	//	short_term_courses_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.CTRL_MASK));
    //    short_term_courses_item.setIcon(new ImageIcon("newrecieve.png"));
//		short_term_courses_item.addActionListener(this);
	
	    student_masterList=new JMenuItem("Students");
	    student_masterList.setFont(new Font("Times New Roman",Font.PLAIN,13));
	    student_masterList.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,ActionEvent.CTRL_MASK));
	    student_masterList.addActionListener(this);
	    student_masterList.setIcon(new ImageIcon("CUSTOMER.PNG"));
	    
	    query_builderItem=new JMenuItem("Query Builder");
	    query_builderItem.setFont(new Font("Times New Roman",Font.PLAIN,13));
	    query_builderItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
	    query_builderItem.setIcon(new ImageIcon("EXPENSE.PNG"));
	
	    courseItem=new JMenuItem("Student by Course");
	    courseItem.setFont(new Font("Times New Roman",Font.PLAIN,13));
	    courseItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
	    courseItem.setIcon(new ImageIcon("newrecieve.png"));
	    courseItem.addActionListener(this);
	  
	    by_YearLevel=new JMenuItem("Query student by Year");
	    by_YearLevel.setFont(new Font("Times New Roman",Font.PLAIN,13));
	    by_YearLevel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
	    by_YearLevel.setIcon(new ImageIcon("newrecieve.png"));
	    by_YearLevel.addActionListener(this);
	  
	    by_Sem=new JMenuItem("Query student by Semester");
	    by_Sem.setFont(new Font("Times New Roman",Font.PLAIN,13));
	    by_Sem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,ActionEvent.CTRL_MASK));
	    by_Sem.setIcon(new ImageIcon("EXPENSE.PNG"));
	    by_Sem.addActionListener(this);
	 
	    by_Sy=new JMenuItem("Query student by SY");
	    by_Sy.setFont(new Font("Times New Roman",Font.PLAIN,13));
	    by_Sy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
	    by_Sy.setIcon(new ImageIcon("SEARCH.png"));
	    by_Sy.addActionListener(this);
	 
	    by_Birthdate=new JMenuItem("Query student by BirthDate");
	    by_Birthdate.setFont(new Font("Times New Roman",Font.PLAIN,13));
	    by_Birthdate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK));
	    by_Birthdate.setIcon(new ImageIcon("SalesRep.png"));
	    by_Birthdate.addActionListener(this);
	

	    by_Faculty_Load=new JMenuItem("Query Faculty by Loading");
	    by_Faculty_Load.setFont(new Font("Times New Roman",Font.PLAIN,13));
	    by_Faculty_Load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
	    by_Faculty_Load.setIcon(new ImageIcon("purchaseorder.png"));
	    by_Faculty_Load.addActionListener(this);
	
	    by_EnrollmentStatus=new JMenuItem("Query Student by Enrollment Status");
	    by_EnrollmentStatus.setFont(new Font("Times New Roman",Font.PLAIN,13));
	    by_EnrollmentStatus.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
	    by_EnrollmentStatus.setIcon(new ImageIcon("SEARCH.png"));
	    by_EnrollmentStatus.addActionListener(this);
	 
	    studs_Archieves=new JMenu("Student Enrollment File");
	    studs_Archieves.setFont(new Font("Times New Roman",Font.PLAIN,13));
	    studs_Archieves.setIcon(new ImageIcon("EXPENSE.PNG"));
	    
	    Class_Schedules_Archieves=new JMenuItem("Class Schedules Archieves");
	    Class_Schedules_Archieves.setFont(new Font("Times New Roman",Font.PLAIN,13));
        Class_Schedules_Archieves.setIcon(new ImageIcon("EXPENSE.PNG"));
	    Class_Schedules_Archieves.addActionListener(this);
	       
	   
     	//---Add to JMenu
     	file_menu.add(signout_item);
     	file_menu.addSeparator();
        file_menu.add(exit_item);
     	
        studs_Archieves.add(studentmaster_item);
        studs_Archieves.addSeparator();
        studs_Archieves.add(studentMasterList_Archieves);
        studs_Archieves.addSeparator();
        studs_Archieves.add(Class_Schedules_Archieves);
        
        registration_menu.add(studs_Archieves);
        registration_menu.addSeparator();
        registration_menu.add(studentMasterRecord);
        registration_menu.add(schedule_item);
        registration_menu.addSeparator();
        registration_menu.add(course_item);
        registration_menu.addSeparator();
        registration_menu.add(subject_item);
        registration_menu.addSeparator();
        registration_menu.add(room_item);
        registration_menu.addSeparator();
        registration_menu.add(curriculum_item);
        registration_menu.addSeparator();
        registration_menu.add(employee_item);
        registration_menu.addSeparator();
        registration_menu.add(grade_item);
     //   registration_menu.addSeparator();
      //  registration_menu.add(short_term_courses_item);
 
        
        reports_menu.add(courseItem);
        reports_menu.addSeparator();
        reports_menu.add(query_builderItem);
        reports_menu.addSeparator();
        reports_menu.add(grade_report_item);
        reports_menu.addSeparator();
        reports_menu.add(room_master_list_item);
        reports_menu.addSeparator();
        reports_menu.add(by_YearLevel);
        reports_menu.addSeparator();
        reports_menu.add(by_Sem);
        reports_menu.addSeparator();
        reports_menu.add(by_Sy);
        reports_menu.addSeparator();
        reports_menu.add(by_Birthdate);
        reports_menu.addSeparator();
        reports_menu.add(by_Faculty_Load);
        reports_menu.addSeparator();
        reports_menu.add(by_EnrollmentStatus);  
        
        view_menu.add(student_masterList);
       // query_menu.add(query_builderItem);
         
        setting_menu.add(administrative_item);
 
        help_menu.add(about_item);
        help_menu.addSeparator();
        help_menu.add(tutorial_item);
        help_menu.addSeparator();
        help_menu.add(documentation_item);
               
        //reports_menu.add(facultyloading_item);
     	//---Add to JMenuBar
     	mbar.add(file_menu);
     	mbar.add(registration_menu);
        mbar.add(reports_menu); 
        mbar.add(view_menu);
        mbar.add(setting_menu);
        mbar.add(help_menu);
 
        setBounds(5,2,790,570);
     	setVisible(true);
     	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
  
     	getCollection();
        
       //	disabledAll();
       
      //  studMaster=new StudentMaster_onLoad(this);
      //  addFrame(studMaster);
      //  studMaster.setBounds(20,5,750,450);
      //  studMaster.setVisible(true);
        
        
       // introframe = new IntroFrame(this);
       // addFrame3(introframe);
       // introframe.setBounds(230,130,330,170);
     //	introframe.setVisible(true);
   
     	}//---Constructor Ends Here---//
  
       void disabledAll()
       {
        schedule_item.setEnabled(false);
        employee_item.setEnabled(false);
        course_item.setEnabled(false);
        subject_item.setEnabled(false);
        curriculum_item.setEnabled(false);
        room_item.setEnabled(false);
        grade_item.setEnabled(false);
        studentmaster_item.setEnabled(false);
       // grade_report_item.setEnabled(false);
       // classlist_item.setEnabled(false);
        administrative_item.setEnabled(false);
        room_master_list_item.setEnabled(false);
       // classmaster_list_item.setEnabled(false);
        signout_item.setEnabled(false);
        exit_item.setEnabled(false);
      //  short_term_courses_item.setEnabled(false);
        showStudentMenu.setEnabled(false);
        courseItem.setEnabled(false);
        grade_report_item.setEnabled(false);       
      
        query_builderItem.setEnabled(false);
        student_masterList.setEnabled(false);
        room_master_list_item.setEnabled(false);
        
        by_YearLevel.setEnabled(false);
        by_Sem.setEnabled(false);
        by_Sy.setEnabled(false);
        by_Birthdate.setEnabled(false);
        by_Faculty_Load.setEnabled(false);
        by_EnrollmentStatus.setEnabled(false);       
        studentMasterRecord.setEnabled(false);
        
       }
     
       public void enabledAdmin()
       {
        studentmaster_item.setEnabled(true);
        employee_item.setEnabled(true);
        schedule_item.setEnabled(true);
        course_item.setEnabled(true);
        subject_item.setEnabled(true);
        curriculum_item.setEnabled(true);
        room_item.setEnabled(true);
        grade_item.setEnabled(true);
        studentmaster_item.setEnabled(true);
        administrative_item.setEnabled(true);
        room_master_list_item.setEnabled(true);
        signout_item.setEnabled(true);
        exit_item.setEnabled(true);
        showStudentMenu.setEnabled(true);
     
        query_builderItem.setEnabled(true);
        student_masterList.setEnabled(true);
        room_master_list_item.setEnabled(true);
     
        showStudentMenu.setEnabled(true);
        courseItem.setEnabled(true);
        grade_report_item.setEnabled(true);       
      
     
        by_YearLevel.setEnabled(true);
        by_Sem.setEnabled(true);
        by_Sy.setEnabled(true);
        by_Birthdate.setEnabled(true);
        by_Faculty_Load.setEnabled(true);
        by_EnrollmentStatus.setEnabled(true);       
      
       
        studentMasterRecord.setEnabled(true);
        
       }
       public void enabledGuest()
       {
       studentmaster_item.setEnabled(true);
       signout_item.setEnabled(true);
       schedule_item.setEnabled(true);
       exit_item.setEnabled(true);
       }
       //---Window Adapter---//
       public void addFrame(MESFrame frame){
   	   desktop.add (frame, javax.swing.JLayeredPane.PALETTE_LAYER);
       frame.moveToFront();
       frame.setMaximizable(false);
       frame.setClosable(false);
       frame.setIconifiable(true);
       desktop.setSelectedFrame(frame);
       desktop.getDesktopManager().activateFrame(frame);
       int x = (getSize(). width / 2) - (frame.getSize(). width / 2);
       int y = (getSize().height  / 2) - (frame.getSize(). height / 2 )- 30;
       frame.setBounds(20,5,750,450);
       frame.setVisible(true);
       }
       
       public void addFrame2(MESFrame frame){
   	   desktop.add (frame, javax.swing.JLayeredPane.PALETTE_LAYER);
       frame.moveToFront();
       frame.setMaximizable(false);
       frame.setClosable(false);
       frame.setIconifiable(true);
       desktop.setSelectedFrame(frame);
       desktop.getDesktopManager().activateFrame(frame);
       int x = (getSize(). width / 2) - (frame.getSize(). width / 2);
       int y = (getSize().height  / 2) - (frame.getSize(). height / 2 )- 30;
       frame.setBounds(5,5,780,450);
       frame.setVisible(true);
       }
       public void addFrame3(MESFrame frame3){
   	   desktop.add (frame3, javax.swing.JLayeredPane.PALETTE_LAYER);
       frame3.moveToFront();
       frame3.setMaximizable(false);
       frame3.setClosable(false);
       desktop.setSelectedFrame(frame3);
       desktop.getDesktopManager().activateFrame(frame3);
       int x = (getSize(). width / 2) - (frame3.getSize(). width / 2);
       int y = (getSize().height  / 2) - (frame3.getSize(). height / 2 )- 30;
       frame3.setBounds(230,120,330,190);
       frame3.setVisible(true);
       }

	   public void addFrame3_part2(MESFrame frame2){
   	   desktop.add (frame2, javax.swing.JLayeredPane.PALETTE_LAYER);
       frame2.moveToFront();
       frame2.setMaximizable(false);
       frame2.setClosable(false);
       desktop.setSelectedFrame(frame2);
       desktop.getDesktopManager().activateFrame(frame2);
       int x = (getSize(). width / 2) - (frame2.getSize(). width / 2);
       int y = (getSize().height  / 2) - (frame2.getSize(). height / 2 )- 30;
       frame2.setBounds(230,80,330,250);
       frame2.setVisible(true);
       }

	   public void addFrame4(MESFrame frame4){
   	   desktop.add (frame4, javax.swing.JLayeredPane.PALETTE_LAYER);
       frame4.moveToFront();
       frame4.setMaximizable(false);
       frame4.setClosable(false);
       desktop.setSelectedFrame(frame4);
       desktop.getDesktopManager().activateFrame(frame4);
       int x = (getSize(). width / 2) - (frame4.getSize(). width / 2);
       int y = (getSize().height  / 2) - (frame4.getSize(). height / 2 )- 30;
       frame4.setBounds(200,80,400,290);
       frame4.setVisible(true);
       }
    
  	   public void addFrame5(MESFrame frame5){
   	   desktop.add (frame5, javax.swing.JLayeredPane.PALETTE_LAYER);
       frame5.moveToFront();
       frame5.setMaximizable(false);
       frame5.setClosable(false);
       desktop.setSelectedFrame(frame5);
       desktop.getDesktopManager().activateFrame(frame5);
       int x = (getSize(). width / 2) - (frame5.getSize(). width / 2);
       int y = (getSize().height  / 2) - (frame5.getSize(). height / 2 )- 30;
       frame5.setBounds(180,80,450,300);
       frame5.setVisible(true);
       }
       public void addPopup(MESFrame frame){
   	   desktop.add (frame, javax.swing.JLayeredPane.POPUP_LAYER);
   	   frame.moveToFront();
   	   desktop.setSelectedFrame(frame);
   	   desktop.getDesktopManager().activateFrame(frame);
	   int x = (getSize(). width / 2) - (frame.getSize(). width / 2);
  	   int y = (getSize().height  / 2) - (frame.getSize(). height / 2 )- 30;
   	   frame.setLocation(x,y);
  	   }
    	public void actionPerformed(ActionEvent e)
     	{
     	 Object source=e.getSource();	
           if(source==studentmaster_item)
           {
           	addFrame2(new showStudentList(this) );
           }
           if(source==student_masterList)
           {
            addFrame2(new showStudentMasterRecord(this));	
           }
           if(source==schedule_item)
           {
           	addFrame2(new showClassPrograms(this) );
           }
           if(source==signout_item)
     	   {
     	   	disabledAll();
            callIntro();
     	   }
     	   if(source==employee_item)
            {
            	addFrame2(new showFacultyList(this) );
            }
           if(source==courseItem)
           {
           	addFrame2(new ClassList(this));
           }
           if(source==student_masterList)
           {
           	addFrame2(new showStudentMasterRecord(this));
           }
           if(source==grade_report_item)
           {
           	addFrame2(new Query_RoomNo_Schedule(this));
           }
           if(source==subject_item)
           {
           	addFrame2(new showCourseList(this));
           }
           if(source==studentMasterRecord)
           {
           	addFrame2(new showStudentMasterRecord(this));
           }
           if(source==administrative_item)
           {
           	 addFrame3_part2(new Add_UserAccounts(this));
           }
           if(source==exit_item)
           {
           int ans=JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Exit",JOptionPane.YES_NO_OPTION);
             
              if(ans==JOptionPane.YES_OPTION)
              {
              	System.exit(0);
              }  
              if(ans==JOptionPane.NO_OPTION)
              {
              	JOptionPane.showMessageDialog(null,"Termination Attempt Failed","Disacard",JOptionPane.WARNING_MESSAGE);
              }  
     	   }//end of if
     	   else if(source==classlist_item)
     	   {
     	   	 addFrame2(new ClassList(this));
     	   }
     	  if(source==btn1)
     	  {
          	addFrame2(new showStudentList(this) );
     	  }
     	  
     	  if(source==studentMasterList_Archieves)
     	  {
     	  	addFrame2(new showStudentList_Archieves(this));
     	  }
     	  if(source==Class_Schedules_Archieves)
     	  {
     	  	addFrame2(new showClassPrograms_Archieves(this));
     	  }
     	  if(source==btn11)
     	  {
          fc= new JFileChooser(new File(filename));
           // Show open dialog; this method does not return until the dialog is closed
          fc.showOpenDialog(frame);
          File selFile = fc.getSelectedFile();
    
          // Show save dialog; this method does not return until the dialog is closed
          fc.showSaveDialog(frame);
          selFile = fc.getSelectedFile();
          }
     	 }
     	void callIntro()
     	{
     	introframe = new IntroFrame(this);
        addFrame3(introframe);
  		introframe.setBounds(230,120,330,190);
       // introframe.setBounds(230,80,330,250);
        introframe.setVisible(true);
     	}

     	public static void main(String args[])
     	{
  // Get the currently installed look and feel
    LookAndFeel lf = UIManager.getLookAndFeel();
    
    // Install a different look and feel; specifically, the Windows look and feel
    try {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    } catch (InstantiationException e) {
    } catch (ClassNotFoundException e) {
    } catch (UnsupportedLookAndFeelException e) {
    } catch (IllegalAccessException e) {
    }
         new MainFrame();
        }     
    
    }    
 
  