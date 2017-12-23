package mes.gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import mes.gui.*;
import mes.dbase.*;
import mes.util.*;


public class ClassSubjects extends MESFrame implements ActionListener
{
		MainFrame parent;
  	    showCourseList showcourselist;
  
	    JTextField SubjectCode,Description,Units,PreRequisite,CourseCode;
        JTextField YearField,Semester,SY;
		JLabel lblClassSubjects,lblSubjectCode,lblDescription,lblUnits,lblPreRequisite,lblCourseCode,lblSY;
		JLabel year,semester;
		JButton cmdSave,cmdAdd,cmdDelete,cmdSearch,cmdEdit,cmdCancel,cmdUpdate,cmdClose;
		JComboBox cboSem,cboYearLevel;
		
	public ClassSubjects(MainFrame main,showCourseList pshowcourselist)
  	{
		
		super(600,700,"", new String[] {"Add","Save"});
  		parent=main;
		showcourselist=pshowcourselist;
	//	setBounds(190,100,460,240);
		setVisible(true);
		setTitle("Class Subjects");
		Layout();
		
	}
		
		void Layout()
	   {
		
	   Container c=this.getContentPane();
		c.setLayout(null);
	
		//Label
	
		lblClassSubjects=new JLabel("Class Subjects");
		lblClassSubjects.setFont(new Font("Tahoma",Font.BOLD,14));
		c.add(lblClassSubjects).setBounds(180,1,175,30);
	
		lblSubjectCode=new JLabel("Subject Code");
		lblSubjectCode.setFont(new Font("Comic Sans",Font.BOLD,12));
		c.add(lblSubjectCode).setBounds(10,30,100,60);
		
		lblDescription=new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma",Font.BOLD,12));
		c.add(lblDescription).setBounds(10,60,100,60);
		
		lblPreRequisite=new JLabel("Pre-Requisite");
		lblPreRequisite.setFont(new Font("Comic Sans",Font.BOLD,12));
		c.add(lblPreRequisite).setBounds(10,90,100,60);
		
		lblUnits=new JLabel("Units");
		lblUnits.setFont(new Font("Comic Sans",Font.BOLD,12));
		c.add(lblUnits).setBounds(205,30,100,60);
	
        lblCourseCode=new JLabel("Cur ID");
		lblCourseCode.setFont(new Font("Comic Sans",Font.BOLD,12));
		c.add(lblCourseCode).setBounds(205,60,100,60);
	
	    year=new JLabel("Year Level");
	    year.setFont(new Font("Comic Sans",Font.BOLD,12));
	    c.add(year).setBounds(205,90,100,60);
	
	    lblSY=new JLabel("School Year");
	    lblSY.setFont(new Font("Comic Sans",Font.BOLD,12));
	    c.add(lblSY).setBounds(205,120,100,60);
	
	//    YearField=new JTextField();
	  //  c.add(YearField).setBounds(280,110,100,23);
	    cboYearLevel=new JComboBox();
	    cboYearLevel.addItem("1st");
	    cboYearLevel.addItem("2nd");
	    c.add(cboYearLevel).setBounds(280,110,100,23);
	  
	    SY=new JTextField();
	    SY.setFont(new Font("Comic Sans",Font.BOLD,12));
	    c.add(SY).setBounds(280,140,100,23);
	  
	    semester=new JLabel("Semester");
	    semester.setFont(new Font("Comic Sans",Font.BOLD,12));
	    c.add(semester).setBounds(10,120,100,60);	    
	    
	    //Semester=new JTextField();
	    //c.add(Semester).setBounds(90,140,100,23);
	 
	    cboSem=new JComboBox();
	    cboSem.addItem("1st Sem");
	    cboSem.addItem("2nd Sem");
	    c.add(cboSem).setBounds(90,140,100,23);
	     
	//textfields
	
	    SubjectCode=new JTextField();
	    //SubjectCode.setFont()
	    c.add(SubjectCode).setBounds(90,50,100,23);
	
	    Description=new JTextField();
	    c.add(Description).setBounds(90,80,100,23);
	   
	    PreRequisite=new JTextField();
	    c.add(PreRequisite).setBounds(90,110,100,23);
	
        Units=new JTextField();
        c.add(Units).setBounds(280,50,100,23);
        
        CourseCode=new JTextField();
        c.add(CourseCode).setBounds(280,80,100,23);	
	
	  //Buttons
	  
	    cmdAdd=new JButton("Add");
	    c.add(cmdAdd).setBounds(5,240,70,23);
	    //cmdAdd.addActionListener(this);
	    
	    cmdSave=new JButton("Save");
	    c.add(cmdSave).setBounds(75,240,75,23);
	    //cmdAdd
	    cmdSearch=new JButton("Search");
	    c.add(cmdSearch).setBounds(150,240,75,23);
	    
	    cmdEdit=new JButton("Edit");
	    c.add(cmdEdit).setBounds(225,240,75,23);
	    
	    cmdCancel=new JButton("Cancel");
	   // c.add(cmdCancel).setBounds(300,185,75,23);
	   // cmdCancel.setVisible(false);
		
		cmdDelete=new JButton("Delete");
	    c.add(cmdDelete).setBounds(300,240,75,23);
	  
	    cmdClose=new JButton("Close");
	    c.add(cmdClose).setBounds(375,240,70,23);
	    cmdClose.addActionListener(this);
	    
		
		}

		
		public void actionPerformed(ActionEvent e)
		{
		 
		  Object source=e.getSource();
		   if(source==cmdClose)
		   {
		   	 setVisible(false);
		   }
		  	
		}
		
//		public static void main (String args[])
//		{
//			new ClassSubjects();
//		}
		
	
}