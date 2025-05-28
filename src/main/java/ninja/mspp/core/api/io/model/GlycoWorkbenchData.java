package ninja.mspp.core.api.io.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GlycoWorkbenchData {
	private String id;
	private int currentIndex;
	private List<Scan> scans;
	private List<Annotation> annotations;
	
	public GlycoWorkbenchData() {
		this.id = UUID.randomUUID().toString();
		this.scans = new ArrayList<Scan>();
		this.currentIndex = 0;
		this.annotations = new ArrayList<Annotation>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public GlycoWorkbenchData(List<Scan> scans) {
		this.scans = scans;
	}
	
	public List<Scan> getScans() {
		return scans;
	}
	
	public void setScans(List<Scan> scans) {
		this.scans = scans;
	}
	
	public void addScan(Scan scan) {
		this.scans.add(scan);
	}
	
	public void removeScan(Scan scan) {
		this.scans.remove(scan);
	}
	
	public void clearScans() {
		this.scans.clear();
	}
	
	public int getCurrentIndex() {
		return currentIndex;
	}
	
	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}
	
	public List<Annotation> getAnnotations() {
		return this.annotations;
	}
	
	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}
}
