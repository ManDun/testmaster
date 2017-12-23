

import javax.swing.*;
import java.awt.*;



public class Desktop extends JScrollPane{
    private TempMainAppletApplication parent;
    
    private JDesktopPane desktopPane;
    public JLabel wallPaper = null;

    public Desktop(TempMainAppletApplication main){
        parent = main;
        
        initDesktop();
        
        parent.add(this,java.awt.BorderLayout.CENTER);
        
        getHorizontalScrollBar().addMouseListener(new java.awt.event.MouseAdapter(){
                public void mouseReleased(java.awt.event.MouseEvent e){
                    HorizontalScrollBarReleased();
                }
            });
            
        getVerticalScrollBar().addMouseListener(new java.awt.event.MouseAdapter(){
                public void mouseReleased(java.awt.event.MouseEvent e){
                    VerticalScrollBarReleased();
                }
            });
    }    
    
    private void initDesktop(){
        desktopPane = new JDesktopPane();
            
        getDesktopPane().setBackground(new Color(58, 110, 165));
        getDesktopPane().setAutoscrolls(true);
        
        this.wallPaper = new JLabel();
        this.wallPaper.setBounds(20, 0, 500, 70);
        this.setBackGroundMessage("Temporary Main Applet Application");
        
        getDesktopPane().add(this.wallPaper, javax.swing.JLayeredPane.DEFAULT_LAYER);
        this.setViewportView(getDesktopPane());
    }
    
    public JDesktopPane getDesktopPane(){
        return desktopPane;
    }
    
    public void setDesktopView(){
        int xloc = 0,yloc=0,xxloc=0,yyloc=0;
        int activeXLoc = 0;
        
        JInternalFrame[] internalframes = getDesktopPane().getAllFrames();
        
        if(internalframes!=null){
            
            for(int i=0;i<internalframes.length;i++){
                int x=0,y=0,xx,yy;
                
                InternalFrame internalframe = (InternalFrame)internalframes[i];
                if(!internalframe.getTitle().equals("Product Lists")){
                    xx = internalframe.getX();
                    yy = internalframe.getHeight();
                    
                    x = xx+internalframe.getWidth();
                    y = internalframe.getY()+internalframe.getHeight();
                    
                    if(x > xloc) xloc = x;
                    if(y > yloc) yloc = y;
                }
            }
        }
        
        parent.getDesktop().wallPaper.setLocation(20,0);
        getDesktopPane().setPreferredSize(new Dimension(xloc,yloc));
        setViewportView(this.getDesktopPane());
        repaint();
        
        
        repaint();
    }
    
    private void setBackGroundMessage(String mess){
        String html = "<html><body><center><font face = Verdana size = 5 color = white><em><b>"+mess+"</b></em></font></center></body></html>";
        wallPaper.setText(html);
    }
    
    private void HorizontalScrollBarReleased(){
        JScrollBar hbar = getHorizontalScrollBar();
 
        JInternalFrame internalframes[] = getDesktopPane().getAllFrames();
        
        if(internalframes!=null){
            int xlocmin=0,xlocmax=0,
            
            loc[] = getLocations(internalframes);
            xlocmin=loc[0];xlocmax=loc[1];
                      
            if((xlocmax-xlocmin)<=getWidth()){
                getDesktopPane().setPreferredSize(new Dimension(xlocmax-xlocmin,(int)getDesktopPane().getPreferredSize().getHeight()));
                setViewportView(this.getDesktopPane());
            
                for(int i=0;i<internalframes.length;i++){
                    InternalFrame internalframe = (InternalFrame)internalframes[i];
                    if(!internalframe.getTitle().equals("Product Lists")){
                        internalframe.setLocation(internalframe.getX()-(hbar.getMaximum()-getWidth())-hbar.getBlockIncrement(), internalframe.getY());
                    }
                }
            }
        }
    }
    
    private void VerticalScrollBarReleased(){
        JScrollBar vbar = getVerticalScrollBar();
 
        JInternalFrame internalframes[] = getDesktopPane().getAllFrames();
        
        if(internalframes!=null){
            int ylocmin=0,ylocmax=0,
            
            loc[] = getLocations(internalframes);
            ylocmin=loc[2];ylocmax=loc[3];
                        
            if((ylocmax-ylocmin)<=getHeight()){
                getDesktopPane().setPreferredSize(new Dimension((int)getDesktopPane().getPreferredSize().getWidth(),ylocmax-ylocmin));
                setViewportView(this.getDesktopPane());
            
                for(int i=0;i<internalframes.length;i++){
                    InternalFrame internalframe = (InternalFrame)internalframes[i];
                    if(!internalframe.getTitle().equals("Product Lists")){
                        internalframe.setLocation(internalframe.getX(), internalframe.getY()-(vbar.getMaximum()-getHeight())-vbar.getBlockIncrement());
                    }
                }
            }
        }
    }
    
    public int[] getLocations(JInternalFrame[] internalframes){
        int xlocmin=0,xlocmax=0,ylocmin=0,ylocmax=0;
        for(int i=0;i<internalframes.length;i++){
            int x=0,y=0,xx=0,yy=0;
                
            InternalFrame internalframe = (InternalFrame)internalframes[i];
            if(!internalframe.getTitle().equals("Product Lists")){
                xx = internalframe.getX();
                yy = internalframe.getY();
                    
                x = xx+internalframe.getWidth();
                y = yy+internalframe.getHeight();
                    
                if(i==0){
                    xlocmin = xx;
                    xlocmax = x;
                        
                    ylocmin = yy;
                    ylocmax = y;
                }
                    
                xlocmin=(xx<xlocmin)?xx:xlocmin;
                xlocmax = (x>xlocmax)?x:xlocmax;
                    
                ylocmin=(yy<ylocmin)?yy:ylocmin;
                ylocmax = (y>ylocmax)?y:ylocmax;
            }
        }
        
        return new int[] {xlocmin,xlocmax,ylocmin,ylocmax};
    }
    
    public void addIF(java.awt.Component c, Integer index){
        //parent.taskBar.addButtonFrame((.util.InternalFrame)c);
        this.getDesktopPane().add(c,index);
    }
}