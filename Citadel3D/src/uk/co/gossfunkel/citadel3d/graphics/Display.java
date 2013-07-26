package uk.co.gossfunkel.citadel3d.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Display extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1280;
	public static final int HEIGHT = WIDTH/16*9;
	public static final Dimension size = new Dimension(WIDTH, HEIGHT);
	private static JFrame frame;
	
	private static BufferedImage img;
	private static int[] pixels;
	
	private static String title = "Citadel3D prealpha";
	
	public Display() {
		frame = new JFrame();
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
	}

	@Override
	public void run() {
		frame.add(this);
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(size);
		frame.setMinimumSize(size);
		frame.setMaximumSize(size);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
	}
	
	public void draw(Render render) {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		for (int i = 0; i < render.pixels.length; i++) {
			pixels[i] = render.pixels[i];
		}
		
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		{
			// draw cornflower blue background
			g.setColor(new Color(0x6495ED));
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			// draw rendered image
			g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
		}
		g.dispose();
		bs.show();
	}
	
	public void addToTitle(String str) {
		frame.setTitle(title + " | " + str);
	}
}
