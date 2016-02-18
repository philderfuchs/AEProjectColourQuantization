package colourQuantization;

import imageProcessors.PixelReader;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import javax.imageio.ImageIO;

import visualisation.ColorVis;
import visualisation.ImageCreater;
import kMeans.KMeans;
import medianCut.MedianCut;
import medianCut.PaletteFilter;
import entities.Histogram;
import entities.Pixel;

public class Main {

	public static void main(String[] args) {
		// ImageCreater imageCreator = new ImageCreater();
		// imageCreator.createImage();

		try {
			PixelReader pixelReader = new PixelReader("resources/kanye.jpg");
			Histogram histogram = pixelReader.getHistogram();
			// System.out.println(histogram.getHistogram().size());

//			for (int i = 1; i <= 109; i++) {
//
//				if (i == 10) {
//					System.out.println("--------------");
//				}
//				long start = System.currentTimeMillis();
				
				 

//				reduceByKMeans(histogram);
				
				 ColorVis colorVis1 = new ColorVis(reduceByKMeans(histogram),
				 "K-Means");
				 colorVis1.visualizePalette();
//				 ColorVis colorVis2 = new ColorVis(quantizedColorPalette,
//				 "MedianCut");
//				 colorVis2.visualizePalette();

//				long end = System.currentTimeMillis();
//				System.out.println(end - start);
//				System.gc();
//			}

		} catch (IOException e) {
			System.err.println("Error while creating pixelReader");
			e.printStackTrace();
		}

	}
	
	private static HashSet<Pixel> reduceByMedianCut(Histogram histogram) {
		MedianCut medianCut = new MedianCut(5);
//		return medianCut.quantizeWithFilter(histogram, 4);
		return medianCut.quantize(histogram);

	}

	private static HashSet<Pixel> reduceByKMeans(Histogram histogram) {
		ColorQuantizer kMeans = new KMeans(5);
		return kMeans.quantize(histogram);
		// return kMeans.quantizeWithFilter(histogram, 10);
	}
}
