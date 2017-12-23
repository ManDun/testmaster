
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import javax.swing.DesktopManager;


public abstract class InternalFrame extends JInternalFrame {
    public TempMainAppletApplication parent;
   
    private javax.swing.JComponent parentComponent;
    private boolean isMoved = false;
    
    public javax.swing.JButton btnTask = null;
    public InternalFrame(String title,TempMainAppletApplication main){
        super(title, false, true, false, true);
        
        this.parent = main;
                
        this.addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameActivated(InternalFrameEvent e){
                
                frameActivated();
            }
            public void internalFrameClosing(InternalFrameEvent e){
                close();
                closeFrame();
            }
            
        });
        
        if(!getTitle().equals("Product Lists")){
            addComponentListener(new java.awt.event.ComponentAdapter(){
                public void componentMoved(java.awt.event.ComponentEvent e){
                    isMoved = true;
                }
            });
            
            this.getComponent(1).addMouseListener(new java.awt.event.MouseListener(){
                    public void mouseReleased(java.awt.event.MouseEvent e){
                        if(isMoved){
                            componentMoving();
                            isMoved = false;   
                        }
                    }
                    public void mouseExited(java.awt.event.MouseEvent e){}
                    public void mouseEntered(java.awt.event.MouseEvent e){}
                    public void mousePressed(java.awt.event.MouseEvent e){}
                    public void mouseClicked(java.awt.event.MouseEvent e){}
                });
            
        }
            
        btnTask = new javax.swing.JButton();
        main.getDesktop().addIF(this,JLayeredPane.PALETTE_LAYER);
        

        setContentPane(new javax.swing.JPanel());
        setLocationOnDesktop();
    }
    
    public void setLocationOnDesktop(){
        int index = 0;
        
        JInternalFrame internalframe = null;
        JInternalFrame[] internalframes = getDesktopPane().getAllFramesInLayer(100);
        if(internalframes!=null){
            for(int i=1;i<internalframes.length;i++){
                internalframe = (JInternalFrame)internalframes[i];
                index = index + internalframe.getComponent(1).getHeight();
            }
        }
        
        setLocation(index,index);
        
        repaint();
        //return internalframe;
    }
    
    public void setModalLayer(){
        this.setLayer(JLayeredPane.MODAL_LAYER);
    }
    
    public void setLocationRelativeTo(InternalFrame main){
        double xloc = main.getLocation().getX()+(main.getWidth()-getWidth())/2;
        double yloc = main.getLocation().getY()+(main.getHeight()-getHeight())/2;
        
        double dw = main.getDesktopPane().getWidth();
        double dh = main.getDesktopPane().getHeight();
        
        if(xloc<0) xloc = 1;
        if(yloc<0) yloc = 1;
        
        if(xloc>dw) xloc = dw - getWidth();
        if(yloc>dw) yloc = dh - getHeight();
        
        super.setLocation((int)xloc,(int)yloc);
    }
    
    private void componentMoving(){
        parent.getDesktop().setDesktopView();
    }    
    
    public abstract void refresh();
    public abstract void frameActivated();
    public abstract void closeFrame();
    
    public void close(){
        
    }

    public void dispose(){
        close();
        super.dispose();
    }
}
