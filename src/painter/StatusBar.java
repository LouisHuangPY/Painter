// By 106403052 ��ޤGB ���~��

package painter;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusBar extends JPanel{  // �w�q���A��(Panel)
	
	private final JLabel statusBar ; 
	
	public StatusBar( ) {
		
		statusBar = new JLabel("��Ц�m : (0,0)") ; 
		add(statusBar) ;  // �NLabel�[�i���A��Panel
		
	}
	
	public JLabel getStatusBar() {  // getter method�A���btoolBar���ɯ�եΤ���method
		return statusBar ;          
	}                               
	
}