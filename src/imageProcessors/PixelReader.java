package imageProcessors;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import entities.Histogram;
import entities.Pixel;

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
	
	public Histogram getHistogram(){
		int imgHeight = img.getHeight();
		int imgWidth = img.getWidth();
		
		HashMap<Integer, Integer> pixelMap = new HashMap();
		Integer currentPixelColor;
		
		for(int y = 0; y < imgHeight; y++) {
			for(int x = 0; x < imgWidth; x++) {
				currentPixelColor = img.getRGB(x, y);
				if (pixelMap.containsKey(currentPixelColor)){
					pixelMap.put(currentPixelColor, pixelMap.get(currentPixelColor) + 1);
				} else {
					pixelMap.put(currentPixelColor, 1);
				}
			}
		}
		
		ArrayList<Pixel> pixelList = new ArrayList<>();
		for (Integer i : pixelMap.keySet()) {
			Color c = new Color(i);
			pixelList.add(new Pixel(c.getRed(), c.getGreen(), c.getBlue(), pixelMap.get(i)));
		}
		Histogram histogram = new Histogram (pixelList);
		System.out.println(histogram.getLength());
				
		return histogram;
	}
	
}
