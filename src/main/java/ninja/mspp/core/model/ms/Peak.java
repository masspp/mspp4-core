package ninja.mspp.core.model.ms;

public class Peak extends Point {
	private String annotation;
	private double start;
	private double end;

	public Peak(double x, double y, double start, double end) {
		super(x, y);
		this.start = start;
		this.end = end;
		this.annotation = null;
	}

	public String getAnnotation() {
		return annotation;
	}
	
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	
	public double getStart() {
		return start;
	}
	
	public double getEnd() {
		return end;
	}
}
