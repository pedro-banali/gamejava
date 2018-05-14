package jogo.entidades;

import java.awt.Graphics;

import jogo.obstaculos.Bloco;
import jogo.recursosexternos.Imagem;

public class VilaoMario extends Vilao {
	
	private int limiteEsquerda;
	private int limiteDireita;	
	private int vida;

	public VilaoMario(int x, int y, int id, int limiteEsquerda, int limiteDireita) {
		super();
		vida = 1;
		movimento = 1;
		retangulo.setBounds(x, y, Bloco.tamanhoBloco, Bloco.tamanhoBloco);
		
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


		retangulo.x += getMovimento() * 2;
	}

	public void draw(Graphics g) {
		if (id != 0) {
			g.drawImage(Imagem.getInstance().getImagens()[10], retangulo.x - (int) camera.getxOffset(),
					retangulo.y - (int) camera.getyOffset(), retangulo.width, retangulo.height, null);

		}
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}
}