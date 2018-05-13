package jogo.estados;

import java.awt.Graphics;
import java.util.Stack;

public class GerenciadorDeEstado {
	
	private Stack<Estado> estados;

	public GerenciadorDeEstado() {
		EstadoMenu estadoMenu = new EstadoMenu(this);
		estados = new Stack<Estado>();
		estados.push(estadoMenu);
	}
	
	public Stack<Estado> getEstados() {
		return estados;
	}

	public void setEstados(Stack<Estado> estados) {
		this.estados = estados;
	}
	
	public void tick() {
		estados.peek().tick();
	}
	
	public void draw(Graphics g) {
		estados.peek().draw(g);
	}
	
	public void keyPressed(int k) {
		estados.peek().keyPressed(k);
	}
	
	public void keyReleased(int k) {
		estados.peek().keyReleased(k);
	}
}
