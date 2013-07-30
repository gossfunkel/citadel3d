package uk.co.gossfunkel.citadel3d.levels;

import org.lwjgl.util.vector.Vector3f;

import uk.co.gossfunkel.citadel3d.graphics.Mesh;
import uk.co.gossfunkel.citadel3d.graphics.Vertex;
import uk.co.gossfunkel.citadel3d.shader.ResourceLoader;
import uk.co.gossfunkel.citadel3d.shader.Shader;
import uk.co.gossfunkel.citadel3d.shader.ShaderLoadException;

public class LevelOne extends Game {
	
	private Mesh mesh;
	private Shader shader;
	
	public LevelOne() {
		mesh = new Mesh();
		
		Vertex[] vertices = new Vertex[] {	new Vertex(new Vector3f(-1, -1, 0)),
											new Vertex(new Vector3f(0, 1, 0)),
											new Vertex(new Vector3f(1, -1, 0))};
		
		mesh.addVertices(vertices);
		
		try {
			shader = new Shader();
			shader.addFragmentShader(ResourceLoader.loadShader("basicVertex.vs"));
			shader.addVertexShader(ResourceLoader.loadShader("basicFragment.fs"));
			shader.compileShader();
		} catch (ShaderLoadException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void render() {
		shader.bind();
		mesh.render();
	}

}
