package mes.util.print;

public class ManualPageSetup extends javax.swing.JPopupMenu{
    
    //java.awt.print.PageFormat pageFormat;
    
    private javax.swing.JPanel pnlContent;
    private NumberField txtWidth,txtHeight,
        txtMarginLeft,txtMarginRight,txtMarginTop,txtMarginButtom;
    private javax.swing.JRadioButton btnPortrait,btnLandscape;
    private javax.swing.JButton btnClose,btnSave;
    
    public ManualPageSetup(){
        
        pnlContent = new javax.swing.JPanel();
        pnlContent.setPreferredSize(new java.awt.Dimension(220,180));
        this.add(pnlContent);
        
        init();
    }
    
    public void init(){
        pnlContent.setLayout(null);
        
        int width = 50;
        javax.swing.JPanel pnlPaper = new javax.swing.JPanel(null);
        pnlPaper.setBounds(1,1,(int)this.getPreferredSize().getWidth()-3,40);
        pnlPaper.setBorder(new javax.swing.border.TitledBorder("Paper"));
        pnlContent.add(pnlPaper);
        
        javax.swing.JLabel lbl = new javax.swing.JLabel("Width");
        lbl.setBounds(5,15,width,20);
        pnlPaper.add(lbl);
        
        this.txtWidth = new NumberField(1,3);
        txtWidth.setBounds(lbl.getX() + lbl.getWidth(),15,50,20);
        pnlPaper.add(txtWidth);
        
        lbl = new javax.swing.JLabel("Height");
        lbl.setBounds(txtWidth.getX() + txtWidth.getWidth()+10,15,width,20);
        pnlPaper.add(lbl);
        
        this.txtHeight = new NumberField(1,3);
        txtHeight.setBounds(lbl.getX() + lbl.getWidth(),15,50,20);
        pnlPaper.add(txtHeight);
        
        
        javax.swing.JPanel pnlMargin = new javax.swing.JPanel(null);
        pnlMargin.setBounds(1, 41,(int)this.getPreferredSize().getWidth()-3,65);
        pnlMargin.setBorder(new javax.swing.border.TitledBorder("Margin"));
        pnlContent.add(pnlMargin);
        
        int yloc =15;
        lbl = new javax.swing.JLabel("Left");
        lbl.setBounds(5,yloc,width,20);
        pnlMargin.add(lbl);
        
        this.txtMarginLeft = new NumberField(1,3); 
        txtMarginLeft.setBounds(lbl.getX() + lbl.getWidth(),15,50,20);
        pnlMargin.add(txtMarginLeft);
        
        lbl = new javax.swing.JLabel("Right");
        lbl.setBounds(txtMarginLeft.getX() + txtMarginLeft.getWidth()+10,15,width,20);
        pnlMargin.add(lbl);
        
        this.txtMarginRight = new NumberField(1,3); 
        txtMarginRight.setBounds(lbl.getX() + lbl.getWidth(),lbl.getY(),50,20);
        pnlMargin.add(txtMarginRight);
        
        lbl = new javax.swing.JLabel("Top");
        lbl.setBounds(5,yloc+=25,width,20);
        pnlMargin.add(lbl);
        
        this.txtMarginTop = new NumberField(1,3); 
        txtMarginTop.setBounds(lbl.getX() + lbl.getWidth(),lbl.getY(),50,20);
        pnlMargin.add(txtMarginTop);
        
        lbl = new javax.swing.JLabel("Buttom");
        lbl.setBounds(txtMarginTop.getX() + txtMarginTop.getWidth()+10,yloc,width,20);
        pnlMargin.add(lbl);
        
        this.txtMarginButtom = new NumberField(1,3); 
        txtMarginButtom.setBounds(lbl.getX() + lbl.getWidth(),lbl.getY(),50,20);
        pnlMargin.add(txtMarginButtom);
        //pnlContent
        
        javax.swing.JPanel pnlOrientation = new javax.swing.JPanel(null);
        pnlOrientation.setBounds(1, 106,(int)this.getPreferredSize().getWidth()-3,40);
        pnlOrientation.setBorder(new javax.swing.border.TitledBorder("Orientation"));
        pnlContent.add(pnlOrientation);
        
        this.btnPortrait = new javax.swing.JRadioButton("Portrait");
        btnPortrait.setBounds(5,15,60,20);
        pnlOrientation.add(btnPortrait);
        
        this.btnLandscape = new javax.swing.JRadioButton("Landscape");
        btnLandscape.setBounds(btnPortrait.getX() + btnPortrait.getWidth()+20,15,80,20);
        pnlOrientation.add(btnLandscape);
        
        javax.swing.ButtonGroup btngroup = new javax.swing.ButtonGroup();
        btngroup.add(this.btnPortrait);
        btngroup.add(this.btnLandscape);
        
        btnPortrait.setSelected(true);
        
        
        javax.swing.JPanel pnlButtons = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,1,3));
        pnlButtons.setBounds(1, 146,(int)this.getPreferredSize().getWidth()-3,40);
        //pnlButtons.setBorder(new javax.swing.border.TitledBorder(""));
        pnlContent.add(pnlButtons);
        
        btnSave = new javax.swing.JButton("OK");
        
        btnSave.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e){
                setHideMenu();
            }
            });
        pnlButtons.add(btnSave);
    }
    
    public void setPageFormat(java.awt.print.PageFormat pageFormat){
        //this.pageFormat = pageFormat;
                
        this.txtWidth.setText("" + (pageFormat.getWidth()/72));
        this.txtHeight.setText("" + (pageFormat.getHeight()/72));
        this.txtMarginLeft.setText("" + (pageFormat.getImageableX()/72));
        this.txtMarginRight.setText("" + 
            ((pageFormat.getWidth() - pageFormat.getImageableWidth() - pageFormat.getImageableX())/72));
            
        this.txtMarginTop.setText("" + (pageFormat.getImageableY()/72));
        this.txtMarginButtom.setText("" + 
            ((pageFormat.getHeight() - pageFormat.getImageableHeight() - pageFormat.getImageableY())/72));
        
        this.btnLandscape.setSelected(pageFormat.getOrientation()==0);
        this.btnPortrait.setSelected(pageFormat.getOrientation()==1);
        
    }
    
    public java.awt.print.PageFormat getPageFormat(){
        java.awt.print.PageFormat pageFormat = new java.awt.print.PageFormat();
        java.awt.print.Paper papel = new java.awt.print.Paper();
        //short
        papel.setSize(this.txtWidth.getDouble().doubleValue() *72,this.txtHeight.getDouble().doubleValue()*72);
        papel.setImageableArea(this.txtMarginLeft.getDouble().doubleValue()*72,
            this.txtMarginTop.getDouble().doubleValue()*72,
            this.txtWidth.getDouble().doubleValue()*72 - (this.txtMarginLeft.getDouble().doubleValue()*72 + this.txtMarginRight.getDouble().doubleValue()*72),
            this.txtHeight.getDouble().doubleValue()*72 - (this.txtMarginTop.getDouble().doubleValue()*72 + this.txtMarginButtom.getDouble().doubleValue()*72));
        
        pageFormat = new java.awt.print.PageFormat();
        pageFormat.setPaper(papel);
        
        if(this.btnPortrait.isSelected())
            pageFormat.setOrientation(java.awt.print.PageFormat.PORTRAIT);
        else
            pageFormat.setOrientation(java.awt.print.PageFormat.LANDSCAPE);
        return pageFormat;
    }
    
    public void setHideMenu(){
        super.setVisible(false);
    }


}