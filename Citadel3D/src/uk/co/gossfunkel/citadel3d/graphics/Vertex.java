package uk.co.gossfunkel.citadel3d.graphics;

import org.lwjgl.util.vector.Vector3f;

public class Vertex {
	public static final int SIZE = 3;
	
	private Vector3f pos;
	
	public Vertex(Vector3f pos) {
		this.setPos(pos);
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}
}
