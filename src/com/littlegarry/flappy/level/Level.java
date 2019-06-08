package com.littlegarry.flappy.level;

import com.littlegarry.flappy.graphics.Shader;
import com.littlegarry.flappy.graphics.Texture;
import com.littlegarry.flappy.graphics.VertexArray;
import com.littlegarry.flappy.math.Matrix4f;
import com.littlegarry.flappy.math.Vector3f;

public class Level {
	
	private VertexArray background;
	private Texture bgTexture;
	
	private int xScroll = 0;
	private int map = 0;
	
	private Bird bird;
	
	public Level() {
		
		float[] vertices = new float[] {
			-10.0f, -10.0f * 9.0f / 16.0f, 0.0f,
			-10.0f,  10.0f * 9.0f / 16.0f, 0.0f,
			  0.0f,  10.0f * 9.0f / 16.0f, 0.0f,
			  0.0f, -10.0f * 9.0f / 16.0f, 0.0f,
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
		
		background = new VertexArray(vertices, indices, tcs);
		bgTexture = new Texture("res/bg.jpeg");
		
		bird = new Bird();
	}
	
	public void update() {
		xScroll--;
		if (-xScroll % 335 == 0)
			map++;
	}
	
	public void render() {
		bgTexture.bind();
		Shader.BG.enable();
		background.bind();
		for (int i = map; i < map + 3; i++) {
			Shader.BG.setUniformMat4f("vw_matrix", Matrix4f.translate(new Vector3f(i * 10 + xScroll * 0.03f, 0.0f, 0.0f)));
			background.draw();
		}
		Shader.BG.disable();
		bgTexture.unbind();
		
		bird.render();
	}
}
