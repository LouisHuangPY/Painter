// By 106403052 資管二B 黃品毅

package painter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class PaintTools extends JPanel{  // 定義繪畫工具(Panel)
	
	private final JComboBox<String> paintTools ;  
	private final String[] tools = 
		{ "筆刷","直線","橢圓形","矩形","圓角矩形" } ;
	
	public PaintTools() {		
		
		this.setBackground( Color.lightGray );  // 設定Panel背景色
		
		paintTools = new JComboBox<String>( tools ) ;  
		paintTools.setPreferredSize( new Dimension(140,35) ) ;  // 做基本設定:尺寸、字型
		paintTools.setFont( new Font("Dialog", Font.BOLD, 15) ) ;
		
		add( paintTools ) ; // 將CheckBox加進此Panel
	}
	
	public JComboBox<String> getPaintTools() { // getter method，讓在toolBar內時能調用內部method
		return paintTools ;
	}
	
}
