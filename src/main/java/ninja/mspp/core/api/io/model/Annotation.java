package ninja.mspp.core.api.io.model;

public class Annotation {
	private String id;
	private double mass;
	private double intensity;
	private String name;
	private String image;
	
	public Annotation() {
		this.id = "";
		this.mass = 0.0;
		this.image = "";
	}
	
	public String getId() {
		return id;
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
	
	public void setId(String id) {
		this.id = id;
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
