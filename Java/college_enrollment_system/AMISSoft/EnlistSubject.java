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

public class EnlistSubject extends MESFrame implements ActionListener{

    MainFrame parent;
    EnrollmentChecking eChecking;

    public String studno="",description="",examcode="",timeoutpm,timeinpm;
    JTextField numberField,questionField,answerField, cAField, cBField, cCField;
    JTextField lectureField,labField,dayField,timeField,roomField,instructorField;
   
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
    public EnlistSubject(MainFrame main,EnrollmentChecking paramECheck){
        super(600,370,"", new String[] {"Ok","Close"});
        parent = main;
        eChecking=paramECheck;
        init();
        setTitle("Add Student Subject");
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
        roomField=new JTextField();
        instructorField=new JTextField();
        
        JLabel t2=new JLabel("Add Student Subject");
        MAINPANEL.add(t2).setBounds(115,10,270,25);
        t2.setFont(new Font("Verdana",Font.BOLD,15));
        t2.setBackground(new Color(0,128,192));
       
        JLabel borderLabel=new JLabel("");
        MAINPANEL.add(borderLabel).setBounds(5,40,380,350);
        borderLabel.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
       
        MAINPANEL.add(numberField).setBounds(100,50,100,20);
        MAINPANEL.add(questionField).setBounds(100,80,100,20);
        MAINPANEL.add(answerField).setBounds(100,110,250,20);
        MAINPANEL.add(lectureField).setBounds(100,140,100,20);
        MAINPANEL.add(labField).setBounds(100,170,100,20);
        MAINPANEL.add(dayField).setBounds(240,50,100,20);
        MAINPANEL.add(timeField).setBounds(240,80,100,20);
        MAINPANEL.add(roomField).setBounds(240,140,100,20);
        MAINPANEL.add(instructorField).setBounds(100,200,220,20);
        
        JLabel t3=new JLabel("Class No.:");
        MAINPANEL.add(t3);
        t3.setFont(new Font("Verdana",Font.PLAIN,13));
        t3.setBounds(8,50,100,20);
       
        JLabel t4=new JLabel("SubjectCode");
        MAINPANEL.add(t4);
        t4.setFont(new Font("Verdana",Font.PLAIN,13));
        t4.setBounds(8,80,100,20);
        
        JLabel t5=new JLabel("Description");
        MAINPANEL.add(t5);
        t5.setFont(new Font("Verdana",Font.PLAIN,13));
        t5.setBounds(8,110,140,20);
     
        JLabel t6=new JLabel("Lecture Units");
        MAINPANEL.add(t6);
        t6.setFont(new Font("Verdana",Font.PLAIN,13));
        t6.setBounds(8,140,140,20);
     
        JLabel t7=new JLabel("Lab. Units");
        MAINPANEL.add(t7);
        t7.setFont(new Font("Verdana",Font.PLAIN,13));
        t7.setBounds(8,170,140,20);
     
        JLabel t8=new JLabel("Days");
        MAINPANEL.add(t8);
        t8.setFont(new Font("Verdana",Font.PLAIN,13));
        t8.setBounds(200,50,140,20);
     
        JLabel t9=new JLabel("Time");
        MAINPANEL.add(t9);
        t9.setFont(new Font("Verdana",Font.PLAIN,13));
        t9.setBounds(200,80,140,20);
     
        JLabel t10=new JLabel("Room");
        MAINPANEL.add(t10);
        t10.setFont(new Font("Verdana",Font.PLAIN,13));
        t10.setBounds(200,140,140,20);

		JLabel t11=new JLabel("Instructor");
        MAINPANEL.add(t11);
        t11.setFont(new Font("Verdana",Font.PLAIN,13));
        t11.setBounds(8,200,140,20);
     

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
        onFocus();
        }
    }
    void search_class()
    {
    	try
    	  {
    	  	Class.forName(driver);
    	  	connection=DriverManager.getConnection(url,username,password);
    	  	statement=connection.createStatement();
    	  	
    	  	String query="SELECT ClassNo,SubjectCode,Title,Lec,Lab,Days,TimeSlot,RoomNo,Ins_ID FROM class_schedule WHERE ClassNo='"+numberField.getText()+"'";
    	    rs=statement.executeQuery(query);
    	    rs.next();
    	    numberField.setText(rs.getString(1));
    	    questionField.setText(rs.getString(2));
    	    answerField.setText(rs.getString(3));
    	    lectureField.setText(rs.getString(4));
    	    labField.setText(rs.getString(5));
    	    dayField.setText(rs.getString(6));
    	    timeField.setText(rs.getString(7));
    	    roomField.setText(rs.getString(8));
    	    instructorField.setText(rs.getString(9));
    	    
    	  
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
    numberField.setText("");
    numberField.requestFocus();
    questionField.setText("");
    answerField.setText("");
    lectureField.setText("");
    labField.setText("");
    dayField.setText("");
    timeField.setText("");
    roomField.setText("");
    instructorField.setText("");
    }
    void onFocus()
    {
      	
    }
    
    void save(){
    int intchk=JOptionPane.showConfirmDialog(null,"Are you sure you want to save data?","Save",JOptionPane.YES_NO_OPTION);
    if(! complete()){
    JOptionPane.showMessageDialog(this, "Fill the required information", "Data Required", JOptionPane.WARNING_MESSAGE);
    return;
    }
       if(intchk==JOptionPane.YES_OPTION)
       {
            String number = numberField.getText();
            String question = questionField.getText();
            String answer = answerField.getText();
            String lecture=lectureField.getText();
            String laboratory=labField.getText();
            String day=dayField.getText();
            String time=timeField.getText();
            String room=roomField.getText();
            
            String sql = "INSERT INTO enrollment_data(IDNumber,ClassNo,SubjectCode,Title,LecUnit,LabUnit,Days,TSlot,Room)VALUES('"+eChecking.strID+"','"+number+"','"+question+"','"+answer+"','"+lecture+"','"+laboratory+"','"+day+"','"+time+"','"+room+"')";
            int update = parent.database.executeUpdate(sql);
            if(update ==0)																																																								
            {
                Hashtable h = new Hashtable();
                h.put("IDNumber",eChecking.strID);
                h.put("ClassNo",number);
                h.put("SubjectCode",question);
                h.put("Title",answer);
                h.put("LecUnit",lecture);
                h.put("LabUnit",laboratory);
                h.put("Days",day);
                h.put("TSlot",time);
                h.put("Room",room);
                parent.getCollection("enrollment_data").add(h);
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
            eChecking.fillTable();
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