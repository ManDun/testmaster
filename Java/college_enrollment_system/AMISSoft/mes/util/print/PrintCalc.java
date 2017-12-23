
package mes.util.print;

import java.awt.print.PageFormat;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class PrintCalc{
    public boolean pageinfoCalculated = false;
    public int totalNumPages = 0;
    int splitColumns = 0;
    int splitRows = 0;
    int totalwidth = 0;
    int totalheight = 0;
    
    int pageHeight = 0;
    
    JTable ppTable;
    JTableHeader tableHeader;
    MainPrintFrame printframe;
    
    public PrintCalc(MainPrintFrame printframe){
        this.printframe = printframe;
        this.ppTable = printframe.t;
        
        tableHeader = printframe.t.getTableHeader();
    		//{{INIT_CONTROLS
		//}}
}
    
    // initialize ducument information
    public void getPageInfo (Graphics g, PageFormat pageFormat) {
        // reset values
        totalNumPages = 0;
        splitColumns = 0;
        splitRows = 0;
        ppTable.removeRowSelectionInterval(0, ppTable.getRowCount()-1);
        double oneRowHeight=(ppTable.getRowHeight()+
                      ppTable.getRowMargin());
                      
        int headerHeight  = tableHeader.getHeight();
        int fontHeight = g.getFontMetrics().getHeight();
        
        // compute for the total width and 
        // total height of the document
        //totalwidth  = ppTable.getTableHeader().getWidth();
        totalwidth  = ppTable.getColumnModel().getTotalColumnWidth();
        
        
        totalheight = (int)(printframe.l.getPreferredSize().getHeight() + headerHeight + (oneRowHeight * ppTable.getRowCount()));//ppTable.getHeight();
        
        printframe.l.setSize(totalwidth,printframe.l.getHeight());
        printframe.l.setPreferredSize(new java.awt.Dimension(totalwidth,printframe.l.getHeight()));
        
        
        //System.out.println("\n\nDocumant/Page Information:");
        //System.out.println("\t-Total Width \t= " + totalwidth + "\n\t-Total Height \t= "+totalheight);
        
        // compute for the page height
        // removing the header height per page 
        // and allocating the area for the 
        // page number using the font height
        pageHeight = (int)pageFormat.getImageableHeight() - fontHeight;
        //System.out.println("\t-Page Height \t= "+pageHeight);
        
        // compute for the number of columns 
        // of the document for printing
        splitColumns = (int)(totalwidth / pageFormat.getImageableWidth());
        if(totalwidth % pageFormat.getImageableWidth() > 0){
            splitColumns++;
        }
        
        // compute for the number of rows
        // of the document for printing
        splitRows = (int)(totalheight / pageHeight);
        if(totalheight % pageHeight > 0){
            splitRows  ++;
        }
        
        //System.out.println("\t-Split Rows \t= " + splitRows + "\n\t-Split Columns\t= "+splitColumns);
        
        // compute for the total number
        // of pages of the document
        totalNumPages = splitRows * splitColumns;
        
        
        //System.out.println("\t-totalNumPages \t= " + totalNumPages);
        
        //set to true so that there is no recalculation
        pageinfoCalculated = true;
    }

    public void printPagePart (Graphics2D g2, PageFormat pageFormat,int pageIndex) {
        int rowIndex = 0;
        int colIndex = 0;
        
        //compute for the row and column given the page index
        int temP = 0;
        for( ; rowIndex <= splitRows ; rowIndex ++){
            for( ; colIndex < splitColumns ; colIndex ++){
                temP++;
                
                if(temP > pageIndex){
                    break;
                }
            }
            
            if(temP > pageIndex) break;
            if(colIndex>=splitColumns-1) colIndex = 0;
        }
        
        //plot the page number
        String pagenum = (rowIndex + 1) + "-" + (colIndex + 1);
        if(splitColumns == 1) pagenum = (rowIndex + 1)+"";
        
        g2.drawString(pagenum,
            (int)((pageFormat.getWidth() - getStringWidth(g2,pagenum)) / 2),
            (int)(pageFormat.getImageableY()+pageFormat.getImageableHeight()-g2.getFontMetrics().getDescent()));// - g2.getFontMetrics().getHeight() + g2.getFontMetrics().getDescent()));
        
        
        int xlocreportfrom = (int)(colIndex * pageFormat.getImageableWidth());
        int xlocreportto = (int)((colIndex + 1) * pageFormat.getImageableWidth());
        int width = (int)pageFormat.getImageableWidth();
        
        int ylocreportto = (rowIndex + 1) * pageHeight;
        int ylocreportfrom = rowIndex * pageHeight;
        
        if(colIndex == (splitColumns - 1)){
            width = totalwidth - xlocreportfrom;
        }
        
        
        g2.translate(pageFormat.getImageableX() - xlocreportfrom ,
            pageFormat.getImageableY() - ylocreportfrom);
            
        //paint title
        g2.setClip(xlocreportfrom,ylocreportfrom,width,pageHeight);
        printframe.l.paint(g2);
        
        //paint table header
        int titleheight = (int)printframe.l.getHeight();
        g2.translate(0, titleheight);
        g2.setClip(xlocreportfrom,ylocreportfrom - titleheight,width,pageHeight);
        tableHeader.paint(g2);
        
        
        //paint table
        int header = (int)tableHeader.getHeight();
        titleheight = (int)printframe.l.getHeight();
        g2.translate(0,header);
        
        int clipArea = 0;
        
        
        if(ylocreportto > (titleheight + header) && ylocreportfrom >  (titleheight + header)){
            //g2.translate(0,-(header+titleheight));
            //g2.setClip(xlocreportfrom,ylocreportfrom - titleheight - header,width,pageHeight);
            clipArea  = pageHeight;
            g2.setClip(xlocreportfrom,ylocreportfrom-(header+titleheight),width,clipArea);
            
        }else if(ylocreportto > (titleheight + header) && ylocreportfrom < (titleheight + header)){
            
            //g2.setClip(xlocreportfrom,ylocreportfrom - titleheight - header,width,pageHeight);
            clipArea  = pageHeight - (titleheight + header);
            g2.setClip(xlocreportfrom,ylocreportfrom,width,clipArea);
        }
        
        //g2.setClip(xlocreportfrom,ylocreportfrom,width,clipArea);
        
        
        ppTable.paint(g2);    
        
    }     
    
    
    public int getStringWidth(java.awt.Graphics2D g2,String str){
        return g2.getFontMetrics().stringWidth(str);
    }
	//{{DECLARE_CONTROLS
	//}}
}