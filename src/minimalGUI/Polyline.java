package minimalGUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Polyline implements Polypoints {
	
	// MEMBER VARIABLES
	
	private ArrayList<Point> polyline;
	
	
	// CONSTRUCTORS
	
	public Polyline() {
		setPoints(new ArrayList<Point>());
	}
	
	public Polyline(ArrayList<Point> polyline) {
		this.polyline = polyline;
	}
	
	
	// GETTERS & SETTERS
	
	public ArrayList<Point> getPoints() {
		return polyline;
	}
	
	public Point get(int i) {
		return polyline.get(i);
	}
	
	public int getPointCount() {
		return polyline.size();
	}
	
	public void setPoints(ArrayList<Point> points) {
		this.polyline = points;
	}
		
	//public void set(int i, Point p) {
	//	this.polyline.i = p;
	//}
	
	public String getType() { return "POLYLINE"; }
	
	
	// METHODS
	
	public boolean add(Point e) {
		return getPoints().add(e);
	}
	
	public String toString() {
		return Arrays.toString(getPoints().toArray());
	}
	
	public boolean checkValid() {
		return getPoints().size() >= 2;
	}
	
	public double getArea() {
		return 0.0;
	}
	
	public double getLength() {
		Iterator<Point> pointIterator = getPoints().iterator();
		Point lastPoint = pointIterator.next();
		Double distance = 0.0;
		while (pointIterator.hasNext()) {
			Point p = pointIterator.next();
			distance += lastPoint.distance(p);
			lastPoint = p;
		}
		return distance;
	}
	
	public void clear() {
		polyline.clear();
	}

}
