
//module:subjects.java

import javax.swing.JOptionPane;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
 
  public class subjects extends JFrame implements ActionListener
  {
   JTextField SubjectID,Title,Prerequisite,NoofUnits,Semester,CourseID,YearLevel;
   
     public subjects()
     {
     setBounds(200,140,400,290);
     setVisible(true);	
     init();	
     }
  
     void init()
     {
     	Container c=this.getContentPane();
     	c.setLayout(null);
     	
     	JLabel t1=new JLabel("Subjects Offer");
     	c.add(t1).setBounds(145,10,150,24);
     	t1.setFont(new Font("Times New Roman",Font.BOLD,16));
   
        JLabel t2=new JLabel("SubjectID");
        c.add(t2).setBounds(20,45,100,24);
     	t2.setFont(new Font("Times New Roman",Font.PLAIN,14));
     	
     }
     public void actionPerformed(ActionEvent e)
     {
     	Object source=e.getSource();
     }
    
     public static void main(String args[])
     {
     	new subjects();
     } 
  
  }