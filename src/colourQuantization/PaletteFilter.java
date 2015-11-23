package colourQuantization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

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

		double weight = 0;
		while (weightedColorPalette.size() > this.count) {
			for (WeightedPixel p1 : weightedColorPalette) {
				weight = 0;
				for (WeightedPixel p2 : weightedColorPalette) {
					weight += calculateDistance(p1, p2);
				}
				p1.setWeight(weight);
				System.out.println(weight);
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
		double purity = Math.log(Math.max(Math.max(p1.getR(), p1.getG()), p1.getB()) - Math
				.min(Math.min(p1.getR(), p1.getG()), p1.getB()));
		double count = Math.log(p1.getCount());
		return euklDistance*purity*count;
	}

}
