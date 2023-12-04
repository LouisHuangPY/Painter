// By 106403052 ��ޤGB ���~��

package painter;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PainterFrame extends JFrame {  // �w�q�DFrame
	
	private final ToolBar toolBar ;       // �ŧi�UPanel(�ѩ�Panel�����۩w�qMethod�Aref���A�u��Τl���O)
	private final PaintArea paintArea ;
	private final StatusBar statusBar ;	 
	
	public PainterFrame() {
		super("Painter ver.1") ;  // Title
		
		statusBar = new StatusBar() ;
		paintArea = new PaintArea( statusBar ) ;
		toolBar = new ToolBar( paintArea ) ;
		paintArea.setThisObj( paintArea ) ;  // �NpaintArea����Ǧ^�L��class����(reflection method�ݭn)
		
		add( toolBar, BorderLayout.NORTH ) ;  // �]�w�UPanel�ƪ�
		add( paintArea, BorderLayout.CENTER ) ;
		add( statusBar, BorderLayout.SOUTH ) ;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;  // �]�wFrame�򥻳]�w:�����B�ؤo�B�i����
		this.setSize(1200,800) ;
		this.setVisible(true) ;
		
		JOptionPane.showMessageDialog(this,"Welcome.") ;  // �]�w�w��T��
		
	}

}
