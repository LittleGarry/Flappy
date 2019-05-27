package com.littlegarry.flappy;


import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

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
			
		}
		
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		window = glfwCreateWindow(width, height, title, NULL, NULL);
	}
	
	public void run() {
		init();
		
		while (running) {
			update();
			render();
		}
	}
	
	private  void update() {
		
	}
	
	private void render() {

	}
	
	public static void main(String[] args) {
		new Main().start();
	}
	
}
