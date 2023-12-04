// By 106403052 資管二B 黃品毅

package painter;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class Fullfillment extends JPanel {  // 定義填滿(Panel)
	
	private final JCheckBox fullfillment ;  
	
	public Fullfillment() {
		
		this.setBackground( Color.lightGray ) ;	 // 設定Panel背景色
		
		fullfillment = new JCheckBox() ;         // 建立物件，設定基本設定:背景色、字型
		fullfillment.setBackground( Color.lightGray ) ;
		fullfillment.setFont( new Font("Dialog", Font.BOLD, 15) ) ;
		fullfillment.setEnabled( false ) ;
		
		add( fullfillment ) ;  // 將CheckBox將進此Panel
		
	}
	
	public JCheckBox getFullfillment() {  // getter method，讓在toolBar內時能調用內部method
		return fullfillment ;
	}

}
