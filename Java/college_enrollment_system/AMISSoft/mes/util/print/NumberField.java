package mes.util.print;

import javax.swing.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

/**
* class NumberField
* @author Winston Obas Pidor
*/
public class NumberField extends JTextField {
    private int keyStore = 0;
    
    private boolean deci = true;
    private int mininteger = 1;
    private int maxFracDgt = 2;
    
    public NumberField(){
        setHorizontalAlignment(JTextField.RIGHT); 
        addfocuslistner();
        setText("0");
    } 
    
    public NumberField(boolean deci,int mininteger){
        this.deci = deci;
        this.mininteger = mininteger;
        setHorizontalAlignment(JTextField.RIGHT); 
        addfocuslistner();
        setText("0");
    } 
    
    public NumberField(int mininteger, int maxFracDgt){
        this.deci = deci;
        this.mininteger = mininteger;
        this.maxFracDgt = maxFracDgt;
        
        setHorizontalAlignment(JTextField.RIGHT); 
        addfocuslistner();
        setText("0");
    } 
    
    public void addfocuslistner(){
        this.addFocusListener(new FocusAdapter(){
            public void focusLost(FocusEvent e){
                onfocuslost();
            }
            
            public void focusGained(FocusEvent e){
                onfocusgained();
            }

        });
    }
    
    public void onfocusgained(){
        this.selectAll();
    }
    
    public void onfocuslost(){
        String val = super.getText();
        if(deci==false) setText(val);
        else setText(val);
        
        val = super.getText();
        if(val.indexOf('-')!=-1) super.setForeground(java.awt.Color.red);
        else super.setForeground(java.awt.SystemColor.controlText);
    }
    
    protected void processKeyEvent(KeyEvent e){
        int code = e.getKeyCode();
        char keyChar = e.getKeyChar();
        
        if(code == 0)
	        e.setKeyCode(keyStore);
        else 
	        keyStore = code;

	    code = e.getKeyCode();
	    
	    switch(code){
		    case 8:     //back space
		    case 10:    //enter key
		    case 35:    //end key
		    case 36:    //home key
		    case 37:    //left
		    case 38:    //up
		    case 39:    //right
		    case 40:    //down
		    case 127:   //del key
                super.processKeyEvent(e);
		}
		
		if((keyChar >= '0'  && keyChar <= '9'))
            super.processKeyEvent(e);
        if(keyChar == '.' && super.getText().trim().indexOf('.') == -1 && deci == true)
            super.processKeyEvent(e);
        if(keyChar == '-' && super.getText().trim().indexOf('-') == -1 ){
            if(super.getCaretPosition()==0) super.processKeyEvent(e);
        }
        
        if(super.getText().indexOf('-')!=-1) {
            setForeground(java.awt.Color.red);
        }else setForeground(java.awt.SystemColor.controlText);
        
        if(super.getText().equals("")) super.setText("0");
	}
	
	public String toDecimalDoubleFormat(String strVal){   
        double dval =0.00;
        
        dval = Double.valueOf(CheckNumberFormat(1,strVal)).doubleValue();    
        DecimalFormat mydf = new DecimalFormat("##################0.00################");
		mydf.setMinimumIntegerDigits(mininteger);
		mydf.setMaximumFractionDigits(maxFracDgt);
		return mydf.format(dval);
    }
    
    public String toDecimalIntegerFormat(String strVal){   
        double dval =0.00;
        
        dval = Double.valueOf(CheckNumberFormat(0,strVal)).doubleValue();    
        DecimalFormat mydf = new DecimalFormat("##################0");
		mydf.setMinimumIntegerDigits(mininteger);
		
		return mydf.format(dval);
    }
    
    public String CheckNumberFormat(int type,String strVal){
        //type 0 = integer/long
        //type 1 = double
        if(strVal == null || strVal.trim().length()==0){
            if(type == 0) return "0";
            else return "0.00";
		}
		
		double dval =0.00;
        String left="0";
        String right="00";
        int dotIndex = strVal.indexOf('.');
        
        if(dotIndex!=-1) {
            left = strVal.substring(0,dotIndex);
            right = strVal.substring(dotIndex+1);
            
            if(right.equals("")) right = "00";
            if(left.equals("")) left = "0";
        }else {
            left = strVal;
            right = "00";
        }
        
        if(type == 0) strVal = ""+Double.valueOf(left+"."+right).intValue();  
		else strVal = ""+Double.valueOf(left+"."+right).doubleValue();    
		
		return strVal;
    }
    
    public void setText(String text){
        if(deci==false) super.setText(""+toDecimalIntegerFormat(CheckNumberFormat(0,text)));
        else super.setText(""+toDecimalDoubleFormat(CheckNumberFormat(1,text)));
        
        if(text.indexOf('-')!=-1 ) {
            super.setForeground(java.awt.Color.red);
        }else super.setForeground(java.awt.SystemColor.controlText);
    }
    
    public String getText(){
        String str="0";
        
        str = super.getText();
        if(str.equals("-")) str="0";
        return str;
    }
    
    public Double getDouble(){return Double.valueOf(getText());}
	public Integer getInt(){return Integer.valueOf(""+getDouble().intValue());}
    public void setMinimumIntegerDigits(int mininteger){this.mininteger = mininteger;}
    public void setMaximumFractionDigits(int maxFracDgt){this.maxFracDgt = maxFracDgt;}
}