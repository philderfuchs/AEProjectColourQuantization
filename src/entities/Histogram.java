package entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;


public class Histogram {

	private ArrayList<Pixel> histogram;

	public Histogram() {
		histogram = new ArrayList<Pixel>();
	}

	public Histogram(ArrayList<Pixel> histogram) {
		this.histogram = histogram;
	}
	
	public void sort(Colors c) {
		switch (c) {
		case R:
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
		case G:
			Collections.sort(histogram, new Comparator<Pixel>(){
		        @Override
		        public int compare(Pixel  p1, Pixel  p2) {
		        	if (p1.getG() > p2.getG()) {
		        		return 1;
		        	} else if(p1.getG() == p2.getG()) {
		        		return 0;
		        	} else {
		        		return -1;
		        	}
		        }
			});
			break;
		case B:
			Collections.sort(histogram, new Comparator<Pixel>(){
		        @Override
		        public int compare(Pixel  p1, Pixel  p2) {
		        	if (p1.getB() > p2.getB()) {
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
	
	public void add(Pixel p) {
		this.histogram.add(p);
	}
	
	public int getLength() {
		return histogram.size();
	}
	
	public int getCountOfPixels() {
		int count = 0;
		for (Pixel p : histogram) {
			count+=p.getCount();
		}
		return count;
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
