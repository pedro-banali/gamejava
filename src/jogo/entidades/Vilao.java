package jogo.entidades;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Vilao{
	protected int id;
	protected int movimento;
	protected Rectangle retangulo;	

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
