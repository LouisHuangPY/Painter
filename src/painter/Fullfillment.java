// By 106403052 ��ޤGB ���~��

package painter;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class Fullfillment extends JPanel {  // �w�q��(Panel)
	
	private final JCheckBox fullfillment ;  
	
	public Fullfillment() {
		
		this.setBackground( Color.lightGray ) ;	 // �]�wPanel�I����
		
		fullfillment = new JCheckBox() ;         // �إߪ���A�]�w�򥻳]�w:�I����B�r��
		fullfillment.setBackground( Color.lightGray ) ;
		fullfillment.setFont( new Font("Dialog", Font.BOLD, 15) ) ;
		fullfillment.setEnabled( false ) ;
		
		add( fullfillment ) ;  // �NCheckBox�N�i��Panel
		
	}
	
	public JCheckBox getFullfillment() {  // getter method�A���btoolBar���ɯ�եΤ���method
		return fullfillment ;
	}

}
