/* Notes/to do:
 * - how to move where buttons are placed?
 * - add way for user to type in POI name
 * - add snap to close path
 */

package minimalGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MinimalGUIFileExample extends JPanel implements MouseListener, MouseMotionListener, ActionListener {

	// ATTRIBUTES
	private static BufferedImage campus;
	private StringBuffer text;
	
	private int x=0,y=0; // variables to store mouse coordinates
	private String n="."; // POI names will be stored here
	private int snapBuffer = 15; // distance from POI to snap path or display name
	
	private ArrayList<POI> pois = new ArrayList<POI>();
	private Polyline path = new Polyline();
	
	private JButton poiButton = new JButton("POI entry mode");
	private JButton pathButton = new JButton("Draw a path");
	private JButton pathResetButton = new JButton("Reset the path");

	private boolean drawPOI = true;
	private boolean drawPath = false;
	private boolean drawNames = false;
	
	// CONSTRUCTOR
	public MinimalGUIFileExample() {
		super(true); // enable DoubleBuffering to avoid flickering		
		setPreferredSize(new Dimension(699,500)); // Set the (preferred) size of the panel
		add(poiButton);
		add(pathButton);
		add(pathResetButton);
	}
		
	// PAINT
	public void paintComponent(Graphics g) {
        super.paintComponent(g);    // Paints all other stuff, e.g., background
        
        // All you need to draw the image is the instance of the BufferedImage class.
        g.drawImage(campus, 0, 0, this);
    	
        g.drawString(text.toString(),50, 460);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.black);
        g2d.drawString("Mouse coordinates: "+x+", "+y, 15, 30);
        
        // draw in path if that is turned on
        if(drawPath) {
        	int radius = 5;
        	g2d.setColor(Color.red);
        	
        	//Point lastPt = new Point();
    		//lastPt = path.get(path.getPointCount()-1);
    		//if (lastPt.distance(path.get(0)) <= snapBuffer) {
    		//	path.set((path.getPointCount()-1), path.get(0));
    		//}
        	
        	for (int i = 0; i < path.getPointCount()-1; i++) {
        		g.drawLine(
        				(int)(path.get(i)).getX(),
        				(int)(path.get(i)).getY(),
        				(int)(path.get(i+1)).getX(),
        				(int)(path.get(i+1)).getY());
        	}
        	
        	for (int i=0; i < path.getPointCount(); i++) {   		
        		g2d.fillOval((int)path.get(i).getX()-radius, (int)path.get(i).getY()-radius, radius*2, radius*2);
        		}
        }

        for (int i = 0; i < pois.size(); i++) {
        	int radius = 4;
        	g2d.setColor(Color.black);
        	g2d.fillOval((int)pois.get(i).getX()-radius, (int)pois.get(i).getY()-radius, radius*2, radius*2);
        	for (int j = 0; j < path.getPointCount(); j++) {
        		if(pois.get(i).getX() == path.get(j).getX() &&
        				pois.get(i).getY() == path.get(j).getY()) {
        			g2d.drawString(pois.get(i).getName(), (int)pois.get(i).getX()-radius*2, (int)pois.get(i).getY()-radius*2);
        		}
			}
        }   
	}
	
	// throws IOException tells Java that this method may result in an error
	public static void main(String[] args) throws IOException{
		
		MinimalGUIFileExample gui = new MinimalGUIFileExample();
		
		// Load the image (this time we do not handle the exception but throw it somewhere else ;-))
		campus = ImageIO.read(new File("./campus.png"));
		
		// Add active listeners & mouse listeners
		gui.poiButton.addActionListener(gui);
		gui.pathButton.addActionListener(gui);
		gui.pathResetButton.addActionListener(gui);
		gui.addMouseListener(gui);
		gui.addMouseMotionListener(gui);
		
		// You always need a frame to place other components such as panels or buttons
		JFrame frame = new JFrame("A UCSB campus map");
		
		// Exit on close
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Add an instance of the MinimalDrawingGUI to the frame.
		frame.add(gui);
				
		// Set the size, arrange components, and display the frame.
		frame.pack();
		frame.setVisible(true);
		
		gui.text = new StringBuffer();
		
		//start of error handling
        try{
        	FileReader input = new FileReader("./test.txt");
        	BufferedReader bufRead = new BufferedReader(input);
        	int aChar = bufRead.read();
        	gui.text.append((char)aChar);
        	while (aChar != -1) {
				aChar = bufRead.read();
				if(aChar==10){ // Stop at the line feed. Windows users may have to change this
					break;
				}
				gui.text.append((char)aChar);
			}
			bufRead.close();
			input.close();
        }
        
       // Do this if things go wrong.
        catch(Exception e){
        	System.out.println("Something went wrong: "+e);
        }
	}
	
	// OVERRIDES FOR DAYS
	@Override
	public void mousePressed(MouseEvent arg0) {}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {}		

	@Override
	public void mouseDragged(MouseEvent arg0) {}

	@Override
	public void mouseMoved(MouseEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent arg0) { // Save the mouse coordinates in the member variables x and y 
		x =  arg0.getX();
		y =  arg0.getY();
		
		if(drawPOI == true) {
			n = Integer.toString(pois.size()); // need to make this a way that user can type a name
			pois.add(new POI(x, y, n));
		}
		
		if(drawPath == true) {
			Point pathPoint = new Point(x,y);
			for (int i = 0; i < pois.size(); i++) {
				if(pathPoint.distance(pois.get(i)) <= snapBuffer) {
					pathPoint.setX(pois.get(i).getX());
					pathPoint.setY(pois.get(i).getY());
				}
			}
			path.add(pathPoint);
		}
		
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == poiButton) {
			drawPOI = true;
			drawNames = false;
			drawPath = false;
			repaint();
		}
		
		if(e.getSource() == pathButton) {
			drawPath = true;
			drawPOI = false;
			drawNames = false;
			repaint();
		}
		
		if(e.getSource() == pathResetButton) {
			path.clear();
			repaint();
		}	
	}
	
}
