package uk.co.gossfunkel.citadel3d.shader;

public class ShaderLoadException extends Exception {
	private static final long serialVersionUID = 1L;

	public ShaderLoadException() {
		super();
		System.err.println("Shader could not be loaded!");
		System.exit(1);
	}

	public ShaderLoadException(String glShaderInfoLog) {
		super();
		System.err.println("Shader could not be run: ");
		System.err.println(glShaderInfoLog);
		System.exit(1);
	}

}
