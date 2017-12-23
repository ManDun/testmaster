package mes.util.print;

public class PreviewReport extends javax.swing.JPanel{
    
    /*static{
        try{
        javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){}
    }*/
    int pageIndex = 0;
    double scale = 1.0;
    MainPrintFrame printframe;
    public PrintCalc printCalc;
    public PreviewReport(MainPrintFrame printframe){
        this.printframe = printframe;
        
           init();
    		//{{INIT_CONTROLS
		//}}
}
    
    void init(){
        printCalc = new PrintCalc(printframe);
        //javax.swing.RepaintManager.currentManager(this).setDoubleBufferingEnabled(false);
        addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseReleased(java.awt.event.MouseEvent e){
                        
                if(e.isMetaDown()) scale = scale-0.05;
                else scale = scale+0.05;
                        
                repaintParent();
            }
            });
                    
            setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
    }
    
    public void repaintParent(){
        java.awt.print.PageFormat pageFormat = printframe.pageFormat;
        setSize(new java.awt.Dimension((int)((pageFormat.getWidth()+30)*scale),(int)((pageFormat.getHeight()+30)*scale)));   
        setPreferredSize(new java.awt.Dimension((int)((pageFormat.getWidth()+30)*scale),(int)((pageFormat.getHeight()+30)*scale)));   
        
        printframe.reSizeView(new java.awt.Dimension(this.getWidth(),this.getHeight()));   
                
        printframe.s.repaint();
    }
    
    public void paint(java.awt.Graphics g){
        
        
        java.awt.Graphics2D g2 = (java.awt.Graphics2D)g;
        
        
        g2.clearRect(0, 0, (int)printframe.s.getViewport().getViewSize().getWidth(), (int)printframe.s.getViewport().getViewSize().getHeight());
        
        java.awt.geom.AffineTransform at = new java.awt.geom.AffineTransform();
	    //at.translate(5, 5);
	    at.scale(scale, scale);
	    g2.transform(at);
	    
        if(pageIndex >= printCalc.totalNumPages) pageIndex = printCalc.totalNumPages - 1;
        if(pageIndex < 0) pageIndex = 0;
        
        
        //g2.translate(5, 5);
        g2.setColor(java.awt.Color.white);
        
        g2.fill3DRect(0, 0, (int)printframe.pageFormat.getWidth(),(int)printframe.pageFormat.getHeight(),true);
        
        g2.setColor(java.awt.Color.white);
        
        g2.fillRect((int)printframe.pageFormat.getImageableX(),
            (int)printframe.pageFormat.getImageableY(), 
            (int)printframe.pageFormat.getImageableWidth(),
            (int)printframe.pageFormat.getImageableHeight());
        
        g2.setColor(java.awt.Color.black);
        if(pageIndex == 0 ){
          //  g2.drawImage(payroll.images.PayrollImages.CDRLOGO.getImage(), 20, 20, payroll.images.PayrollImages.CDRLOGO.getImageObserver());
        }
        
        //if(!printCalc.pageinfoCalculated)
        
        g2.scale(0.99,0.99);
            printCalc.getPageInfo(g, printframe.pageFormat);
        printCalc.printPagePart(g2, printframe.pageFormat,pageIndex);
        
        
    }
    
    public void zoomIn(){
        scale = scale+0.05;
        repaintParent();
    }
    
    public void zoomOut(){
        scale = scale-0.05;
        repaintParent();
    }
    
    public void restoreZoom(){
        scale = 1.0;
        repaintParent();
    }
	//{{DECLARE_CONTROLS
	//}}
}