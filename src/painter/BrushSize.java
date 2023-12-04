// By 106403052 ��ޤGB ���~��

package painter;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class BrushSize extends JPanel {  // �w�q����j�p(Panel)
	
	private final JRadioButton smallBrush ;  
	private final JRadioButton mediumBrush ;
	private final JRadioButton largeBrush ;
	private final ButtonGroup radioGroup ;
	
	public BrushSize() {
		
		this.setBackground( Color.lightGray ) ;  // �]�wPanel�I����
		
		smallBrush = new JRadioButton( "�p", true ) ;  // �w�]���
		mediumBrush = new JRadioButton( "��", false ) ;
		largeBrush = new JRadioButton( "�j", false ) ;
		smallBrush.setBackground( Color.lightGray ) ;  // �]�w�I����
		mediumBrush.setBackground( Color.lightGray ) ;
		largeBrush.setBackground( Color.lightGray ) ;
		smallBrush.setFont(new Font("Dialog", Font.BOLD, 15)) ;   // �]�w�r��
		mediumBrush.setFont( smallBrush.getFont() ) ;
		largeBrush.setFont( smallBrush.getFont() ) ;
		
		radioGroup = new ButtonGroup() ;  // �إ߳s��
		radioGroup.add(smallBrush) ;
		radioGroup.add(mediumBrush) ;
		radioGroup.add(largeBrush) ;
		
		add( smallBrush ) ;  // �N�URadioButton�[�JPanel
		add( mediumBrush ) ;
		add( largeBrush ) ;	

	}
	
	public JRadioButton getSmallBrush() {  // getter method�A���btoolBar���ɯ�եΤ���method
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