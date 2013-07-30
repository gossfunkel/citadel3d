package uk.co.gossfunkel.citadel3d;

import uk.co.gossfunkel.citadel3d.graphics.RenderUtil;
import uk.co.gossfunkel.citadel3d.graphics.Window;
import uk.co.gossfunkel.citadel3d.levels.LevelOne;

public class GameLoop {

	private static boolean running = false;
	private static boolean debug = false;

	private static LevelOne level;
	
	private static Timer timer;

	public static void main(String[] args) {
		if (args.length > 0) if (args[0].equals("-d")) debug = true;
		GameLoop gl = new GameLoop();
		gl.start();
	}
	
	private void start() {
		if (running) return;
		
		Window.createWindow(debug);
		RenderUtil.initGraphics();
		
		timer = new Timer();
		
		level = new LevelOne();
		run();
	}
	
	private void stop() {
		if (!running) return;
		running = false;
		Window.dispose();
		System.exit(0);
	}

	public void run() {		
		running = true;
		while (running) {
			if (Window.isCloseRequested()) stop();
			timer.tick();
			if (timer.getTime() - timer.getSecond() > 1000) {
				timer.accumulateSecond();
				//display.addToTitle(timer.returnFPS());
				//display.shownString = timer.getFPS() + " fps";
				Window.addToTitle(timer.returnFPS());
				timer.resetTick();
			}
			while (timer.getDelta() >= 1) {
				// every time delta goes greater than one, update and supertick
				tick();
				timer.superTick();
			}
			Window.sync();
			draw();
		}
		stop();
	}
	
	private void tick() {
		level.update();
	}
	
	private void draw() {
		RenderUtil.clear();
		level.render();
		Window.render();
	}

}
