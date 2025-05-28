package ninja.mspp.core.api.io.model;

import java.util.List;

public class Scan {
	private String id;
	private Integer msLevel;
	private Double rt;
	private Double precursorMz;
	private Double minMz;
	private Double maxMz;
	private Boolean centroidMode;
	
	private List<ScanPoint> points; 
	
	public Scan() {
		this.id = null;
		this.msLevel = null;
		this.rt = null;
		this.precursorMz = null;
	}
	
	public String getId() {
		return id;
	}
	
	public Integer getMsLevel() {
		return msLevel;
	}
	
	public Double getRt() {
		return rt;
	}
	
	public Double getPrecursorMz() {
		return precursorMz;
	}
	
	public List<ScanPoint> getPoints() {
		return points;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setMsLevel(Integer msLevel) {
		this.msLevel = msLevel;
	}
	
	public void setRt(Double rt) {
		this.rt = rt;
	}
	
	public void setPrecursorMz(Double precursorMz) {
		this.precursorMz = precursorMz;
	}
	
	public void setPoints(List<ScanPoint> points) {
		this.points = points;
	}

	public Double getMinMz() {
		return minMz;
	}
	
	public void setMinMz(Double minMz) {
		this.minMz = minMz;
	}
	
	public Double getMaxMz() {
		return maxMz;
	}
	
	public void setMaxMz(Double maxMz) {
		this.maxMz = maxMz;
	}
	
	public Boolean getCentroidMode() {
		return centroidMode;
	}
	
	public void setCentroidMode(Boolean centroidMode) {
		this.centroidMode = centroidMode;
	}
}
