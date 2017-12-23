package mes.gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;


public class MESFrame extends JInternalFrame{

    public String buttonNames[] = null;
    public JButton BUTTONS[] = null;
    public JPanel MAINPANEL = null;
  //  Color bground = new Color(213,229,219);
   
    //Color bground = new Color(255,255,204);
   // Color bground = new Color(231,226,221);
    
    public MESFrame(int w,int h,String title, String[] buttons){
        super(title);
        setSize(w,h);
        buttonNames =  buttons;
        BUTTONS = new JButton[buttons.length];
        create();
    }
    
    void create(){
        MAINPANEL = new JPanel(){
            public Component  add(Component comp){
                if(comp instanceof JLabel){
                    comp.setFont(new Font("", Font.PLAIN, 11));
                    
                }
                return super.add(comp);
            }
        };
//        MAINPANEL.setBackground(bground);
        
        JLabel titleLabel = new JLabel(getTitle(), JLabel.CENTER);
        titleLabel.setFont(new Font("", Font.BOLD, 13));
        if(getTitle().trim().length() == 0){
//            titleLabel.setBackground(bground);
        }else
        {
         //   titleLabel.setBackground(new Color(0,128,192));
         //  titleLabel.setColor(new Color(255,255,192));
           // titleLabel.setForeground(new Color(255,255,204));
	       // titleLabel.setForeground(Color.black);
	        titleLabel.setBorder(new EtchedBorder());
            
        }
        titleLabel.setOpaque(true);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); 
        for(int i = 0; i < buttonNames.length;  i++){
            JButton b = new JButton(buttonNames[i]);
            b.setFont(new Font("", Font.PLAIN, 11));
            b.setMnemonic(buttonNames[i].charAt(0));
            b.setActionCommand(buttonNames[i]);
         //   b.setBackground(new Color(0,128,192));
          //  b.setForeground(new Color(255,255,204));
          //  b.setForeground(Color.black);
            buttonPanel.add(b);
            BUTTONS[i] = b;
        }
        
        buttonPanel.setBorder(new EtchedBorder());
//        buttonPanel.setBackground(bground);
        JPanel panel  = new JPanel(new BorderLayout());
        panel.add(titleLabel , BorderLayout.NORTH);
        panel.add(MAINPANEL, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panel);
        
    }
    
    public void addButtonActionListener(ActionListener source){
        for(int i = 0 ; i < BUTTONS.length; i++){
            BUTTONS[i].addActionListener(source);
        }

    }
    
}