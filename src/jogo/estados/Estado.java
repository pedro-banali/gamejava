package jogo.estados;

import java.awt.Graphics;

public abstract class Estado {
	protected GerenciadorDeEstado gerenciadorDeEstado;
	public static double xOffset, yOffset;
	public Estado(GerenciadorDeEstado gerenciadorDeEstado) {
		this.gerenciadorDeEstado = gerenciadorDeEstado;
		
		xOffset = 0;
		yOffset = 0;
		
		inicializar();
	}
	
	public abstract void inicializar();
	public abstract void tick();
	public abstract void draw(Graphics g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
}
