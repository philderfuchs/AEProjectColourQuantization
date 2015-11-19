package colourQuantization;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	public static final String imgSource = "resources/samplePalette.png";
	
	public static void main(String[] args) {
		 BufferedImage img;
		 
	        try {
				img = ImageIO.read(new File(imgSource));
				System.out.println("Image read succesfully");
				System.out.println(img.getHeight());
				System.out.println(img.getWidth());
				
				int imgHeight = img.getHeight();
				int imgWidth = img.getWidth();
				
				Color[] pixelData = new Color[imgHeight * imgWidth];
				int arrayCounter = 0;
				
				for(int y = 0; y < imgHeight; y++) {
					for(int x = 0; x < imgWidth; x++) {
						pixelData[arrayCounter++] = new Color(img.getRGB(x, y));
					}
				}
				
				for (Color c : pixelData){
					System.out.println("(" + c.getRed() + ", " + c.getGreen() + ", " + c.getBlue() + ")");
				}
				
			} catch (IOException e) {
				System.err.println("Error while Reading Image");
				e.printStackTrace();
			}
		 
	}

}
