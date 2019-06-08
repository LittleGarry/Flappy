package com.littlegarry.flappy.level;

import org.lwjgl.glfw.GLFW;

import com.littlegarry.flappy.graphics.Shader;
import com.littlegarry.flappy.graphics.Texture;
import com.littlegarry.flappy.graphics.VertexArray;
import com.littlegarry.flappy.input.Input;
import com.littlegarry.flappy.math.Matrix4f;
import com.littlegarry.flappy.math.Vector3f;

public class Bird {
	private float SIZE = 1.0f;
	private VertexArray mesh;
	private Texture texture;
	
	private Vector3f position = new Vector3f();
	private float rot;
	private float delta = 0.0f;
	
	public Bird() {
		float[] vertices = new float[] {
			-SIZE / 2.0f, -SIZE / 2.0f, 0.1f,
			-SIZE / 2.0f,  SIZE / 2.0f, 0.1f,
			 SIZE / 2.0f,  SIZE / 2.0f, 0.1f,
			 SIZE / 2.0f, -SIZE / 2.0f, 0.1f
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
		texture = new Texture("res/bird.png");
	}
	
	public void update() {
		if (Input.keys[GLFW.GLFW_KEY_UP])
			position.y += 0.1f;
		if (Input.keys[GLFW.GLFW_KEY_DOWN])
			position.y -= 0.1f;
		if (Input.keys[GLFW.GLFW_KEY_LEFT])
			position.x -= 0.1f;
		if (Input.keys[GLFW.GLFW_KEY_RIGHT])
			position.x += 0.1f;
	}
	
	public void render() {
		Shader.BIRD.enable();
		Shader.BIRD.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
		texture.bind();
		mesh.render();
		Shader.BIRD.disable();
	}
}
