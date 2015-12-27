package kMeans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import medianCut.Cube;
import medianCut.PaletteFilter;
import colourQuantization.ColorQuantizer;
import entities.Histogram;
import entities.Pixel;

public class KMeans implements ColorQuantizer {

	private int k;

	public KMeans(int k) {
		this.k = k;
	}

	public HashSet<Pixel> quantize(Histogram histogram) {

		ArrayList<HistogramWithMean> resultHistograms = this.kMeans(histogram,
				this.k);

		HashSet<Pixel> reducedColorPalette = new HashSet();

		for (HistogramWithMean h : resultHistograms) {
			reducedColorPalette.add(h.getMean());
		}

		return reducedColorPalette;
	}

	@Override
	public HashSet<Pixel> quantizeWithFilter(Histogram histogram,
			int preFilterColorCount) {
		ArrayList<HistogramWithMean> resultHistograms = this.kMeans(histogram,
				preFilterColorCount);

		// Collections.sort(resultHistograms);

		HashSet<Pixel> reducedColorPalette = new HashSet();

		for(HistogramWithMean h : resultHistograms) {
			h.getMean().setCount(h.getHistogram().getLength());
			reducedColorPalette.add(h.getMean());
			System.out.println(h.getMean().getCount());
		}

		PaletteFilter paletteFiler = new PaletteFilter(k);
		return paletteFiler.filterPalette(reducedColorPalette);

		// return reducedColorPalette;
	}

	private ArrayList<HistogramWithMean> kMeans(Histogram histogram, int means) {
		// initialize random means
		ArrayList<HistogramWithMean> histogramsWithMeans = new ArrayList<>();

		for (int i = 0; i < means; i++) {
			int r = (int) (Math.random() * 255);
			int g = (int) (Math.random() * 255);
			int b = (int) (Math.random() * 255);
			histogramsWithMeans.add(new HistogramWithMean(
					new Pixel(r, g, b, 1), new Histogram()));
		}
//		int temp = 255 / (means + 1);
//		int r = 0;
//		int g = 0;
//		int b = 0;
//
//		for (int i = 0; i < means; i++) {
//			r += temp;
//			g += temp;
//			b += temp;
//			// System.out.println(r + " | " + g + " | " + b);
//			histogramsWithMeans.add(new HistogramWithMean(
//					new Pixel(r, g, b, 1), new Histogram()));
//		}

		boolean meanChanged = true;
		while (meanChanged) {

			for (HistogramWithMean h : histogramsWithMeans) {
				// System.out.println(h.getHistogram().getCountOfPixels());
				h.getHistogram().getHistogram().clear();
			}

			for (Pixel p : histogram.getHistogram()) {

				// first clear all old histograms

				double minDistance = Integer.MAX_VALUE;
				int indexOfMeanWithShortestDistance = 0;
				int currentIndexOfMean = 0;
				for (HistogramWithMean h : histogramsWithMeans) {
					double currentDistance = getEucledianDistance(p,
							h.getMean());
					if (currentDistance < minDistance) {
						minDistance = currentDistance;
						indexOfMeanWithShortestDistance = currentIndexOfMean;
					}
					currentIndexOfMean++;
				}
				histogramsWithMeans.get(indexOfMeanWithShortestDistance)
						.getHistogram().add(p);
			}

			// move means
			meanChanged = false;

			for (HistogramWithMean h : histogramsWithMeans) {
				if (h.getHistogram().getLength() == 0) {
					// histogramsWithMeans.remove(h);
					// int r = (int) (Math.random() * 255);
					// int g = (int) (Math.random() * 255);
					// int b = (int) (Math.random() * 255);
					// histogramsWithMeans.add(new HistogramWithMean(new
					// Pixel(r, g, b, 1), new Histogram()));
					continue;
				}

				long meanR = 0;
				long meanG = 0;
				long meanB = 0;
				for (Pixel p : h.getHistogram().getHistogram()) {
					meanR += p.getR() * p.getCount();
					meanG += p.getG() * p.getCount();
					meanB += p.getB() * p.getCount();
				}
				meanR = meanR / h.getHistogram().getCountOfPixels();
				meanG = meanG / h.getHistogram().getCountOfPixels();
				meanB = meanB / h.getHistogram().getCountOfPixels();
				Pixel newMean = new Pixel((int) meanR, (int) meanG,
						(int) meanB, 1);

				if (!h.getMean().containsSameColorsAs(newMean)) {
					h.setMean(newMean);
					meanChanged = true;
				}

			}

		}
		return histogramsWithMeans;
	}

	private double getEucledianDistance(Pixel p1, Pixel p2) {
		return Math.sqrt(Math.pow(p1.getR() - p2.getR(), 2)
				+ Math.pow(p1.getG() - p2.getG(), 2)
				+ Math.pow(p1.getB() - p2.getB(), 2));

	}

}
