// By 106403052 資管二B 黃品毅

package painter;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusBar extends JPanel{  // 定義狀態欄(Panel)
	
	private final JLabel statusBar ; 
	
	public StatusBar( ) {
		
		statusBar = new JLabel("游標位置 : (0,0)") ; 
		add(statusBar) ;  // 將Label加進狀態欄Panel
		
	}
	
	public JLabel getStatusBar() {  // getter method，讓在toolBar內時能調用內部method
		return statusBar ;          
	}                               
	
}