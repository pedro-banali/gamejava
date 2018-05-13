package jogo.obstaculos;

import java.awt.Graphics;
import java.awt.Rectangle;

import jogo.estados.Estado;

public class Bloco extends Rectangle {
	private static final long serialVersionUID= 1L;
	public static final int tamanhoBloco = 64;
	
	public Bloco(int x,int y) {
		setBounds(x, y, tamanhoBloco, tamanhoBloco);
	}	
	
	public void tick() {

	}
	
	public void draw(Graphics g) {
//		if(x+ tamanhoBloco >= 0  )
		g.fillRect( x - (int)Estado.xOffset, y - (int)Estado.yOffset, width, height);
	}
}
