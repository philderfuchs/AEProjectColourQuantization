package colourQuantization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MedianCut {
	
	int count;

	public MedianCut(int count) {
		this.count = count;
//		if((count % 2) == 1) {
//			this.count++;
//		}
	}
	
	public HashSet<Pixel> quantize(Histogram histogram){
		
		ArrayList<Cube> cubeList = new ArrayList<>();
		
      	Cube initialCube = new Cube(histogram);
      	initialCube.shrink();
    	cubeList.add(initialCube);

    	do {

    		
    		Cube cube = this.getBiggestCube(cubeList);
    		cubeList.remove(cube);
        	Colors colorWithLongestDistance = cube.getLongestDistance();
        	
        	sortHistogram(cube, colorWithLongestDistance);
        	
        	int colorValueSum = this.getSumOfColorValues(cube, colorWithLongestDistance);
        	
        	Histogram histogramOfChildCube1 = new Histogram();
        	Histogram histogramOfChildCube2 = new Histogram();
        	
        	int sum = 0;
    		for (Pixel p : cube.getHistogram().getHistogram()) {
    			sum += p.get(colorWithLongestDistance)*p.getCount();
    	    	if (sum <= colorValueSum/2) {
    				histogramOfChildCube1.add(p);
    	    	} else {
    				histogramOfChildCube2.add(p);
    	    	}
    		}
    		
    		Cube childCube1 = new Cube(histogramOfChildCube1);
    		childCube1.shrink();
    		Cube childCube2 = new Cube(histogramOfChildCube2);
    		childCube2.shrink();
    		
    		cubeList.add(childCube1);
    		cubeList.add(childCube2);
    	} while(cubeList.size() < this.count);
    	
		HashSet<Pixel> reducedColorPalette = new HashSet();
		
		for(Cube cube : cubeList) {
			reducedColorPalette.add(cube.getCentroid());
		}
		
		return reducedColorPalette;
	}
	
	private Cube getBiggestCube(ArrayList<Cube> cubeList){
		Collections.sort(cubeList);
		for (Cube cube : cubeList) {
			if (cube.getHistogram().getHistogram().size() > 1) {
				return cube;
			}
		}
		return cubeList.get(0);
	}
	
	private int getSumOfColorValues(Cube cube, Colors colorWithLongestDistance) {
		int sum = 0;
		for (Pixel p : cube.getHistogram().getHistogram()) {
			sum += p.get(colorWithLongestDistance)*p.getCount();
		}
		return sum;
	}


	private void sortHistogram(Cube cube, Colors colorWithLongestDistance) {
		switch (colorWithLongestDistance) {
		case R:
        	cube.getHistogram().sort(Colors.R);
			break;
		case G:
        	cube.getHistogram().sort(Colors.G);
			break;
		case B:
        	cube.getHistogram().sort(Colors.B);
			break;
		default:
			break;
    	}
    	
//    	for (Pixel p : cube.getHistogram().getHistogram()) {
//    		System.out.println("Color: " + p.getR() + ", " + p.getG() + ", " + p.getB() + " | Count: " + p.getCount());
//    	}
//    	
//    	System.out.println("-----------");
	}
	
	private void printCubeHistogram (Cube cube) {
    for (Pixel p : cube.getHistogram().getHistogram()) {
		System.out.println("Color: " + p.getR() + ", " + p.getG() + ", " + p.getB() + " | Count: " + p.getCount());
	}
	
	System.out.println(" ");
	}
	
	
}
