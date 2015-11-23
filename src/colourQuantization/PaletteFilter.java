package colourQuantization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class PaletteFilter {

	int count;
	
	public PaletteFilter(int count) {
		this.count = count;
	}
	
	public HashSet<Pixel> filterPalette(HashSet<Pixel> palette){
		
    	ArrayList<WeightedPixel> weightedColorPalette = new ArrayList<>();

    	for (Pixel p : palette) {
    		weightedColorPalette.add(new WeightedPixel(p, 0));
    	}
    	
    	double weight = 0;
    	while (weightedColorPalette.size() > this.count) {
        	for(WeightedPixel p1 : weightedColorPalette) {
        		weight = 0;
	        	for(WeightedPixel p2 : weightedColorPalette) {
	        		weight += calculateDistance(p1, p2);
	        	}
	        	p1.setWeight(weight);
        	}
        	
        	// remove pixel with worst weight
        	Collections.sort(weightedColorPalette);
        	weightedColorPalette.remove(0);
    	}
    	
    	HashSet<Pixel> filteredPalette = new HashSet<>();
    	for (Pixel p : weightedColorPalette) {
    		filteredPalette.add(p);
    	}
    	
		return filteredPalette;
	}
	
	private int calculateDistance(Pixel p1, Pixel p2) {
		return (int) Math.sqrt(Math.pow(Math.max(p1.getR(), p2.getR()) - Math.min(p1.getR(), p2.getR()) , 2) +
				Math.pow(Math.max(p1.getG(), p2.getG()) - Math.min(p1.getG(), p2.getG()) , 2) + 
				Math.pow(Math.max(p1.getB(), p2.getB()) - Math.min(p1.getB(), p2.getB()) , 2));
	}

}
