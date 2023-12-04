// By 106403052 ��ޤGB ���~��
// �[���D�G�W�@�B

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


public class PaintArea extends JPanel{  // �w�q�e����(Panel)
	
	private PaintArea paintArea ;
	private final StatusBar statusPane ; // �ŧi�Ψө�ǤJ��Panel��reference
	
	private final ArrayList<PaintSection> sections = new ArrayList<PaintSection>() ;
	private int flag = 0 ;
	
	private String currentMethod = "setBrush" ; //�إ߷sPaintSection�ɥΪ�Method�W (�קKrepaint��paintMethod�Q�ﱼ)
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
		
		private final ArrayList<Point2D.Float> points ;  // �w�q�@��ø�e�һݸ��
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
			// �u���~�[�]�w
			if( method == "setLinear" ) { 
				this.backColor = null ;
				if( fullfillment ) {
					stroke = new BasicStroke( size ) ;	
				}
				else {  // �Ѽƨ̧Ǭ��u�����ʲӡB���ݧΪ��B�s���I�Ϊ��B�s���I�Y�X�̤j���סB���u�q�����ׯx�}�B���u�q�w�]�}�l�B
					stroke = new BasicStroke( size, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 10.0f, dash, 0 ) ;	
				}
			}
			else {  // �D���u�����]�w��u
				stroke = new BasicStroke( size ) ;	
				
				if( fullfillment ) {
					this.backColor = backColor ;
				}
				else {
					this.backColor = null ;
				}
			}
		}		
		
		public void addPoint(Point point) { points.add( new Point2D.Float( point.x, point.y ) ) ; }  // �N�y�Х[�i�u�q��
		public ArrayList<Point2D.Float> getSection() { return this.points ; }  // getter method
		public String getMethod() { return this.method ; } 
		public int getSize() { return this.size ; } 
		public Color getForeColor() { return this.foreColor ; } 
		public Color getBackColor() { return this.backColor ; } 
		public Boolean getFullfillment() { return this.fullfillment ; } 
		public BasicStroke getStroke() { return this.stroke ; }
		
	}
	
	public PaintArea( StatusBar statusPane ) {  // status����ǤJ
		this.statusPane = statusPane ;          
		
		this.setBackground( Color.white ) ;     
		addMouseListener( new MousePerform() ) ;
		addMouseMotionListener( new MouseLoc() ) ; 
	}
	
	private class MouseLoc implements MouseMotionListener {  // �w�qMouseMotionListener�A�Ϥ���ק�Ƕi�Ӫ�Panel(���A��)����Label(��Ц�m)���]�w
		@Override
		public void mouseMoved(MouseEvent event) {
			statusPane.getStatusBar().setText( String.format("��Ц�m : (%d,%d)",
					event.getX(), event.getY()) ) ;
		}
		@Override
		public void mouseDragged(MouseEvent event) {  // �y�Х[�J�Arepaint:update+paint
			sections.get( sections.size()-1 ).addPoint(event.getPoint()) ;
            repaint() ;	
		}
	}
	private class MousePerform extends MouseAdapter {  // �I���W�[�u�q  
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
		
		try {  // reflection�A�Q��method�W�١B�Ѽ����O�ӴM��method�A�T�w��ӷ����O�P�Ѽƫ�Ĳ�o(invoke)
			for( PaintSection paintSection : sections ) {
				flag = sections.indexOf( paintSection ) ;
				paintMethod = paintSection.getMethod() ;
				paintSize = paintSection.getSize() ;
				foreColor = paintSection.getForeColor() ;
				backColor = paintSection.getBackColor() ;
				fullfillment = paintSection.getFullfillment() ;
				stroke = paintSection.getStroke() ;
				
				if(!sections.get(flag).getSection().isEmpty()) {  // �קKDrag�H�~���ƥ�ɭPrepaint�o�S���I�i�Hø�s
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
	
	public void setBrush(Graphics2D paint) {  // �w�q�U��ø�e�覡�G�e����B�I����B�u���B�Ϊ�
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
	public void setOval(Graphics2D paint2D) {  // ���F���ϧί੹�U�Ӥ�V�e�A�W�[�@�ǧP�w
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
