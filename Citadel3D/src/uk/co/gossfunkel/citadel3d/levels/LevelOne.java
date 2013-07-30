package uk.co.gossfunkel.citadel3d.levels;

import org.lwjgl.util.vector.Vector3f;

import uk.co.gossfunkel.citadel3d.graphics.Mesh;
import uk.co.gossfunkel.citadel3d.graphics.Vertex;

public class LevelOne extends Game {
	
	private Mesh mesh;
	
	public LevelOne() {
		mesh = new Mesh();
		
		Vertex[] vertices = new Vertex[] {	new Vertex(new Vector3f(-1, -1, 0)),
											new Vertex(new Vector3f(0, 1, 0)),
											new Vertex(new Vector3f(1, -1, 0))};
		
		mesh.addVertices(vertices);
	}
	
	@Override
	public void render() {
		mesh.render();
	}

}
