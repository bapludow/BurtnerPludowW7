package minimalGUI;

public class POI extends PointBuffer {
	
	// ATTRIBUTES
	private String name;
	private boolean visited;
	
	// CONSTRUCTORS
	//public POI(double x, double y, String n) {
	//	super(x, y);
	//	this.name = n;
	//}
	
	public POI(Point p, double radius, String name, boolean visited) {
		super(p, radius);
		this.name = name;
		this.visited = visited;
	}
	
	// GETTERS & SETTERS
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public boolean isVisited() {return visited;}
	public void setVisited(boolean visited) {this.visited = visited;}
	public String getType() { return "POI";}

}

