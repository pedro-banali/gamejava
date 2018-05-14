package jogo.obstaculos;

import java.awt.Graphics;
import java.awt.Rectangle;

import jogo.recursosexternos.Imagem;

public class Bloco  extends Obstaculo{
	public static final int tamanhoBloco = 64;

	public Bloco(int x, int y, int id) {
		retangulo = new Rectangle();
		if (id == 0)
			retangulo.setBounds(x, y, 0, 0);
		else
			retangulo.setBounds(x, y, tamanhoBloco, tamanhoBloco);
		this.id = id;
	}

	public void tick() {}

	public void draw(Graphics g) {
		
		// if(x+ tamanhoBloco >= 0 )
		if (id != 0)
			g.drawImage(Imagem.getInstance().getImagens()[0] , retangulo.x - (int) camera.getxOffset(),
					retangulo.y - (int) camera.getyOffset(), retangulo.width, retangulo.height, null);
	}
	
	public void setID(int id)
	{
		this.id = id;
	}
}
