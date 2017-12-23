
package mes.gui;

  import javax.swing.*;
  import java.awt.event.*;
  import java.awt.*;
  import javax.swing.JOptionPane.*;
  import javax.swing.ImageIcon.*;
  import java.sql.*;
  import mes.gui.*;
  
     public class ChangeUserID2 extends MESFrame implements ActionListener
     {
     MainFrame parent;
     LoginGuest loginguest;
     public JTextField userID,newUserID;
     public JPasswordField passID,newpassID;
     String url="jdbc:odbc:DBMaster";
     String driver="sun.jdbc.odbc.JdbcOdbcDriver";
     Connection connection;
     Statement statement;
     ResultSet rs;
     String chrs="Did you forget your username?.\n"
                 +"Please type your userID and password again\n"
                 +"Be sure to use the proper CAPSLOCK Key";
     String data="";
      public ChangeUserID2(MainFrame main,LoginGuest pLoginGuest)
      {
      super(340,230 ,"Login Guest", new String[] {"Update","Cancel"});
      setTitle("Change Account");
      parent=main;
      loginguest=pLoginGuest;
      init();
      setVisible(true);
      } 
      void init()
      {
	  MAINPANEL.setLayout(null);
      addButtonActionListener(this);	
	  
	  userID=new JTextField(10);
	  userID.addActionListener(this);
	  MAINPANEL.add(userID).setBounds(120,20,160,24);
	  
	  newUserID=new JTextField(10);
	  MAINPANEL.add(newUserID).setBounds(120,80,160,24);
	  newUserID.setEditable(false);
	 
	  passID=new JPasswordField(10);
	  MAINPANEL.add(passID).setBounds(120,50,160,24);
	  passID.setEditable(false);
	  
	   
	  newpassID=new JPasswordField(10);
	  MAINPANEL.add(newpassID).setBounds(120,110,160,24);
	  newpassID.setEditable(false);
	  
	  JLabel t1=new JLabel("Old UserName");
	  t1.setFont(new Font("Times New Roman",Font.PLAIN,13));
	  MAINPANEL.add(t1).setBounds(30,20,100,24);
	  
	  JLabel t2=new JLabel("Old Password");
	  t2.setFont(new Font("Times New Roman",Font.PLAIN,13));
	  MAINPANEL.add(t2).setBounds(30,50,100,24);
	  
	  JLabel t3=new JLabel("New Username");
	  t3.setFont(new Font("Times New Roman",Font.PLAIN,13));
	  MAINPANEL.add(t3).setBounds(30,80,100,24);
	  
	  JLabel t4=new JLabel("New Password");
	  t4.setFont(new Font("Times New Roman",Font.PLAIN,13));
	  MAINPANEL.add(t4).setBounds(30,110,100,24);
	  
	  }
      public void actionPerformed(ActionEvent e)
      {
      String action=e.getActionCommand();
       if(action.equals("Cancel"))
       {
       setVisible(false);	
       parent.introframe.setVisible(true);
       }
	   else if(action.equals("Update"))
	   {
	   updateAccounts();
	   }	
	   Object source=e.getSource();
	    if(source==userID)
	    {
	    loadUserInfo();
	    }
	  }
      void loadUserInfo()
      {
       try
          {
          Class.forName(driver);
          connection=DriverManager.getConnection(url);
          statement=connection.createStatement();
          String query="SELECT * FROM Authentication WHERE Authentication.UserID='"+userID.getText().toLowerCase()+"'";
          rs=statement.executeQuery(query);
          rs.next();
          userID.setText(rs.getString(1));
          passID.setText(rs.getString(2));
          enabledEntry();
          }
          catch(ClassNotFoundException c)
          {
          System.err.println(c);	
          }
          catch(SQLException sqle)
          {
          JOptionPane.showMessageDialog(null,chrs,"UserID Not Found",JOptionPane.WARNING_MESSAGE);
          userID.setText("");
          }
      }
      void updateAccounts()
      {
      	 if(! complete()){
                JOptionPane.showMessageDialog(this, "Fill the required information", "Data Required", JOptionPane.WARNING_MESSAGE);
            return;
            
        }
       try
          {
          Class.forName(driver);
          connection=DriverManager.getConnection(url);
          statement=connection.createStatement();
          String query="UPDATE Authentication SET UserID='"+newUserID.getText()+"',PassWord='"+newpassID.getText()+"' WHERE UserID='"+userID.getText()+"'";
          statement.executeUpdate(query);
          data="User ID: \t[ "+userID.getText()+" ]  has been successfully updated\n"
              +"You are advised to use the new User ID next time\n"
              +"you logon.Thanks!";
          JOptionPane.showMessageDialog(null,data,"UserID Updated",JOptionPane.WARNING_MESSAGE);															
          statement.close();
          connection.close();
          }
          catch(ClassNotFoundException c)
          {
          System.err.println(c);	
          }
          catch(SQLException sqle)
          {
          System.err.println(sqle);	
          }
      }
      void enabledEntry()
      {
      newUserID.setEditable(true);
      newUserID.requestFocus();
      newpassID.setEditable(true);
      }
        boolean complete(){
        boolean a = userID.getText().trim().length() == 0;
        boolean b = passID.getText().trim().length() == 0;
        boolean c = newUserID.getText().trim().length() == 0;
        boolean d = newpassID.getText().trim().length() == 0;
        
        return !(a || b || c || d);
  
    }
 } 