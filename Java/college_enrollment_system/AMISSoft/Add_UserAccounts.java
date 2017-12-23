
package mes.gui;

  import javax.swing.*;
  import java.awt.event.*;
  import java.awt.*;
  import javax.swing.JOptionPane.*;
  import javax.swing.ImageIcon.*;
  
  import java.sql.*;
  import mes.gui.*;
  
     public class Add_UserAccounts extends MESFrame implements ActionListener
     {
      MainFrame parent;
      LoginAdmin admin;
      String foundUserID;
      String strUserName;
      JTextField UserName;
      JPasswordField PassWord;
      JComboBox cboAccessType;
    
      String url="jdbc:mysql://192.168.1.37/dbmaster";
	  String driver ="org.gjt.mm.mysql.Driver";
    
      String username="root";
      String password="rose";
   
      Connection connection;
      Statement statement;
      ResultSet rs;
      
      public Add_UserAccounts(MainFrame main)
      {
      super(300, 170 , "Select Mode", new String[] {"Clear","Save","Close"} );
      setTitle("User & Password");
      parent=main;
      init();
      setVisible(true);
      } 
      void init()
      {
	  MAINPANEL.setLayout(null);
      addButtonActionListener(this);	
	  ImageIcon image = new ImageIcon("chatters.gif");
      JLabel imageLabel = new JLabel("Provide your \"USERNAME & PASSWORD\" then LOGIN.",image,SwingConstants.CENTER);
	  imageLabel.setIcon(image);
	  MAINPANEL.add(imageLabel);
      imageLabel.setBounds(10,5,300,50);
	  
	  JLabel t1=new JLabel("User Name",JLabel.CENTER);
	  MAINPANEL.add(t1).setBounds(20,60,130,23);
	  UserName=new JTextField();
	  MAINPANEL.add(UserName).setBounds(120,60,130,23); 
	  
	  
	  JLabel t2=new JLabel("Password",JLabel.CENTER);
	  MAINPANEL.add(t2).setBounds(20,90,130,23);
	  
	  PassWord=new JPasswordField(20);
	  MAINPANEL.add(PassWord).setBounds(120,90,130,23); 
	  
	  JLabel t3=new JLabel("Access Type",JLabel.CENTER);
	  MAINPANEL.add(t3).setBounds(20,120,100,23);
	  
	  cboAccessType=new JComboBox();
	  cboAccessType.addItem("Administrator");
	  cboAccessType.addItem("Guest");
	  MAINPANEL.add(cboAccessType).setBounds(120,120,130,23); 
	  }
	  void notShow(boolean b)
	  {
	  this.setVisible(false);
	  }
      void doSave()
      {
      	if(!complete())
      	{
      		JOptionPane.showMessageDialog(null,"One of the Field is left empty","Empty",JOptionPane.WARNING_MESSAGE);
      	   return;
      	}
      	
      	try 
      	  {
      	  	Class.forName(driver);
      	  	connection=DriverManager.getConnection(url,username,password);
      	  	statement=connection.createStatement();
      	  	String Type=cboAccessType.getSelectedItem().toString();
      	  	String query="INSERT INTO Authentication(UserID,PassWord,AccessType)VALUES('"+UserName.getText()+"','"+PassWord.getText()+"','"+Type+"')";
      	    statement.executeUpdate(query);
      	    JOptionPane.showMessageDialog(null,"Record Successfully Added","Confirmed Intention",JOptionPane.INFORMATION_MESSAGE);
      	    UserName.setText("");
      	    UserName.requestFocus();
      	    PassWord.setText("");
      	  }
      	  catch(ClassNotFoundException c)
      	  {
      	  	System.err.println(c);
      	  }
      	  catch(SQLException sql)
      	  {
          JOptionPane.showMessageDialog(null,"UserName already taken,please try again","Error",JOptionPane.WARNING_MESSAGE);
          }
      }
      void clear()
      {
       UserName.setText("");
       UserName.requestFocus();
       PassWord.setText("");
      }
      boolean complete()
      {
      boolean a = UserName.getText().trim().length() == 0;
      boolean b = PassWord.getText().trim().length() == 0;
      
      return !(a | b);
      }
      public void actionPerformed(ActionEvent e)
      {
      String action=e.getActionCommand();
       if(action.equals("EXIT"))
       {
       	 int out=JOptionPane.showConfirmDialog(this,"Are sure you want to sign-out?","Sign-Out",JOptionPane.YES_NO_OPTION);
       	 if(out==0)
         {
         System.exit(0);
         }
       }//end of if
      else if(action.equals("Clear"))
      {
      	clear();
      }
      if(action.equals("Save"))
      {
        doSave();
      }
      else if(action.equals("Close"))
      {
      	dispose();
      }
   }
 
 } 