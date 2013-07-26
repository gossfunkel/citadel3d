package uk.co.gossfunkel.citadel3d.graphics;

public class Render3D extends Render {
	
	private double ymove = 0.0;
	private double xrotate = 0.0;
	private double cosine = Math.cos(xrotate);
	private double sine = Math.sin(xrotate);
	private double horizontalmove = 0;
	
	private final int CEILING_HEIGHT = 1000;
	
	private double[] zBuffer;
	private double drawDistance = 6000;
	
	public Render3D(int w, int h) {
		super(w, h);
		
		zBuffer = new double[WIDTH*HEIGHT];
	}
	
	public void floor(int ydirection, int xrotation, int xdirection) {
		if (ydirection > 0) ymove -= 0.5;
		if (ydirection < 0) ymove += 0.5;
		//if (xrotation > 0) xrotate += 0.01;
		//if (xrotation < 0) xrotate -= 0.01;
		if (xdirection < 0) horizontalmove -= 0.3;
		if (xdirection > 0) horizontalmove += 0.3;

		cosine = Math.cos(xrotate);
		sine = Math.sin(xrotate);
		
		double yDepth;
		double z;
		
		double xDepth;
		int xx;
		int yy;
		for (int y = 0; y < HEIGHT; y++) {
			//yDepth = (y - HEIGHT / ymove) / HEIGHT;
			yDepth = (y - HEIGHT / 2.0) / HEIGHT;
			
			z = 8.0 / yDepth;
			
			if (yDepth < 0) z = CEILING_HEIGHT / -yDepth;
			
			for (int x = 0; x < WIDTH; x++) {
				xDepth = (x - WIDTH / 2.0) / HEIGHT;
				xDepth *= z;
				xx = (int) (xDepth * cosine + z * sine + horizontalmove);
				yy = (int) ((z + ymove) * cosine - xDepth * sine);
				
				zBuffer[x+y*WIDTH] = z;
				if (z < 800) pixels[x+y*WIDTH] = ((xx&15)*16) | ((yy&15)*16)<<8;
			}
		}
	}
	
	public void drawDistanceLimiter() {
		//TODO make this more efficient
		int brightness, r, g, b;
		for (int i = 0; i < WIDTH*HEIGHT; i++) {
			brightness = (int) (drawDistance / zBuffer[i]);
			
			// minimum and maximum brightness of 0 and 255
			if (brightness < 0) brightness = 0;
			else if (brightness > 255) brightness = 255;
			
			r = ((pixels[i] >> 16) & 0xff)*brightness >>> 8;
			g = ((pixels[i] >> 8) & 0xff)*brightness >>> 8;
			b = (pixels[i] & 0xff)*brightness >>> 8;
			
			pixels[i] = r << 16 | g << 8 | b;
		}
	}

}
