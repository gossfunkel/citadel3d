package uk.co.gossfunkel.citadel3d.graphics;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window {
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = WIDTH/16*9;
	
	public static final int FPS = 60;
	
	public static final String TITLE = "Citadel3D prealpha";
	
	public static void createWindow(boolean debug) {
		try {
			DisplayMode displayMode = null;
	        DisplayMode[] modes = Display.getAvailableDisplayModes();
	
	         for (int i = 0; i < modes.length; i++)
	         {
	             if (modes[i].getWidth() == WIDTH
	             && modes[i].getHeight() == HEIGHT
	             && modes[i].isFullscreenCapable())
	               {
	                    displayMode = modes[i];
	               }
	         }
			Display.setDisplayMode(displayMode);
			Display.setFullscreen(!debug);
			// Enable VSync to act as a framerate limiter if windowed or to 
			//	prevent tearing in fullscreen
			Display.setVSyncEnabled(true);
			Display.setTitle(TITLE);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public static void dispose() {
		Display.destroy();
	}
	
	public static void sync() {
		Display.sync(FPS);
	}
	
	public static void addToTitle(String str) {
		Display.setTitle(TITLE + " | " + str);
	}
	
	public static void render() {
		Display.update();
	}
	
	public static boolean isCloseRequested() {
		return Display.isCloseRequested();
	}
	
	public static int getWidth() {
		return Display.getDisplayMode().getWidth();
	}
	
	public static int getHeight() {
		return Display.getDisplayMode().getHeight();
	}
	
	public static String getTitle() {
		return Display.getTitle();
	}
}
