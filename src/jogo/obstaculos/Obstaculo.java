package jogo.obstaculos;

import java.awt.Graphics;
import java.awt.Rectangle;

import jogo.estados.Estado;
import jogo.recursosexternos.Imagem;

public class Obstaculo extends Rectangle {
	private static final long serailVersionUID = 1L;
	

	private int id;
	private int imagem;
	
	public Obstaculo(int x, int y, int id, int imagem ) {
		setBounds(x,y, Bloco.tamanhoBloco, Bloco.tamanhoBloco);
		this.id = id;		
		this.imagem = imagem;
	}
	
	public void tick() {} 

	public void draw(Graphics g) {
		if(id !=0) {
			g.drawImage(Imagem.getInstance().getImagens()[imagem],
					x - (int)Estado.xOffset, y - (int)Estado.yOffset,
					width, height, null);
			
		}
	}
	
	public int getID() {
		return id;
	}
}

