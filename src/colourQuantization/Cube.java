package colourQuantization;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Cube implements Comparable {

	private int rMin;
	private int rMax;

	private int gMin;
	private int gMax;
	

	private int bMin;
	private int bMax;
	
	private Histogram histogram;

	public Cube(int rMin, int rMax, int gMin, int gMax, int bMin, int bMax, Histogram histogram) {
		super();
		this.rMin = rMin;
		this.rMax = rMax;
		this.gMin = gMin;
		this.gMax = gMax;
		this.bMin = bMin;
		this.bMax = bMax;
		this.histogram = histogram;
	}
	
	public Cube(Histogram histogram) {
		super();
		this.histogram = histogram;
	}
	
	public Pixel getCentroid(){
		int count = 0;
		for (Pixel p : this.histogram.getHistogram()){
			count+= p.getCount();
		}
		return new Pixel(this.rMin+this.getrDistance()/2, this.gMin+this.getgDistance()/2, this.bMin+this.getbDistance()/2, count);
	}
	
	@Override
	public int compareTo(Object cube) {
		if (this.getSize() < ((Cube) cube).getSize()) {
			return 1;
		} else if (this.getSize() == ((Cube) cube).getSize()) {
			return 0;
		} else {
			return -1;
		}
	}
	
	public void shrink() {
    	rMin = 256;
    	rMax = 0;

    	gMin = 256;
    	gMax = 0;
    	
    	bMin = 256;
    	bMax = 0;
    	
		
    	for(Pixel p : histogram.getHistogram()) {
    		if(p.getR() < rMin) {
    			rMin = p.getR();
    		}
    		
    		if(p.getR() > rMax) {
    			rMax = p.getR();
    		}
    		
    		if(p.getG() < gMin) {
    			gMin = p.getG();
    		}
    		
    		if(p.getG() > gMax) {
    			gMax = p.getG();
    		}
    		
    		if(p.getB() < bMin) {
    			bMin = p.getB();
    		}

    		if(p.getB() > bMax) {
    			bMax = p.getB();
    		}    		
    	}
	}
	
	public Colors getLongestDistance () {
		if (this.getrDistance() >= this.getgDistance()) {
			if (this.getrDistance() >= this.getbDistance()) {
				return Colors.R;
			} else {
				return Colors.B;
			}
		} else {
			if (this.getgDistance() >= this.getbDistance()) {
				return Colors.G;
			} else {
				return Colors.B;
			}
		}
	}
	
	public int getrDistance() {
		return rMax-rMin;
	}
	
	public int getbDistance() {
		return bMax-bMin;
	}
	
	
	public int getgDistance() {
		return gMax-gMin;
	}
	
	public int getSize() {
		return this.getrDistance()*this.getgDistance()*this.getbDistance();
	}
	
	public int getrMin() {
		return rMin;
	}
	public void setrMin(int rMin) {
		this.rMin = rMin;
	}
	public int getrMax() {
		return rMax;
	}
	public void setrMax(int rMax) {
		this.rMax = rMax;
	}
	public int getgMin() {
		return gMin;
	}
	public void setgMin(int gMin) {
		this.gMin = gMin;
	}
	public int getgMax() {
		return gMax;
	}
	public void setgMax(int gMax) {
		this.gMax = gMax;
	}
	public int getbMin() {
		return bMin;
	}
	public void setbMin(int bMin) {
		this.bMin = bMin;
	}
	public int getbMax() {
		return bMax;
	}
	public void setbMax(int bMax) {
		this.bMax = bMax;
	}

	public Histogram getHistogram() {
		return histogram;
	}

	public void setHistogram(Histogram histogram) {
		this.histogram = histogram;
	}


}
