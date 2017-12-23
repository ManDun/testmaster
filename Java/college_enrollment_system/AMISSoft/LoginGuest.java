
package mes.gui;

  import javax.swing.*;
  import java.awt.event.*;
  import java.awt.*;
  import javax.swing.JOptionPane.*;
  import javax.swing.ImageIcon.*;
  import java.sql.*;
  
  import mes.gui.*;
  
     public class LoginGuest extends MESFrame implements ActionListener
     {
     MainFrame parent;
     IntroFrame intro;
     public JTextField userID;
     public JPasswordField passID;
    
     String url="jdbc:mysql://192.168.1.37/dbmaster";
 	 String driver ="org.gjt.mm.mysql.Driver";
    
     String username="root";
     String password="rose";
  
    
     Connection connection;
     Statement statement;
     ResultSet rs;
     String chrs="Did you forget your password?.\n"
                 +"Please type your userID and password again\n"
                 +"Be sure to use the proper CAPSLOCK Key";
      public LoginGuest(MainFrame main,IntroFrame introFrame)
      {
      super(300,170 ,"Login Guest", new String[] {"Change Account","Login","Cancel"});
      setTitle("Login");
      parent=main;
      intro=introFrame;
      init();
      setVisible(true);
      } 
      void init()
      {
	  MAINPANEL.setLayout(null);
      addButtonActionListener(this);	
	  
	  userID=new JTextField(10);
	  MAINPANEL.add(userID).setBounds(90,20,160,24);
	  
	  
	  passID=new JPasswordField(10);
	  MAINPANEL.add(passID).setBounds(90,50,160,24);
	  
	  
	  JLabel t1=new JLabel("UserName");
	  t1.setFont(new Font("Times New Roman",Font.PLAIN,13));
	  MAINPANEL.add(t1).setBounds(30,20,100,24);
	  
	  JLabel t2=new JLabel("Password");
	  t2.setFont(new Font("Times New Roman",Font.PLAIN,13));
	  MAINPANEL.add(t2).setBounds(30,50,100,24);
	  
	  }
      public void actionPerformed(ActionEvent e)
      {
      String action=e.getActionCommand();
       if(action.equals("Cancel"))
       {
       setVisible(false);	
       parent.introframe.setVisible(true);
       }
	   else if(action.equals("Login"))
	   {
	    logAdminStud();
	   }	
	   else if(action.equals("Change Account"))
	   {
	   parent.addFrame3_part2(new ChangeUserID2(parent,this));
	   setVisible(false);
	   }
	  }
      void logAdminStud()
      {
       try
          {
          Class.forName(driver);
          connection=DriverManager.getConnection(url,username,password);
          statement=connection.createStatement();
          String query="SELECT * FROM Authentication WHERE Authentication.UserID='"+userID.getText().toLowerCase()+"' and PassWord='"+passID.getText().toLowerCase()+"'";
          rs=statement.executeQuery(query);
          rs.next();
          boolean found=false;//===========ASSUME THAT THERE IS NO LOGIN====//
          userID.setText(rs.getString(1));
          passID.setText(rs.getString(2));
          String userid=userID.getText();
          String passid=passID.getText();
          String accesstype=rs.getString(3);
          found=rs.next();
          found=true;
            if((found) && accesstype.equals("Administrator")||accesstype.equals("administrator"))
            {
            setVisible(false);
            intro.setVisible(false);
            parent.enabledAdmin();//passing parameters
           // parent.enabledSignOut(true);
            }
            else if(found)
            {
            setVisible(false);
            intro.setVisible(false);
            parent.enabledGuest();
           // parent.enabledSignOut(true);
            }
         }
         catch(ClassNotFoundException c)
         {
         System.err.println(c);	
         }
         catch(SQLException sqle)
         {
       
         JOptionPane.showMessageDialog(null,chrs,"UserID Not Found",JOptionPane.WARNING_MESSAGE);
         }
      }
 } 