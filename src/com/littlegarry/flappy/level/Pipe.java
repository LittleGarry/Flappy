package com.littlegarry.flappy.level;

import com.littlegarry.flappy.graphics.Texture;
import com.littlegarry.flappy.graphics.VertexArray;
import com.littlegarry.flappy.math.Matrix4f;
import com.littlegarry.flappy.math.Vector3f;

public class Pipe {
	private Vector3f position = new Vector3f();
	private Matrix4f ml_matrix;
	
	private static float width = 1.5f, height = 8.0f;
	private static Texture texture;
	private static VertexArray mesh;
	
	public static void create() {
		float[] vertices = new float[] {
			0.0f, 0.0f, 0.1f,
			0.0f, height, 0.1f,
			width, height, 0.1f,
			width, 0.0f, 0.1f
		};
		
		byte[] indices = new byte[] {
			0, 1, 2,
			2, 3, 0
		};
		
		float[] tcs = new float[] {
			0, 1,
			0, 0,
			1, 0,
			1, 1
		};
		
		mesh = new VertexArray(vertices, indices, tcs);
		texture = new Texture("res/pipe.png");
	}
}
