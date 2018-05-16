package jogo.entidades;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import jogo.fisica.Colisao;
import jogo.modelos.Usuario;
import jogo.obstaculos.Bloco;
import jogo.obstaculos.BlocoMovimento;
import jogo.obstaculos.ObstaculoDiverso;
import jogo.principal.PainelDoJogo;
import jogo.recursosexternos.Camera;
import jogo.recursosexternos.Imagem;

public class Player extends Personagem {
	private Camera camera;
	private boolean right = false;
	private boolean left = false;
	private boolean colisaoTopo = false;
	private Usuario dadosPlayer;
	// private Usuario dadosPlayer2;
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
	private boolean matouChefao = false;
	private Bloco[][] blocos;
	private ArrayList<BlocoMovimento> blocoMovimentos;
	private ArrayList<Personagem> viloes;
	private ArrayList<ObstaculoDiverso> obstaculoDiversos;

	public Player(int width, int height, Bloco[][] b, ArrayList<BlocoMovimento> blocoMovimentos,
					ArrayList<Personagem> viloes,
					ArrayList<ObstaculoDiverso> obstaculoDiversos) {
		dadosPlayer = new Usuario();
		this.blocos = b;
		this.blocoMovimentos = blocoMovimentos;
		this.viloes = viloes;
		this.obstaculoDiversos = obstaculoDiversos;
		// dadosPlayer2 = new Usuario();
		velPulo = 5.5;
		atualVelPulo = velPulo;
		maxVelCaindo = 5.5;
		atualVelCaindo = 0.1;

		x = PainelDoJogo.WIDTH / 2;
		y = PainelDoJogo.HEIGHT / 2;
		this.width = width;
		this.height = height;

		camera = Camera.getInstance();
	}

	public void tick() {

		int iX = (int) x;
		int iY = (int) y;

		this.validarColisacaoBloco(blocos, iX, iY);
		this.validarColisaoBlocoMovimento(blocoMovimentos, iX, iY);
		this.validarColisaoVilao(viloes, iX, iY);
		this.validarColisacaoObstaculo(obstaculoDiversos, iX, iY);

		colisaoTopo = false;

		if (right) {
			camera.setxOffset(camera.getxOffset() + velocidadeDeMovimento);
		} else if (left) {
			camera.setxOffset(camera.getxOffset() - velocidadeDeMovimento);
		}
		if (pulando) {
			camera.setyOffset(camera.getyOffset() - atualVelPulo);
			atualVelPulo -= 0.1;
			if (atualVelPulo <= 0) {
				atualVelPulo = velPulo;
				pulando = false;
				caindo = true;
			}
		} else if (caindo) {
			camera.setyOffset(camera.getyOffset() + atualVelCaindo);
			if (atualVelCaindo < maxVelCaindo) {
				atualVelCaindo += 0.1;
			}
		} else if (!caindo) {
			atualVelCaindo = 0.1;

		}

	}

	public void draw(Graphics g) {
		//g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 32));
		g.drawString("Vidas: " + dadosPlayer.getVida(), (int) (x - 400), (int) (y - 150));
		g.setFont(new Font("Arial", Font.PLAIN, 32));
		g.drawString("Pontos: " + dadosPlayer.getPontos(), (int) (x - 400), (int) (y - 100));
//		g.fillRect((int) x, (int) y, width, height);
		g.drawImage(Imagem.getInstance().getImagens()[12], (int)x,
				(int)y, width, height, null);

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

	public Bloco[][] getBlocos() {
		return blocos;
	}

	public void setBlocos(Bloco[][] blocos) {
		this.blocos = blocos;
	}

	public ArrayList<BlocoMovimento> getBlocoMovimentos() {
		return blocoMovimentos;
	}

	public void setBlocoMovimentos(ArrayList<BlocoMovimento> blocoMovimentos) {
		this.blocoMovimentos = blocoMovimentos;
	}

	public ArrayList<Personagem> getViloes() {
		return viloes;
	}

	public void setViloes(ArrayList<Personagem> viloes) {
		this.viloes = viloes;
	}

	public ArrayList<ObstaculoDiverso> getObstaculoDiversos() {
		return obstaculoDiversos;
	}

	public void setObstaculoDiversos(ArrayList<ObstaculoDiverso> obstaculoDiversos) {
		this.obstaculoDiversos = obstaculoDiversos;
	}
	
	public Usuario getDadosPlayer() {
		return dadosPlayer;
	}
	
	public boolean getMatouChefao() {
		return matouChefao;
	}
	

	
	private void validarColisacaoObstaculo(ArrayList<ObstaculoDiverso> obstaculoDiversos, int iX, int iY) {
		for (int j = 0; j < obstaculoDiversos.size(); j++) {
			// direita
			ObstaculoDiverso obs = obstaculoDiversos.get(j);
			if (Colisao.playerObstaculo(
					new Point(iX + width + (int) camera.getxOffset(), iY + (int) camera.getyOffset() + 2), obs)
					|| Colisao.playerObstaculo(new Point(iX + width + (int) camera.getxOffset(),
							iY + height + (int) camera.getyOffset() - 1), obs)) {
				right = false;
			}
			// esquerda
			if (Colisao.playerObstaculo(
					new Point(iX + (int) camera.getxOffset() - 1, iY + (int) camera.getyOffset() + 2), obs)
					|| Colisao.playerObstaculo(
							new Point(iX + (int) camera.getxOffset() - 1, iY + height + (int) camera.getyOffset() - 1),
							obs)) {
				left = false;
			}
			// topo
			if (Colisao.playerObstaculo(new Point(iX + (int) camera.getxOffset() + 1, iY + (int) camera.getyOffset()),
					obs)
					|| Colisao.playerObstaculo(
							new Point(iX + width + (int) camera.getxOffset() - 2, iY + (int) camera.getyOffset()),
							obs)) {
				pulando = false;
				caindo = true;
			}
			// baixo
			if (Colisao.playerObstaculo(
					new Point(iX + (int) camera.getxOffset() + 2, iY + height + (int) camera.getyOffset() + 1), obs)
					|| Colisao.playerObstaculo(new Point(iX + width + (int) camera.getxOffset() - 2,
							iY + height + (int) camera.getyOffset() + 1), obs)) {
				y = obs.getRetangulo().getY() - height - camera.getyOffset();
				caindo = false;
				colisaoTopo = true;
			} else {
				if (!colisaoTopo && !pulando)
					caindo = true;
			}
		}		
	}

	private void validarColisaoVilao(ArrayList<Personagem> viloes, int iX, int iY) {
		for (int i = 0; i < viloes.size(); i++) {
			Personagem personagem = viloes.get(i);
			if (personagem.getID() != 0) {
				if (Colisao.playerVilao(
						new Point(iX + width + (int) camera.getxOffset(), iY + (int) camera.getyOffset() + 2), personagem)
						|| Colisao.playerVilao(new Point(iX + width + (int) camera.getxOffset(),
								iY + height + (int) camera.getyOffset() - 2), personagem)) {
					right = false;
					camera.setxOffset(dadosPlayer.getCheckPointX());
					camera.setyOffset(dadosPlayer.getCheckPointY());
					dadosPlayer.setVida();
				}
				// esquerda
				if (Colisao.playerVilao(
						new Point(iX + (int) camera.getxOffset() - 1, iY + (int) camera.getyOffset() + 2), personagem)
						|| Colisao.playerVilao(new Point(iX + (int) camera.getxOffset() - 2,
								iY + height + (int) camera.getyOffset() - 2), personagem)) {
					left = false;

				}
				// topo
				if (Colisao.playerVilao(new Point(iX + (int) camera.getxOffset() + 1, iY + (int) camera.getyOffset()),
						personagem)
						|| Colisao.playerVilao(
								new Point(iX + width + (int) camera.getxOffset() - 2, iY + (int) camera.getyOffset()),
								personagem)) {
					pulando = false;

				}
				// baixo
				if (Colisao
						.playerVilao(new Point(iX + (int) camera.getxOffset() + 2,
								iY + height + (int) camera.getyOffset() + 1), personagem)
						|| Colisao.playerVilao(new Point(iX + width + (int) camera.getxOffset(),
								iY + height + (int) camera.getyOffset()), personagem)) {

					colisaoTopo = true;
					if (personagem instanceof VilaoSouth || 
							personagem instanceof VilaoGoomba || 
							personagem instanceof VilaoFlappy ||
							personagem instanceof VilaoMario) {
						personagem.getRetangulo().height = 0;
						personagem.getRetangulo().width = 0;
						
						this.dadosPlayer.setPontos(20);
					}
					
					colisaoTopo = true;
					if (personagem instanceof VilaoChefao) {
						if(((VilaoChefao) personagem).getVida() == 0) 
						{
							personagem.getRetangulo().height = 0;
							personagem.getRetangulo().width = 0;
							matouChefao = true;
							JOptionPane.showMessageDialog(null, "Parabéns você zerou este lindissimo jogo :)", "You're are a champion", 1);
							
						}
						camera.setxOffset(-400);
						((VilaoChefao) personagem).setVida();
						this.dadosPlayer.setPontos(20);
					}


					camera.setxOffset(camera.getxOffset() + personagem.getMovimento());

				}
			}
		}
	}

	private void validarColisaoBlocoMovimento(ArrayList<BlocoMovimento> blocoMovimentos, int iX, int iY) {
		for (int i = 0; i < blocoMovimentos.size(); i++) {
			BlocoMovimento bloco = blocoMovimentos.get(i);
			if (bloco.getID() != 0) {
				if (Colisao.playerObstaculo(
						new Point(iX + width + (int) camera.getxOffset(), iY + (int) camera.getyOffset() + 2), bloco)
						|| Colisao.playerObstaculo(new Point(iX + width + (int) camera.getxOffset(),
								iY + height + (int) camera.getyOffset() - 2), bloco)) {
					right = false;
					caindo = true;
				}
				// esquerda
				if (Colisao.playerObstaculo(
						new Point(iX + (int) camera.getxOffset() - 1, iY + (int) camera.getyOffset() + 2), bloco)
						|| Colisao.playerObstaculo(new Point(iX + (int) camera.getxOffset() - 2,
								iY + height + (int) camera.getyOffset() - 2), bloco)) {
					left = false;
					caindo = true;
				}
				// topo
				if (Colisao.playerObstaculo(
						new Point(iX + (int) camera.getxOffset() + 1, iY + (int) camera.getyOffset()), bloco)
						|| Colisao.playerObstaculo(
								new Point(iX + width + (int) camera.getxOffset() - 2, iY + (int) camera.getyOffset()),
								bloco)) {
					pulando = false;
					caindo = true;
				}
				// baixo
				if (Colisao
						.playerObstaculo(new Point(iX + (int) camera.getxOffset() + 2,
								iY + height + (int) camera.getyOffset() + 1), bloco)
						|| Colisao.playerObstaculo(new Point(iX + width + (int) camera.getxOffset(),
								iY + height + (int) camera.getyOffset()), bloco)) {

					caindo = false;
					colisaoTopo = true;

					camera.setxOffset(camera.getxOffset() + bloco.getMovimento());

				} else {
					if (!colisaoTopo && !pulando)
						caindo = true;
				}
			}
		}
		
	}
	
	public void validarColisacaoBloco(Bloco[][] b, int iX, int iY) {
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				// direita
				if (Colisao.playerObstaculo(
						new Point(iX + width + (int) camera.getxOffset(), iY + (int) camera.getyOffset() + 2), b[i][j])
						|| Colisao.playerObstaculo(new Point(iX + width + (int) camera.getxOffset(),
								iY + height + (int) camera.getyOffset() - 1), b[i][j])) {
					right = false;
				}
				// esquerda
				if (Colisao.playerObstaculo(
						new Point(iX + (int) camera.getxOffset() - 1, iY + (int) camera.getyOffset() + 2), b[i][j])
						|| Colisao.playerObstaculo(new Point(iX + (int) camera.getxOffset() - 1,
								iY + height + (int) camera.getyOffset() - 1), b[i][j])) {
					left = false;
				}
				// topo
				if (Colisao.playerObstaculo(
						new Point(iX + (int) camera.getxOffset() + 1, iY + (int) camera.getyOffset()), b[i][j])
						|| Colisao.playerObstaculo(
								new Point(iX + width + (int) camera.getxOffset() - 2, iY + (int) camera.getyOffset()),
								b[i][j])) {
					pulando = false;
					caindo = true;
				}
				// baixo
				if (Colisao
						.playerObstaculo(new Point(iX + (int) camera.getxOffset() + 2,
								iY + height + (int) camera.getyOffset() + 1), b[i][j])
						|| Colisao.playerObstaculo(new Point(iX + width + (int) camera.getxOffset() - 2,
								iY + height + (int) camera.getyOffset() + 1), b[i][j])) {
					y = b[i][j].getRetangulo().getY() - height - camera.getyOffset();
					caindo = false;
					colisaoTopo = true;
				} else {
					if (!colisaoTopo && !pulando)
						caindo = true;
				}
			}
		}
	}

}
