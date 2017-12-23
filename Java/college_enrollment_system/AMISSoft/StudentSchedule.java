
//module:subjects.java

package mes.gui;

import javax.swing.JOptionPane;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*; 
import java.util.*;

import mes.gui.*;
import mes.*;
import mes.util.*;
import mes.dbase.*;
 
  public class StudentSchedule extends MESFrame implements ActionListener
  {

   MainFrame parent;
   showClassPrograms showclassprograms;
   JTextField ClassNo,SubjectCode,Title,Prerequisite,LecUnits,LabUnits,Semester,CourseID,YearLevel,CTime,RoomNo,Capacity,Instructor,daysField;
   JButton cmdAdd,cmdSave,cmdSearch,cmdCancel,cmdEdit,cmdUpdate,cmdClose;
   JComboBox comboCourseID;
  
   String url="jdbc:mysql://192.168.1.37/dbmaster";
   String driver ="org.gjt.mm.mysql.Driver";
    
   String username="root";
   String password="rose";
  
   Connection connection;
   Statement statement;
   ResultSet rs;
   String courseID="";
   JTextField ClassNoField;
   String strClassNo;
   String ClassID;
   private int count1=0,cnt=0,count3=0,count4=0,count=0,count2=0;
     public StudentSchedule(MainFrame main,showClassPrograms paramprograms)
//     public StudentSchedule()
     {
     super(600,370,"", new String[] {"Add","Save","Search","Edit","Cancel","Close"});
     parent=main;
     showclassprograms=paramprograms;
     //setBounds(170,100,450,300);
     init();	
     setTitle("Class Scheduling");
     setVisible(true);	
     disabledFields();    
     }
     void init()
     {
      	Container c=this.getContentPane();
     	c.setLayout(null);
     //	c.setBackground(new Color(255,255,204));

     	JLabel t1=new JLabel("Class Scheduling");
     	c.add(t1).setBounds(160,10,150,24);
     	t1.setFont(new Font("Times New Roman",Font.BOLD,17));

     	JLabel t3=new JLabel("Subject Code");
        c.add(t3).setBounds(10,80,100,24);
     	t3.setFont(new Font("Times New Roman",Font.PLAIN,14));
     	
     	SubjectCode=new JTextField();
     	c.add(SubjectCode).setBounds(90,80,110,23);
     	
     	JLabel t4=new JLabel("Description");
        c.add(t4).setBounds(10,110,100,24);
     	t4.setFont(new Font("Times New Roman",Font.PLAIN,14));
     	
     	Title=new JTextField();
     	c.add(Title).setBounds(90,110,300,23);
     	
     	
        JLabel t5=new JLabel("Lec Unit(s)");
        c.add(t5).setBounds(10,140,100,24);
     	t5.setFont(new Font("Times New Roman",Font.PLAIN,14));
    
     	LecUnits=new JTextField();
     	c.add(LecUnits).setBounds(90,140,100,23);
     	
     	
     	JLabel t6=new JLabel("Lab Unit(s)");
        c.add(t6).setBounds(10,170,100,24);
     	t6.setFont(new Font("Times New Roman",Font.PLAIN,14));
    
     	LabUnits=new JTextField();
     	c.add(LabUnits).setBounds(90,170,100,23);
      
     	JLabel t7=new JLabel("Course Code");
        c.add(t7).setBounds(10,50,100,24);
     	t7.setFont(new Font("Times New Roman",Font.PLAIN,14));
      
        comboCourseID=new JComboBox();
        comboCourseID.addItem("DCET");
        comboCourseID.addItem("DEP");
        comboCourseID.addItem("DIT");
        comboCourseID.addItem("DCS");
        c.add(comboCourseID).setBounds(90,50,110,23);
        
     	JLabel t8=new JLabel("Time");
        c.add(t8).setBounds(210,80,100,24);
     	t8.setFont(new Font("Times New Roman",Font.PLAIN,14));
   
        CTime=new JTextField();
     	c.add(CTime).setBounds(295,80,90,23);
     
       	JLabel t9=new JLabel("Room#");
        c.add(t9).setBounds(210,140,100,24);
     	t9.setFont(new Font("Times New Roman",Font.PLAIN,14));
   
        RoomNo=new JTextField();
     	c.add(RoomNo).setBounds(295,140,90,23);
     
       	JLabel t10=new JLabel("Capacity");
        c.add(t10).setBounds(210,170,100,24);
     	t10.setFont(new Font("Times New Roman",Font.PLAIN,14));
   
        Capacity=new JTextField();
     	c.add(Capacity).setBounds(295,170,90,23);
    
        JLabel t11=new JLabel("Instructor");
        c.add(t11).setBounds(10,200,100,24);
     	t11.setFont(new Font("Times New Roman",Font.PLAIN,14));
    
        JLabel t12=new JLabel("Days");
        c.add(t12).setBounds(210,50,100,24);
        t12.setFont(new Font("Times New Roman",Font.PLAIN,14));
    
        
        daysField=new JTextField();
       	c.add(daysField).setBounds(295,50,90,24);
     
    
	    Instructor=new JTextField();
        c.add(Instructor).setBounds(90,200,240,24);
       	
       	
        cmdAdd=new JButton("Add");
        c.add(cmdAdd).setBounds(10,235,70,24);
        cmdAdd.setFont(new Font("Times New Roman",Font.PLAIN,13));
        cmdAdd.addActionListener(this);
        
        cmdSave=new JButton("Save");
        c.add(cmdSave).setBounds(80,235,70,24);
        cmdSave.setFont(new Font("Times New Roman",Font.PLAIN,13));
        cmdSave.addActionListener(this);
        
        cmdSearch=new JButton("Search");
        c.add(cmdSearch).setBounds(150,235,70,24);
        cmdSearch.setFont(new Font("Times New Roman",Font.PLAIN,13));
        cmdSearch.addActionListener(this);
    
        cmdEdit=new JButton("Edit");
        c.add(cmdEdit).setBounds(220,235,70,24);
        cmdEdit.setFont(new Font("Times New Roman",Font.PLAIN,13));
        cmdEdit.addActionListener(this);
      
        cmdUpdate=new JButton("Update");
        c.add(cmdUpdate).setBounds(210,235,80,24);
        cmdUpdate.setFont(new Font("Times New Roman",Font.PLAIN,13));
        cmdUpdate.addActionListener(this);
        cmdUpdate.setVisible(false);
     
        cmdCancel=new JButton("Cancel");
        c.add(cmdCancel).setBounds(290,235,70,24);
        cmdCancel.setFont(new Font("Times New Roman",Font.PLAIN,12));
        cmdCancel.addActionListener(this);
   
        cmdClose=new JButton("Close");
        c.add(cmdClose).setBounds(360,235,70,24);
        cmdClose.setFont(new Font("Times New Roman",Font.PLAIN,13));
        cmdClose.addActionListener(this);
       }
    //\\\\\\\\\\\Saving Data\\\\\\\\\\\\\\\\\\//
    void onSave()
    {
    int intchk=JOptionPane.showConfirmDialog(null,"Are you sure you want to save data?","Save",JOptionPane.YES_NO_OPTION);
       if(!complete())
       {
       JOptionPane.showMessageDialog(this, "ALL INFORMATION IS REQUIRED","DATA REQUIRED", JOptionPane.WARNING_MESSAGE);
       return;
       }
       if(intchk==JOptionPane.YES_OPTION)
       {
       
       try
            {
            Class.forName(driver);
            connection=DriverManager.getConnection(url,username,password);
            statement=connection.createStatement();
            String query="SELECT * FROM class_schedule";
            rs=statement.executeQuery(query);
            cnt = 1;
			count3=0;
			count4=0;
   		    count2=0;
            while(rs.next())
            {
             cnt++;//count all records
            			if(cnt==10)
						{
						cnt=0;
						count3=count3+1;
						}
						else if((cnt==9)&&(count3==9))
						{
						cnt=0;	
						count3=0;	
						count4=count4+1;
						}
						else if((cnt==9)&&(count2==9))
						{
						cnt=0;
						count3=0;
						count4=0;
						count2=count2+1;
						}//end of if
	          }//end of while
	       }
	        catch(ClassNotFoundException c)
            {
            System.err.println(c);	
            }
            catch(SQLException sqle)
            {
            System.err.println(sqle);	
            }
            ClassID = "116-"+count1+count4+count3+cnt;
            String subjectcode=SubjectCode.getText();
            String title=Title.getText();
            String lecunits=LecUnits.getText();
            String labunits=LabUnits.getText();
            String days=daysField.getText();
            String timeslot=CTime.getText();
            String roomno=RoomNo.getText();
            String limit=Capacity.getText();
            String ins_id=Instructor.getText();
            courseID=comboCourseID.getSelectedItem().toString();
            String query="INSERT INTO class_schedule(ClassNo,SubjectCode,Title,Lec,Lab,Days,TimeSlot,RoomNo,Limits,Ins_ID,CourseID)VALUES('"+ClassID+"','"+subjectcode+"','"+title+"','"+lecunits+"','"+labunits+"','"+days+"','"+timeslot+"','"+roomno+"','"+limit+"','"+ins_id+"','"+courseID+"')";
            int update = parent.database.executeUpdate(query);
            if(update ==0)								
            {
                Hashtable h = new Hashtable();
                h.put("ClassNo",ClassID);
                h.put("SubjectCode",subjectcode);
                h.put("Title",title);
                h.put("Lec",lecunits);
                h.put("Lab",labunits);
                h.put("Days",days);
                h.put("TimeSlot",timeslot);
                h.put("RoomNo",roomno);
                h.put("Limits",limit);
                h.put("Ins_ID",ins_id);
                h.put("CourseID",courseID);
                parent.getCollection("class_schedule").add(h);
               clearFields();
            }
          else
            {
            return;
            }
          // dispose();
           showclassprograms.fillTable();
         } //end of check 
       }
    
    //\\\\\\\\\\\Searching\\\\\\\\\\\\\\\\\\\\\\\\\\//
    void recSearch()
    {
    String searchRecID=JOptionPane.showInputDialog("TYPE ClassID OR SubjectCode to Search?");	 
    	 try
    	   {
    	   	Class.forName(driver);
    	   	connection=DriverManager.getConnection(url,username,password);
    	   	statement=connection.createStatement();
    	   	String query="SELECT ClassNo,SubjectCode,Title,Lec,Lab,Days,TimeSlot,RoomNo,Limits,Ins_ID,CourseID FROM class_schedule WHERE ClassNo='"+searchRecID+"'";
    	   	rs=statement.executeQuery(query);
    	    //\\\\loop to all records\\\\//
    	    rs.next();
    	    strClassNo=rs.getString(1);
    	    SubjectCode.setText(rs.getString(2));
    	    Title.setText(rs.getString(3));
    	    LecUnits.setText(rs.getString(4));
    	    LabUnits.setText(rs.getString(5));
    	    daysField.setText(rs.getString(6));
    	    CTime.setText(rs.getString(7));
    	    RoomNo.setText(rs.getString(8)); 
    	    Capacity.setText(rs.getString(9));
    	    Instructor.setText(rs.getString(10));
    	    String courseID=rs.getString(11);
    	    comboCourseID.setSelectedItem(courseID);
    	   }
    	   catch(ClassNotFoundException c)
    	   {
    	   	System.err.println(c);
    	   }
    	   catch(SQLException sql)
    	   {
    	  	JOptionPane.showMessageDialog(null,"ClassNo Not Found","Record Not Found",JOptionPane.WARNING_MESSAGE);
    	  //System.err.println(sql);
    	   }
    }
   
    //\\\\\\\\\\\\\\\Update Records\\\\\\\\\\\\\\\//
    void onUpdate()
    {
    int intchk=JOptionPane.showConfirmDialog(null,"Are you sure you want to save data?","Save",JOptionPane.YES_NO_OPTION);
      if(!complete())
      {
      JOptionPane.showMessageDialog(this, "ALL INFORMATION IS REQUIRED","DATA REQUIRED", JOptionPane.WARNING_MESSAGE);
      return;
     }
            String subjectcode=SubjectCode.getText();
            String title=Title.getText();
            String lecunits=LecUnits.getText();
            String labunits=LabUnits.getText();
            String days=daysField.getText();
            String timeslot=CTime.getText();
            String roomno=RoomNo.getText();
            String limit=Capacity.getText();
            String ins_id=Instructor.getText();
            courseID=comboCourseID.getSelectedItem().toString();
            String query="UPDATE class_schedule SET ClassNo='"+strClassNo+"',SubjectCode='"+subjectcode+"',Title='"+title+"',Lec='"+lecunits+"',Lab='"+labunits+"',Days='"+days+"',TimeSlot='"+timeslot+"',RoomNo='"+roomno+"',Limits='"+limit+"',Ins_ID='"+ins_id+"',CourseID='"+courseID+"' WHERE ClassNo='"+strClassNo+"'";
            int update = parent.database.executeUpdate(query);
            if(update ==0)								
            {
                Hashtable h = new Hashtable();
                h.put("ClassNo",strClassNo);
                h.put("SubjectCode",subjectcode);
                h.put("Title",title);
                h.put("Lec",lecunits);
                h.put("Lab",labunits);
                h.put("Days",days);
                h.put("TimeSlot",timeslot);
                h.put("RoomNo",roomno);
                h.put("Limit",limit);
                h.put("Ins_ID",ins_id);
                h.put("CourseID",courseID);
            parent.getCollection("class_schedule").add(h);
            JOptionPane.showMessageDialog(null,"Record#"+" "+strClassNo+"  Successfully updated...","Record Updated",JOptionPane.INFORMATION_MESSAGE);
    	    onEditCancel();
	        disabledFields();    
            }
        
          else
            {
            return;
            }
           //this.dispose();
         //  showclassprograms.fillTable();

    }
     //\\\\\\\\\clear fields\\\\\\\\\\//
    void clearFields()
    {
        SubjectCode.setText("");
    	Title.setText("");
    	CTime.setText("");
    	RoomNo.setText("");
    	LecUnits.setText("");
    	LabUnits.setText("");
    	Capacity.setText("");
    	Instructor.setText("");
    	daysField.setText("");
    }
     void enabledFields()
     {
       comboCourseID.setEditable(true);
       SubjectCode.setEditable(true);
       Title.setEditable(true);
       CTime.setEditable(true);
       RoomNo.setEditable(true);
       LecUnits.setEditable(true);
       LabUnits.setEditable(true);
       Capacity.setEditable(true);
       Instructor.setEditable(true);
       daysField.setEditable(true);
     }
     void enabledButtons()
     {
       comboCourseID.setEnabled(true);
       SubjectCode.setEnabled(true);
       Title.setEnabled(true);
       CTime.setEnabled(true);
       RoomNo.setEnabled(true);
       LecUnits.setEnabled(true);
       LabUnits.setEnabled(true);
       Capacity.setEnabled(true);
       Instructor.setEnabled(true);
       daysField.setEnabled(true);
     }
     void disabledFields()
     {
       comboCourseID.setEditable(false);
       SubjectCode.setEditable(false);
       Title.setEditable(false);
       CTime.setEditable(false);
       RoomNo.setEditable(false);
       LecUnits.setEditable(false);
       LabUnits.setEditable(false);
       Capacity.setEditable(false);
       Instructor.setEditable(false);
       daysField.setEditable(false);
     }
     void disableOnSearch()
     {
      cmdAdd.setEnabled(false);
      cmdSave.setEnabled(false);
     // cmdEdit.setEnabled(false);
      cmdClose.setEnabled(false);
    
     }
     void disableOnAdd()
     {
     	cmdSearch.setEnabled(false);
     	cmdEdit.setEnabled(false);
     	cmdClose.setEnabled(false);
        cmdSave.setEnabled(true);
     }
     void enableOnCancel()
     {
     	cmdSearch.setEnabled(true);
     	cmdEdit.setEnabled(true);
     	cmdClose.setEnabled(true);
        cmdAdd.setEnabled(true);
        cmdSave.setEnabled(false);
        
     }
     void onEdit()
     {
     	enabledFields();
     	cmdAdd.setVisible(false);
     	cmdSave.setVisible(false);
     	cmdSearch.setVisible(false);
     	cmdEdit.setVisible(false);
     	cmdClose.setVisible(false);
        cmdUpdate.setVisible(true);
     }
     void onEditCancel()
     {
     	cmdAdd.setVisible(true);
     	cmdSave.setVisible(true);
     	cmdSearch.setVisible(true);
     	cmdEdit.setVisible(true);
     	cmdClose.setVisible(true);
     	cmdUpdate.setVisible(false);
     }
     //\\\\\\\\\\\\\\trap blank fields\\\\\\\\\\\\\\\\\\\\\\\//
     
     boolean complete()
     {
     	//boolean a=ClassNo.getText().trim().length()==0;
     	boolean b=SubjectCode.getText().trim().length()==0;
     	boolean c=Title.getText().trim().length()==0;
     	boolean d=LecUnits.getText().trim().length()==0;
     	boolean e=LabUnits.getText().trim().length()==0;
     	boolean f=daysField.getText().trim().length()==0;
     	boolean g=RoomNo.getText().trim().length()==0;
     	boolean h=CTime.getText().trim().length()==0;
     	boolean i=Instructor.getText().trim().length()==0;
     	//boolean j=CourseID.getText().trim().length()==0;
     	
        return !(b | c | d | e | f | g | h | i );
     }
      
     public void actionPerformed(ActionEvent e)
     {
     	Object source=e.getSource();
        String act=e.getActionCommand();  
          if(source==cmdAdd)
          {
          	clearFields();
            enabledButtons();
            disableOnAdd();
            enabledFields();
          }
          if(source==cmdSave)
          {
          	onSave();
          }
          if(source==cmdSearch)
          {
           disableOnSearch();	 
           recSearch();        	
          }
          if(source==cmdCancel)
          {
          	enableOnCancel();
            disabledFields();
            onEditCancel();
          } 
          
          if(source==cmdEdit)
          {
          	onEdit();
          }
          if(source==cmdUpdate)
          {
          	onUpdate();
          }
          if(source==cmdClose)
          {
          	setVisible(false);
          }
     }
   
//     public static void main(String args[])
  //   {
    // new StudentSchedule();
     //} 
  
  }