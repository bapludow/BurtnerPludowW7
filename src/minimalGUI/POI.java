package minimalGUI;

public class POI extends Point {
	
	// ATTRIBUTES
	private String name;
	
	// CONSTRUCTORS
	public POI(double x, double y, String n) {
		super(x, y);
		this.name = n;
	}
	
	public POI() {
		super(0,0);
		this.name = "undefined";
	}
	
	// GETTERS & SETTERS
	public String getName() {return name;}
	public void setName(String n) {this.name = n;}
	public String getType() { return "POI";}
		
	// Methods
	public String toString() {
		return getType() +" "+ name +" ("+ this.getX() +", "+ this.getY() +")";
	}

}
