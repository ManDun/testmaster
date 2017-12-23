package mes.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.util.*;

import mes.util.print.*;

public class PrintPreviewFrame extends  JFrame {
    
    MainPrintFrame printPanel;
    
    JTable dataTable;
    JLabel titleLabel;
    String printTitle;  
    
    public PrintPreviewFrame( JTable dataTable , JLabel title , String printTitle){
        super("Printing "+printTitle);
        super.setTitle("Printing "+printTitle);
        this.dataTable = dataTable;
        this.titleLabel= title;
        this.setResizable(true);
        this.printTitle = printTitle;
        init();
        printPanel.setPrintSize(600, 550);
        setVisible(true);
        
    }
    
    
    void init(){
        
        this.setSize(650,465);
        this.setLocation(30,30);
        
        printPanel = new MainPrintFrame(dataTable , titleLabel);
        printPanel.setTitle(printTitle);
   
        setContentPane(printPanel);
        
        printPanel.refresh(dataTable , titleLabel);
        
    }
}
