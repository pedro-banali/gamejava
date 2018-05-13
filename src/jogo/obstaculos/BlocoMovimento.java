package jogo.obstaculos;

import java.awt.Graphics;
import java.awt.Rectangle;

import jogo.estados.Estado;
import jogo.recursosexternos.Imagem;

public class BlocoMovimento extends Rectangle {
	private static final long serailVersionUID = 1L;
	
	private int limiteEsquerda;
	private int limiteDireita;
	private int movimento;
	private int id;
	
	public BlocoMovimento(int x, int y, int id, int limiteEsquerda, int limiteDireita ) {
		movimento = 1;
		setBounds(x,y, Bloco.tamanhoBloco, Bloco.tamanhoBloco);
		this.id = id;
		this.limiteDireita = limiteDireita;
		this.limiteEsquerda = limiteEsquerda;
		
	}
	
	public void tick() {
		if(x + width - Estado.xOffset >= limiteDireita - Estado.xOffset && movimento != -1) {
			movimento*= -1;
		}
		if (x - Estado.xOffset <= limiteEsquerda - Estado.xOffset && movimento !=1) {
			movimento *= -1;
		}
		
		x += movimento;
	}

	public void draw(Graphics g) {
		if(id !=0) {
			g.drawImage(Imagem.getInstance().getImagens()[1],
					x - (int)Estado.xOffset, y - (int)Estado.yOffset,
					width, height, null);
			
		}
	}
	
	public int getMovimento() {
		return movimento;
	}
	
	public int getID() {
		return id;
	}
}

