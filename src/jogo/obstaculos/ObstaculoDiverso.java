package jogo.obstaculos;

import java.awt.Graphics;
import java.awt.Rectangle;

import jogo.recursosexternos.Imagem;

public class ObstaculoDiverso extends Obstaculo{
	

	private int imagem;

	public ObstaculoDiverso(int x, int y, int id, int imagem ) {
		super();
		retangulo = new Rectangle();
		retangulo.setBounds(x,y, Bloco.tamanhoBloco, Bloco.tamanhoBloco);
		this.id = id;		
		this.imagem = imagem;
	}
	
	public void tick() {} 

	public void draw(Graphics g) {
		if(id !=0) {
			g.drawImage(Imagem.getInstance().getImagens()[imagem],
					retangulo.x - (int)camera.getxOffset(), retangulo.y - (int)camera.getyOffset(),
					retangulo.width, retangulo.height, null);
			
		}
	}
	

}

