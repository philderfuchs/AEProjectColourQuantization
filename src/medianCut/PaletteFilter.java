package medianCut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import entities.Pixel;
import entities.WeightedPixel;

public class PaletteFilter {

	int count;

	public PaletteFilter(int count) {
		this.count = count;
	}

	public HashSet<Pixel> filterPalette(HashSet<Pixel> palette) {

		ArrayList<WeightedPixel> weightedColorPalette = new ArrayList<>();

		for (Pixel p : palette) {
			weightedColorPalette.add(new WeightedPixel(p, 0));
		}

		double distance = 0;
		while (weightedColorPalette.size() > this.count) {
			for (WeightedPixel p1 : weightedColorPalette) {
				distance = 0;
				for (WeightedPixel p2 : weightedColorPalette) {
					distance += calculateDistance(p1, p2);
				}
				p1.setWeight(this.adjustWeight(p1, distance));
//        		System.out.println("Color: " + p1.getR() + ", " + p1.getG() + ", " + p1.getB() + 
//        				" | weight: " + weight + " | " + String.format("#%02x%02x%02x", p1.getR(), p1.getG(), p1.getB()));
			}

			// remove pixel with worst weight
			Collections.sort(weightedColorPalette);
			weightedColorPalette.remove(0);
		}

		HashSet<Pixel> filteredPalette = new HashSet<>();
		for (Pixel p : weightedColorPalette) {
			filteredPalette.add(p);
		}

		return filteredPalette;
	}

	private double calculateDistance(Pixel p1, Pixel p2) {
		double euklDistance = Math.sqrt(
				Math.pow(p1.getR() - p2.getR(), 2)
				+ Math.pow(p1.getG() - p2.getG(), 2)
				+ Math.pow(p1.getB() - p2.getB(), 2)
				);

		return euklDistance;
	}
	
	private double adjustWeight(Pixel p, double weight) {
		double purity = Math.log(Math.max(Math.max(p.getR(), p.getG()), p.getB()) - Math
				.min(Math.min(p.getR(), p.getG()), p.getB()));
		double count = Math.log(p.getCount());
		return Math.pow(weight, 2)*purity*count;
	}

}
