package minimalGUI;

public class Point implements Geometry {
	
	// MEMBER VARIABLES
	
	private double x, y;
	
	
	// CONSTRUCTORS
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Point() {
		this(0, 0);
	}
	
	
	// GETTERS & SETTERS
	
	public double getX() {return x;}
	
	public double getY() {return y;}
	
	public void setX(double x) {this.x = x;}
	
	public void setY(double y) {this.y = y;}
	
	public String getType(){ return "POINT"; }
	
	
	// METHODS
	
	public String toString() {
		return getType() + " (" + x + ", " + y + ")"; 
	}
	
	public double getLength() {return 0.0;}
	
	public double distance(Point p) {
		return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y,  2));
	}
	
	public double getArea () {
		return 0.0;
	}
}
