package jogo.obstaculos;

import java.awt.Graphics;

public class Mapa {
	private String caminho;
	private int width, height;
	
	private Bloco[][] blocos;
	
	public Mapa(String caminho, int width, int height) {
		this.caminho = caminho;
		this.width = width;
		this.height = height;
		
		blocos = new Bloco[height][width];
		for (int i = 0; i < blocos.length; i++) {
			for (int j = 0; j < blocos[i].length; j++) {
				blocos[i][j] = new Bloco(j* Bloco.tamanhoBloco, i * Bloco.tamanhoBloco);
			}
		}
	}
	
	public void draw(Graphics g)
	{
		for (int i = 0; i < blocos.length; i++) {
			for (int j = 0; j < blocos[i].length; j++) {
				blocos[i][j].draw(g);
			}
		}
	}
	
	public Bloco[][] getBlocos(){
		return blocos;
	}
}
