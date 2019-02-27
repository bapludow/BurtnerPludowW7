package minimalGUI;

public class PointBuffer implements BoundingArea{
	
	// MEMBER VARIABLES
	
	private Circle buffer; // removed "= new Circle(null, 0);"
	private Point p;
	private double radius;
	
	// CONSTRUCTOR
	public PointBuffer(Point p, double radius) {
		this.buffer = new Circle(p, radius);
		this.p = p;
		this.radius = radius;
	}
	
	// GETTERS & SETTERS
	
	public String getType() { return "POINTBUFFER"; }
		
	
	// METHODS
	
	public double getArea() {
		return buffer.getArea();
	}
	
	public double getLength() {
		return buffer.getLength();
	}
	
	public double getRadius() {
		return buffer.getRadius();
	}
	
	public boolean isInside(Point p) {
		if (p.distance(buffer.getCenter()) <= buffer.getRadius()) {return true;}
		else {return false;} // removed "this.buffer" and "this.length"
	}
	
	public Point getCenter() {
		return buffer.getCenter();
	
	//public String getCenter() {
		//return "This buffer is centered at " + p.toString() + " with radius " + radius;
	} // removed "this.p", added "radius"

	public void setRadius(Integer decode) {
		 this.radius = decode;
	}

}
