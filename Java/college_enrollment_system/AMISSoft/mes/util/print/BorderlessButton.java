package mes.util.print;

/**
 * created by: kent alfred degrano
 * created on: february 26, 2001
 * source: BorderlessButton.java 
 */   

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class BorderlessButton extends javax.swing.JButton
{ 
	
	public BorderlessButton(){
		super();
		this.createButton();
	}
	
	public BorderlessButton(String text){
		super(text); 
		this.createButton();
	}	
	
	public BorderlessButton(Icon icon){
		super(icon);
		this.createButton();
	}
	
	public BorderlessButton(String text, Icon icon){
		super(text, icon);
		this.createButton();
	}
	
	private boolean borderless = true;
	public void setBorderless(boolean b){
	    this.borderless = b;
	    if(!b){
	        this.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.lightGray, Color.darkGray));
	        this.setBorderPainted(!b);
	        this.setFocusPainted(false);
	        this.setContentAreaFilled(true);
	        this.setRequestFocusEnabled(true);
	        this.setBackground(Color.gray);
	    }
	}
	
	private void createButton(){
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setRequestFocusEnabled(false);
		this.setMargin(new Insets(1, 1, 0, 1));
		this.setForeground(javax.swing.UIManager.getColor("Button.foreground"));
		
		this.setVerticalAlignment(JButton.TOP);
		this.setVerticalTextPosition(JButton.BOTTOM);
		this.setHorizontalAlignment(JButton.CENTER);
		this.setHorizontalTextPosition(JButton.CENTER);
		
		this.setToolTipText(this.getText());
		
		this.addMouseListener(new MouseAdapter(){
			
			public void mouseEntered(MouseEvent e){
			    if(getShowBorder()){
			        if(borderless)
				        mouseEnter();
				}
			}
			
			public void mouseExited(MouseEvent e){
			    if(getShowBorder()){
			        if(borderless)
				        mouseExit();
				}
			}
			
			public void mousePressed(MouseEvent e){
			    if(getShowBorder()){
			        setPressed(true);
			    }
			}
			
			public void mouseReleased(MouseEvent e){
			    if(getShowBorder()){
				    setPressed(false);
				}
			}
			
			});
		this.addMouseMotionListener(new MouseMotionAdapter(){
		    public void mouseDragged(MouseEvent e){
		        if(getShowBorder()){
		            if(borderless)
		                dragged(true);
		        }
		    }
		});
	}
	
	public void setEnabled(boolean bool){
	    if(!bool) this.setForeground(Color.lightGray);
	    else     this.setForeground(Color.white); 
	    super.setEnabled(bool);
	    repaint();
	}
	
	boolean drag = false;
	public void dragged(boolean bool){
	    drag = bool;
	}
	
	
	public void setPressed(boolean isPressed){
		if(!this.getText().equals("")){
			if(isPressed){
				this.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.darkGray));
			}else{
			    this.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.lightGray, Color.darkGray));
			}
		}else{
		    this.mouseExit();
		}
	}
	
	
	public void mouseExit(){
		if(!this.getText().equals("")){
			this.setBorderPainted(false);
		}
	}
	
	public void mouseEnter(){
	    
		if(!this.getText().equals("")){
			this.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.lightGray, Color.darkGray));
			this.setBorderPainted(true);
		}
	}
	
	public void setText(String str){
	    if(str.equals(""))
	        this.mouseExit();
	    super.setText(str);
	    super.setToolTipText(str);
	}
	
	public void setToolTipText(String str){
	    String html = "<html><body bgcolor = FFFFFF><center><font face = Verdana size = 2 color = blue><em><b>  "+str+
	    "  </b></em></font></center></body></html>";
	    super.setToolTipText(html);
	}
	
	private boolean showBordered = true;
	public void showBorder(boolean b){
	    this.showBordered = b;
	    this.setBorderPainted(b);
	}
	public boolean getShowBorder(){
	    return this.showBordered;
	}
	
	public void setBackground(Color c){
	    super.setBackground(c);
	}
	
	public void setForeground(Color b){
	    super.setForeground(b);
	}

	
	public static void main(String args[]){
		JFrame frame = new JFrame();
		
		frame.setSize(300, 150);
		frame.setTitle("Button testing");
		frame.setDefaultCloseOperation(3);
		Container c = frame.getContentPane();
		
		c.setLayout(null);
		BorderlessButton b1 = new BorderlessButton("One");
		BorderlessButton b2 = new BorderlessButton("Two");
		
		b1.setBounds(50, 50, 80, 30);
		b2.setBounds(150, 50, 80, 30);
		
		c.add(b1);
		c.add(b2);
		frame.setVisible(true);
	}
	
	
}
