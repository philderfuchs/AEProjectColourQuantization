package colourQuantization;

public class Pixel {
	private int r;
	private int g;
	private int b;
	private int count;
	
	public Pixel(int r, int g, int b, int count) {
		
		this.r = r;
		this.g = g;
		this.b = b;
		this.count = count;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
