package jogo.entidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import jogo.estados.Estado;
import jogo.fisica.Colisao;
import jogo.modelos.Usuario;
import jogo.obstaculos.Bloco;
import jogo.obstaculos.BlocoMovimento;
import jogo.obstaculos.Obstaculo;
import jogo.principal.PainelDoJogo;

public class Player {

	private boolean right = false;
	private boolean left = false;
	private boolean colisaoTopo = false;
	private Usuario dadosPlayer;
	private Usuario dadosPlayer2;
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
		dadosPlayer = new Usuario();
		dadosPlayer2 = new Usuario();
		velPulo = 5.5;
		atualVelPulo = velPulo;
		maxVelCaindo = 5.5;
		atualVelCaindo = 0.1;

		x = PainelDoJogo.WIDTH / 2;
		y = PainelDoJogo.HEIGHT / 2;
		this.width = width;
		this.height = height;

	}

	public void tick(Bloco[][] b, ArrayList<BlocoMovimento> blocoMovimentos, ArrayList<Vilao> viloes, ArrayList<Obstaculo> obstaculos) {

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
				if (Colisao.playerBloco(new Point(iX + (int) Estado.xOffset - 1, iY + (int) Estado.yOffset + 2),
						b[i][j])
						|| Colisao.playerBloco(
								new Point(iX + (int) Estado.xOffset - 1, iY + height + (int) Estado.yOffset - 1),
								b[i][j])) {
					left = false;
				}
				// topo
				if (Colisao.playerBloco(new Point(iX + (int) Estado.xOffset + 1, iY + (int) Estado.yOffset), b[i][j])
						|| Colisao.playerBloco(
								new Point(iX + width + (int) Estado.xOffset - 2, iY + (int) Estado.yOffset), b[i][j])) {
					pulando = false;
					caindo = true;
				}
				// baixo
				if (Colisao.playerBloco(
						new Point(iX + (int) Estado.xOffset + 2, iY + height + (int) Estado.yOffset + 1), b[i][j])
						|| Colisao.playerBloco(new Point(iX + width + (int) Estado.xOffset - 2,
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

		for (int i = 0; i < blocoMovimentos.size(); i++) {
			BlocoMovimento bloco = blocoMovimentos.get(i);
			if (bloco.getID() != 0) {
				if (Colisao.playerBlocoMovimento(
						new Point(iX + width + (int) Estado.xOffset, iY + (int) Estado.yOffset + 2), bloco)
						|| Colisao.playerBlocoMovimento(
								new Point(iX + width + (int) Estado.xOffset, iY + height + (int) Estado.yOffset - 2),
								bloco)) {
					right = false;
					caindo = true;
				}
				// esquerda
				if (Colisao.playerBlocoMovimento(
						new Point(iX + (int) Estado.xOffset - 1, iY + (int) Estado.yOffset + 2), bloco)
						|| Colisao.playerBlocoMovimento(
								new Point(iX + (int) Estado.xOffset - 2, iY + height + (int) Estado.yOffset - 2),
								bloco)) {
					left = false;
					caindo = true;
				}
				// topo
				if (Colisao.playerBlocoMovimento(new Point(iX + (int) Estado.xOffset + 1, iY + (int) Estado.yOffset),
						bloco)
						|| Colisao.playerBlocoMovimento(
								new Point(iX + width + (int) Estado.xOffset - 2, iY + (int) Estado.yOffset), bloco)) {
					pulando = false;
					caindo = true;
				}
				// baixo
				if (Colisao.playerBlocoMovimento(
						new Point(iX + (int) Estado.xOffset + 2, iY + height + (int) Estado.yOffset + 1), bloco)
						|| Colisao.playerBlocoMovimento(
								new Point(iX + width + (int) Estado.xOffset, iY + height + (int) Estado.yOffset),
								bloco)) {

					caindo = false;
					colisaoTopo = true;

					Estado.xOffset += bloco.getMovimento();

				} else {
					if (!colisaoTopo && !pulando)
						caindo = true;
				}
			}
		}


		for (int i = 0; i < viloes.size(); i++) {
			Vilao vilao = viloes.get(i);
			if (vilao.getID() != 0) {
				if (Colisao.playerVilao(new Point(iX + width + (int) Estado.xOffset, iY + (int) Estado.yOffset + 2),
						vilao)
						|| Colisao.playerVilao(
								new Point(iX + width + (int) Estado.xOffset, iY + height + (int) Estado.yOffset - 2),
								vilao)) {
					right = false;
					Estado.xOffset = dadosPlayer.getCheckPointX();
					Estado.yOffset = dadosPlayer.getCheckPointY();
					dadosPlayer.setVida();
				}
				// esquerda
				if (Colisao.playerVilao(new Point(iX + (int) Estado.xOffset - 1, iY + (int) Estado.yOffset + 2), vilao)
						|| Colisao.playerVilao(
								new Point(iX + (int) Estado.xOffset - 2, iY + height + (int) Estado.yOffset - 2),
								vilao)) {
					left = false;

				}
				// topo
				if (Colisao.playerVilao(new Point(iX + (int) Estado.xOffset + 1, iY + (int) Estado.yOffset), vilao)
						|| Colisao.playerVilao(
								new Point(iX + width + (int) Estado.xOffset - 2, iY + (int) Estado.yOffset), vilao)) {
					pulando = false;

				}
				// baixo
				if (Colisao.playerVilao(
						new Point(iX + (int) Estado.xOffset + 2, iY + height + (int) Estado.yOffset + 1), vilao)
						|| Colisao.playerVilao(
								new Point(iX + width + (int) Estado.xOffset, iY + height + (int) Estado.yOffset),
								vilao)) {

					colisaoTopo = true;
					if (vilao instanceof VilaoSouth) {
						vilao.getRetangulo().height = 0;
						vilao.getRetangulo().width = 0;
					}

					Estado.xOffset += vilao.getMovimento();

				}
			}
		}
		
		for (int j = 0; j < obstaculos.size(); j++) {
			// direita
			Obstaculo obs = obstaculos.get(j);
			if (Colisao.playerObstaculo(new Point(iX + width + (int) Estado.xOffset, iY + (int) Estado.yOffset + 2),
					obs)
					|| Colisao.playerObstaculo(
							new Point(iX + width + (int) Estado.xOffset, iY + height + (int) Estado.yOffset - 1),
							obs)) {
				right = false;
			}
			// esquerda
			if (Colisao.playerObstaculo(new Point(iX + (int) Estado.xOffset - 1, iY + (int) Estado.yOffset + 2),
					obs)
					|| Colisao.playerObstaculo(
							new Point(iX + (int) Estado.xOffset - 1, iY + height + (int) Estado.yOffset - 1),
							obs)) {
				left = false;
			}
			// topo
			if (Colisao.playerObstaculo(new Point(iX + (int) Estado.xOffset + 1, iY + (int) Estado.yOffset), obs)
					|| Colisao.playerObstaculo(
							new Point(iX + width + (int) Estado.xOffset - 2, iY + (int) Estado.yOffset), obs)) {
				pulando = false;
				caindo = true;
			}
			// baixo
			if (Colisao.playerObstaculo(
					new Point(iX + (int) Estado.xOffset + 2, iY + height + (int) Estado.yOffset + 1), obs)
					|| Colisao.playerObstaculo(new Point(iX + width + (int) Estado.xOffset - 2,
							iY + height + (int) Estado.yOffset + 1), obs)) {
				y = obs.getY() - height - Estado.yOffset;
				caindo = false;
				colisaoTopo = true;
			} else {
				if (!colisaoTopo && !pulando)
					caindo = true;
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
		g.setFont(new Font("Arial", Font.PLAIN, 32));
		g.drawString("Vidas: " + dadosPlayer.getVida(), (int) (x - 400), (int) (y - 150));
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

	public void setCheckpoint(int x) {
		dadosPlayer.setCheckPointX(x);
		dadosPlayer.setCheckPointY(-300);
	}

	public void getCheckpoint() {
		Estado.xOffset = dadosPlayer.getCheckPointX();
		Estado.yOffset = dadosPlayer.getCheckPointY();
	}
}
