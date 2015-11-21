package colourQuantization;

import java.util.ArrayList;
import java.util.HashMap;

public class MedianCut {
	
	int count;

	public MedianCut(int count) {
		this.count = count;
	}
	
	public HashMap<Integer, Integer> quantize(Histogram histogram){
		
      	Cube cube = new Cube(histogram);
    	cube.shrink();
    	
    	for (Pixel p : cube.getHistogram().getHistogram()) {
    		System.out.println("Color: " + p.getR() + ", " + p.getG() + ", " + p.getB() + " | Count: " + p.getCount());
    	}
    	
    	Colors colorWithLongestDistance = cube.getLongestDistance();
    	
    	sortHistogram(cube, colorWithLongestDistance);
    	

    	ArrayList<Pixel> pixelList = new ArrayList<>();
    	int i;
    	for(i = 0; i < cube.getHistogram().getLength()/2; i++) {
    		pixelList.add(cube.getHistogram().get(i));
    	}
    	i--;
    	Cube cube2 = new Cube(cube.getHistogram().get(0).getR(), cube.getHistogram().get(0).getG(), cube.getHistogram().get(0).getB(),
    			cube.getHistogram().get(i).getR(), cube.getHistogram().get(i).getG(), cube.getHistogram().get(i).getB(), new Histogram(pixelList));

//    	for (Pixel p : cube2.getHistogram().getHistogram()) {
//    		System.out.println("Color: " + p.getR() + ", " + p.getG() + ", " + p.getB() + " | Count: " + p.getCount());
//    	}
//    	System.out.println("-----------");
    	pixelList = new ArrayList<>();
    	int j = i + 1; 
    	for(i = j; i < cube.getHistogram().getLength(); i++) {
    		pixelList.add(cube.getHistogram().get(i));
    	}
    	i--;
    	Cube cube3 = new Cube(cube.getHistogram().get(j).getR(), cube.getHistogram().get(j).getG(), cube.getHistogram().get(j).getB(),
    			cube.getHistogram().get(i).getR(), cube.getHistogram().get(i).getG(), cube.getHistogram().get(i).getB(), new Histogram(pixelList));

//    	for (Pixel p : cube3.getHistogram().getHistogram()) {
//    		System.out.println("Color: " + p.getR() + ", " + p.getG() + ", " + p.getB() + " | Count: " + p.getCount());
//    	}
		System.out.println("Stop");
		
//    	for (Pixel p : histogram.getHistogram()) {
//		System.out.println("Color: " + p.getR() + ", " + p.getG() + ", " + p.getB() + " | Count: " + p.getCount());
//	}
		
		HashMap<Integer, Integer> reducedColorPalette = new HashMap();
		return reducedColorPalette;
	}
	
	private int getMedian(Cube cube, Colors colorWithLongestDistance) {
		
	}


	private void sortHistogram(Cube cube, Colors colorWithLongestDistance) {
		switch (colorWithLongestDistance) {
		case R:
        	cube.getHistogram().sort(Colors.R);
			System.out.println("Sorting red. Distance is" + cube.getrDistance());
			break;
		case G:
        	cube.getHistogram().sort(Colors.G);
			System.out.println("Sorting green. Distance is" + cube.getgDistance());
			break;
		case B:
        	cube.getHistogram().sort(Colors.B);
			System.out.println("Sorting blue. Distance is" + cube.getbDistance());
			break;
		default:
			break;
    	}
    	
    	for (Pixel p : cube.getHistogram().getHistogram()) {
    		System.out.println("Color: " + p.getR() + ", " + p.getG() + ", " + p.getB() + " | Count: " + p.getCount());
    	}
    	
    	System.out.println("-----------");
	}
	
	
}
