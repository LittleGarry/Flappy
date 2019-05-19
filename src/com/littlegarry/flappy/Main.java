package com.littlegarry.flappy;

public class Main implements Runnable{
	
	private int width = 1280;
	private int height = 720;
	private String title = "Flappy";
	
	private boolean running = false;
	private Thread thread;
	
	public void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public void run() {
		Display.setDisplayMode(new DisplayMode(width, height));
	}
	
	public static void main(String[] args) {
		
	
	}
	
}