package uk.co.gossfunkel.citadel3d.graphics;

public class Render3D extends Render {
	
	private double ymove = 0.0;
	
	public Render3D(int w, int h) {
		super(w, h);
	}
	
	public void floor(int ydirection) {
		if (ydirection > 0) ymove -= 0.2;
		if (ydirection < 0) ymove += 0.2;
		
		double yDepth;
		double z;
		
		double xDepth;
		int xx;
		int yy;
		for (int y = 0; y < HEIGHT; y++) {
			//yDepth = (y - HEIGHT / ymove) / HEIGHT;
			yDepth = (y - HEIGHT / 2.0) / HEIGHT;
			
			if (yDepth < 0) yDepth = -yDepth;
			z = 8.0 / yDepth;
			yy = (int) (z + ymove) & 15;
			
			for (int x = 0; x < WIDTH; x++) {
				xDepth = (x - WIDTH / 2.0) / HEIGHT;
				xDepth *= z;
				xx = (int) (xDepth) & 15;
				pixels[x+y*WIDTH] = (xx*16) | (yy*16) << 8;
			}
		}
	}

}
