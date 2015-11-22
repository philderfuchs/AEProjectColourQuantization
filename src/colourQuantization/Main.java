package colourQuantization;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.imageio.ImageIO;

public class Main {
	
	public static void main(String[] args) {
		 
	        try {
	        	PixelReader pixelReader = new PixelReader("resources/cover.jpg");
	        	Histogram histogram = pixelReader.getHistogram();
	        	
	        	MedianCut medianCut = new MedianCut(5);
	        	HashSet<Pixel> reducedColorPalette = medianCut.quantize(histogram);
	        	
	        	for(Pixel p : reducedColorPalette) {
	        		System.out.println("Color: " + p.getR() + ", " + p.getG() + ", " + p.getB() + " | Count: " + p.getCount());
	        	}
	  
			} catch (IOException e) {
				System.err.println("Error while creating pixelReader");
				e.printStackTrace();
			}
		 
	}

}
