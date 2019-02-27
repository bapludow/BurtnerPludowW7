package minimalGUI;

public class Circle implements Geometry{
	
	// MEMBER VARIABLES
	
	private Point center;
	private double radius;
	
	// CONSTRUCTORS
	
	public Circle(Point p, double r) {
		this.center = p;
		this.radius = r;
	}
	
	public void setCenter(Point p) {
		this.center = p;
	}
	public void setRadius(double r) {
		this.radius = r;
	}
	
	// GETTERS & SETTERS
	
	public double getRadius() { return radius; } 
	
	public String getType() { return "CIRCLE"; }
	
		
	// METHODS
	
	public double getLength() {
		return 2*Math.PI*radius; // changed
	}
	
	public double getArea() {
		return Math.PI*Math.pow(radius, 2);
	}
	
	public Point getCenter() {
		return center; // changed to "center" from "this.center"
	}
		
}
