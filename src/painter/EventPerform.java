// By 106403052 資管二B 黃品毅

package painter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import painter.ToolBar ;

public class EventPerform {  // 將各個Listener集中管理，由toolBar的Method來處理Event觸發
	
	private ToolBar toolBar ; 
	private PaintToolsHDL paintToolsHDL ;
	private BrushSizeHDL brushSizeHDL ;
	private FullfillmentHDL fullfillmentHDL ;
	private GroundButtonsHDL groundButtonsHDL ;
	private ColorChooserHDL colorChooserHDL ;
	private CleanButtonHDL cleanButtonHDL ;
	private EraserButtonHDL eraserButtonHDL ;
	private BackButtonHDL backButtonHDL ;
	
	public EventPerform( ToolBar toolBar ) {
		
		this.toolBar = toolBar ;
		paintToolsHDL = new PaintToolsHDL() ;
		brushSizeHDL = new BrushSizeHDL() ;
		cleanButtonHDL = new CleanButtonHDL() ;
		groundButtonsHDL = new GroundButtonsHDL() ;
		colorChooserHDL = new ColorChooserHDL() ;
		fullfillmentHDL = new FullfillmentHDL() ;
		eraserButtonHDL = new EraserButtonHDL() ;
		backButtonHDL = new BackButtonHDL() ;
		
	}
	private class PaintToolsHDL implements ItemListener {  // ComboBox
		@Override  
		public void itemStateChanged(ItemEvent event) {
			if ( event.getStateChange() == ItemEvent.SELECTED ) {
				toolBar.PaintToolsControl() ;
			}
		}
	} 
	private class BrushSizeHDL implements ItemListener {  // RadioButton
		@Override
		public void itemStateChanged(ItemEvent event) {     
			toolBar.BrushSizeControl() ;
		}
	} 
	private class FullfillmentHDL implements ItemListener {  // CheckBox
		@Override
		public void itemStateChanged(ItemEvent event) {
			toolBar.FullfillmentControl() ;
		}
	}
	private class GroundButtonsHDL implements ActionListener {  // 前景色、背景色用同一個監聽
		@Override
		public void actionPerformed(ActionEvent event)
	    {
			if( event.getActionCommand() == "前景色" ) {
				toolBar.ForegroundControl() ;
			}
			else {
				toolBar.BackgroundControl() ;
			}
	    }
	}
	private class CleanButtonHDL implements ActionListener {  // 清除畫面
		@Override
		public void actionPerformed(ActionEvent event)
	    {
			toolBar.CleanButtonControl() ;
	    }
	} 
	private class ColorChooserHDL implements ActionListener {  // 監聽JColorChooser的Dialog
		@Override
		public void actionPerformed(ActionEvent event)
	    {
			if( event.getActionCommand() == "OK" ) {
				toolBar.ColorPass() ;
			}
			else {
			}
	    }
	} 
	private class EraserButtonHDL implements ActionListener {  // 橡皮擦
		@Override
		public void actionPerformed(ActionEvent event)
	    {
			toolBar.EraserControl() ;
	    }
	} 
	private class BackButtonHDL implements ActionListener {  // 上一步
		@Override
		public void actionPerformed(ActionEvent event)
	    {
			toolBar.BackControl() ;
	    }
	} 
	
	public PaintToolsHDL getPaintToolsHDL() {  // getter method，讓ToolBar給各個物件加Listener
		return paintToolsHDL ;
	}
	public BrushSizeHDL getBrushSizeHDL() {
		return brushSizeHDL ;
	}
	public FullfillmentHDL getFullfillmentHDL() {
		return fullfillmentHDL ;
	}
	public GroundButtonsHDL getGroundButtonsHDL() {
		return groundButtonsHDL ;
	}
	public CleanButtonHDL getCleanButtonHDL() {
		return cleanButtonHDL ;
	}
	public ColorChooserHDL getColorChooserHDL() {
		return colorChooserHDL ;
	}
	public EraserButtonHDL getEraserButtonHDL() {
		return eraserButtonHDL ;
	}
	public BackButtonHDL getBackButtonHDL() {
		return backButtonHDL ;
	}
}
