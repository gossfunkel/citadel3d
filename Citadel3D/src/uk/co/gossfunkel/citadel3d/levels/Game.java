package uk.co.gossfunkel.citadel3d.levels;

import org.lwjgl.input.Keyboard;

public abstract class Game {
	
	private static int ydirection = 0;
	private static int xdirection = 0;
	private static int xrotation = 0;
	
	public Game() {}
	
	public void input() {

		ydirection = 0;
		xrotation = 0;
		xdirection = 0;
		if (Keyboard.isKeyDown(Keyboard.KEY_W) || 
								Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			ydirection = -1;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S) || 
								Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			ydirection = 1;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A) || 
								Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			xdirection = -1;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D) || 
								Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			xdirection = 1;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			xrotation = -1;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			xrotation = 1;
		}
	}
	
	public void update() {
		input();
	}
	
	public void render() {}

}
