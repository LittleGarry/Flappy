package com.littlegarry.flappy.level;

import java.util.Random;

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
	private int index = 0;
	
	private Bird bird;
	private Pipe[] pipes = new Pipe[5 * 2];
	
	private Random random = new Random();
	
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
		
		createPipes();
	}
	
	private void createPipes() {
		Pipe.create();
		
		for (int i = 0; i < 5 * 2; i += 2) {
			pipes[i] = new Pipe(index * 3.0f, random.nextFloat() * 4);
			pipes[i + 1] = new Pipe(pipes[i].getX(), pipes[i].getY() - 11.0f);
			
			index += 2;
		}
	}
	
	public void update() {
		xScroll--;
		if (-xScroll % 335 == 0)
			map++;
		
		bird.update();
	}
	
	private void renderPipes() {
		Shader.PIPE.enable();
		Shader.PIPE.setUniformMat4f("vw_matrix", Matrix4f.translate(new Vector3f(xScroll * 0.03f, 0.0f, 0.0f)));
		Pipe.getTexture().bind();
		Pipe.getMesh().bind();
		
		for (int i = 0; i < 5 * 2; i++) {
			Shader.PIPE.setUniformMat4f("ml_matrix", pipes[i].getModelMatrix());
			Shader.PIPE.setUniform1i("top", i % 2 == 0 ? 1 : 0);
			Pipe.getMesh().draw();
		}
		
		Pipe.getMesh().unbind();
		Pipe.getTexture().unbind();
	}
	
	public void render() {
		bgTexture.bind();
		Shader.BG.enable();
		background.bind();
		for (int i = map; i < map + 4; i++) {
			Shader.BG.setUniformMat4f("vw_matrix", Matrix4f.translate(new Vector3f(i * 10 + xScroll * 0.03f, 0.0f, 0.0f)));
			background.draw();
		}
		Shader.BG.disable();
		bgTexture.unbind();
		
		renderPipes();
		bird.render();
	}
}
