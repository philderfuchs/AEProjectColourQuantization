package colourQuantization;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Main {
	
	public static void main(String[] args) {
		 
	        try {
	        	PixelReader pixelReader = new PixelReader("resources/samplePalette.png");
	        	Histogram histogram = pixelReader.getHistogram();

	        	
	        	for (Pixel p : histogram.getHistogram()) {
	        		System.out.println("Color: " + p.getR() + ", " + p.getG() + ", " + p.getB() + " | Count: " + p.getCount());
	        	}

	        	Cube cube = new Cube(0, 256, 0, 256, 0, 256, histogram);
	        	cube.shrink();
	        	
	        	System.out.println(cube.getgMin());
	        	System.out.println(cube.getgMax());
	        	
	        	int rDistance = 0;
	        	int gDistance = 0;
	        	int bDistance = 0;
	        	rDistance = cube.getrMax()-cube.getrMin();
	        	gDistance = cube.getgMax()-cube.getgMin();
	        	bDistance = cube.getbMax()-cube.getbMin();

	        	if(rDistance > gDistance) {
	        		if(rDistance > bDistance) {
	    	        	cube.getHistogram().sort("r");
	        			System.out.println("Sorting red" + rDistance);
	        		} else {
	    	        	cube.getHistogram().sort("b");
	        			System.out.println("Sorting blue" + bDistance);
	        		}
	        	} else {
	        		if(gDistance > bDistance){
	    	        	cube.getHistogram().sort("g");
	        			System.out.println("Sorting green" + gDistance);
	        		} else {
	    	        	cube.getHistogram().sort("b");
	        			System.out.println("Sorting blue" + bDistance);
	        		}
	        	}
	        	
	        	for (Pixel p : cube.getHistogram().getHistogram()) {
	        		System.out.println("Color: " + p.getR() + ", " + p.getG() + ", " + p.getB() + " | Count: " + p.getCount());
	        	}
	        	System.out.println("-----------");
	        	
	        	// Splitting Cube
	        	ArrayList<Pixel> pixelList = new ArrayList<>();
	        	int i;
	        	for(i = 0; i < cube.getHistogram().getLength()/2; i++) {
	        		pixelList.add(cube.getHistogram().get(i));
	        	}
	        	i--;
	        	Cube cube2 = new Cube(cube.getHistogram().get(0).getR(), cube.getHistogram().get(0).getG(), cube.getHistogram().get(0).getB(),
	        			cube.getHistogram().get(i).getR(), cube.getHistogram().get(i).getG(), cube.getHistogram().get(i).getB(), new Histogram(pixelList));

	        	for (Pixel p : cube2.getHistogram().getHistogram()) {
	        		System.out.println("Color: " + p.getR() + ", " + p.getG() + ", " + p.getB() + " | Count: " + p.getCount());
	        	}
	        	System.out.println("-----------");
	        	pixelList = new ArrayList<>();
	        	int j = i + 1; 
	        	for(i = j; i < cube.getHistogram().getLength(); i++) {
	        		pixelList.add(cube.getHistogram().get(i));
	        	}
	        	i--;
	        	Cube cube3 = new Cube(cube.getHistogram().get(j).getR(), cube.getHistogram().get(j).getG(), cube.getHistogram().get(j).getB(),
	        			cube.getHistogram().get(i).getR(), cube.getHistogram().get(i).getG(), cube.getHistogram().get(i).getB(), new Histogram(pixelList));

	        	for (Pixel p : cube3.getHistogram().getHistogram()) {
	        		System.out.println("Color: " + p.getR() + ", " + p.getG() + ", " + p.getB() + " | Count: " + p.getCount());
	        	}
				
			} catch (IOException e) {
				System.err.println("Error while creating pixelReader");
				e.printStackTrace();
			}
		 
	}

}
