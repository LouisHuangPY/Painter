// By 106403052 ��ޤGB ���~��

package painter;

import java.awt.* ;
import java.util.Enumeration;

import javax.swing.* ;

public class ToolBar extends JPanel{  // �w�q�u��C(Panel)
	
	private final PaintArea paintArea ;  // �NpaintArea����ǤJ�A�H�K�Q�ΥL��method�վ�䤺���ݩ�
 	private JColorChooser colorChooser ;  
	private Dialog dialog ;  // �����s��colorChooser�Ҳ��ͤ�Dialog��reference
	private Color foreColor ;
	private Color backColor ;
	
	private final JLabel comboBox ;     // �ŧi�u��C��Label
	private final JLabel radioButton ;  
	private final JLabel checkBox ;     
	
	private final PaintTools paintTools ;   // �ŧi�u��C����Panel
	private final BrushSize brushSize ;    
	private final Fullfillment fullfillment ; 
	private final ButtonPane buttonPane ;   
	
	private final EventPerform eventPerform ;  // �����NListener������class
	
	public ToolBar( PaintArea paintArea ) {
		
		this.setBackground( Color.lightGray ) ;  
		this.setLayout( new GridBagLayout() ) ;  
		GridBagConstraints toolPane = new GridBagConstraints() ;  // �إ�GridBagLayout�Ψӳ]�w�����ݩʪ�����
		
		this.paintArea = paintArea ;
		comboBox = new JLabel("ø�Ϥu��") ;
		radioButton = new JLabel("����j�p") ;
		checkBox = new JLabel("��") ;
		paintTools = new PaintTools() ;
		brushSize = new BrushSize() ;
		fullfillment = new Fullfillment() ;
		buttonPane = new ButtonPane() ;
		eventPerform = new EventPerform( this ) ;  // �NtoolBar����ǤJ�A�Ϩ�I�sToolBar��Method
		colorChooser = new JColorChooser() ;
		
		paintTools.getPaintTools().addItemListener( eventPerform.getPaintToolsHDL() ) ;  // �NEventPerform���w�q��Listener�[�i�U����
		brushSize.getSmallBrush().addItemListener( eventPerform.getBrushSizeHDL() ) ;
		brushSize.getMediumBrush().addItemListener( eventPerform.getBrushSizeHDL() ) ;
		brushSize.getLargeBrush().addItemListener( eventPerform.getBrushSizeHDL() ) ;
		fullfillment.getFullfillment().addItemListener( eventPerform.getFullfillmentHDL() ) ;
		buttonPane.getForegroundButton().addActionListener( eventPerform.getGroundButtonsHDL() ) ;
		buttonPane.getBackgroundButton().addActionListener( eventPerform.getGroundButtonsHDL() );
		buttonPane.getCleanButton().addActionListener( eventPerform.getCleanButtonHDL() ) ;
		buttonPane.getEraserButton().addActionListener( eventPerform.getEraserButtonHDL() ) ;
		buttonPane.getBackButton().addActionListener( eventPerform.getBackButtonHDL() ) ;
		
		comboBox.setFont(new Font("Serif", Font.BOLD, 16)) ;  // Label�r���]�w
		radioButton.setFont(comboBox.getFont()) ;
		checkBox.setFont(comboBox.getFont()) ;
		
		//�H�U�ϰ쳣�O�Q��GridBagLayout�]�w�U���󪺱ƪ�
		
		toolPane.weightx = 0.1 ;  // �]�w����x�b�v���A�]�N�O�U�Ӯ�l��ɻP��Panel��ɶ������j���צbx�b����Ҥ��t(�����Y�񤴦��ĪG)�A�Ʀr�������L�v������~���N�q
		toolPane.gridx = 0 ;      // �]�w��l�b�G����l�}�C����x�y��
		toolPane.gridy = 0 ;      // �]�w��l�b�G����l�}�C����y�y��
		toolPane.anchor = GridBagConstraints.WEST ; // �]�w�Y���󥼦�����l�A�h����b��l�������
		toolPane.insets = new Insets( 15,20,4,0 ) ; // �]�w����P��l�������Z�A�Ǧ��վ�ӷL�ƪ��A�Ʀr�̧Ǭ��W���U�k
		this.add( comboBox, toolPane ) ;            // ����GridBagLayout���]�w�[�J��Panel
		
		// ���ק諸�ݩʫh�O���ۦP�A�ptoolPane.weightx
		toolPane.gridx = 1 ;
		toolPane.gridy = 0 ;
		toolPane.insets = new Insets( 15,0,4,0 ) ;
		this.add( radioButton, toolPane ) ;
		
		toolPane.gridx = 2 ;
		toolPane.gridy = 0 ;
		toolPane.insets = new Insets( 15,-10,4,0 ) ;
		this.add( checkBox, toolPane ) ;
		
		toolPane.gridx = 0 ;
		toolPane.gridy = 1 ;
		toolPane.insets = new Insets( 0,30,15,0 ) ;
		this.add( paintTools, toolPane ) ;
		
		toolPane.gridx = 1 ;
		toolPane.gridy = 1 ;
		toolPane.insets = new Insets( -15,0,0,0 ) ;
		this.add( brushSize, toolPane ) ;
		
		toolPane.gridx = 2 ;
		toolPane.gridy = 1 ;
		toolPane.insets = new Insets( -15,-7,2,0 ) ;
		this.add( fullfillment, toolPane ) ;
		
		toolPane.weightx = 2 ;     // �ǥѻP��ɶZ�����վ�A�N��Ӥu��C�����b����
		toolPane.gridx = 3 ;
		toolPane.gridy = 0 ;
		toolPane.gridheight = 2 ;  // �]�w����l��y�b�ү��V����l��(�Ϥ�������column)
		toolPane.insets = new Insets( 0,-10,0,0 ) ;
		this.add( buttonPane, toolPane ) ;
		
	}
	
	public void PaintToolsControl() {  // �w�q�U���󲣥�Event����檺��k�A�j�h�Ƥ�k���O��paintArea��method�h�ק���ݩ�
		System.out.println(String.format(
				  "��� %s", paintTools.getPaintTools().getSelectedItem().toString() )) ;
		if( paintTools.getPaintTools().getSelectedIndex() == 0 ) {
			paintArea.setCurrentMethod( "setBrush" ) ; 
			fullfillment.getFullfillment().setEnabled( false ) ;
			fullfillment.getFullfillment().setSelected( false ) ;
		}
		else if( paintTools.getPaintTools().getSelectedIndex() == 1 ) {
			paintArea.setCurrentMethod( "setLinear" ) ;
			fullfillment.getFullfillment().setEnabled( true ) ;
		}
		else if( paintTools.getPaintTools().getSelectedIndex() == 2 ) {
			paintArea.setCurrentMethod( "setOval" ) ;
			fullfillment.getFullfillment().setEnabled( true ) ;
		}
		else if( paintTools.getPaintTools().getSelectedIndex() == 3 ) {
			paintArea.setCurrentMethod( "setRec" ) ;
			fullfillment.getFullfillment().setEnabled( true ) ;
		}
		else {
			paintArea.setCurrentMethod( "setRoundRec" ) ;
			fullfillment.getFullfillment().setEnabled( true ) ;
		}
		
	}
	/*  �Q��ButtonGroup��getElements()���oButton���󪺦C�|��A
	 *  �ΰj��BAbstractButton��nextElement()�PisSelected()�@�@�ˬd�ҿ襤������ 
	 */
	public void BrushSizeControl() {
		for( Enumeration<AbstractButton> buttons = brushSize.getBrushButtonGroup().getElements() ; buttons.hasMoreElements() ; ) {
			AbstractButton button = buttons.nextElement() ; 
			if( button.isSelected() ) {
				if(button.getText() == "�p") {
					paintArea.setCurrentPaintSize(4) ;
				}
				else if(button.getText() == "��") {
					paintArea.setCurrentPaintSize(8) ;
				}
				else {
					paintArea.setCurrentPaintSize(12) ;
				}
				System.out.println( String.format( "��� %s ����", button.getText() ) ) ;
			}	
		}
	}
	public void FullfillmentControl() {
		if( fullfillment.getFullfillment().isSelected() ) {
			paintArea.setFullfillment(true) ;
			System.out.println( "��ܶ�" ) ;
		}
		else {
			paintArea.setFullfillment(false) ;
			System.out.println( "������" ) ;
		}
	}
	public void ForegroundControl() {  // �Ы�JColorChooser��Dialog
		System.out.println(String.format( "�I�� %s", buttonPane.getForegroundButton().getText() )) ;
		dialog = JColorChooser.createDialog(paintArea, "�e������", true, colorChooser, eventPerform.getColorChooserHDL(), null ) ;
		dialog.setName( "ForeColor" ) ;
		dialog.setVisible(true) ;
		
	}
	public void BackgroundControl() {  // �Ы�JColorChooser��Dialog
		System.out.println(String.format( "�I�� %s", buttonPane.getBackgroundButton().getText() )) ;
		dialog = JColorChooser.createDialog(paintArea, "�I������", true, colorChooser, eventPerform.getColorChooserHDL(), null ) ;
		dialog.setName( "BackColor" ) ;
		dialog.setVisible(true) ;
	}
	public void ColorPass() {
		if( dialog.getName() == "ForeColor" ) {
			paintArea.setForeColor( colorChooser.getColor() ) ;
		}
		else {
			paintArea.setBackColor( colorChooser.getColor() ) ;
		}
	}
	public void CleanButtonControl() {  // �έI����\�L�òM���x�s���
		System.out.println(String.format(
				  "�I�� %s", buttonPane.getCleanButton().getText() )) ;
		Graphics paint = paintArea.getGraphics() ;
		paint.setColor( paintArea.getBackground() ) ;
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize() ;  // ���o�ù��j�p
		paint.fillRect( 0, 0, screenSize.width, screenSize.height ) ;
		paintArea.getSections().clear() ;
	}
	public void EraserControl() {
		
		if( paintArea.getForeColor() != paintArea.getBackground() && paintArea.getBackColor() != paintArea.getBackground() ) {
			this.foreColor = paintArea.getForeColor() ;
			this.backColor = paintArea.getBackColor() ;
			System.out.println(String.format(
				  "�I�� %s�A�A���I���i����", buttonPane.getEraserButton().getText() )) ;
			paintArea.setForeColor( paintArea.getBackground() ) ;
			paintArea.setBackColor( paintArea.getBackground() ) ;
		}
		else {
			System.out.println( "�w�ܦ^�e��" );
			paintArea.setForeColor( this.foreColor ) ;
			paintArea.setBackColor( this.backColor ) ;
		}
		
	}
	public void BackControl() {  // �����@�ո��
		System.out.println(String.format(
				  "�I�� %s", buttonPane.getBackButton().getText() )) ;
		if( paintArea.getSections().size() != 0 ) {
			paintArea.getSections().remove( paintArea.getSections().size()-1 ) ;
			paintArea.repaint() ;
		}
		else {
			System.out.println( "�w�L�W�@�B" ) ;
		}
		
	}
	
}
