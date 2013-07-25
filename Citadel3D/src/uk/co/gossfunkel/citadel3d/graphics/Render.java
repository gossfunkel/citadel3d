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
	
	public void draw () {
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				if ((x+y*WIDTH) > pixels.length-1) continue; 
				pixels[x+y*WIDTH] = random.nextInt();
			}
		}
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

}
