
package mes.util.print;

public class PrintReport implements java.awt.print.Printable{
    public PrintCalc printCalc;
    MainPrintFrame printframe;
    
    public PrintReport(MainPrintFrame printframe){
        this.printframe = printframe;
        
        printCalc = new PrintCalc(printframe);
        
        java.awt.print.PrinterJob printJob = printframe.printJob;
        if(printJob == null) printJob = java.awt.print.PrinterJob.getPrinterJob ();
            
        java.awt.print.PageFormat pageFormat = printframe.pageFormat;
            
        printJob.setJobName(printframe.getTitle());
        printJob.setPrintable(this, pageFormat);
        
        if(printJob.printDialog()){
            //printJob.defaultPage(pageFormat);
            try{
                printJob.print();
            }catch(java.awt.print.PrinterException pe){
                javax.swing.JOptionPane.showMessageDialog(printframe,pe.getMessage() , "Print error - CDR", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    		//{{INIT_CONTROLS
		//}}
}

    public int print(java.awt.Graphics g, java.awt.print.PageFormat pageFormat, int pageIndex){
        java.awt.Graphics2D g2 = (java.awt.Graphics2D)g;
        
        if(pageIndex == 0 ){
           // g2.drawImage(payroll.images.PayrollImages.CDRLOGO.getImage(), 20, 20, payroll.images.PayrollImages.CDRLOGO.getImageObserver());
        }
        
        g2.setColor(java.awt.Color.black);
        g2.scale(0.99,0.99);
        
        if(!printCalc.pageinfoCalculated) 
            printCalc.getPageInfo(g2, printframe.pageFormat);
            
        printCalc.printPagePart(g2, printframe.pageFormat,pageIndex);
        
        if(pageIndex < printCalc.totalNumPages){
            //System.out.println("*** Printing page " + pageIndex + " ***");
            return this.PAGE_EXISTS;
        }
        
        //System.out.println("**** end printing...!!! ***");
        return this.NO_SUCH_PAGE;
    }

	//{{DECLARE_CONTROLS
	//}}
}