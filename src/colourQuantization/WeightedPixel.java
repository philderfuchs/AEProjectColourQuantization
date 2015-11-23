package colourQuantization;

public class WeightedPixel extends Pixel {
	
	private double weight;
	
	public WeightedPixel(Pixel p, double weight) {
		super(p.getR(), p.getG(), p.getB(), p.getCount());
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Object pixel) {
		if (this.weight > ((WeightedPixel) pixel).getWeight()) {
			return 1;
		} else if (this.weight == ((WeightedPixel) pixel).getWeight()){
			return 0;
		} else {
			return -1;
		}
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
