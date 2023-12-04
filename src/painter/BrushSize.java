// By 106403052 資管二B 黃品毅

package painter;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class BrushSize extends JPanel {  // 定義筆刷大小(Panel)
	
	private final JRadioButton smallBrush ;  
	private final JRadioButton mediumBrush ;
	private final JRadioButton largeBrush ;
	private final ButtonGroup radioGroup ;
	
	public BrushSize() {
		
		this.setBackground( Color.lightGray ) ;  // 設定Panel背景色
		
		smallBrush = new JRadioButton( "小", true ) ;  // 預設選擇
		mediumBrush = new JRadioButton( "中", false ) ;
		largeBrush = new JRadioButton( "大", false ) ;
		smallBrush.setBackground( Color.lightGray ) ;  // 設定背景色
		mediumBrush.setBackground( Color.lightGray ) ;
		largeBrush.setBackground( Color.lightGray ) ;
		smallBrush.setFont(new Font("Dialog", Font.BOLD, 15)) ;   // 設定字型
		mediumBrush.setFont( smallBrush.getFont() ) ;
		largeBrush.setFont( smallBrush.getFont() ) ;
		
		radioGroup = new ButtonGroup() ;  // 建立連結
		radioGroup.add(smallBrush) ;
		radioGroup.add(mediumBrush) ;
		radioGroup.add(largeBrush) ;
		
		add( smallBrush ) ;  // 將各RadioButton加入Panel
		add( mediumBrush ) ;
		add( largeBrush ) ;	

	}
	
	public JRadioButton getSmallBrush() {  // getter method，讓在toolBar內時能調用內部method
		return smallBrush ;
	}
	public JRadioButton getMediumBrush() {
		return mediumBrush ;
	}
	public JRadioButton getLargeBrush() {
		return largeBrush ;
	}
	public ButtonGroup getBrushButtonGroup() {
		return radioGroup ;
	}
}