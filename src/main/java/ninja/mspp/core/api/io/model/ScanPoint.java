package ninja.mspp.core.api.io.model;

public class ScanPoint {
	private double x;
	private double y;
	
	public ScanPoint() {
		this.x = 0.0;
		this.y = 0.0;
	}
	
	public ScanPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
}
