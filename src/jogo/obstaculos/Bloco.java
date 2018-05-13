package jogo.obstaculos;

import java.awt.Graphics;
import java.awt.Rectangle;

import jogo.estados.Estado;
import jogo.recursosexternos.Imagem;

public class Bloco extends Rectangle {
	private static final long serialVersionUID = 1L;
	public static final int tamanhoBloco = 64;
	
	private int id;

	public Bloco(int x, int y, int id) {
		
		if (id == 0)
			setBounds(x, y, 0, 0);
		else
			setBounds(x, y, tamanhoBloco, tamanhoBloco);
		this.id = id;
	}

	public void tick() {}

	public void draw(Graphics g) {
		
		// if(x+ tamanhoBloco >= 0 )
		if (id != 0)
			g.drawImage(Imagem.getInstance().getImagens()[0] , x - (int) Estado.xOffset, y - (int) Estado.yOffset, width, height,null);
	}
	
	public void setID(int id)
	{
		this.id = id;
	}
	
	public int getID() {
		return this.id;
	}
}
