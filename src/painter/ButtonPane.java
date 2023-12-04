// By 106403052 ��ޤGB ���~��

package painter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPane extends JPanel {  // �w�q�u��C���s�C(Panel)
	
	private final JButton foreground ;  
	private final JButton background ; 
	private final JButton cleanPane ; 
	private final JButton eraser ; 
	private final JButton back ; 
	private final FlowLayout layout ;  // �Ψӳ]�w���s���Z
	
	public ButtonPane() {
		
		layout = new FlowLayout() ;  
		layout.setHgap( 15 );        
		this.setLayout( layout );    
		this.setBackground( Color.lightGray );  // �]�wPanel�I����
		
		foreground = new JButton( "�e����" ) ;  
		background = new JButton( "�I����" ) ; 
		cleanPane = new JButton( "�M���e��" ) ; 
		eraser = new JButton( "�����" ) ; 
		back = new JButton( "�W�@�B" ) ; 
		foreground.setFont( new Font("Dialog", Font.BOLD, 16) ) ;  // ���򥻳]�w�G�r���B�ؤo
		background.setFont( foreground.getFont( )) ;
		cleanPane.setFont( foreground.getFont( )) ;
		eraser.setFont( foreground.getFont( )) ;
		back.setFont( foreground.getFont( )) ;
		foreground.setPreferredSize( new Dimension(105,70) ) ;
		background.setPreferredSize( foreground.getPreferredSize() ) ;
		cleanPane.setPreferredSize( foreground.getPreferredSize() ) ;
		eraser.setPreferredSize( foreground.getPreferredSize() ) ;
		back.setPreferredSize( foreground.getPreferredSize() ) ;
		
		add( foreground ) ;  // �N�UButton�[�i��Panel
		add( background ) ;
		add( cleanPane ) ;
		add( eraser ) ;
		add( back ) ;
		
	}
	
	public JButton getForegroundButton() {  // getter method�A���btoolBar���ɯ�եΤ���method
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
