package jogo.entidades;

import java.awt.Graphics;
import java.awt.Rectangle;

import jogo.estados.Estado;
import jogo.obstaculos.Bloco;
import jogo.recursosexternos.Imagem;

public class VilaoSouth extends Vilao {
	
	private int limiteEsquerda;
	private int limiteDireita;	
	private int id;
	private int vida;

	public VilaoSouth(int x, int y, int id, int limiteEsquerda, int limiteDireita) {

		vida = 1;
		movimento = 1;
		
		retangulo = new Rectangle();
		retangulo.setBounds(x, y, Bloco.tamanhoBloco, Bloco.tamanhoBloco);
		
		this.id = id;
		this.limiteDireita = limiteDireita;
		this.limiteEsquerda = limiteEsquerda;

	}

	public void tick() {
		if (retangulo.x + retangulo.width - Estado.xOffset >= limiteDireita - Estado.xOffset && getMovimento() != -1) {
			movimento *= -1;
		}
		if (retangulo.x - Estado.xOffset <= limiteEsquerda - Estado.xOffset && getMovimento() != 1) {
			movimento *= -1;
		}


		retangulo.x += getMovimento() * 2;
	}

	public void draw(Graphics g) {
		if (id != 0) {
			g.drawImage(Imagem.getInstance().getImagens()[5], retangulo.x - (int) Estado.xOffset,
					retangulo.y - (int) Estado.yOffset, retangulo.width, retangulo.height, null);

		}
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getMovimento() {
		return movimento;
	}

	public int getID() {
		return id;
	}
}