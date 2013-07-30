package uk.co.gossfunkel.citadel3d.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class RenderUtil {
	
	public static void clear() {
		//TODO Stencil buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public static void initGraphics() {
		// initialise openGL and get to base state for engine start
		// default clear colour is Cornflower Blue
		glClearColor(0.39f, 0.53f, 0.92f, 0.0f);
		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		//TODO depth clamp
		
		glEnable(GL_FRAMEBUFFER_SRGB);
	}
	
	public static String getOpenGLVersion() {
		return glGetString(GL_VERSION);
	}

}
