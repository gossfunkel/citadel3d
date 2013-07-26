package uk.co.gossfunkel.citadel3d.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {
	
	public static Render floor = loadBitmap("/textures/grass.png");
	
	public static Render loadBitmap(String filename) {
		Render render = null;
		try {
			BufferedImage image = ImageIO.read(Texture.class.getResource(filename));
			int width = image.getWidth();
			int height = image.getHeight();
			render = new Render(width, height);
			image.getRGB(0, 0, width, height, render.pixels, 0, width);
		} catch (IOException e) {
			System.err.println("Texture file read error!");
			throw new RuntimeException(e);
		}
		return render;
	}

}
