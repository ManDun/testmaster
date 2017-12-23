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

public class FacultyMaster extends MESFrame implements ActionListener{

    MainFrame parent;
    showFacultyList facultyList;
    String custno="",compname="";
    JTextField EmployeeNo,FirstName,LastName,Address,Email,Father,Mother,Contact,imageField;
    JComboBox cboGroup,cbReligion,cbCitizenship,cbCStatus,cbGender,cbCourse,cbYearLevel;
    JTextField ParentsAddress,ParentsContactNo;
    JFormattedTextField BirthDate;
    JTextField School1,School2,School3;
    JTextField Address1,Address2,Address3;
    JTextField Graduated1,Graduated2,Graduated3;
    String url="jdbc:odbc:DBMaster";
    String driver="sun.jdbc.odbc.JdbcOdbcDriver";
    Statement statement;
    Connection connection;
    ResultSet rs;
    private int count1=0,count2=0,count3=0,count4=0;
    int cnt=1;
    
    JLabel ImageLabel;
    String imagename = "steam.gif";
    ImageIcon ii = new ImageIcon(imagename);
   
   String customerCounter="";
    public FacultyMaster(MainFrame main,showFacultyList paramFacultyList){
        super(390,300,"", new String[] {"Add","Save","Search","Edit","Cancel","Close"});
        parent = main;
        facultyList=paramFacultyList;
        init();
        setTitle("Academic Personnel Record");
        setVisible(true);
    }
    void init(){
        addButtonActionListener(this);
        MAINPANEL.setLayout(null);
        //MAINPANEL.setBackground(new Color(213,229,219));
    
        ///JPanel barPanel=new JPanel();
        //barPanel.setBackground(new Color(0,128,192));
        //MAINPANEL.add(barPanel).setBounds(0,50,710,24);
        
        JLabel recLabel=new JLabel("");
        MAINPANEL.add(recLabel).setBounds(20,35,700,440);
        recLabel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
   
        JLabel t1=new JLabel("Academic Personnel Record");
        MAINPANEL.add(t1).setBounds(270,10,300,25);
        t1.setFont(new Font("Verdana",Font.BOLD,14));
       
        JLabel t4=new JLabel("Employee No");
        MAINPANEL.add(t4);
        t4.setFont(new Font("Verdana",Font.PLAIN,13));
        t4.setBounds(30,50,100,20);
        
        JLabel t5=new JLabel("FirstName");
        MAINPANEL.add(t5);
        t5.setFont(new Font("Verdana",Font.PLAIN,13));
        t5.setBounds(30,80,100,20);
        
        JLabel t6=new JLabel("LastName");
        MAINPANEL.add(t6);
        t6.setFont(new Font("Verdana",Font.PLAIN,13));
        t6.setBounds(30,110,100,20);
        
        JLabel t7=new JLabel("Address");
        MAINPANEL.add(t7);
        t7.setFont(new Font("Verdana",Font.PLAIN,13));
        t7.setBounds(30,140,100,20);
        
        JLabel t8=new JLabel("SSS#");
        MAINPANEL.add(t8);
        t8.setFont(new Font("Verdana",Font.PLAIN,13));
        t8.setBounds(30,170,100,20);
       
        JLabel t9=new JLabel("Birth Date");
        MAINPANEL.add(t9).setBounds(305,50,180,23);
        t9.setFont(new Font("Verdana",Font.PLAIN,13));
       
        JLabel t10=new JLabel("Religion");
        MAINPANEL.add(t10).setBounds(305,80,180,23);
        t10.setFont(new Font("Verdana",Font.PLAIN,13));
       
        JLabel t11=new JLabel("Gender");
        MAINPANEL.add(t11).setBounds(490,50,180,23);
        t11.setFont(new Font("Verdana",Font.PLAIN,13));
       
        ImageLabel=new JLabel(" ", ii ,JLabel.CENTER);//---default image
        MAINPANEL.add(ImageLabel).setBounds(570,45,145,125);
        ImageLabel.setFont(new Font("Verdana",Font.PLAIN,13));
        ImageLabel.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED) );
        
        JLabel t13=new JLabel("Tel No.");
        MAINPANEL.add(t13).setBounds(370,110,80,23);
        t13.setFont(new Font("Verdana",Font.PLAIN,13));
        
        JLabel t14=new JLabel("Citizenship");
        MAINPANEL.add(t14).setBounds(370,140,80,23);
        t14.setFont(new Font("Verdana",Font.PLAIN,13));
       
        JLabel t15=new JLabel("TIN#");
        MAINPANEL.add(t15);
        t15.setFont(new Font("Verdana",Font.PLAIN,13));
        t15.setBounds(30,200,100,20);
        
        JLabel t16=new JLabel("Education");
        MAINPANEL.add(t16);
        t16.setFont(new Font("Verdana",Font.BOLD,12));
        t16.setBounds(30,270,200,20);
        
        JLabel t17=new JLabel("Name of School");
        MAINPANEL.add(t17);
        t17.setFont(new Font("Verdana",Font.BOLD,12));
        t17.setBounds(170,270,200,20);
        
        JLabel t18=new JLabel("Address");
        MAINPANEL.add(t18);
        t18.setFont(new Font("Verdana",Font.BOLD,12));
        t18.setBounds(420,270,200,20);
        
        JLabel t19=new JLabel("Date Graduated");
        MAINPANEL.add(t19);
        t19.setFont(new Font("Verdana",Font.BOLD,12));
        t19.setBounds(590,270,200,20);
        
        JLabel t20=new JLabel("Elementary");
        MAINPANEL.add(t20);
        t20.setFont(new Font("Verdana",Font.BOLD,12));
        t20.setBounds(30,300,200,20);
        
        JLabel t21=new JLabel("Secondary");
        MAINPANEL.add(t21);
        t21.setFont(new Font("Verdana",Font.BOLD,12));
        t21.setBounds(30,330,200,20);
        
        JLabel t22=new JLabel("College");
        MAINPANEL.add(t22);
        t22.setFont(new Font("Verdana",Font.BOLD,12));
        t22.setBounds(30,360,200,20);
        
        JLabel t23=new JLabel("Image");
        MAINPANEL.add(t23).setBounds(370,200,80,23);
        t23.setFont(new Font("Verdana",Font.PLAIN,13));
        
        JLabel t24=new JLabel("Status");
        MAINPANEL.add(t24).setBounds(370,170,80,23);
        t24.setFont(new Font("Verdana",Font.PLAIN,13));
        
       // JLabel t25=new JLabel("Course");
       // MAINPANEL.add(t25).setBounds(550,170,80,23);
       // t25.setFont(new Font("Verdana",Font.PLAIN,13));
   
        //JLabel t26=new JLabel("Year Level");
        //MAINPANEL.add(t26).setBounds(550,200,80,23);
        //t26.setFont(new Font("Verdana",Font.PLAIN,13));
   
        JLabel t27=new JLabel("Mobile No.");
        MAINPANEL.add(t27).setBounds(30,230,200,24);
        t27.setFont(new Font("Verdana",Font.PLAIN,13));
   
        JLabel t28=new JLabel("Date Hired");
        MAINPANEL.add(t28).setBounds(490,230,200,24);
        t28.setFont(new Font("Verdana",Font.PLAIN,13));
   
        JLabel t29=new JLabel("Faculty Group");
        MAINPANEL.add(t29).setBounds(250,230,200,24);
        t29.setFont(new Font("Verdana",Font.PLAIN,13));
  
  
        cboGroup=new JComboBox();
        cboGroup.addItem("");
        cboGroup.addItem("IT");
        cboGroup.addItem("GE");
        MAINPANEL.add(cboGroup).setBounds(350,230,120,24);
        
        JMenuBar mbar=new JMenuBar();
        MAINPANEL.add(mbar).setBounds(20,270,700,25);
        mbar.setBackground(new Color(255,255,192));
        mbar.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        
        EmployeeNo=new JTextField();
        FirstName =new JTextField();
        LastName  =new JTextField();
        Email     =new JTextField();
        Address   =new JTextField();
          //apply maskformatter
          try
             {
             	MaskFormatter m=new MaskFormatter("##/##/##");
             	BirthDate=new JFormattedTextField(m);
             }
             catch(Exception e)
             {
             	
             }
         
        //BirthDate=new JTextField();
        cbReligion=new JComboBox();
        cbReligion.addItem("Catholic");
        cbReligion.addItem("Islam");
        cbReligion.addItem("Protestant");
        cbReligion.addItem("Jehovahs Witnesses");
        cbReligion.addItem("Judaism");
        
        cbGender=new JComboBox();
        cbGender.addItem("Male");
        cbGender.addItem("Female");
        
        cbCitizenship=new JComboBox();
        cbCitizenship.addItem("Filipino");
        cbCitizenship.addItem("Foreigner");
        
        cbCStatus=new JComboBox();
        cbCStatus.addItem("Single");
        cbCStatus.addItem("Married");
        cbCStatus.addItem("Divoirce");
      
        cbCourse=new JComboBox();
        cbCourse.addItem("DCS");
        cbCourse.addItem("DEP");
        cbCourse.addItem("DIT");
        cbCourse.addItem("DCET");
      
        cbYearLevel=new JComboBox();
        cbYearLevel.addItem("1st");
        cbYearLevel.addItem("2nd");
        
        Father =new JTextField();
        Mother =new JTextField();
        Contact=new JTextField();
        
        School1 =new JTextField();
        School2 =new JTextField();
         School3 =new JTextField();
        
        Address1 =new JTextField();
        Address2 =new JTextField();
        Address3 =new JTextField();
        
        Graduated1 =new JTextField();
  		Graduated2 =new JTextField();
        Graduated3 =new JTextField();
        
        imageField=new JTextField();
       
        ParentsAddress=new JTextField(); 
        ParentsContactNo=new JTextField();
       
        MAINPANEL.add(EmployeeNo).setBounds(118,50,180,23);
        MAINPANEL.add(FirstName).setBounds(118,80,180,23);
        MAINPANEL.add(LastName).setBounds(118,110,180,23);
        MAINPANEL.add(Address).setBounds(118,140,240,23);
        MAINPANEL.add(Email).setBounds(118,170,240,23);
       
       
        MAINPANEL.add(BirthDate).setBounds(380,50,100,23);
        MAINPANEL.add(cbReligion).setBounds(380,80,100,23);
        MAINPANEL.add(cbGender).setBounds(490,80,70,23);
     //   MAINPANEL.add(Father).setBounds(118,200,240,23);
        MAINPANEL.add(Mother).setBounds(118,200,240,23);//tine
        MAINPANEL.add(Contact).setBounds(440,110,125,23);
        MAINPANEL.add(cbCitizenship).setBounds(440,140,125,23);
        MAINPANEL.add(cbCStatus).setBounds(440,170,100,23);
       
       
        MAINPANEL.add(School1).setBounds(118,300,230,23);
        MAINPANEL.add(School2).setBounds(118,330,230,23);
        MAINPANEL.add(School3).setBounds(118,360,230,23);
        
        MAINPANEL.add(Address1).setBounds(347,300,250,23);
  		MAINPANEL.add(Address2).setBounds(347,330,250,23);
  		MAINPANEL.add(Address3).setBounds(347,360,250,23);

  		MAINPANEL.add(Graduated1).setBounds(597,300,100,23);
  		MAINPANEL.add(Graduated2).setBounds(597,330,100,23);
  		MAINPANEL.add(Graduated3).setBounds(597,360,100,23);
        MAINPANEL.add(imageField).setBounds(440,200,100,23);
        
        MAINPANEL.add(ParentsAddress).setBounds(118,230,120,24);//moBileNo
        MAINPANEL.add(ParentsContactNo).setBounds(580,230,130,24);//date hired
        
        //disable_fields
        disable_fields();
        disable_OnLoad();
      
      }//=====end of constructor//
  
    public void actionPerformed(ActionEvent event){
        String act = event.getActionCommand();
        
        if(act.equals("Close")){
            dispose();
        //    disabledFields();
        }else if(act.equals("Save")){
            save();
        }
         else if(act.equals("Search"))
         {
         searchRECORD();
         }
         else if(act.equals("Add"))
         {
         enable_fields();
         clear_fields();
         //disabled_OnAdd();
         enabledOnAdd();
         }
         else if(act.equals("Cancel"))
         {
         enabled_OnCancel();	
         disable_fields();
         }
    }
    void save(){
     //  if(! complete()){
      //          JOptionPane.showMessageDialog(this, "Fill the required information", "Data Required", JOptionPane.WARNING_MESSAGE);
     //       return;
            
       // }
           String employeeno=EmployeeNo.getText();
	        String fname  =FirstName.getText();
            String lname  =LastName.getText();
            String address=Address.getText();
            String sssno  =Email.getText();
            String tinno  =Mother.getText();//tine number
            String contactno=ParentsAddress.getText();
            String birthdate=BirthDate.getText();
            String religion=cbReligion.getSelectedItem().toString();
            String gender=cbGender.getSelectedItem().toString();
            String telno=Contact.getText();//telno
            String citizenship=cbCitizenship.getSelectedItem().toString();
            String cstatus=cbCStatus.getSelectedItem().toString();
            String image=imageField.getText();
            String facultygroup=cboGroup.getSelectedItem().toString();
            String datehired=ParentsContactNo.getText();
            
            String school1=School1.getText();
            String address1=Address1.getText();
            String graduated1=Graduated1.getText();
            
          
            String school2=School2.getText();
            String address2=Address2.getText();
            String graduated2=Graduated2.getText();
          
            String school3=School3.getText();
            String address3=Address3.getText();
            String graduated3=Graduated3.getText();
          
           
            
            String sql = "INSERT INTO FacultyMaster(EmployeeNo,FirstName,LastName,Address,SSSNo,TINNo,ContactNo,BirthDate,Religion,Gender,TelNo,Citizenship,Status,Image,FacultyGroup,DateHired,School1,Address1,Graduated1,School2,Address2,Graduated2,School3,Address3,Graduated3)VALUES('"+employeeno+"','"+fname+"','"+lname+"','"+address+"','"+sssno+"','"+tinno+"','"+contactno+"','"+birthdate+"','"+religion+"','"+gender+"','"+telno+"','"+citizenship+"','"+cstatus+"','"+image+"','"+facultygroup+"','"+datehired+"','"+school1+"','"+address1+"','"+graduated1+"','"+school2+"','"+address2+"','"+graduated2+"','"+school3+"','"+address3+"','"+graduated3+"')";
            int update = parent.database.executeUpdate(sql);
            if(update ==0)								
            {
                Hashtable h = new Hashtable();
                h.put("EmployeeNo",employeeno);
                h.put("FirstName",fname);
                h.put("LastName",lname);
                h.put("Address",address);
                h.put("SSSNo",sssno);
                h.put("TINNo",tinno);
                h.put("ContactNo",contactno);
                parent.getCollection("FacultyMaster").add(h);
            }
          else
            {
            return;
            }
          // dispose();
           facultyList.fillTable();
        }
    void searchRECORD()
    {
      String srcKey=JOptionPane.showInputDialog(null,"TYPE STUDENT NO. OR LASTNAME TO SEARCH");
      String msg="The Record your are trying to retrive does\n"
                 +"not found with our database.Please check your\n"
                 +"spelling and try again...Thank you!!!";
      try
        {
        Class.forName(driver);
        connection=DriverManager.getConnection(url);
        statement=connection.createStatement();
        String query="SELECT EmployeeNo,FirstName,LastName,Address,SSSNo,TINNo,ContactNo,BirthDate,Religion,Gender,TelNo,Citizenship,Status,Image,FacultyGroup,DateHired,School1,Address1,Graduated1,School2,Address2,Graduated2,School3,Address3,Graduated3 FROM FacultyMaster WHERE EmployeeNo='"+srcKey+"' OR LastName='"+srcKey+"'";
        rs=statement.executeQuery(query);
        rs.next();
        EmployeeNo.setText(rs.getString(1));    
        FirstName.setText(rs.getString(2) );
        LastName.setText(rs.getString(3) );
        Address.setText(rs.getString(4) );
        Email.setText(rs.getString(5) );
        Mother.setText(rs.getString(6));
        ParentsAddress.setText(rs.getString(7));
       
        BirthDate.setText(rs.getString(8));
       
        String strReligion=rs.getString(9);
        cbReligion.setSelectedItem(strReligion);
    
     
        String strGender=rs.getString(10);
        cbGender.setSelectedItem(strGender);
     
        Contact.setText(rs.getString(11));
       
        
        String strCitizenship=rs.getString(12);
        cbCitizenship.setSelectedItem(strCitizenship);
     
        String strCStatus=rs.getString(13);
        cbCStatus.setSelectedItem(strCStatus);
       
        
        imagename=rs.getString(14);
       
        imageField.setText(imagename);
       
        ImageLabel.setIcon(new ImageIcon(imagename));      
       
       
        
        String facultygroup=rs.getString(15);
        cboGroup.setSelectedItem(facultygroup);
        
        ParentsContactNo.setText(rs.getString(16));
     
        School1.setText(rs.getString(17));
        Address1.setText(rs.getString(18));
        Graduated1.setText(rs.getString(19));
            
          
        School2.setText(rs.getString(20));
        Address2.setText(rs.getString(21));
        Graduated2.setText(rs.getString(22));          
        School3.setText(rs.getString(23));
        Address3.setText(rs.getString(24));
        Graduated3.setText(rs.getString(25));
          
        
        
        statement.close();
        connection.close();
        }//---end of try
   		catch(ClassNotFoundException c)
        {
         System.err.println(c);	
        }
        catch(SQLException sql)
        {
        JOptionPane.showMessageDialog(null,msg,"No Record Found",JOptionPane.WARNING_MESSAGE);
        }    
        	
    }
    void clear_fields()
    {
        EmployeeNo.setText("");
        EmployeeNo.requestFocus();
        FirstName.setText("");
    	LastName.setText("");
    	Address.setText("");
    	Email.setText("");
    	Father.setText("");
    	Mother.setText("");
        BirthDate.setText("");
        Father.setText("");
        Mother.setText("");
        cbGender.setSelectedItem(null);
        cbCStatus.setSelectedItem(null);
        cbReligion.setSelectedItem(null);
        cbCitizenship.setSelectedItem(null);
        cboGroup.setSelectedItem(null);
        Contact.setText("");
        imageField.setText("");
        ImageLabel.setIcon(new ImageIcon(""));   
        School1.setText("");
        School2.setText("");
        School3.setText("");
        Address1.setText("");
        Address2.setText("");
        Address3.setText("");
        Graduated1.setText("");
        Graduated2.setText("");
        Graduated3.setText("");
        ParentsAddress.setText("");
        ParentsContactNo.setText("");

    }
    void disable_fields()
    {
    	EmployeeNo.setEditable(false);
    	FirstName.setEditable(false);
    	LastName.setEditable(false);
    	Address.setEditable(false);
    	Email.setEditable(false);
    	Father.setEditable(false);
    	Mother.setEditable(false);
    	BirthDate.setEditable(false);
    	cbGender.setEnabled(false);
    	cbCStatus.setEnabled(false);
    	cbReligion.setEnabled(false);
    	cbCitizenship.setEnabled(false);
    	Contact.setEditable(false);
    	imageField.setEditable(false);
        School1.setEditable(false);
        School2.setEditable(false);
        School3.setEditable(false);
        Address1.setEditable(false);
        Address2.setEditable(false);
        Address3.setEditable(false);
        Graduated1.setEditable(false);
        Graduated2.setEditable(false);
        Graduated3.setEditable(false);
        ParentsAddress.setEditable(false);
        ParentsContactNo.setEditable(false);
    }
    void enable_fields()
    {
    	EmployeeNo.setEditable(true);
    	FirstName.setEditable(true);
    	LastName.setEditable(true);
    	Address.setEditable(true);
    	Email.setEditable(true);
    	Father.setEditable(true);
    	Mother.setEditable(true);
    	BirthDate.setEditable(true);
    	cbGender.setEnabled(true);
    	cbCStatus.setEnabled(true);
    	cbReligion.setEnabled(true);
    	cbCitizenship.setEnabled(true);
    	Contact.setEditable(true);
    	imageField.setEditable(true);
        School1.setEditable(true);
        School2.setEditable(true);
        School3.setEditable(true);
        Address1.setEditable(true);
        Address2.setEditable(true);
        Address3.setEditable(true);
        Graduated1.setEditable(true);
        Graduated2.setEditable(true);
        Graduated3.setEditable(true);
        ParentsAddress.setEditable(true);
        ParentsContactNo.setEditable(true);
    	
    }
    void disable_OnLoad()
    {
   //"Add","Save","Search","Edit","Cancel","Close"});
   
    BUTTONS[1].setEnabled(false);
    BUTTONS[3].setEnabled(false);
    BUTTONS[4].setEnabled(false);
    }
    void disabled_OnAdd()
    {
    BUTTONS[2].setEnabled(false);
    BUTTONS[4].setEnabled(true);
   // BUTTONS[3].setEnabled(true);
    }
    void enabled_OnCancel()
    {
    BUTTONS[2].setEnabled(true);
    BUTTONS[4].setEnabled(false);
    }
    void enabledOnAdd()
    {
    BUTTONS[1].setEnabled(true);
    BUTTONS[3].setEnabled(true);
    BUTTONS[4].setEnabled(true);
    }
    boolean complete(){
        boolean b = FirstName.getText().trim().length() == 0;
        boolean c = LastName.getText().trim().length()==0;
        boolean d = Address.getText().trim().length()==0;
 //       boolean e = Email.getText().trim().length()==0;
        boolean f = Father.getText().trim().length()==0;
        boolean g = Mother.getText().trim().length()==0;
        return !( b | c | d | f | g);
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