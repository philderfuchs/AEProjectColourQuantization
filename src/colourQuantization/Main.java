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
	        	PixelReader pixelReader = new PixelReader("resources/lockitup.png");
	        	Histogram histogram = pixelReader.getHistogram();
	        	
	        	MedianCut medianCut = new MedianCut(10);
	        	HashSet<Pixel> quantizedColorPalette = medianCut.quantize(histogram);
	        	
	        	PaletteFilter paletteFiler = new PaletteFilter(5);
	        	HashSet<Pixel> filteredPalette = paletteFiler.filterPalette(quantizedColorPalette);
	        	
	        	for (Pixel p : filteredPalette) {
	        		System.out.println("Color: " + p.getR() + ", " + p.getG() + ", " + p.getB() + 
	        				" | " + String.format("#%02x%02x%02x", p.getR(), p.getG(), p.getB()));
	        	}

			} catch (IOException e) {
				System.err.println("Error while creating pixelReader");
				e.printStackTrace();
			}
		 
	}
	
	


}
