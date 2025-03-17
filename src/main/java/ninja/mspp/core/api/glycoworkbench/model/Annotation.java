package ninja.mspp.core.api.glycoworkbench.model;

public class Annotation {
	private double mass;
	private double intensity;
	private String name;
	private String image;
	
	public Annotation() {
		this.mass = 0.0;
		this.image = "";
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public double getIntensity() {
		return intensity;
	}

	public void setIntensity(double intensity) {
		this.intensity = intensity;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
