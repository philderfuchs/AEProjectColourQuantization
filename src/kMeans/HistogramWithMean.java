package kMeans;

import medianCut.Cube;
import entities.Histogram;
import entities.Pixel;

public class HistogramWithMean implements Comparable {

	private Pixel mean;
	private Histogram histogram;

	public HistogramWithMean() {
		super();
	}

	public HistogramWithMean(Pixel mean, Histogram histogram) {
		super();
		this.mean = mean;
		this.histogram = histogram;
	}

	@Override
	public int compareTo(Object histogramWithMean) {
		// long thisWeight = this.histogram.getCountOfPixels();
		// * (int) Math.sqrt(getPurity(this.mean));
		// long otherWeight = ((HistogramWithMean) histogramWithMean)
		// .getHistogram().getCountOfPixels();
		// * (int) Math.sqrt(getPurity(((HistogramWithMean)
		// histogramWithMean).getMean()));

		double thisWeight = getPurity(this.mean);
//				* Math.log(this.histogram.getCountOfPixels());
		double otherWeight = getPurity(((HistogramWithMean) histogramWithMean)
				.getMean());
//				* Math.log(((HistogramWithMean) histogramWithMean)
//						.getHistogram().getCountOfPixels());

//		System.out.println(this.histogram.getCountOfPixels());

		if (thisWeight < otherWeight) {
			return 1;
		} else if (thisWeight == otherWeight) {
			return 0;
		} else {
			return -1;
		}
	}

	private double getPurity(Pixel p) {
		return Math.max(Math.max(p.getR(), p.getG()), p.getB())
				- Math.min(Math.min(p.getR(), p.getG()), p.getB());
	}

	public Pixel getMean() {
		return mean;
	}

	public void setMean(Pixel mean) {
		this.mean = mean;
	}

	public Histogram getHistogram() {
		return histogram;
	}

	public void setHistogram(Histogram histogram) {
		this.histogram = histogram;
	}

}
