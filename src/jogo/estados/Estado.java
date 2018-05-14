package jogo.estados;

import java.awt.Graphics;

import jogo.recursosexternos.Camera;

public abstract class Estado {
	protected GerenciadorDeEstado gerenciadorDeEstado;
//	public static double xOffset, yOffset;
	protected Camera camera;
	public Estado(GerenciadorDeEstado gerenciadorDeEstado) {
		this.gerenciadorDeEstado = gerenciadorDeEstado;
		camera = Camera.getInstance();
		camera.setxOffset(0);
		camera.setyOffset(0); 		
		inicializar();
	}
	
	public abstract void inicializar();
	public abstract void tick();
	public abstract void draw(Graphics g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
}
