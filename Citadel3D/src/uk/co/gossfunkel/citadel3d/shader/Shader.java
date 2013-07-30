package uk.co.gossfunkel.citadel3d.shader;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;

public class Shader {
	private int program;
	
	public Shader() throws ShaderLoadException {
		program = glCreateProgram();
		
		if (program == 0) {
			throw new ShaderLoadException();
		}
	}
	
	public void bind() {
		glUseProgram(program);
	}
	
	public void addVertexShader(String text) throws ShaderLoadException {
		addProgram(text, GL_VERTEX_SHADER);
	}
	
	public void addGeometryShader(String text) throws ShaderLoadException {
		addProgram(text, GL_GEOMETRY_SHADER);
	}
	
	public void addFragmentShader(String text) throws ShaderLoadException {
		addProgram(text, GL_FRAGMENT_SHADER);
	}
	
	public void compileShader() throws ShaderLoadException {
		glLinkProgram(program);
		
		if (glGetProgrami(program, GL_LINK_STATUS) == 0) {
			throw new ShaderLoadException(glGetShaderInfoLog(program, 1024));
		}
		
		glValidateProgram(program);
		
		if (glGetProgrami(program, GL_VALIDATE_STATUS) == 0) {
			throw new ShaderLoadException(glGetShaderInfoLog(program, 1024));
		}
	}
	
	private void addProgram(String text, int type) throws ShaderLoadException {
		int shader = glCreateShader(type);
		if (shader == 0) {
			throw new ShaderLoadException();
		}
		glShaderSource(shader, text);
		glCompileShader(shader);
		
		if (glGetShaderi(shader, GL_COMPILE_STATUS) == 0) {
			throw new ShaderLoadException(glGetShaderInfoLog(shader, 1024));
		}
		
		glAttachShader(program, shader);
	}
}
