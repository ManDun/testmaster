package mes.gui;

import javax.swing.JOptionPane.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.Timer;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.swing.text.MaskFormatter;
import java.text.*;

import mes.util.*;
import mes.dbase.*;
import mes.*;

public class Query_RoomNo_Schedule extends MESFrame implements ActionListener{

    MainFrame parent;
   
    String custno="",compname="";
    JComboBox dataList,dataList2;
    String dataBank="",dataBank2="";
     
    String url="jdbc:mysql://192.168.1.37/dbmaster";
	String driver ="org.gjt.mm.mysql.Driver";
    
    String username="root";
    String password="rose";
   
  
    Statement statement;
    Connection connection;
    ResultSet rs;
    private int count1=0,count2=0,count3=0,count4=0;
    int cnt=1;
    String customerCounter="";
    
    Timer timer;
    Date date;
    SimpleDateFormat dateFormat=new SimpleDateFormat("MM-dd-yy");
    JTextField timeText;
    JLabel dateToday;
//    Statement statement;
//    Connection connection;  
    roomlistner rl;
    
    JTable table;    
    roomtbl_model model;
    roomtbl_model finalRM;
    int paramRows;
    JScrollPane resultsPane;
    ResultSet resultSet;
    String msg;
    JTextField BranchCode,TotalUnitsField,BranchAddress,PCurriculum;
    JTextField roomField,subjectField,schoolyearField,semesterField,instructorField,timeField;
    JTextField totalCount;
    int cntRows;
    
    public Query_RoomNo_Schedule(MainFrame main){
        super(390,300,"", new String[] {"Page Setup","Print","Exit Window"});
        parent = main;
        init();
        setTitle("Query Room Schedule");
        setVisible(true);
    }
    void init(){
        addButtonActionListener(this);
        MAINPANEL.setLayout(null);
    
        Calendar calendar=Calendar.getInstance();
        date=calendar.getTime();
        timeText=new JTextField();
        MAINPANEL.add(timeText=new JTextField(dateFormat.format(date)+""));
        timeText.setBounds(220,16,100,20);
        timeText.setForeground(Color.white);
        timeText.setFont(new Font("Times New Roman",Font.PLAIN,13));
        timeText.setBackground(new Color(0,128,192));
        timeText.setEditable(false);
        timeText.setVisible(false);
        timer=new Timer(990,new clockHandler());
        timer.start();
        
        String timeViewer=timeText.getText();
        dateToday=new JLabel(""+timeViewer);
       
       
        BranchCode=new JTextField();
        BranchAddress=new JTextField();
        roomField=new JTextField();
        subjectField=new JTextField();
        TotalUnitsField=new JTextField();
        
        MAINPANEL.add(dateToday).setBounds(80,40,100,20);
        dateToday.setText(timeViewer);
        dateToday.setFont(new Font("Times New Roman",Font.PLAIN,14));
        
        MAINPANEL.add(BranchCode).setBounds(120,70,100,23);
        BranchCode.addActionListener(this);
   
        
        JLabel t1=new JLabel("Class Room Schedule");
        MAINPANEL.add(t1).setBounds(300,10,170,25);
        t1.setFont(new Font("Times New Roman",Font.BOLD,16));
     
        JLabel t3=new JLabel("Today is:");
        MAINPANEL.add(t3);
        t3.setFont(new Font("Times New Roman",Font.PLAIN,14));
        t3.setBounds(20,40,100,20);
       
        JLabel t4=new JLabel("RoomNo");
        MAINPANEL.add(t4);
        t4.setFont(new Font("Times New Roman",Font.PLAIN,14));
        t4.setBounds(20,70,100,20);
        
        JLabel t5=new JLabel("Description");
        MAINPANEL.add(t5).setBounds(20,100,100,20);
        t5.setFont(new Font("Times New Roman",Font.PLAIN,14));
       
        JLabel t6=new JLabel("Remarks");
        MAINPANEL.add(t6).setBounds(20,130,100,20);
        t6.setFont(new Font("Times New Roman",Font.PLAIN,14));
       
        MAINPANEL.add(roomField).setBounds(120,130,100,23);
        roomField.setEditable(false);
       
        JLabel t7=new JLabel("Total Units");
        MAINPANEL.add(t7).setBounds(230,70,100,20);
        t7.setFont(new Font("Times New Roman",Font.PLAIN,14));
       
        MAINPANEL.add(TotalUnitsField).setBounds(330,70,100,23);
        TotalUnitsField.setEditable(false);
       
   //     JLabel t8=new JLabel("Time");
       // MAINPANEL.add(t8).setBounds(230,130,100,20);
       // t8.setFont(new Font("Times New Roman",Font.PLAIN,14));
       
        
        MAINPANEL.add(BranchAddress).setBounds(120,100,300,23);
        BranchAddress.setEditable(false);
        
        JLabel recLabel=new JLabel("");
        recLabel.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        MAINPANEL.add(recLabel).setBounds(5,35,760,340);
       
  /*
   //LOAD BRANCH ID
   try
      {
  	    Class.forName(driver);
        connection=DriverManager.getConnection(url);
        statement=connection.createStatement();
     	String query = "SELECT BranchId FROM BranchDetails";
        ResultSet resultSet=statement.executeQuery(query);
        resultSet=statement.executeQuery(query);
       //--------------------------Place all CourseCode in the ComboBox-----------------------//
        while(resultSet.next())
        {
        dataBank2=resultSet.getString("BranchId");
        dataList2.addItem(dataBank2);
        }
      }          
      catch(ClassNotFoundException ex)
      {
      System.err.println(ex);
      }
      catch(SQLException sq)
      {
      System.err.println(sq);
 	}
 */	
 	//BRANCH ID
   /*
    BranchCode.addActionListener(new ActionListener(){
 	 	public  void actionPerformed(ActionEvent e)
 	 	{
 	 	 try
 	 	   {
 	 	   Class.forName(driver);
 	 	   connection=DriverManager.getConnection(url);
 	 	   statement=connection.createStatement();
 	 	   String query="SELECT * FROM BranchDetails WHERE BranchId='"+BranchCode.getText()+"'";
 	 	   ResultSet rs2=statement.executeQuery(query);
 	 	   rs2.next();
 	 	   
 	 	   BranchAddress.setText(rs2.getString(4));
 	 	  
 	 	  }
 	 	   catch(ClassNotFoundException c)
 	 	   {
 	 	   System.err.println(c);	
 	 	   }
 	 	   catch(SQLException sql)
 	 	   {
 	 	   System.err.println(sql);
 	 	   }
 	 	}
 	 }
 );	 
 */
   //comboBoxListener
    
   
    try
 	   {
	    Class.forName(driver);
		connection=DriverManager.getConnection(url,username,password);
		statement=connection.createStatement();
	    model = new roomtbl_model( connection ); 
        table=new JTable(model);
	    table.setFont(new Font("Times New Roman",Font.PLAIN,12));
		table.setGridColor(Color.red.darker());
		resultsPane=new JScrollPane(table);
		MAINPANEL.add(resultsPane); 
		resultsPane.setBounds(10, 160, 755, 165);
	    }	
		 catch(ClassNotFoundException cl)
		 {
		 System.err.println(cl);		
		 }
		 catch(SQLException slqe)
		 {
		 System.err.println(slqe);
		 }
		 rl = new roomlistner (connection, model, BranchCode,BranchAddress,TotalUnitsField,roomField);
         //dataList2.addItemListener ( bcl );
     //    finalRM.getList(BranchCode.getText());
         BranchCode.addActionListener(rl);
  }//=====end of constructor//
  
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
        
        if(act.equals("Exit Window")){
            dispose();
        }else if(act.equals("Save")){
        }
         else if(act.equals("New"))
         {
         }
    }
    
  
    String clearField(String s){
        if(s == null){
            return "";
        }
        char h= ("' ").charAt(0);
        String c = "";
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != h){
                c = c + s.charAt(i);
            }
        }
        return c;
    }
    
}