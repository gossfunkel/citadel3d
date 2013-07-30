package uk.co.gossfunkel.citadel3d.shader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ResourceLoader {
	
	public static String loadShader(String filename) {
		StringBuilder shaderSource = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader
													("/shaders/" + filename));
			String line;
			while ((line = br.readLine()) != null) {
				shaderSource.append(line).append("\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return shaderSource.toString();
	}

}
