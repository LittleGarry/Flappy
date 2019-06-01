package com.littlegarry.flappy;


import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import com.littlegarry.flappy.input.Input;


public class Main implements Runnable{
	
	private int width = 1280;
	private int height = 720;
	private String title = "Flappy";
	
	private long window;
	
	private boolean running = false;
	private Thread thread;
	
	public void start() {
		running = true;
		thread = new Thread(this, "Game");
		thread.start();
	}
	
	private void init() {
		if (!glfwInit()) {
			return;
		}
		
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		window = glfwCreateWindow(width, height, title, NULL, NULL);
		if (window == NULL) {
			return;
		}
		
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);
		
		glfwSetKeyCallback(window, new Input());
		
		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
		GL.createCapabilities();
		
		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		glEnable(GL_DEPTH_TEST);
	}
	
	public void run() {
		init();
		
		while (running) {
			update();
			render();
			
			if (glfwWindowShouldClose(window))
				running = false;
		}
	}
	
	private  void update() {
		glfwPollEvents();
	}
	
	private void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glfwSwapBuffers(window);
	}
	
	public static void main(String[] args) {
		new Main().start();
	}
	
}
