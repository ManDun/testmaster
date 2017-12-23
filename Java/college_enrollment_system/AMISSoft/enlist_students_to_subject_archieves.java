package mes.gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.Color;
import javax.swing.Timer;
import java.sql.*;

import mes.dbase.*;
import mes.gui.*;
import mes.util.*;

public class enlist_students_to_subject_archieves extends MESFrame implements ActionListener{

    MainFrame parent2;
    ClassProgramChecking_Archieves CPChecking;

    public String studno="",description="",examcode="",timeoutpm,timeinpm;
    JTextField numberField,questionField,answerField, cAField, cBField, cCField;
    JTextField lectureField,labField,dayField,timeField,roomField;
   
    String url="jdbc:mysql://192.168.1.37/dbmaster";
	String driver ="org.gjt.mm.mysql.Driver";
    
     String username="root";
     String password="rose";
   
    Connection connection;
    Statement statement;
    ResultSet rs;
    String msg1="The Class No. you're trying to find does not\n"
               +"found in our database.Please check if the CAPS\n"
               +"LOCK is on and try again!"; 
    public enlist_students_to_subject_archieves(MainFrame main2,ClassProgramChecking_Archieves cChecking){
        super(600,370,"", new String[] {"Ok","Close"});
        parent2 = main2;
        
        CPChecking=cChecking;
        init();
        setTitle("Student Class Registration");
        setVisible(true);
    }
    void init(){
        addButtonActionListener(this);
       
        MAINPANEL.setLayout(null);
        //MAINPANEL.setBackground(new Color(212,230,240));
   
        numberField = new JTextField();
        questionField = new JTextField();
        answerField = new JTextField();
        lectureField = new JTextField();
        labField=new JTextField();
        dayField=new JTextField();
        timeField=new JTextField();
        //roomField=new JTextField();
        
        JLabel t2=new JLabel("Student Class Registration");
        MAINPANEL.add(t2).setBounds(100,10,270,25);
        t2.setFont(new Font("Verdana",Font.BOLD,15));
        t2.setBackground(new Color(0,128,192));
       
        JLabel borderLabel=new JLabel("");
        MAINPANEL.add(borderLabel).setBounds(5,40,380,300);
        borderLabel.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
       
        MAINPANEL.add(numberField).setBounds(100,50,100,20);
        MAINPANEL.add(questionField).setBounds(100,80,100,20);
        MAINPANEL.add(answerField).setBounds(100,110,250,20);
        MAINPANEL.add(lectureField).setBounds(100,140,250,20);
        MAINPANEL.add(labField).setBounds(100,170,100,20);
        MAINPANEL.add(dayField).setBounds(290,50,90,20);
        MAINPANEL.add(timeField).setBounds(290,80,90,20);
       // MAINPANEL.add(roomField).setBounds(240,140,100,20);
        
       
       
        JLabel t3=new JLabel("Student ID:");
        MAINPANEL.add(t3);
        t3.setFont(new Font("Verdana",Font.PLAIN,13));
        t3.setBounds(8,50,100,20);
       
        JLabel t4=new JLabel("FirstName");
        MAINPANEL.add(t4);
        t4.setFont(new Font("Verdana",Font.PLAIN,13));
        t4.setBounds(8,80,100,20);
        
        JLabel t5=new JLabel("LastName");
        MAINPANEL.add(t5);
        t5.setFont(new Font("Verdana",Font.PLAIN,13));
        t5.setBounds(8,110,140,20);
     
        JLabel t6=new JLabel("Address");
        MAINPANEL.add(t6);
        t6.setFont(new Font("Verdana",Font.PLAIN,13));
        t6.setBounds(8,140,140,20);
     
        JLabel t7=new JLabel("Gender");
        MAINPANEL.add(t7);
        t7.setFont(new Font("Verdana",Font.PLAIN,13));
        t7.setBounds(8,170,140,20);
     
        JLabel t8=new JLabel("Course ID");
        MAINPANEL.add(t8);
        t8.setFont(new Font("Verdana",Font.PLAIN,13));
        t8.setBounds(200,50,140,20);
     
        JLabel t9=new JLabel("Year Level");
        MAINPANEL.add(t9);
        t9.setFont(new Font("Verdana",Font.PLAIN,13));
        t9.setBounds(200,80,140,20);
     
        JLabel t10=new JLabel("Room");
       // MAINPANEL.add(t10);
      //  t10.setFont(new Font("Verdana",Font.PLAIN,13));
       // t10.setBounds(200,140,140,20);

        numberField.addActionListener(this);
    }
    public void actionPerformed(ActionEvent event){
        String act = event.getActionCommand();
        Object source=event.getSource();
            
        if(act.equals("Close"))
        {
            dispose();
        }else if(act.equals("Ok"))
        {
            save();
        }
        else if(source==numberField)
        {
        search_class();	
        }
    }
    void search_class()
    {
    	try
    	  {
    	  	Class.forName(driver);
    	  	connection=DriverManager.getConnection(url,username,password);
    	  	statement=connection.createStatement();
    	  	String query="SELECT IDNumber,FirstName,LastName,Address,Gender,YearLevel,Course FROM temp WHERE IDNumber='"+numberField.getText()+"'";
    	    rs=statement.executeQuery(query);
    	   
    	    rs.next();
    	    
    	    numberField.setText(rs.getString(1));
    	    questionField.setText(rs.getString(2));
    	    answerField.setText(rs.getString(3));
    	    lectureField.setText(rs.getString(4));
    	    labField.setText(rs.getString(5));
    	    timeField.setText(rs.getString(7));
    	    dayField.setText(rs.getString(6));
    	 
    	   }
    	  catch(ClassNotFoundException c)
    	  {
    	  System.err.println(c);	
    	  }
    	  catch(SQLException sql)
    	  {
    	  //System.err.println(sql);
    	  JOptionPane.showMessageDialog(null,msg1,"Record Not Found",JOptionPane.ERROR_MESSAGE);
    	  clr_field();
    	  }
    	  
    	  
    }
    void clr_field()
    {
    numberField.requestFocus();
 	numberField.setText("");
    questionField.setText("");
    answerField.setText("");
    lectureField.setText("");
    labField.setText("");
    timeField.setText("");
    dayField.setText("");
      
    }
    
    //Saving starts here...........
    void save()
    {
    int intchk=JOptionPane.showConfirmDialog(null,"Are you sure you want to save data?","Save",JOptionPane.YES_NO_OPTION);
    if(! complete()){
    JOptionPane.showMessageDialog(this, "Fill the required information", "Data Required", JOptionPane.WARNING_MESSAGE);
    return;
    }
       if(intchk==JOptionPane.YES_OPTION)
       {
            String number   = numberField.getText();
            String question = questionField.getText();
            String answer   = answerField.getText();
            String address  = lectureField.getText();
            String gender   = labField.getText();
            String course   = timeField.getText();
            String yearlevel= dayField.getText();
           
            String sql ="INSERT INTO classAttendance_data(ClassNo,IDNumber,FirstName,LastName,Address,Gender,Course,YearLevel)VALUES('"+CPChecking.strID+"','"+number+"','"+question+"','"+answer+"','"+address+"','"+gender+"','"+course+"','"+yearlevel+"')";
            int update = parent2.database.executeUpdate(sql);																																														
            if(update ==0)																																																								
            {
                Hashtable h = new Hashtable();
                h.put("ClassNo",CPChecking.strID);
                h.put("IDNumber",number);
                h.put("FirstName",question);
                h.put("LastName",answer);
                h.put("Address",address);
                h.put("Gender",gender);
                h.put("YearLevel",yearlevel);
                h.put("Course",course);
                parent2.getCollection("classAttendance_data").add(h);
            }
             else{
                return;
            }
       }//end of intchk
    else{
    	JOptionPane.showMessageDialog(null,"Process Cancelled","Cancelled",JOptionPane.WARNING_MESSAGE);
  	    }

           //dispose();
            clr_field();
            CPChecking.fillTable();
    }
    boolean complete(){
        boolean a = numberField.getText().trim().length() == 0;
        boolean b = answerField.getText().trim().length() == 0;
        return !(a || b );
        
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