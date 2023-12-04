// By 106403052 資管二B 黃品毅

package painter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPane extends JPanel {  // 定義工具列按鈕列(Panel)
	
	private final JButton foreground ;  
	private final JButton background ; 
	private final JButton cleanPane ; 
	private final JButton eraser ; 
	private final JButton back ; 
	private final FlowLayout layout ;  // 用來設定按鈕間距
	
	public ButtonPane() {
		
		layout = new FlowLayout() ;  
		layout.setHgap( 15 );        
		this.setLayout( layout );    
		this.setBackground( Color.lightGray );  // 設定Panel背景色
		
		foreground = new JButton( "前景色" ) ;  
		background = new JButton( "背景色" ) ; 
		cleanPane = new JButton( "清除畫面" ) ; 
		eraser = new JButton( "橡皮擦" ) ; 
		back = new JButton( "上一步" ) ; 
		foreground.setFont( new Font("Dialog", Font.BOLD, 16) ) ;  // 做基本設定：字型、尺寸
		background.setFont( foreground.getFont( )) ;
		cleanPane.setFont( foreground.getFont( )) ;
		eraser.setFont( foreground.getFont( )) ;
		back.setFont( foreground.getFont( )) ;
		foreground.setPreferredSize( new Dimension(105,70) ) ;
		background.setPreferredSize( foreground.getPreferredSize() ) ;
		cleanPane.setPreferredSize( foreground.getPreferredSize() ) ;
		eraser.setPreferredSize( foreground.getPreferredSize() ) ;
		back.setPreferredSize( foreground.getPreferredSize() ) ;
		
		add( foreground ) ;  // 將各Button加進此Panel
		add( background ) ;
		add( cleanPane ) ;
		add( eraser ) ;
		add( back ) ;
		
	}
	
	public JButton getForegroundButton() {  // getter method，讓在toolBar內時能調用內部method
		return foreground ;
	}
	public JButton getBackgroundButton() {
		return background ;
	}
	public JButton getCleanButton() {
		return cleanPane ;
	}
	public JButton getEraserButton() {
		return eraser ;
	}
	public JButton getBackButton() {
		return back ;
	}

}
