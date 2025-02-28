package ninja.mspp.core.model.ms;

import java.awt.Image;

public class Peak extends Point {
	private String annotation;
	private double start;
	private double end;
	private Image image;

	public Peak(double x, double y, double start, double end) {
		super(x, y);
		this.start = start;
		this.end = end;
		this.annotation = null;
		this.image = null;
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
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
}
