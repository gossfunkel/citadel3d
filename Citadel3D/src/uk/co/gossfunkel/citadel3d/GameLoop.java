package uk.co.gossfunkel.citadel3d;

import uk.co.gossfunkel.citadel3d.graphics.Display;
import uk.co.gossfunkel.citadel3d.graphics.Render3D;
import uk.co.gossfunkel.citadel3d.input.Keyboard;
import uk.co.gossfunkel.citadel3d.input.Mouse;

public class GameLoop implements Runnable{

	private static boolean running = false;
	private static boolean debug = false;

	private static Thread tgl;
	private static Display display;
	private static Thread tdisplay;
	
	private static Keyboard key;
	private static Mouse mouse;
	private static int ydirection = 0;
	private static int xdirection = 0;
	private static int xrotation = 0;
	
	//private static Render render;
	private static Render3D r3d;
	
	private static Timer timer;

	public static void main(String[] args) {
		if (args.length > 0) if (args[0].equals("-d")) debug = true;
		GameLoop gl = new GameLoop();
		tgl = new Thread(gl);
		tgl.start();
	}
	
	private void start() {
		if (running) return;
		
		display = new Display(debug);
		tdisplay = new Thread(display);
		tdisplay.run();
		
		key = new Keyboard();
		mouse = new Mouse();
		display.addKeyListener(key);
		display.addMouseListener(mouse);
		
		//render = new Render(Display.WIDTH, Display.HEIGHT);
		r3d = new Render3D(Display.WIDTH, Display.HEIGHT);
		
		timer = new Timer();
		
		running = true;
	}
	
	private void stop() {
		if (!running) return;
		running = false;
		try {
			tgl.join();
			tdisplay.join();
		} catch (InterruptedException e) {}
		System.exit(0);
	}

	@Override
	public void run() {
		start();
		while (running) {
			timer.tick();
			if (System.currentTimeMillis() - timer.getSecond() > 1000) {
				timer.accumulateSecond();
				display.addToTitle(timer.returnFPS());
				display.shownString = timer.getFPS() + " fps";
				timer.resetTick();
			}
			while (timer.getDelta() >= 1) {
				// every time delta goes greater than one, update and supertick
				tick();
				timer.superTick();
			}
			if (timer.getFPS() > 60) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					System.err.println("Sleeping failed: " + e);
				}
			}
			draw();
		}
		stop();
	}
	
	private void tick() {
		key.update();
		ydirection = 0;
		xrotation = 0;
		xdirection = 0;
		if (key.up || key.w) {
			ydirection = -1;
		}
		if (key.down || key.s) {
			ydirection = 1;
		}
		if (key.left || key.a) {
			xdirection = -1;
		}
		if (key.right || key.d) {
			xdirection = 1;
		}
		if (key.q) {
			xrotation = -1;
		}
		if (key.e) {
			xrotation = 1;
		}
	}
	
	private void draw() {
		r3d.clear();
		r3d.floor(ydirection, xrotation, xdirection);
		r3d.drawDistanceLimiter();
		display.draw(r3d);
	}

}
