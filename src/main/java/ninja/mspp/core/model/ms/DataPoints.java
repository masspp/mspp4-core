package ninja.mspp.core.model.ms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataPoints extends ArrayList<Point> {
	public double findMaxY(double startX, double endX) {
		double maxY = 0.0;
		
		int startIndex = 0;
		int endIndex = this.size() - 1;
		
		if(startX > 0.0) {
			startIndex = Collections.binarySearch(this, new Point(startX, 0.0));
			if(startIndex < 0) {
				startIndex = - startIndex - 1;
			}
		}
		
		if (endX > 0.0) {
			endIndex = Collections.binarySearch(this, new Point(endX, 0.0));
			if (endIndex < 0) {
				endIndex = - endIndex - 2;
			}
		}
		
		for(int i = startIndex; i <= endIndex; i++) {
			double y = this.get(i).getY();
			if(y > maxY) {
				maxY = y;
			}
		}
		return maxY;
	}
	

	public double calculateTotal(double startX, double endX) {
		double total = 0.0;
		
		int startIndex = 0;
		int endIndex = this.size() - 1;
		
		if(startX > 0.0) {
			startIndex = Collections.binarySearch(this, new Point(startX, 0.0));
			if(startIndex < 0) {
				startIndex = - startIndex - 1;
			}
		}
		
		if (endX > 0.0) {
			endIndex = Collections.binarySearch(this, new Point(endX, 0.0));
			if (endIndex < 0) {
				endIndex = - endIndex - 2;
			}
		}
		
		for(int i = startIndex; i <= endIndex; i++) {
			double y = this.get(i).getY();
			total += y;
		}
		return total;
	}
	
	public double calculateInterpolationY(double x) {
		double y = 0.0;
		int index = Collections.binarySearch(this, new Point(x, 0.0));
		if(index >= 0) {
			y = this.get(index).getY();
		}
		else {
			int beforeIndex = - index - 2;
			int afterIndex = - index - 1;
			
			if(beforeIndex < 0 && afterIndex >= this.size()) {
				y = 0.0;
			}
			else if(beforeIndex < 0) {
				y = this.get(afterIndex).getY();
			}
			else if(afterIndex >= this.size()) {
				y = this.get(beforeIndex).getY();
			}
			else {
				double x1 = this.get(beforeIndex).getX();
				double y1 = this.get(beforeIndex).getY();
				double x2 = this.get(afterIndex).getX();
				double y2 = this.get(afterIndex).getY();
				
				y = y1 + (y2 - y1) * (x - x1) / (x2 - x1);
			}
		}
		return y;
	}
	
	public String toString() {
		List<String> lines = new ArrayList<String>();
		for (Point point : this) {
			lines.add(point.getX() + "," + point.getY());
		}
		String data = String.join(";", lines);
		return data;

	}
	
	public static DataPoints fromString(String data) {
		DataPoints points = new DataPoints();
		String[] lines = data.split(";");
		for (String line : lines) {
			String[] parts = line.split(",");
			double x = Double.parseDouble(parts[0]);
			double y = Double.parseDouble(parts[1]);
			points.add(new Point(x, y));
		}
		return points;
	}
}
