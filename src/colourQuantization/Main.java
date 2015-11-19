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
	        	ArrayList<Pixel> histogram = pixelReader.getHistogram();

	        	
	        	for (Pixel p : histogram) {
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
	        			System.out.println("red" + rDistance);
	        		} else {
	        			System.out.println("blue" + bDistance);
	        		}
	        	} else {
	        		if(gDistance > bDistance){
	        			System.out.println("green" + gDistance);
	        		} else {
	        			System.out.println("blue" + bDistance);
	        		}
	        	}
	        	
	        	// Splitting at green
	        	
				
				
			} catch (IOException e) {
				System.err.println("Error while creating pixelReader");
				e.printStackTrace();
			}
		 
	}

}
