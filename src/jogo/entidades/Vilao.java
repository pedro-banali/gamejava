package jogo.entidades;

import java.awt.Graphics;
import java.awt.Rectangle;

import jogo.recursosexternos.Camera;

public abstract class Vilao{
	protected int id;
	protected int movimento;
	protected Rectangle retangulo;	
	protected Camera camera;
	
	public Vilao() {
		camera = Camera.getInstance();
		retangulo = new Rectangle();
	}

	public abstract void tick();
	public abstract void draw(Graphics g);
	
	public Rectangle getRetangulo() {
		return retangulo;
	}
	public int getMovimento() {
		return movimento;
	}
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
}
