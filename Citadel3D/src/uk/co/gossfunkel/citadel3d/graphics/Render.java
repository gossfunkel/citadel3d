package uk.co.gossfunkel.citadel3d.graphics;

import java.util.Random;

public class Render {

	private static Random random;
	public final int WIDTH;
	public final int HEIGHT;
	public int[] pixels;
	
	public Render(int w, int h) {
		random = new Random();
		WIDTH = w;
		HEIGHT = h;
		pixels = new int[w*h];
	}
	
	public void draw (int xOffs, int yOffs) {
		int yPix, xPix = -1;
		for (int y = 0; y < HEIGHT; y++) {
			yPix = y + yOffs;
			for (int x = 0; x < WIDTH; x++) {
				xPix = x + xOffs;
				pixels[xPix+yPix*WIDTH] = random.nextInt();
			}
		}
	}

}
