package mes.util.print;

import mes.util.*;
import mes.util.print.*;

public class MainPrintFrame extends javax.swing.JPanel{
    
    public java.awt.print.PageFormat pageFormat;
    public javax.swing.JTable t;
    public javax.swing.JLabel l;
    public javax.swing.JScrollPane s;
    public javax.swing.JPanel panel;
    
    String title = "Print Panel";
    
    PreviewReport previewReport;
    PrintToolbar printToolbar;
    java.awt.print.PrinterJob printJob;
    PrintReport printReport;
    
    public MainPrintFrame(javax.swing.JTable t, javax.swing.JLabel l){        
        loadPageAttributes();
        
        initFrame();
        initPage(t,l);
        //this.setBorder(new javax.swing.border.LineBorder(payroll.util.SystemDefaultSettings.BACKGROUND.darker() ));
        
    }
    
   
    
    public void setPrintSize(int w, int h){
        java.awt.print.Paper papel = new java.awt.print.Paper();
        papel.setSize(w,h);
        papel.setImageableArea(18,18,(w-36),(h-36));
        pageFormat.setPaper(papel);
        repaint();
        
    }
    
    public void loadPageAttributes(){
        java.awt.print.Paper papel = new java.awt.print.Paper();
        
        //short
        papel.setSize(612,792);
        papel.setImageableArea(18,38,576,762);
        
        //long
        /*papel.setSize(612,1007);
        papel.setImageableArea(18,18,576,977);9*/
        
        /*papel.setSize(612,230);
        papel.setImageableArea(18,18,576,200);*/
        
        pageFormat = new java.awt.print.PageFormat();
        pageFormat.setPaper(papel);
        pageFormat.setOrientation(java.awt.print.PageFormat.PORTRAIT);
    }
    
    public void setUpPrinter(){
	    try{
	        if(printJob==null) printJob = java.awt.print.PrinterJob.getPrinterJob ();
    	    
	        if(pageFormat == null){
	            
	            pageFormat = printJob.defaultPage();
            }else{
	            pageFormat = printJob.defaultPage(pageFormat);

	        }

            pageFormat = printJob.pageDialog(pageFormat);
    	    
	        if(this.previewReport !=null){
	            java.awt.Dimension d = new java.awt.Dimension((int)pageFormat.getWidth(),(int)pageFormat.getHeight());
                previewReport.setPreferredSize(d);
	            previewReport.repaint();
	        }
	        
	    }catch(Exception e){
	        System.out.println("Error : Exception caught in MainPrintFrame@setUpPrinter : " + e.getMessage());
	    }
	}
    
    public void print(){
        printReport = new PrintReport(this);
        printReport = null;
    }
    
    void initFrame(){
        setLayout(new java.awt.BorderLayout());
    }
    
    void initPage(javax.swing.JTable tabledata, javax.swing.JLabel ll){
        
        /*tabledata.setRowSelectionInterval(-1, -1);
        tabledata.setColumnSelectionInterval(-1, -1);*/
        t = tabledata;
        
        //t.getTableHeader().setBorder(new javax.swing.border.LineBorder(payroll.util.SystemDefaultSettings.BACKGROUND.darker() ));
                
        l = new javax.swing.JLabel();
        l.setPreferredSize(new java.awt.Dimension (t.getTableHeader().getWidth(),ll.getHeight()));
        l.setSize(new java.awt.Dimension (tabledata.getTableHeader().getWidth(),ll.getHeight()));
        
        l.setIcon(ll.getIcon());
        //l.setIcon(payroll.images.PayrollImages.CDRLOGO);
        l.setText(ll.getText());
        l.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        
        printToolbar = new PrintToolbar(this);
        previewReport = new PreviewReport(this);
        
        s = new javax.swing.JScrollPane(previewReport);
       // s.setBackground(new java.awt.Color(58,110,165));
        s.setBackground(java.awt.Color.white);
        
        add(printToolbar,java.awt.BorderLayout.NORTH);
        add(s,java.awt.BorderLayout.CENTER);
    }
    
    public void refresh(javax.swing.JTable t,javax.swing.JLabel l){
        this.l.setText(l.getText());
        this.l.setIcon(l.getIcon());
        
        java.awt.Dimension d = new java.awt.Dimension((int)pageFormat.getWidth(),(int)pageFormat.getHeight());
        previewReport.setPreferredSize(d);
        previewReport.repaint();
    }
    
    public String getTitle(){
        return title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public javax.swing.JTable getTable(){
        return t;
    }
    
    public javax.swing.JLabel getTitleLabel(){
        return l;
    }
    
    public void reSizeView(java.awt.Dimension d){
        s.getViewport().setViewSize(d);
        s.revalidate();
       
    }
    
    
}