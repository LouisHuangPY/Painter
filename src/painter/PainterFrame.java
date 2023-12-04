// By 106403052 資管二B 黃品毅

package painter;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PainterFrame extends JFrame {  // 定義主Frame
	
	private final ToolBar toolBar ;       // 宣告各Panel(由於此Panel內有自定義Method，ref型態只能用子類別)
	private final PaintArea paintArea ;
	private final StatusBar statusBar ;	 
	
	public PainterFrame() {
		super("Painter ver.1") ;  // Title
		
		statusBar = new StatusBar() ;
		paintArea = new PaintArea( statusBar ) ;
		toolBar = new ToolBar( paintArea ) ;
		paintArea.setThisObj( paintArea ) ;  // 將paintArea物件傳回他的class本身(reflection method需要)
		
		add( toolBar, BorderLayout.NORTH ) ;  // 設定各Panel排版
		add( paintArea, BorderLayout.CENTER ) ;
		add( statusBar, BorderLayout.SOUTH ) ;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;  // 設定Frame基本設定:關閉、尺寸、可見性
		this.setSize(1200,800) ;
		this.setVisible(true) ;
		
		JOptionPane.showMessageDialog(this,"Welcome.") ;  // 設定歡迎訊息
		
	}

}
