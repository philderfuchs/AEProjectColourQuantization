package colourQuantization;

public class WeightedPixel extends Pixel {
	
	private int weight;
	
	public WeightedPixel(Pixel p, int weight) {
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
