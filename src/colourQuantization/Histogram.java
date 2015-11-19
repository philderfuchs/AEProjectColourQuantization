package colourQuantization;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Collections;


public class Histogram {

	private ArrayList<Pixel> histogram;

	public Histogram(ArrayList<Pixel> histogram) {
		this.histogram = histogram;
	}
	
	public void sort(String s) {
		switch (s) {
		case "r":
			Collections.sort(histogram, new Comparator<Pixel>(){
		        @Override
		        public int compare(Pixel  p1, Pixel  p2) {
		        	if (p1.getR() > p2.getR()) {
		        		return 1;
		        	} else if(p1.getR() == p2.getR()) {
		        		return 0;
		        	} else {
		        		return -1;
		        	}
		        }
			});
			break;
		case "g":
			Collections.sort(histogram, new Comparator<Pixel>(){
		        @Override
		        public int compare(Pixel  p1, Pixel  p2) {
		        	if (p1.getG() >= p2.getG()) {
		        		return 1;
		        	} else if(p1.getG() == p2.getG()) {
		        		return 0;
		        	} else {
		        		return -1;
		        	}
		        }
			});
			break;
		case "b":
			Collections.sort(histogram, new Comparator<Pixel>(){
		        @Override
		        public int compare(Pixel  p1, Pixel  p2) {
		        	if (p1.getB() >= p2.getB()) {
		        		return 1;
		        	} else if(p1.getB() == p2.getB()) {
		        		return 0;
		        	} else {
		        		return -1;
		        	}
		        }
			});
			break;
		default:
			break;
		}

	}
	
	public int getLength() {
		return histogram.size();
	}
	
	public Pixel get(int index){
		return histogram.get(index);
	}

	public ArrayList<Pixel> getHistogram() {
		return histogram;
	}

	public void setHistogram(ArrayList<Pixel> histogram) {
		this.histogram = histogram;
	}


}
