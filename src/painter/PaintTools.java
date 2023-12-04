// By 106403052 ��ޤGB ���~��

package painter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class PaintTools extends JPanel{  // �w�qø�e�u��(Panel)
	
	private final JComboBox<String> paintTools ;  
	private final String[] tools = 
		{ "����","���u","����","�x��","�ꨤ�x��" } ;
	
	public PaintTools() {		
		
		this.setBackground( Color.lightGray );  // �]�wPanel�I����
		
		paintTools = new JComboBox<String>( tools ) ;  
		paintTools.setPreferredSize( new Dimension(140,35) ) ;  // ���򥻳]�w:�ؤo�B�r��
		paintTools.setFont( new Font("Dialog", Font.BOLD, 15) ) ;
		
		add( paintTools ) ; // �NCheckBox�[�i��Panel
	}
	
	public JComboBox<String> getPaintTools() { // getter method�A���btoolBar���ɯ�եΤ���method
		return paintTools ;
	}
	
}
