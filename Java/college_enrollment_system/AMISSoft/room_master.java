
/**
 *java application program 
 *written in SDK1.5.0
 *author:Bernardo D. Tongcos Jr.
 *module name:room_master.java
 **/
 
 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.JOptionPane; 
 import javax.swing.border.*;
 
  /*--class--*/
  
   public class room_master extends JFrame implements ActionListener
   {
   	 /*--cdm--*/
   	 JButton cmdAdd,cmdSave,cmdSearch,cmdEdit,cmdDelete,cmdClose;
   	 JTextField roomField,locationField,capacityField;
   	
   	 public room_master()
   	 {
   	  setBounds(200,140,460,320);
   	  setVisible(true);
   	  setTitle("Room Master Entry Form");
   	  guiConstructor();
   	 
   	 }
  
     void  guiConstructor()
     {
     
       Container c=this.getContentPane();
       c.setLayout(null);
     
       JLabel t1=new JLabel("Room Master");
       c.add(t1).setBounds(180,30,100,24);
       t1.setFont(new Font("Times New Roman",Font.BOLD,16));
       
       JLabel labelLocation=new JLabel("Location");
       c.add(labelLocation).setBounds(20,150,100,23);       
 
      
       JLabel labelCapacity=new JLabel("Capacity");
       c.add(labelCapacity).setBounds(20,120,100,23);       
 
      
       capacityField=new JTextField();
       c.add(capacityField).setBounds(70,120,100,23);       
       
       JLabel labelRoom=new JLabel("Room#:");
       c.add(labelRoom).setBounds(20,90,100,23);
     
       roomField=new JTextField();
       c.add(roomField).setBounds(70,90,100,23);       
       
       cmdAdd=new JButton("Add");
       c.add(cmdAdd).setBounds(20,260,70,23);
       cmdAdd.setFont(new Font("Times New Roman",Font.PLAIN,12));
        
       cmdSave=new JButton("Save");
       c.add(cmdSave).setBounds(90,260,70,23);
       cmdSave.setFont(new Font("Times New Roman",Font.PLAIN,12));
       
       cmdSearch=new JButton("Search");
       c.add(cmdSearch).setBounds(160,260,70,23);
       cmdSearch.setFont(new Font("Times New Roman",Font.PLAIN,12));
       
       cmdEdit=new JButton("Edit");
       c.add(cmdEdit).setBounds(230,260,70,23);
       cmdEdit.setFont(new Font("Times New Roman",Font.PLAIN,12));
      
       cmdDelete=new JButton("Delete");
       c.add(cmdDelete).setBounds(300,260,70,23);
       cmdDelete.setFont(new Font("Times New Roman",Font.PLAIN,12));
       
       cmdClose=new JButton("Close");
       c.add(cmdClose).setBounds(370,260,70,23);
       cmdClose.setFont(new Font("Times New Roman",Font.PLAIN,12));
       
     
       JLabel borderLabel=new JLabel("");
       c.add(borderLabel).setBounds(5,30,440,300);
       borderLabel.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
      
       }
     
     public void actionPerformed(ActionEvent e)
     {
     	Object source=e.getSource();
     	
     }
     
     public static void main(String args[])
     {
     	new room_master();
     }
     
 }    
      