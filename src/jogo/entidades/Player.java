package jogo.entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

import jogo.estados.Estado;
import jogo.fisica.Colisao;
import jogo.obstaculos.Bloco;
import jogo.principal.PainelDoJogo;

public class Player {

	private boolean right = false;
	private boolean left = false;
	private boolean colisaoTopo = false;

	// limites
	private double x, y;
	private int width, height;

	// velocidade
	private double velocidadeDeMovimento = 2;

	// Controle caindo e pulando
	private boolean pulando = false;
	private boolean caindo = false;

	// velocidade do pulo
	private double velPulo;
	private double atualVelPulo;
	// velocidade ao cair
	private double maxVelCaindo;
	private double atualVelCaindo;

	public Player(int width, int height) {
		velPulo = 5;
		atualVelPulo = velPulo;
		maxVelCaindo = 5;
		atualVelCaindo = 0.1;

		x = PainelDoJogo.WIDTH / 2;
		y = PainelDoJogo.HEIGHT / 2;
		this.width = width;
		this.height = height;
	}

	public void tick(Bloco[][] b) {

		int iX = (int) x;
		int iY = (int) y;

		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				// direita
				if (Colisao.playerBloco(new Point(iX + width + (int) Estado.xOffset, iY + (int) Estado.yOffset + 2),
						b[i][j])
						|| Colisao.playerBloco(
								new Point(iX + width + (int) Estado.xOffset, iY + height + (int) Estado.yOffset - 1),
								b[i][j])) {
					right = false;
				}
				// esquerda
				if (Colisao.playerBloco(new Point(iX + (int) Estado.xOffset - 1, iY + (int) Estado.yOffset + 2), b[i][j])
						|| Colisao.playerBloco(
								new Point(iX + (int) Estado.xOffset - 1, iY + height + (int) Estado.yOffset - 1),
								b[i][j])) {
					left = false;
				}
				// topo
				if (Colisao.playerBloco(new Point(iX + (int) Estado.xOffset + 1, iY + (int) Estado.yOffset), b[i][j])
						|| Colisao.playerBloco(
								new Point(iX + width + (int) Estado.xOffset - 1, iY + (int) Estado.yOffset), b[i][j])) {
					pulando = false;
					caindo = true;
				}
				// baixo
				if (Colisao.playerBloco(
						new Point(iX + (int) Estado.xOffset + 2, iY + height + (int) Estado.yOffset + 1), b[i][j])
						|| Colisao.playerBloco(new Point(iX + width + (int) Estado.xOffset - 1,
								iY + height + (int) Estado.yOffset + 1), b[i][j])) {
					y = b[i][j].getY() - height - Estado.yOffset;
					caindo = false;
					colisaoTopo = true;
				} else {
					if (!colisaoTopo && !pulando)
						caindo = true;
				}
			}

		}

		colisaoTopo = false;

		if (right) {
			Estado.xOffset += velocidadeDeMovimento;
		} else if (left) {
			Estado.xOffset -= velocidadeDeMovimento;
		}
		if (pulando) {
			Estado.yOffset -= atualVelPulo;
			atualVelPulo -= 0.1;
			if (atualVelPulo <= 0) {
				atualVelPulo = velPulo;
				pulando = false;
				caindo = true;
			}
		} else if (caindo) {
			Estado.yOffset += atualVelCaindo;
			if (atualVelCaindo < maxVelCaindo) {
				atualVelCaindo += 0.1;
			}
		} else if (!caindo) {
			atualVelCaindo = 0.1;

		}

	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((int) x, (int) y, width, height);

	}

	public void KeyPressed(int k) {
		if (k == KeyEvent.VK_D)
			right = true;
		if (k == KeyEvent.VK_A)
			left = true;
		if (k == KeyEvent.VK_SPACE && !pulando && !caindo)
			pulando = true;

	}

	public void KeyReleased(int k) {
		if (k == KeyEvent.VK_D)
			right = false;
		if (k == KeyEvent.VK_A)
			left = false;
	}

}
