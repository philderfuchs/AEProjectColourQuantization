package visualisation;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageCreater {

	private static int width = 512;
	private static int height = 512;
	private static int offset = 1;
	private static String filename = "test2.png";

	public void createImage() {

		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		int x = 0;
		int y = 0;

		for (int r = 0; r < 64; r++) {
			for (int g = 0; g < 64; g++) {
				for (int b = 0; b < 64; b++) {
					Color c = new Color(r * offset, g * offset, b * offset);
					image.setRGB(x, y, c.getRGB());
					x++;
					if (x >= width) {
						x = 0;
						y++;
					}
				}
			}
		}

		try {
			ImageIO.write(image, "png", new File(filename));
		} catch (Exception exception) {
			// code
		}
	}

}
