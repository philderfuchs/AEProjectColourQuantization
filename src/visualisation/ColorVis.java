package visualisation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import entities.Pixel;

public class ColorVis {
	
	HashSet<Pixel> palette;
	int windowLength = 400;
	int windowHeight = 200;
	String title;

	public ColorVis(HashSet<Pixel> palette, String title) {
		this.palette = palette;
		this.title = title;
	}
	
	public void visualizePalette(){
		
		JFrame jFrame = new JFrame();
		MyPanel panel = new MyPanel();
		jFrame.add(panel);
		
		jFrame.setSize(windowLength, windowHeight);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setTitle(title);
		jFrame.setVisible(true);
		
	}
	
	
	class MyPanel extends JPanel{
		
		MyPanel(){
			this.setOpaque(true);
		}
		
		public void paintComponent(Graphics g){
			
			int lengthOfQuader = windowLength / palette.size();
			int x = 0;
			for(Pixel pixel: palette){
				Color c = new Color(pixel.getR(), pixel.getG(), pixel.getB());
				g.setColor(c);
				g.fillRect(x*lengthOfQuader, 0, lengthOfQuader, windowHeight);
				x++;
			}
		}
		
		
		
	}

}
