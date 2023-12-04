// By 106403052 資管二B 黃品毅
// 加分題：上一步

package painter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.* ;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javax.swing.JPanel;


public class PaintArea extends JPanel{  // 定義畫布區(Panel)
	
	private PaintArea paintArea ;
	private final StatusBar statusPane ; // 宣告用來放傳入的Panel的reference
	
	private final ArrayList<PaintSection> sections = new ArrayList<PaintSection>() ;
	private int flag = 0 ;
	
	private String currentMethod = "setBrush" ; //建立新PaintSection時用的Method名 (避免repaint時paintMethod被改掉)
	private String paintMethod = currentMethod ;
	private int currentPaintSize = 4 ;
	private int paintSize = currentPaintSize ;
	private Color currentForeColor = Color.black ;
	private Color foreColor = currentForeColor ;
	private Color currentBackColor = Color.black ;
	private Color backColor = currentForeColor ;
	private Boolean fullfillment = false ;
	private BasicStroke stroke = new BasicStroke( paintSize ) ;	
	private Shape shape ;
	
	public final class PaintSection {
		
		private final ArrayList<Point2D.Float> points ;  // 定義一筆繪畫所需資料
		private final String method ;
		private final int size ;
		private final Color foreColor ;
		private final Color backColor ;
		private final Boolean fullfillment ;
		private BasicStroke stroke ;
		private final float[] dash = { 10.0f, 10.0f } ;
		
		public PaintSection(ArrayList<Point2D.Float> points, String method, int size, Color foreColor, Color backColor, Boolean fullfillment) {
			this.points = points ;
			this.method = method ;
			this.size = size ;
			this.foreColor = foreColor ;
			this.fullfillment = fullfillment ;
			// 線條外觀設定
			if( method == "setLinear" ) { 
				this.backColor = null ;
				if( fullfillment ) {
					stroke = new BasicStroke( size ) ;	
				}
				else {  // 參數依序為線條的粗細、尾端形狀、連接點形狀、連接點凸出最大長度、虛實線段的長度矩陣、虛實線段預設開始處
					stroke = new BasicStroke( size, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 10.0f, dash, 0 ) ;	
				}
			}
			else {  // 非直線不須設定虛線
				stroke = new BasicStroke( size ) ;	
				
				if( fullfillment ) {
					this.backColor = backColor ;
				}
				else {
					this.backColor = null ;
				}
			}
		}		
		
		public void addPoint(Point point) { points.add( new Point2D.Float( point.x, point.y ) ) ; }  // 將座標加進線段內
		public ArrayList<Point2D.Float> getSection() { return this.points ; }  // getter method
		public String getMethod() { return this.method ; } 
		public int getSize() { return this.size ; } 
		public Color getForeColor() { return this.foreColor ; } 
		public Color getBackColor() { return this.backColor ; } 
		public Boolean getFullfillment() { return this.fullfillment ; } 
		public BasicStroke getStroke() { return this.stroke ; }
		
	}
	
	public PaintArea( StatusBar statusPane ) {  // status物件傳入
		this.statusPane = statusPane ;          
		
		this.setBackground( Color.white ) ;     
		addMouseListener( new MousePerform() ) ;
		addMouseMotionListener( new MouseLoc() ) ; 
	}
	
	private class MouseLoc implements MouseMotionListener {  // 定義MouseMotionListener，使之能修改傳進來的Panel(狀態欄)內的Label(游標位置)的設定
		@Override
		public void mouseMoved(MouseEvent event) {
			statusPane.getStatusBar().setText( String.format("游標位置 : (%d,%d)",
					event.getX(), event.getY()) ) ;
		}
		@Override
		public void mouseDragged(MouseEvent event) {  // 座標加入，repaint:update+paint
			sections.get( sections.size()-1 ).addPoint(event.getPoint()) ;
            repaint() ;	
		}
	}
	private class MousePerform extends MouseAdapter {  // 點擊增加線段  
		@Override
		public void mousePressed(MouseEvent event) { 
			sections.add( new PaintSection( new ArrayList<Point2D.Float>(), currentMethod, currentPaintSize, currentForeColor, currentBackColor, fullfillment ) ) ;
			System.out.println( sections.size() ) ;
		}
	}
	
	@Override
	public void paintComponent(Graphics paint) {
		super.paintComponent( paint ) ;
		Graphics2D paint2D = (Graphics2D)paint ;
		
		try {  // reflection，利用method名稱、參數類別來尋找method，確定其來源類別與參數後觸發(invoke)
			for( PaintSection paintSection : sections ) {
				flag = sections.indexOf( paintSection ) ;
				paintMethod = paintSection.getMethod() ;
				paintSize = paintSection.getSize() ;
				foreColor = paintSection.getForeColor() ;
				backColor = paintSection.getBackColor() ;
				fullfillment = paintSection.getFullfillment() ;
				stroke = paintSection.getStroke() ;
				
				if(!sections.get(flag).getSection().isEmpty()) {  // 避免Drag以外的事件導致repaint卻沒有點可以繪製
				Method method = PaintArea.class.getDeclaredMethod( paintMethod, Graphics2D.class ) ;
				method.invoke( paintArea, paint2D ) ;
				}	
			}
			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Graphics getGraphics(Graphics paint) {  // getter method
		return paint ;
	}
	public Color getForeColor() {
		return this.currentForeColor ;
	}public Color getBackColor() {
		return this.currentBackColor ;
	}
	public ArrayList<PaintSection> getSections() {
		return sections ;
	}
	public void setThisObj(PaintArea paintArea) {
		this.paintArea = paintArea ;
	}
	public void setCurrentMethod(String currentMethod) {
		this.currentMethod = currentMethod ;
	}
	public void setCurrentPaintSize(int paintSize) {
		this.currentPaintSize = paintSize ;
	}
	public void setForeColor(Color color) {
		this.currentForeColor = color ;
	}
	public void setBackColor(Color color) {
		this.currentBackColor = color ;
	}
	public void setFullfillment(Boolean fullfillment) {
		this.fullfillment = fullfillment ;
	}
	
	public void setBrush(Graphics2D paint) {  // 定義各個繪畫方式：前景色、背景色、線條、形狀
		paint.setColor( sections.get(flag).getForeColor() ) ;
		for(Point2D.Float point : sections.get(flag).getSection()) {	
				paint.fillOval( (int)point.getX(), (int)point.getY(), this.paintSize, this.paintSize ) ;
		}
	}
	public void setLinear(Graphics2D paint2D) {
		paint2D.setStroke( sections.get(flag).getStroke() ) ;
		paint2D.setColor( sections.get(flag).getForeColor() ) ;
		Shape shape = new Line2D.Float(sections.get(flag).getSection().get(0), sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ) ) ;
		paint2D.draw(shape) ;
	}
	public void setOval(Graphics2D paint2D) {  // 為了讓圖形能往各個方向畫，增加一些判定
		if( sections.get(flag).getSection().get(0) == sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ) ) {}
		else {
			paint2D.setStroke( sections.get(flag).getStroke() ) ;
			paint2D.setColor( sections.get(flag).getForeColor() ) ;
			if( sections.get(flag).getSection().get(0).x <= sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).x
					&& sections.get(flag).getSection().get(0).y > sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).y ) {
				shape = new Ellipse2D.Float( sections.get(flag).getSection().get(0).x, 
						sections.get(flag).getSection().get(sections.get(flag).getSection().size()-1).y,
						Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).x - sections.get(flag).getSection().get(0).x),
						Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).y - sections.get(flag).getSection().get(0).y) ) ;
			}
			else if( sections.get(flag).getSection().get(0).x > sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).x
					&& sections.get(flag).getSection().get(0).y >= sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).y ) {
				shape = new Ellipse2D.Float( sections.get(flag).getSection().get(sections.get(flag).getSection().size()-1).x, 
						sections.get(flag).getSection().get(sections.get(flag).getSection().size()-1).y,
						Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).x - sections.get(flag).getSection().get(0).x),
						Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).y - sections.get(flag).getSection().get(0).y) ) ;
			}
			else if( sections.get(flag).getSection().get(0).x > sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).x
					&& sections.get(flag).getSection().get(0).y < sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).y ) {
				shape = new Ellipse2D.Float( sections.get(flag).getSection().get(sections.get(flag).getSection().size()-1).x, 
						sections.get(flag).getSection().get(0).y,
						Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).x - sections.get(flag).getSection().get(0).x),
						Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).y - sections.get(flag).getSection().get(0).y) ) ;
			}
			else {
				shape = new Ellipse2D.Float( sections.get(flag).getSection().get(0).x, sections.get(flag).getSection().get(0).y,
					Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).x - sections.get(flag).getSection().get(0).x),
					Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).y - sections.get(flag).getSection().get(0).y) ) ;
			}
			paint2D.draw(shape) ;
			if( sections.get(flag).getFullfillment() ) {
				paint2D.setColor( sections.get(flag).getBackColor() ) ;
				paint2D.fill(shape);
			}
		}
	}	
	public void setRec(Graphics2D paint2D) {
		if( sections.get(flag).getSection().get(0) == sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ) ) {}
		else {
			paint2D.setStroke( sections.get(flag).getStroke() ) ;
			paint2D.setColor( sections.get(flag).getForeColor() ) ;
			if( sections.get(flag).getSection().get(0).x <= sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).x
					&& sections.get(flag).getSection().get(0).y > sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).y ) {
				shape = new Rectangle2D.Float( sections.get(flag).getSection().get(0).x, 
						sections.get(flag).getSection().get(sections.get(flag).getSection().size()-1).y,
						Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).x - sections.get(flag).getSection().get(0).x),
						Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).y - sections.get(flag).getSection().get(0).y) ) ;
			}
			else if( sections.get(flag).getSection().get(0).x > sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).x
					&& sections.get(flag).getSection().get(0).y >= sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).y ) {
				shape = new Rectangle2D.Float( sections.get(flag).getSection().get(sections.get(flag).getSection().size()-1).x, 
						sections.get(flag).getSection().get(sections.get(flag).getSection().size()-1).y,
						Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).x - sections.get(flag).getSection().get(0).x),
						Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).y - sections.get(flag).getSection().get(0).y) ) ;
			}
			else if( sections.get(flag).getSection().get(0).x > sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).x
					&& sections.get(flag).getSection().get(0).y < sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).y ) {
				shape = new Rectangle2D.Float( sections.get(flag).getSection().get(sections.get(flag).getSection().size()-1).x, 
						sections.get(flag).getSection().get(0).y,
						Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).x - sections.get(flag).getSection().get(0).x),
						Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).y - sections.get(flag).getSection().get(0).y) ) ;
			}
			else {
				shape = new Rectangle2D.Float( sections.get(flag).getSection().get(0).x, sections.get(flag).getSection().get(0).y,
					Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).x - sections.get(flag).getSection().get(0).x),
					Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).y - sections.get(flag).getSection().get(0).y) ) ;
			}
			paint2D.draw(shape) ;
			if( sections.get(flag).getFullfillment() ) {
				paint2D.setColor( sections.get(flag).getBackColor() ) ;
				paint2D.fill(shape);
			}
		}
	}	
	public void setRoundRec(Graphics2D paint2D) {
		if( sections.get(flag).getSection().get(0) == sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ) ) {}
		else {
			paint2D.setStroke( sections.get(flag).getStroke() ) ;
			paint2D.setColor( sections.get(flag).getForeColor() ) ;
			if( sections.get(flag).getSection().get(0).x <= sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).x
					&& sections.get(flag).getSection().get(0).y > sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).y ) {
				shape = new RoundRectangle2D.Float( sections.get(flag).getSection().get(0).x, 
						sections.get(flag).getSection().get(sections.get(flag).getSection().size()-1).y,
						Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).x - sections.get(flag).getSection().get(0).x),
						Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).y - sections.get(flag).getSection().get(0).y), 
						10.0f, 10.0f ) ;
			}
			else if( sections.get(flag).getSection().get(0).x > sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).x
					&& sections.get(flag).getSection().get(0).y >= sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).y ) {
				shape = new RoundRectangle2D.Float( sections.get(flag).getSection().get(sections.get(flag).getSection().size()-1).x, 
						sections.get(flag).getSection().get(sections.get(flag).getSection().size()-1).y,
						Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).x - sections.get(flag).getSection().get(0).x),
						Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).y - sections.get(flag).getSection().get(0).y),
						10.0f, 10.0f ) ;
			}
			else if( sections.get(flag).getSection().get(0).x > sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).x
					&& sections.get(flag).getSection().get(0).y < sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).y ) {
				shape = new RoundRectangle2D.Float( sections.get(flag).getSection().get(sections.get(flag).getSection().size()-1).x, 
						sections.get(flag).getSection().get(0).y,
						Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).x - sections.get(flag).getSection().get(0).x),
						Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).y - sections.get(flag).getSection().get(0).y),
						10.0f, 10.0f ) ;
			}
			else {
				shape = new RoundRectangle2D.Float( sections.get(flag).getSection().get(0).x, sections.get(flag).getSection().get(0).y,
					Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).x - sections.get(flag).getSection().get(0).x),
					Math.abs(sections.get(flag).getSection().get( sections.get(flag).getSection().size()-1 ).y - sections.get(flag).getSection().get(0).y),
					10.0f, 10.0f ) ;
			}
			paint2D.draw(shape) ;
			if( sections.get(flag).getFullfillment() ) {
				paint2D.setColor( sections.get(flag).getBackColor() ) ;
				paint2D.fill(shape);
			}
		}
	}	
}
