package colourQuantization;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {
	
	public static void main(String[] args) {
		 
	        try {
	        	PixelReader pixelReader = new PixelReader("resources/samplePalette.png");
	        	Color[] pixelData = pixelReader.getPixelData();
				for (Color c : pixelData){
					System.out.println("(" + c.getRed() + ", " + c.getGreen() + ", " + c.getBlue() + ")");
				}
				System.out.println(pixelData.length + " Pixels read.");
			} catch (IOException e) {
				System.err.println("Error while creating pixelReader");
				e.printStackTrace();
			}
		 
	}

}
