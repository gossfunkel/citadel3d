package uk.co.gossfunkel.citadel3d;

import uk.co.gossfunkel.citadel3d.graphics.Display;
import uk.co.gossfunkel.citadel3d.graphics.Render;

public class GameLoop implements Runnable{
	
	private static Display display;
	private static boolean running = false;
	private static Thread tgl;
	private static Thread tdisplay;
	private static Render render;
	
	private void start() {
		if (running) return;
		
		display = new Display();
		tdisplay = new Thread(display);
		tdisplay.run();
		
		render = new Render(display.getWidth(), display.getHeight());
		
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

	public static void main(String[] args) {
		GameLoop gl = new GameLoop();
		tgl = new Thread(gl);
		tgl.start();
	}

	@Override
	public void run() {
		start();
		while (running) {
			tick();
			draw();
		}
		stop();
	}
	
	private void tick() {
		
	}
	
	private void draw() {
		render.draw(0, 0);
		display.draw(render);
	}

}
