package colourQuantization;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import javax.imageio.ImageIO;

public class Main {
	
	public static void main(String[] args) {
		 
	        try {
	        	PixelReader pixelReader = new PixelReader("resources/kanye-west-banned-cover.jpg");
	        	Histogram histogram = pixelReader.getHistogram();
	        	
	        	MedianCut medianCut = new MedianCut(10);
	        	HashSet<Pixel> quantizedColorPalette = medianCut.quantize(histogram);
	        	
//	        	for (Pixel p : quantizedColorPalette) {
//	    		System.out.println("Color: " + p.getR() + ", " + p.getG() + ", " + p.getB() + " | Count: " + p.getCount());
//	        	}
//	        	
//	        	System.out.println("-----------------");

	        	while (quantizedColorPalette.size() > 5) {
		        	ArrayList<WeightedPixel> weightedColorPalette = new ArrayList<>();
		        	int weight;
		        	for(Pixel p1 : quantizedColorPalette) {
		        		weight = 0;
			        	for(Pixel p2 : quantizedColorPalette) {
			        		weight += calculateDistance(p1, p2);
			        	}
			        	weightedColorPalette.add(new WeightedPixel(p1, weight));
		        	}
		        	Collections.sort(weightedColorPalette);
		    		for(Pixel p : quantizedColorPalette) {
		    			if(p.getR() == weightedColorPalette.get(0).getR() &&
		    					p.getG() == weightedColorPalette.get(0).getG() &&
		    					p.getB() == weightedColorPalette.get(0).getB()) {
		    				quantizedColorPalette.remove(p);
		    				break;
		    			}
		    		}
	        	}
	        	
	        	
//	        	HashSet<Pixel> reducedColorPalette = new HashSet<>();

//	        	reducedColorPalette.add(weightedColorPalette.get((int) weightedColorPalette.size()/4 - 1));
//	        	reducedColorPalette.add(weightedColorPalette.get((int) weightedColorPalette.size()/2 - 1));
//	        	reducedColorPalette.add(weightedColorPalette.get((int) (weightedColorPalette.size()/1.6 - 1)));
//	        	reducedColorPalette.add(weightedColorPalette.get((int) (weightedColorPalette.size()/1.3 - 1)));
//	        	reducedColorPalette.add(weightedColorPalette.get((int) (weightedColorPalette.size()-1)));
	        	
	        	for (Pixel p : quantizedColorPalette) {
	    		System.out.println("Color: " + p.getR() + ", " + p.getG() + ", " + p.getB());
	        	}

			} catch (IOException e) {
				System.err.println("Error while creating pixelReader");
				e.printStackTrace();
			}
		 
	}
	
	public static int calculateDistance(Pixel p1, Pixel p2) {
		return (int) Math.sqrt(Math.pow(Math.max(p1.getR(), p2.getR()) - Math.min(p1.getR(), p2.getR()) , 2) +
				Math.pow(Math.max(p1.getG(), p2.getG()) - Math.min(p1.getG(), p2.getG()) , 2) + 
				Math.pow(Math.max(p1.getB(), p2.getB()) - Math.min(p1.getB(), p2.getB()) , 2));
	}

}
