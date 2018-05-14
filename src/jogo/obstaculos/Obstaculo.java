package jogo.obstaculos;

import java.awt.Graphics;
import java.awt.Rectangle;

import jogo.recursosexternos.Camera;

public abstract class Obstaculo {
	protected Rectangle retangulo;
	protected Camera camera;
	protected int id;
		
	public Obstaculo() {
		camera = Camera.getInstance();
		retangulo = new Rectangle();
	}
	
	public abstract void tick();
	public abstract void draw(Graphics g);
	
	public int getID() {
		return id;
	}
	
	public Rectangle getRetangulo() {
		return retangulo;
	}
}
