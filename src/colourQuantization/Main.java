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
	        	
	        	MedianCut medianCut = new MedianCut(8);
	        	medianCut.quantize(histogram);

	  
			} catch (IOException e) {
				System.err.println("Error while creating pixelReader");
				e.printStackTrace();
			}
		 
	}

}
