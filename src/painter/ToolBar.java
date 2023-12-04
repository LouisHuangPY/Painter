// By 106403052 資管二B 黃品毅

package painter;

import java.awt.* ;
import java.util.Enumeration;

import javax.swing.* ;

public class ToolBar extends JPanel{  // 定義工具列(Panel)
	
	private final PaintArea paintArea ;  // 將paintArea物件傳入，以便利用他的method調整其內部屬性
 	private JColorChooser colorChooser ;  
	private Dialog dialog ;  // 此為存放colorChooser所產生之Dialog的reference
	private Color foreColor ;
	private Color backColor ;
	
	private final JLabel comboBox ;     // 宣告工具列的Label
	private final JLabel radioButton ;  
	private final JLabel checkBox ;     
	
	private final PaintTools paintTools ;   // 宣告工具列中的Panel
	private final BrushSize brushSize ;    
	private final Fullfillment fullfillment ; 
	private final ButtonPane buttonPane ;   
	
	private final EventPerform eventPerform ;  // 此為將Listener集中的class
	
	public ToolBar( PaintArea paintArea ) {
		
		this.setBackground( Color.lightGray ) ;  
		this.setLayout( new GridBagLayout() ) ;  
		GridBagConstraints toolPane = new GridBagConstraints() ;  // 建立GridBagLayout用來設定物件屬性的物件
		
		this.paintArea = paintArea ;
		comboBox = new JLabel("繪圖工具") ;
		radioButton = new JLabel("筆刷大小") ;
		checkBox = new JLabel("填滿") ;
		paintTools = new PaintTools() ;
		brushSize = new BrushSize() ;
		fullfillment = new Fullfillment() ;
		buttonPane = new ButtonPane() ;
		eventPerform = new EventPerform( this ) ;  // 將toolBar物件傳入，使其呼叫ToolBar的Method
		colorChooser = new JColorChooser() ;
		
		paintTools.getPaintTools().addItemListener( eventPerform.getPaintToolsHDL() ) ;  // 將EventPerform中定義的Listener加進各物件
		brushSize.getSmallBrush().addItemListener( eventPerform.getBrushSizeHDL() ) ;
		brushSize.getMediumBrush().addItemListener( eventPerform.getBrushSizeHDL() ) ;
		brushSize.getLargeBrush().addItemListener( eventPerform.getBrushSizeHDL() ) ;
		fullfillment.getFullfillment().addItemListener( eventPerform.getFullfillmentHDL() ) ;
		buttonPane.getForegroundButton().addActionListener( eventPerform.getGroundButtonsHDL() ) ;
		buttonPane.getBackgroundButton().addActionListener( eventPerform.getGroundButtonsHDL() );
		buttonPane.getCleanButton().addActionListener( eventPerform.getCleanButtonHDL() ) ;
		buttonPane.getEraserButton().addActionListener( eventPerform.getEraserButtonHDL() ) ;
		buttonPane.getBackButton().addActionListener( eventPerform.getBackButtonHDL() ) ;
		
		comboBox.setFont(new Font("Serif", Font.BOLD, 16)) ;  // Label字型設定
		radioButton.setFont(comboBox.getFont()) ;
		checkBox.setFont(comboBox.getFont()) ;
		
		//以下區域都是利用GridBagLayout設定各物件的排版
		
		toolPane.weightx = 0.1 ;  // 設定物件x軸權重，也就是各個格子邊界與此Panel邊界間的間隔長度在x軸的比例分配(視窗縮放仍有效果)，數字本身跟其他權重比較才有意義
		toolPane.gridx = 0 ;      // 設定格子在二維格子陣列中的x座標
		toolPane.gridy = 0 ;      // 設定格子在二維格子陣列中的y座標
		toolPane.anchor = GridBagConstraints.WEST ; // 設定若物件未佔滿格子，則物件在格子中的方位
		toolPane.insets = new Insets( 15,20,4,0 ) ; // 設定物件與格子間的間距，藉此調整細微排版，數字依序為上左下右
		this.add( comboBox, toolPane ) ;            // 按照GridBagLayout的設定加入此Panel
		
		// 未修改的屬性則保持相同，如toolPane.weightx
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
		
		toolPane.weightx = 2 ;     // 藉由與邊界距離的調整，將整個工具列維持在左方
		toolPane.gridx = 3 ;
		toolPane.gridy = 0 ;
		toolPane.gridheight = 2 ;  // 設定此格子於y軸所能跨越的格子數(使之能佔兩個column)
		toolPane.insets = new Insets( 0,-10,0,0 ) ;
		this.add( buttonPane, toolPane ) ;
		
	}
	
	public void PaintToolsControl() {  // 定義各元件產生Event後執行的方法，大多數方法都是用paintArea的method去修改其屬性
		System.out.println(String.format(
				  "選擇 %s", paintTools.getPaintTools().getSelectedItem().toString() )) ;
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
	/*  利用ButtonGroup的getElements()取得Button物件的列舉後，
	 *  用迴圈、AbstractButton的nextElement()與isSelected()一一檢查所選中之物件 
	 */
	public void BrushSizeControl() {
		for( Enumeration<AbstractButton> buttons = brushSize.getBrushButtonGroup().getElements() ; buttons.hasMoreElements() ; ) {
			AbstractButton button = buttons.nextElement() ; 
			if( button.isSelected() ) {
				if(button.getText() == "小") {
					paintArea.setCurrentPaintSize(4) ;
				}
				else if(button.getText() == "中") {
					paintArea.setCurrentPaintSize(8) ;
				}
				else {
					paintArea.setCurrentPaintSize(12) ;
				}
				System.out.println( String.format( "選擇 %s 筆刷", button.getText() ) ) ;
			}	
		}
	}
	public void FullfillmentControl() {
		if( fullfillment.getFullfillment().isSelected() ) {
			paintArea.setFullfillment(true) ;
			System.out.println( "選擇填滿" ) ;
		}
		else {
			paintArea.setFullfillment(false) ;
			System.out.println( "取消填滿" ) ;
		}
	}
	public void ForegroundControl() {  // 創建JColorChooser的Dialog
		System.out.println(String.format( "點選 %s", buttonPane.getForegroundButton().getText() )) ;
		dialog = JColorChooser.createDialog(paintArea, "前景色選擇", true, colorChooser, eventPerform.getColorChooserHDL(), null ) ;
		dialog.setName( "ForeColor" ) ;
		dialog.setVisible(true) ;
		
	}
	public void BackgroundControl() {  // 創建JColorChooser的Dialog
		System.out.println(String.format( "點選 %s", buttonPane.getBackgroundButton().getText() )) ;
		dialog = JColorChooser.createDialog(paintArea, "背景色選擇", true, colorChooser, eventPerform.getColorChooserHDL(), null ) ;
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
	public void CleanButtonControl() {  // 用背景色蓋過並清除儲存資料
		System.out.println(String.format(
				  "點選 %s", buttonPane.getCleanButton().getText() )) ;
		Graphics paint = paintArea.getGraphics() ;
		paint.setColor( paintArea.getBackground() ) ;
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize() ;  // 取得螢幕大小
		paint.fillRect( 0, 0, screenSize.width, screenSize.height ) ;
		paintArea.getSections().clear() ;
	}
	public void EraserControl() {
		
		if( paintArea.getForeColor() != paintArea.getBackground() && paintArea.getBackColor() != paintArea.getBackground() ) {
			this.foreColor = paintArea.getForeColor() ;
			this.backColor = paintArea.getBackColor() ;
			System.out.println(String.format(
				  "點選 %s，再次點擊可取消", buttonPane.getEraserButton().getText() )) ;
			paintArea.setForeColor( paintArea.getBackground() ) ;
			paintArea.setBackColor( paintArea.getBackground() ) ;
		}
		else {
			System.out.println( "已變回畫筆" );
			paintArea.setForeColor( this.foreColor ) ;
			paintArea.setBackColor( this.backColor ) ;
		}
		
	}
	public void BackControl() {  // 移除一組資料
		System.out.println(String.format(
				  "點選 %s", buttonPane.getBackButton().getText() )) ;
		if( paintArea.getSections().size() != 0 ) {
			paintArea.getSections().remove( paintArea.getSections().size()-1 ) ;
			paintArea.repaint() ;
		}
		else {
			System.out.println( "已無上一步" ) ;
		}
		
	}
	
}
