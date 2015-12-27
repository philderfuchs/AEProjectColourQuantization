package colourQuantization;

import java.util.HashSet;

import entities.Histogram;
import entities.Pixel;

public interface ColorQuantizer {
	public HashSet<Pixel> quantize(Histogram histogram);
	
	public HashSet<Pixel> quantizeWithFilter(Histogram histogram, int preFilterColorCount);

}
