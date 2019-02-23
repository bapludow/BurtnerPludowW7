package minimalGUI;

import java.util.ArrayList;

public interface Polypoints extends Geometry {

	public ArrayList<Point> getPoints();
	
	public void setPoints(ArrayList<Point> points);
	
	public int getPointCount();
	
}
