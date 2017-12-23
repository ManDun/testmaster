//java application program

 package mes.clock;
 
 import java.awt.*;
 import javax.swing.*;
 import java.text.*;
 import java.text.SimpleDateFormat;
 import java.util.*;
 import javax.swing.Timer;
 import java.awt.event.*;
 import java.awt.*;
 
 				public class desktopClock extends JPanel {
							Timer clockTimer;
							Date clockDate;
							SimpleDateFormat clockDateformat = new SimpleDateFormat("h : mm : ss aa ---   EEEE - MMM dd, yyyy");
							JLabel timeText;
						public desktopClock() {
						//	this.setBackground ( Color.yellow.darker());
                                         // this.setForeground(Color.red.darker());
							this.setLayout ( null );
			                        Calendar clockCal = Calendar.getInstance();	
							clockDate = clockCal.getTime();
                                               	this.add ( timeText = new JLabel(clockDateformat.format ( clockDate ) + "" ) );timeText.setBounds ( 10, -12, 300, 50 );timeText.setForeground (Color.black);timeText.setBackground(Color.red); timeText.setFont ( new Font ( "Times New Roman", Font.PLAIN, 13 ) );
								clockTimer = new Timer ( 990, new clockhandler() );
								clockTimer.start();		
						}
							//clockhandler
							public class clockhandler implements ActionListener {
								public void actionPerformed ( ActionEvent e ) {
									Calendar clockCal = Calendar.getInstance();
									clockDate = clockCal.getTime();
									timeText.setText ( clockDateformat.format ( clockDate ) + "" );				
								}
							}//end clockhandler
					}//--end clock==================



