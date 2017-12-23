package mes.gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import mes.dbase.*;
import mes.util.*;
import mes.gui.*;
import mes.util.*;
import mes.util.print.*;

public class showFacultyList extends MESFrame implements ActionListener{

    MainFrame parent;
    MESTable table;
    public showFacultyList(MainFrame main)
    {
        super(700,300,"",new String[] {"Print","Faculty Loading","Faculty MasterFile","Close"}  );
        setTitle("Academic Personnel File");
        parent = main;
        init();
        //setSize(700,300);
        //setBounds(10,10,500,400);
        setVisible(true);
      } 
    void init(){
        addButtonActionListener(this);
        
        MAINPANEL.setLayout(new BorderLayout());
        
        table = new MESTable("",new String[] {"EmployeeNo","FirstName","LastName","Address","SSSNo","TINNo","ContactNo"});
        table.setColumnSize(new int[] {100,120,220,240,100,100,140});
        MAINPANEL.add(table);
        table.useDefaultRenderer(new int[] {-1});
        table.getTable().addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent event){
                String select =  table.getValueAtSelectedRow(2);
                if(select == null){
                    return;
                }else{
                    BUTTONS[0].setEnabled(true);
                    select.trim();
                }
            }
        
        });
        
        //BUTTONS[0].setEnabled(false);
        fillTable();
    }
    void fillTable()
    {
        DataCollection col = parent.getCollection("FacultyMaster");
        table.getTableModel().setRowCount(0);
        
        for(int i = 0; i < col.size(); i++){
            Hashtable data  = (Hashtable)col.get(i);
            Object[] row = { new JLabel(data.get("EmployeeNo")+""),
                             new JLabel(data.get("FirstName")+""),
                             new JLabel(data.get("LastName")+""),
                             new JLabel(data.get("Address")+""),
                             new JLabel(data.get("SSSNo")+""),
                             new JLabel(data.get("TINNo")+""),
                             new JLabel(data.get("ContactNo")+""),
                        };
            table.getTableModel().addRow(row);
        }
    }
    public void actionPerformed(ActionEvent event )
    {
        String act = event.getActionCommand();
        if(act.equals("Close"))
        {
        dispose();	
        }
        else if(act.equals("Modify"))
        {
            if(table.getTable().getSelectedRow() != -1)
            {
      
            }
        }
        else if(act.equals("Faculty MasterFile"))
        {
        parent.addFrame(new FacultyMaster(parent,this));
        }
        else if(act.equals("Faculty Loading"))
        {
           if(table.getTable().getSelectedRow()!=-1)
           {
           	parent.addFrame(new FacultyChecking(parent,this,table.getValueAtSelectedRow(0),table.getValueAtSelectedRow(1),table.getValueAtSelectedRow(2),table.getValueAtSelectedRow(3),table.getValueAtSelectedRow(4),table.getValueAtSelectedRow(5) ) );
           }  	
        }
        else if(act.equals("Print"))
         {
         JLabel titleLabel = new JLabel(createGroundMessage());
         titleLabel.setBounds(20, 3, getSize().width-30, 40);
         new PrintPreviewFrame(table.getTable(),titleLabel,"Student Master List");
         }
         else{
                JOptionPane.showMessageDialog(parent, "Select the examination.", "Update Questionnaire", JOptionPane.WARNING_MESSAGE);
            }
 }       
  private String createGroundMessage(){
        String title="Student Master List";
        String html =   "<html>"+

                        "<head>"+
                        "</head>"+
                        "<body>"+
                       // "<p align=left><b><font size=3>"+table.getValueAtSelectedRow(3)+"</font></b></p>"+
                       // "<p align=left><font size=2>"+table.getValueAtSelectedRow(0)+" - "+table.getValueAtSelectedRow(1)+"</p>"+
                       // "<p align=left><font size=2>"+table.getValueAtSelectedRow(0)+ "</p>"+
                        "<p align=left><font size=3>"+title+"</p>"+
                        "</body>"+
                        "</html>";
                        
        

        return  html ;
    }    
   /*
    void edit(){
        int del = JOptionPane.showConfirmDialog(this, "Are you sure you want to "+BUTTONS[0].getText().toLowerCase()+" this examination?", "Options", 
                                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
                                        );
        if(del ==0 ){    
            String sno = table.getValueAtSelectedRow(0);
            String sql = "UPDATE EXAM SET STATUS = '"+BUTTONS[0].getText()+"'WHERE EXAM_NO = '"+sno+"'";
            int delete = parent.database.executeUpdate(sql);
            
            if(delete == 0){
                DataCollection col = parent.getCollection("ActivityMaster");
                Hashtable empInfo = col.getHashtable("exam_no", sno);
                empInfo.put("status", BUTTONS[0].getText());
               //fillTable();
            }
        }
        
    }
    void delete(){
        if(table.getTable().getSelectedRow() == -1){
            JOptionPane.showMessageDialog(parent, "Select Activity to Delete.", "Delete Activity", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int del = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete Activity?", "Delete", 
                                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
                                        );
        if(del ==0 ){    
            String actCode = table.getValueAtSelectedRow(0);
            String sql = "DELETE FROM ActivityMaster WHERE ActCode = '"+actCode+"'";
            int delete = parent.database.executeUpdate(sql);
           // parent.database.executeUpdate("DELETE FROM QUESTION WHERE EXAM_NO = '"+sno+"'");
            if(delete == 0){
                DataCollection col = parent.getCollection("ActivityMaster");
                Hashtable empInfo = col.getHashtable("ActCode",actCode);
                col.remove(empInfo);
                //fillTable();
            }
        }
        
    }*/
    
}