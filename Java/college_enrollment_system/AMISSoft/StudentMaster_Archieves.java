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
import mes.gui.*;

public class StudentMaster_Archieves extends MESFrame implements ActionListener{

    MainFrame parent;
    showStudentList_Archieves studList;
    String custno="",compname="";
    JTextField ID,FirstName,LastName,Address,Email,Father,Mother,Contact,imageField;
    JComboBox cboEStatus,cboSY,cbReligion,cbCitizenship,cbCStatus,cbGender,cbCourse,cbYearLevel,cboSemester;
    JTextField ParentsAddress,ParentsContactNo;
    JFormattedTextField BirthDate,EnrollmentDate;
    JTextField MiddleName;
    JLabel id;
    
    JComboBox cboSection;
    
    JTextField School1,School2,School3;
    JTextField Address1,Address2,Address3;
    JTextField Graduated1,Graduated2,Graduated3;
   
    String url="jdbc:mysql://192.168.1.37/dbmaster";
	String driver ="org.gjt.mm.mysql.Driver";
    
    String username="root";
    String password="rose";
    
    Statement statement;
    Connection connection;
    ResultSet rs;
    private int count1=0,count2=0,count3=0,count4=0;
    int cnt=1;
    

    private int Oldcount1=0,Oldcount2=0,Oldcount3=0,Oldcount4=0;
    int Oldcnt=1;

    JLabel ImageLabel;
    String imagename = "steam.gif";
    ImageIcon ii = new ImageIcon(imagename);
   
    String customerCounter="";
    public StudentMaster_Archieves(MainFrame main,showStudentList_Archieves paramStudList){

        super(390,300,"", new String[] {"Add","Save","Search","Edit","Cancel","Update","Close"});
        parent = main;
        studList=paramStudList;
        init();
        setTitle("Student Enrollment Master File Archieves");
        setVisible(true);
    }
    void init(){
        addButtonActionListener(this);
        MAINPANEL.setLayout(null);
     
        JLabel recLabel=new JLabel("");
       // MAINPANEL.add(recLabel).setBounds(5,15,730,440);
       // recLabel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
   
        id=new JLabel("ID#:");
        MAINPANEL.add(id).setBounds(30,20,100,23);
        id.setFont(new Font("Verdana",Font.BOLD,13));
      
        ID=new JTextField();
        MAINPANEL.add(ID).setBounds(118,20,180,23);
        //ID.setEditable(false);
       
    //    JLabel t1=new JLabel("Student Master Record");
    //    MAINPANEL.add(t1).setBounds(270,0,200,25);
      //  t1.setFont(new Font("Verdana",Font.BOLD,14));
       
        JLabel t4=new JLabel("FirstName");
        MAINPANEL.add(t4);
        t4.setFont(new Font("Verdana",Font.PLAIN,13));
        t4.setBounds(30,50,100,20);
        
        JLabel t5=new JLabel("LastName");
        MAINPANEL.add(t5);
        t5.setFont(new Font("Verdana",Font.PLAIN,13));
        t5.setBounds(30,80,100,20);
        
        JLabel t6=new JLabel("Address");
        MAINPANEL.add(t6);
        t6.setFont(new Font("Verdana",Font.PLAIN,13));
        t6.setBounds(30,110,100,20);
        
        JLabel t7=new JLabel("EmailAddress");
        MAINPANEL.add(t7);
        t7.setFont(new Font("Verdana",Font.PLAIN,13));
        t7.setBounds(30,140,100,20);
        
        JLabel t8=new JLabel("Father");
        MAINPANEL.add(t8);
        t8.setFont(new Font("Verdana",Font.PLAIN,13));
        t8.setBounds(30,170,100,20);
       
        JLabel t9=new JLabel("Birth Date");
        MAINPANEL.add(t9).setBounds(305,50,180,23);
        t9.setFont(new Font("Verdana",Font.PLAIN,13));
       
        JLabel t32=new JLabel("MI");
        MAINPANEL.add(t32).setBounds(305,20,180,23);
        t32.setFont(new Font("Verdana",Font.PLAIN,13));
       
        JLabel t33=new JLabel("Section");
        MAINPANEL.add(t33).setBounds(420,20,180,23);
        t33.setFont(new Font("Verdana",Font.PLAIN,13));
        
        JLabel t34=new JLabel("Enroll Status");
        MAINPANEL.add(t34).setBounds(585,20,180,23);
        t34.setFont(new Font("Verdana",Font.PLAIN,13));
      
        
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
        
        JLabel t13=new JLabel("Contact#");
        MAINPANEL.add(t13).setBounds(370,110,80,23);
        t13.setFont(new Font("Verdana",Font.PLAIN,13));
        
        JLabel t14=new JLabel("Citizenship");
        MAINPANEL.add(t14).setBounds(370,140,80,23);
        t14.setFont(new Font("Verdana",Font.PLAIN,13));
       
        JLabel t15=new JLabel("Mother");
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
        
        JLabel t25=new JLabel("Course");
        MAINPANEL.add(t25).setBounds(550,170,80,23);
        t25.setFont(new Font("Verdana",Font.PLAIN,13));
   
        JLabel t26=new JLabel("Year Level");
        MAINPANEL.add(t26).setBounds(550,200,80,23);
        t26.setFont(new Font("Verdana",Font.PLAIN,13));
   
        JLabel t30=new JLabel("Sem");
        MAINPANEL.add(t30).setBounds(550,230,80,23);
        t30.setFont(new Font("Verdana",Font.PLAIN,13));
   
        JLabel t31=new JLabel("SY");
        MAINPANEL.add(t31).setBounds(660,230,80,23);
        t31.setFont(new Font("Verdana",Font.PLAIN,13));
   
   
        JLabel t27=new JLabel("Parent's");
        MAINPANEL.add(t27).setBounds(30,230,200,24);
        t27.setFont(new Font("Verdana",Font.PLAIN,13));
    
        JLabel t29=new JLabel("Address");
        MAINPANEL.add(t29).setBounds(30,245,200,24);
        t29.setFont(new Font("Verdana",Font.PLAIN,13));
    
        JLabel t28=new JLabel("Contact#");
        MAINPANEL.add(t28).setBounds(370,230,200,24);
        t28.setFont(new Font("Verdana",Font.PLAIN,13));
   
        //MAINPANEL.add(imageField).setBounds(440,230,100,23);
        
   
        JMenuBar mbar=new JMenuBar();
        MAINPANEL.add(mbar).setBounds(20,270,700,25);
        mbar.setBackground(new Color(255,255,192));
        mbar.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        FirstName =new JTextField();
        LastName  =new JTextField();
        Address   =new JTextField();
        Email     =new JTextField();
        MiddleName=new JTextField();
         //apply maskformatter
          try
             {
             	MaskFormatter m=new MaskFormatter("##/##/##");
             	BirthDate=new JFormattedTextField(m);
             }
             catch(Exception e)
             {
             	
             }
       // try
       ///   {
        ///   MaskFormatter m2=new MaskFormatter("##/##/##");
         //  EnrollmentDate=new JFormattedTextField(m2);
         //}
          //catch(Exception e)
          //{
          	
         // } 
        
        //BirthDate=new JTextField();
        
        cboSection=new JComboBox();
        cboSection.addItem("Section-A");
        cboSection.addItem("Section-B");
        cboSection.addItem("Section-C");
        cboSection.addItem("Section-D");
        
        cbReligion=new JComboBox();
        cbReligion.addItem("Catholic");
        cbReligion.addItem("Islam");
        cbReligion.addItem("Protestant");
        cbReligion.addItem("Jehovahs Witnesses");
        cbReligion.addItem("N/A");

        
        cbGender=new JComboBox();
        cbGender.addItem("Male");
        cbGender.addItem("Female");
        
        cbCitizenship=new JComboBox();
        cbCitizenship.addItem("Filipino");
        cbCitizenship.addItem("Foreigner");
        
        cbCStatus=new JComboBox();
        cbCStatus.addItem("Single");
        cbCStatus.addItem("Married");
        cbCStatus.addItem("Divorce");
      
        cbCourse=new JComboBox();
        cbCourse.addItem("DCS");
        cbCourse.addItem("DEP");
        cbCourse.addItem("DIT");
        cbCourse.addItem("DCET");
      
        cbYearLevel=new JComboBox();
        cbYearLevel.addItem("1st");
        cbYearLevel.addItem("2nd");
        
        cboSemester=new JComboBox();
        cboSemester.addItem("1st");
        cboSemester.addItem("2nd");
   
        
       	cboSY=new JComboBox();
       	cboSY.addItem("06/07");
       	cboSY.addItem("07/08");
       	cboSY.addItem("08/09");
       	
       	cboEStatus=new JComboBox();
       	cboEStatus.addItem("New");
       	cboEStatus.addItem("Old");
       	cboEStatus.addItem("Transferee");
   
   
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
       
        MAINPANEL.add(FirstName).setBounds(118,50,180,23);
        MAINPANEL.add(LastName).setBounds(118,80,180,23);
        MAINPANEL.add(Address).setBounds(118,110,240,23);
        MAINPANEL.add(Email).setBounds(118,140,240,23);
        MAINPANEL.add(BirthDate).setBounds(380,50,100,23);
        MAINPANEL.add(MiddleName).setBounds(350,20,60,23);
       // MAINPANEL.add(EnrollmentDate).setBounds(520,20,60,23);
        MAINPANEL.add(cboSection).setBounds(480,20,100,23);
        
        MAINPANEL.add(cboEStatus).setBounds(680,20,80,23);
        MAINPANEL.add(cbReligion).setBounds(380,80,100,23);
        MAINPANEL.add(cbGender).setBounds(490,80,70,23);
        MAINPANEL.add(Father).setBounds(118,170,240,23);
        MAINPANEL.add(Mother).setBounds(118,200,240,23);
        MAINPANEL.add(Contact).setBounds(440,110,125,23);
        MAINPANEL.add(cbCitizenship).setBounds(440,140,125,23);
        MAINPANEL.add(cbCStatus).setBounds(440,170,100,23);
        MAINPANEL.add(cbCourse).setBounds(620,172,80,23);
        MAINPANEL.add(cbYearLevel).setBounds(620,200,80,23);
        MAINPANEL.add(cboSemester).setBounds(590,230,60,23);
        MAINPANEL.add(cboSY).setBounds(680,230,60,23);
       
       
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
        
        MAINPANEL.add(ParentsAddress).setBounds(118,230,250,24);
        MAINPANEL.add(ParentsContactNo).setBounds(440,230,100,24);
        
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
         disabled_OnAdd();
         }
         else if(act.equals("Cancel"))
         {
         enabled_OnCancel();	
         disable_fields();
         }
         else if(act.equals("Edit"))
         {
        // 	disabled_onEdit();
         onEdit();
         }
         else if(act.equals("Update"))
         {
         	updateRecord();
         }
    }
    void save(){
       if(! complete()){
                JOptionPane.showMessageDialog(this, "ALL INFORMATION IS REQUIRED!","DATA REQUIRED?", JOptionPane.WARNING_MESSAGE);
            return;
            
        }
       /*
           try
            {
            Class.forName(driver);
            connection=DriverManager.getConnection(url,username,password);
            statement=connection.createStatement();
            String query="SELECT IDNumber FROM studentmaster";
            rs=statement.executeQuery(query);
            String recID=rs.getString(1);
            cnt = 1;
            count3=0;
			count4=0;
   		    count2=0;
            while(rs.next())
            {
             cnt++;
            			if((cnt==10))
						{
						cnt=0;
						count3=count3+1;
						}
						//Please Read:If the Records is equal to 0099 the cnt variable must be
						//change to this code below.You must input the 0099 records manually to be able
						//to get the target number to 100 other wise it won't work.
						//Bernardo D. Tongcos Jr. 10/25/2006
						else if((cnt==9)&&(count3==9)||(cnt==10))
					//	else if((cnt==9)&&(count3==9)||(cnt==10)||(count3==7)||(count3==8))
						{
					    cnt=0;
					    cnt-=1;	
						//cnt=0;	
						count3=0;	
						count4=count4+1;
						}
			//			if((cnt==9)&&(count3==9))
			//			{
			//				cnt=0;
			//				count3=0;
			//				count4=count4+1;
			//			}
						else if((cnt==9)&&(count2==9))
						{
						cnt=0;
						count3=0;
						count4=0;
						count2=count2+1;
						}//end of if
	        }//end of while
	         
          }//end of try
            catch(ClassNotFoundException c)
            {
            System.err.println(c);	
            }
            catch(SQLException sqle)
            {
            System.err.println(sqle);	
            }
            String STUDID = "116-2006-"+count1+count4+count3+cnt;
	     */
	        String STUDID =""+ID.getText();
	        String fname  =FirstName.getText();
	        String lname  =LastName.getText();
            String address=Address.getText();
            String email  =Email.getText();
            String father =Father.getText();
            String mother =Mother.getText();
            String parentsaddress=ParentsAddress.getText();
            String birthdate=BirthDate.getText();
            String religion=cbReligion.getSelectedItem().toString();
            String gender=cbGender.getSelectedItem().toString();
            String contact=Contact.getText();
            String citizenship=cbCitizenship.getSelectedItem().toString();
            String cstatus=cbCStatus.getSelectedItem().toString();
            String image=imageField.getText();
            String course=cbCourse.getSelectedItem().toString();
            String yearlevel=cbYearLevel.getSelectedItem().toString();
            String parentscontactno=ParentsContactNo.getText();
            String school1=School1.getText();
            String address1=Address1.getText();
            String graduated1=Graduated1.getText();
            String school2=School2.getText();
            String address2=Address2.getText();
            String graduated2=Graduated2.getText();
            String school3=School3.getText();
            String address3=Address3.getText();
            String graduated3=Graduated3.getText();
            String semester=cboSemester.getSelectedItem().toString();
            String CStatus=cbCStatus.getSelectedItem().toString();
            String sy=cboSY.getSelectedItem().toString();
          //  String strEDate=EnrollmentDate.getText();
            String eStatus=cboEStatus.getSelectedItem().toString();
            String strSection=cboSection.getSelectedItem().toString();
            String sql = "INSERT INTO temp(IDNumber,FirstName,LastName,Address,Email,Father,Mother,ParentsAddress,BirthDate,Religion,Gender,ContactNo,Citizenship,Status,Image,Course,YearLevel,ParentsContactNo,School1,Address1,Graduated1,School2,Address2,Graduated2,School3,Address3,Graduated3,Sem,SY,MI,EnrollmentStatus,Section)VALUES('"+STUDID+"','"+fname+"','"+lname+"','"+address+"','"+email+"','"+father+"','"+mother+"','"+parentsaddress+"','"+birthdate+"','"+religion+"','"+gender+"','"+contact+"','"
            +citizenship+"','"+cstatus+"','"+image+"','"+course+"','"+yearlevel+"','"+parentscontactno+"','"+school1+"','"+address1+"','"+graduated1+"','"+school2+"','"+address2+"','"+graduated2+"','"+school3+"','"+address3+"','"+graduated3+"','"+semester+"','"+sy+"','"+MiddleName.getText()+"','"+eStatus+"','"+strSection+"')";
            int update = parent.database.executeUpdate(sql);
            if(update ==0)								
            {
                Hashtable h = new Hashtable();
                h.put("IDNumber",STUDID);
                h.put("FirstName",fname);
                h.put("LastName",lname);
                h.put("Address",address);
                h.put("Gender",gender);
                h.put("Course",course);
                h.put("YearLevel",yearlevel);
                h.put("Sem",semester);
                h.put("SY",sy);
                h.put("Section",strSection);
                h.put("Status",CStatus);
                parent.getCollection("temp").add(h);
            }
          else
            {
            return;
            }
           dispose();
           studList.fillTable();
        }
    void searchRECORD()
    {
      String srcKey=JOptionPane.showInputDialog(null,"TYPE STUDENT NO. OR LASTNAME TO SEARCH");
      String msg="RECORD NOT FOUND!\n"
                 +"PLEASE TRY AGAIN AND CHECK YOUR SPELLING\n";
      try
        {
        Class.forName(driver);
        connection=DriverManager.getConnection(url,username,password);
        statement=connection.createStatement();
        String query="SELECT IDNumber,FirstName,LastName,Address,Email,Father,Mother,ParentsAddress,BirthDate,Religion,Gender,ContactNo,Citizenship,Status,Image,Course,YearLevel,ParentsContactNo,School1,Address1,Graduated1,School2,Address2,Graduated2,School3,Address3,Graduated3,Sem,SY,MI,EnrollmentStatus,Section FROM temp WHERE IDNumber='"+srcKey+"' OR LastName='"+srcKey+"'";
        rs=statement.executeQuery(query);
       
        rs.next();//next row
      
        ID.setText(rs.getString(1));
        FirstName.setText(rs.getString(2) );
        LastName.setText(rs.getString(3) );
        Address.setText(rs.getString(4) );
        Email.setText(rs.getString(5) );
        Father.setText(rs.getString(6));
        Mother.setText(rs.getString(7));
        ParentsAddress.setText(rs.getString(8));
        
        BirthDate.setText(rs.getString(9));
       
        String strReligion=rs.getString(10);
        cbReligion.setSelectedItem(strReligion);
        
        String strGender=rs.getString(11);
        cbGender.setSelectedItem(strGender);
      
        Contact.setText(rs.getString(12));
       
        String strCitizenship=rs.getString(13);
        cbCitizenship.setSelectedItem(strCitizenship);
        
       
        String strCStatus=rs.getString(14);
        cbCStatus.setSelectedItem(strCStatus);
        
    
        imagename=rs.getString(15);
       
        imageField.setText(imagename);
       
       // ImageLabel.setIcon(new ImageIcon(imagename));      
    
        
        String strCourse=rs.getString(16);
        cbCourse.setSelectedItem(strCourse);
        
        String strYearLevel=rs.getString(17);
        cbYearLevel.setSelectedItem(strYearLevel);
       
        ParentsContactNo.setText(rs.getString(18));
        
        School1.setText(rs.getString(19) );
       
        Address1.setText(rs.getString(20) );
       
        Graduated1.setText(rs.getString(21) );
        
       
        School2.setText(rs.getString(22) );
       
        Address2.setText(rs.getString(23) );
       
        Graduated2.setText(rs.getString(24) );
       
       
        School3.setText(rs.getString(25) );
       
        Address3.setText(rs.getString(26) );
       
        Graduated3.setText(rs.getString(27) );
        
        
        String sem=rs.getString(28);
        cboSemester.setSelectedItem(sem);
        
        String sy=rs.getString(29);
        cboSY.setSelectedItem(sy);
         
        MiddleName.setText(rs.getString(30));
        
        String strEStatus=rs.getString(31);
          
        cboEStatus.setSelectedItem(strEStatus); 
       
        String section=rs.getString(32);
        cboSection.setSelectedItem(section);
       
        statement.close();
        connection.close();
     
        disabled_onSearch();
        
        }//---end of try
   		catch(ClassNotFoundException c)
        {
         System.err.println(c);	
        }
        catch(SQLException sql)
        {
        JOptionPane.showMessageDialog(null,msg,"RECORD NOT FOUND?",JOptionPane.WARNING_MESSAGE);
        }    
        	
    }
    
    void updateRecord()
    {
    	try
    	  {
    	  	Class.forName(driver);
    	  	connection=DriverManager.getConnection(url,username,password);
    	  	statement=connection.createStatement();
            String citizenship=cbCitizenship.getSelectedItem().toString();
            String cstatus=cbCStatus.getSelectedItem().toString();
            String image=imageField.getText();
            String course=cbCourse.getSelectedItem().toString();
            String parentscontactno=ParentsContactNo.getText();
            String yearlevel=cbYearLevel.getSelectedItem().toString();
            String religion=cbReligion.getSelectedItem().toString();
            String gender=cbGender.getSelectedItem().toString();
            String schoo2=School2.getText();
            String address2=Address2.getText();
            String id=ID.getText(); 
            String graduated2=Graduated2.getText();
            String fname=FirstName.getText();
            String lname=LastName.getText();
            String eStatus=cboEStatus.getSelectedItem().toString();
            String section=cboSection.getSelectedItem().toString();
            String Semester=cboSemester.getSelectedItem().toString();
            String SYear=cboSY.getSelectedItem().toString();
            String sql="UPDATE temp SET FirstName='"+FirstName.getText()+"',LastName='"+LastName.getText()+"',Email='"+Email.getText()+"',Father='"+Father.getText()+"',Mother='"+Mother.getText()+"',ParentsAddress='"+ParentsAddress.getText()+"',BirthDate='"+BirthDate.getText()+"',Religion='"+religion+"',Gender='"+gender+"',ContactNo='"+Contact.getText()+"',Citizenship='"+citizenship+"',Status='"+cstatus+"',Image='"+image+"',Course='"+course+"',YearLevel='"+yearlevel+"',ParentsContactNo='"+parentscontactno+"',School1='"+School1.getText()+"',Address1='"+Address1.getText()+"',Graduated1='"+Graduated1.getText()+"',School2='"+School2.getText()+"',Address2='"+Address2.getText()+"',Graduated2='"+Graduated2.getText()+"',School3='"+School3.getText()+"',Address3='"+Address3.getText()+"',Graduated3='"+Graduated3.getText()+"',Sem='"+Semester+"',SY='"+SYear+"',MI='"+MiddleName.getText()+"',EnrollmentStatus='"+eStatus+"',Section='"+section+"' WHERE IDNumber='"+ID.getText()+"'";
            statement.executeUpdate(sql);
            int update = parent.database.executeUpdate(sql);
    	    JOptionPane.showMessageDialog(null,"Record No:  "+id+"\t"+"  Successfully Updated","Record Updated",JOptionPane.INFORMATION_MESSAGE);
    	    disable_fields();
       	    }																																																																																																																																																											
    	   //studList.fillTable();
    
    	  catch(ClassNotFoundException c)
    	  {
    	  	System.err.println(c);
    	  }
    	  catch(SQLException sql)
    	  {
    	  	System.err.println(sql);
    	  }
    }
    void clear_fields()
    {
        ID.setText("");
    	ID.requestFocus();
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
        cbCourse.setSelectedItem(null);
        cbYearLevel.setSelectedItem(null);
        cboSY.setSelectedItem(null);
        cboSemester.setSelectedItem(null);
        cboEStatus.setSelectedItem(null);
        cboSection.setSelectedItem(null);
    	cboSemester.setSelectedItem(null);
    	cboSY.setSelectedItem(null);
        
     
        Contact.setText("");
        imageField.setText("");
        ParentsAddress.setText("");
        ParentsContactNo.setText("");
        School1.setText("");
        School2.setText("");
        School3.setText("");
        
        Address1.setText("");
        Address2.setText("");
        Address3.setText("");
        
        Graduated1.setText("");
        Graduated2.setText("");
        Graduated3.setText("");
        
        MiddleName.setText("");
//        EnrollmentDate.setText("");
        
    }
    void disable_fields()
    {
    	ID.setEditable(false);
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
    	cbCourse.setEnabled(false);
    	cbYearLevel.setEnabled(false);
    	cboEStatus.setEnabled(false);
    	cboSection.setEnabled(false);
    	cboSemester.setEnabled(false);
    	cboSY.setEnabled(false);
    	Contact.setEditable(false);
    	imageField.setEditable(false);
    	
    	School1.setEditable(false);
    	Address1.setEditable(false);
    	Graduated1.setEditable(false);
    	
    	School2.setEditable(false);
    	Address2.setEditable(false);
    	Graduated2.setEditable(false);
    	
    	School3.setEditable(false);
    	Address3.setEditable(false);
    	Graduated3.setEditable(false);
    	
    	ParentsAddress.setEditable(false);
    	ParentsContactNo.setEditable(false);
    	
    	MiddleName.setEditable(false);
    //	EnrollmentDate.setEditable(false);
    	
   }
    void enable_fields()
    {
    	ID.setEditable(true);
    	FirstName.setEditable(true);
    	LastName.setEditable(true);
    	Address.setEditable(true);
    	Email.setEditable(true);
    	Father.setEditable(true);
    	Mother.setEditable(true);
    	BirthDate.setEditable(true);
    	cbGender.setEnabled(true);
    	cbCStatus.setEnabled(true);
    	cboEStatus.setEnabled(true);
    	cboSection.setEnabled(true);
    	cbReligion.setEnabled(true);
    	cbCitizenship.setEnabled(true);
    
    	cboSY.setEnabled(true);
    	cboSemester.setEnabled(true);
    	
    	Contact.setEditable(true);
    	imageField.setEditable(true);
    	
    	cbCourse.setEnabled(true);
    	cbYearLevel.setEnabled(true);
    
        School1.setEditable(true);
    	Address1.setEditable(true);
    	Graduated1.setEditable(true);
    	
    	School2.setEditable(true);
    	Address2.setEditable(true);
    	Graduated2.setEditable(true);
    	
    	School3.setEditable(true);
    	Address3.setEditable(true);
    	Graduated3.setEditable(true);
    		
    	
    	ParentsAddress.setEditable(true);
    	ParentsContactNo.setEditable(true);
   
        MiddleName.setEditable(true);
  //  	EnrollmentDate.setEditable(true);
    	
    	
    }
    void onEdit()
    {
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
        cboSection.setEnabled(true);
        cboEStatus.setEnabled(true);
    	cbCitizenship.setEnabled(true);
    	cboSemester.setEnabled(true);
    	cboSY.setEnabled(true);
    	Contact.setEditable(true);
    	imageField.setEditable(true);
    	
    	cbCourse.setEnabled(true);
    	cbYearLevel.setEnabled(true);
    
        School1.setEditable(true);
    	Address1.setEditable(true);
    	Graduated1.setEditable(true);
    	
    	School2.setEditable(true);
    	Address2.setEditable(true);
    	Graduated2.setEditable(true);
    	
    	School3.setEditable(true);
    	Address3.setEditable(true);
    	Graduated3.setEditable(true);
    
        MiddleName.setEditable(true);
    //	EnrollmentDate.setEditable(true);
    		
    	ParentsAddress.setEditable(true);
    	ParentsContactNo.setEditable(true);
        BUTTONS[5].setEnabled(true);
        //BUTTONS[2].setEnabled(true);
   
    }
    void disable_OnLoad()
    {
   //"Add","Save","Search","Edit","Cancel","Update","Close"});
    
    }
    void disabled_OnAdd()
    {
    BUTTONS[1].setEnabled(true);
    BUTTONS[2].setEnabled(false);
    BUTTONS[3].setEnabled(false);
    
    }
    void enabled_OnCancel()
    {
    BUTTONS[5].setEnabled(false);
    BUTTONS[2].setEnabled(true);
    }
    void disabled_onEdit()
    {
   
    }
    void hideOnEdit()
    {
   
    }
    void disabled_onSearch()
    {
    //BUTTONS[1].setEnabled(false);
    BUTTONS[5].setEnabled(false);
    BUTTONS[3].setEnabled(true);
    
    } 
    
      boolean complete(){
        boolean b = FirstName.getText().trim().length() == 0;
        boolean c = LastName.getText().trim().length()==0;
        boolean d = Address.getText().trim().length()==0;
        boolean e = Email.getText().trim().length()==0;
        boolean f = Father.getText().trim().length()==0;
        boolean g = Mother.getText().trim().length()==0;
        return !( b | c | d | e | f | g);
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