package colourQuantization;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Main {
	
	public static void main(String[] args) {
		 
	        try {
	        	PixelReader pixelReader = new PixelReader("resources/samplePalette.png");
	        	HashMap<Color, Integer> histogram = pixelReader.getHistogram();
	        	for (Color c : histogram.keySet()) {
	        		System.out.println("Color: " + c.getRed() + ", " + c.getGreen() + ", " + c.getBlue() + " | Count: " + histogram.get(c));
	        	}
//				for (Color c : pixelData){
//					System.out.println("(" + c.getRed() + ", " + c.getGreen() + ", " + c.getBlue() + ")");
//				}
//				System.out.println(pixelData.length + " Pixels read.");
				
				
				
			} catch (IOException e) {
				System.err.println("Error while creating pixelReader");
				e.printStackTrace();
			}
		 
	}

}
