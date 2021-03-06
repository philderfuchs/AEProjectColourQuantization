package entities;


public class Pixel implements Comparable<Pixel> {
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
	
	
	public int get(Colors c) {
		switch (c) {
		case R:
			return this.r;
		case G:
			return this.g;
		case B:
			return this.b;
		default:
			break;
		}
		return 0;
	}
	
	public boolean containsSameColorsAs(Pixel p) {
		return p.getR() == this.r &&
				p.getG() == this.g &&
				p.getB() == this.b;
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


	@Override
	public int compareTo(Pixel o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
