package jogo.entidades;

import java.awt.Graphics;
import java.awt.Rectangle;

import jogo.estados.Estado;
import jogo.obstaculos.Bloco;
import jogo.recursosexternos.Imagem;

public class VilaoCogumelo extends Vilao {
	private static final long serailVersionUID = 1L;
	private int limiteEsquerda;
	private int limiteDireita;
	private int movimento;
	private int id;
	private int vida;
	private double atualVelPulo;
	private double velPulo;
	// velocidade ao cair
	private double maxVelCaindo;
	private double atualVelCaindo;
	private int posicaoInicial;
	private boolean pulando, caindo;

	public VilaoCogumelo(int x, int y, int id, int limiteEsquerda, int limiteDireita) {
		velPulo = 2;
		atualVelPulo = velPulo;
		maxVelCaindo = 2;
		atualVelCaindo = maxVelCaindo;
		vida = 1;
		pulando = true;
		caindo = false;
		movimento = 1;
		
		retangulo = new Rectangle();
		retangulo.setBounds(x, y, Bloco.tamanhoBloco, Bloco.tamanhoBloco);
		
		posicaoInicial = retangulo.y;
		
		this.id = id;
		this.limiteDireita = limiteDireita;
		this.limiteEsquerda = limiteEsquerda;
		posicaoInicial = retangulo.y;

	}

	public void tick() {
		if (retangulo.x + retangulo.width - Estado.xOffset >= limiteDireita - Estado.xOffset && movimento != -1) {
			movimento *= -1;
		}
		if (retangulo.x - Estado.xOffset <= limiteEsquerda - Estado.xOffset && movimento != 1) {
			movimento *= -1;
		}
		if(retangulo.y == posicaoInicial)
		{
			pulando = true;
		}
		if(pulando) {
			retangulo.y -= atualVelPulo;
			atualVelPulo -= 0.1;
			if (atualVelPulo <= 0) {
				atualVelPulo = velPulo;
				pulando = false;
				caindo = true;
			}
		}
		if (caindo) {
			retangulo.y += atualVelCaindo;
			if (atualVelCaindo < maxVelCaindo) {
				atualVelCaindo += 0.1;
			}
			if(retangulo.y == posicaoInicial)
				caindo = false;
		}

		retangulo.x += movimento;
	}

	public void draw(Graphics g) {
		if (id != 0) {
			g.drawImage(Imagem.getInstance().getImagens()[4], retangulo.x - (int) Estado.xOffset,
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