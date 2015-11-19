package colourQuantization;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PixelReader {

	private BufferedImage img;

	public PixelReader(String fileName) throws IOException {
		img = ImageIO.read(new File(fileName));
	}
	
	public Color[] getPixelData() {
		int imgHeight = img.getHeight();
		int imgWidth = img.getWidth();
		
		Color[] pixelData = new Color[imgHeight * imgWidth];
		int arrayCounter = 0;
		
		for(int y = 0; y < imgHeight; y++) {
			for(int x = 0; x < imgWidth; x++) {
				pixelData[arrayCounter++] = new Color(img.getRGB(x, y));
			}
		}
		
		return pixelData;
		
	}
	
}
