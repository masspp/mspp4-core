package ninja.mspp.core.api.glycoworkbench.model;

import java.util.List;

import ninja.mspp.core.api.glycoworkbench.model.ms.GlycoWorkbenchSample;
import ninja.mspp.core.api.glycoworkbench.model.ms.GlycoWorkbenchSpectrum;
import ninja.mspp.core.model.ms.Chromatogram;
import ninja.mspp.core.model.ms.DataPoints;
import ninja.mspp.core.model.ms.Point;
import ninja.mspp.core.model.ms.Sample;
import ninja.mspp.core.model.ms.Spectrum;
import ninja.mspp.core.model.ms.TicChromatogram;

public class Scan {
	private Integer msLevel;
	private Double rt;
	private Double precursorMz;
	private Double minMz;
	private Double maxMz;
	private Boolean centroidMode;
	
	private List<ScanPoint> points; 
	
	public Scan() {
		this.msLevel = null;
		this.rt = null;
		this.precursorMz = null;
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
	
	public static Sample createSample(List<Scan> scans) {
		GlycoWorkbenchSample sample = new GlycoWorkbenchSample();
		
		DataPoints ticPoints = new DataPoints();
		
		int scanNumber = 0;
		for(Scan scan : scans) {
			scanNumber++;
			Spectrum spectrum = new GlycoWorkbenchSpectrum(sample, scanNumber, scan);
			sample.getSpectra().add(spectrum);
			
			if (spectrum.getMsLevel() == 1 && spectrum.getRt() >= 0.0) {
				ticPoints.add(new Point(spectrum.getRt(), spectrum.getTic()));
			}
		}
		
		if(ticPoints.size() > 0) {
			Chromatogram tic = new TicChromatogram(sample);
			sample.getChromatograms().add(tic);	
		}

		return sample;
	}
}
