
package mes.gui;

  import javax.swing.*;
  import java.awt.event.*;
  import java.awt.*;
  import javax.swing.JOptionPane.*;
  import javax.swing.ImageIcon.*;
  
  import mes.gui.*;
  
     public class IntroFrame extends MESFrame implements ActionListener
     {
      MainFrame parent;
      LoginAdmin admin;
      public IntroFrame(MainFrame main)
      {
      super(300, 170 , "Select Mode", new String[] {"ADMINISTRATOR","GUEST","EXIT"} );
      setTitle("COMPUTER LOG-IN");
      parent=main;
      init();
      setVisible(true);
      } 
      void init()
      {
	  MAINPANEL.setLayout(null);
      addButtonActionListener(this);	
	  ImageIcon image = new ImageIcon("LoginIcon.gif");
      JLabel imageLabel = new JLabel("Select the \"USER TYPE\" then LOGIN.",image,SwingConstants.CENTER);
	  imageLabel.setIcon(image);
	  MAINPANEL.add(imageLabel);
      imageLabel.setBounds(10,5,300,90);
	  }
	  void notShow(boolean b)
	  {
	  this.setVisible(false);
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
      else if(action.equals("ADMINISTRATOR"))
      {
      parent.addFrame3(new LoginAdmin(parent,this));
      parent.introframe.setVisible(false);
      }
      else if(action.equals("GUEST"))
      {
      parent.addFrame3(new LoginGuest(parent,this));
      parent.introframe.setVisible(false);
      }
    }
 } 