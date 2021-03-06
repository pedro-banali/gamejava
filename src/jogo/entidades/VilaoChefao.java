package jogo.entidades;

import java.awt.Graphics;

import jogo.recursosexternos.Imagem;

public class VilaoChefao extends Personagem {
	
	private int limiteEsquerda;
	private int limiteDireita;	
	private int vida;

	public VilaoChefao(int x, int y, int id, int limiteEsquerda, int limiteDireita) {
		super();
		vida = 3;
		movimento = 1;
		retangulo.setBounds(x, y, 100, 100);
		
		this.id = id;
		this.limiteDireita = limiteDireita;
		this.limiteEsquerda = limiteEsquerda;

	}

	public void tick() {
		if (retangulo.x + retangulo.width - camera.getxOffset() >= limiteDireita - camera.getxOffset() && getMovimento() != -1) {
			movimento *= -1;
		}
		if (retangulo.x - camera.getxOffset() <= limiteEsquerda - camera.getxOffset() && getMovimento() != 1) {
			movimento *= -1;
		}


		retangulo.x += getMovimento();
	}

	public void draw(Graphics g) {
		if (id != 0) {
			g.drawImage(Imagem.getInstance().getImagens()[11], retangulo.x - (int) camera.getxOffset(),
					retangulo.y - (int) camera.getyOffset(), retangulo.width, retangulo.height, null);

		}
	}

	public int getVida() {
		return vida;
	}

	public void setVida() {
		this.vida--;
	}
}