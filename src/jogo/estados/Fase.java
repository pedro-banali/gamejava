package jogo.estados;

import java.awt.Graphics;
import java.util.ArrayList;

import jogo.DAO.RankingDAOTxt;
import jogo.entidades.Player;
import jogo.mapas.Mapa;
import jogo.mapas.PrimeiroMapa;
import jogo.mapas.SegundoMapa;
import jogo.mapas.TerceiroMapa;
import jogo.recursosexternos.Camera;

public class Fase extends Estado {
	private Player player;

	private ArrayList<Mapa> mapas;
	private Mapa mapa;

	public Fase(GerenciadorDeEstado gerenciadorDeEstado, String nome) {
		super(gerenciadorDeEstado);
		camera = Camera.getInstance();
		player.getDadosPlayer().setNome(nome);
	}

	@Override
	public void inicializar() {
		new RankingDAOTxt().ler();
		mapas = new ArrayList<Mapa>();
		mapa = new PrimeiroMapa("/Maps/primeirafase.mapa", true);
		mapas.add(mapa);
		mapas.add(new SegundoMapa("/Maps/segundafase.mapa", false));
		mapas.add(new TerceiroMapa("/Maps/terceirafase.mapa", false));
		
		player = new Player(30, 30, mapa.getBlocos(), mapa.getBlocoMovimento(), mapa.getViloes(), mapa.getObstaculos());
		
		camera.setxOffset(-300);
		camera.setyOffset(-300);

		player.getDadosPlayer().setCheckpoint((int) camera.getxOffset());
	}

	@Override
	public void tick() {
		if (player.getDadosPlayer().getVida() < 0) {
			this.reiniciarJogo();
		}
		if (camera.getyOffset() > 100 && player.getDadosPlayer().getVida() >= 0) {
			player.getDadosPlayer().setVida();
			camera.setxOffset(player.getDadosPlayer().getCheckPointX());
			camera.setyOffset(player.getDadosPlayer().getCheckPointY());
		}

		if (player.getDadosPlayer().getMapa() == 1) {

			if (camera.getxOffset() >= 640 && camera.getxOffset() < 1664 && camera.getyOffset() < 300)
				player.getDadosPlayer().setCheckpoint(640);
			if (camera.getxOffset() >= 1664 && camera.getxOffset() < 2881 && camera.getyOffset() < 300)
				player.getDadosPlayer().setCheckpoint(1664);
			if (camera.getxOffset() == 2881 && camera.getyOffset() < 300)
				player.getDadosPlayer().setCheckpoint(2881);
		}
		if (player.getDadosPlayer().getMapa() == 2) {
			if (camera.getxOffset() >= 705 && camera.getxOffset() < 1664 && camera.getyOffset() < 300)
				player.getDadosPlayer().setCheckpoint(705);
			if (camera.getxOffset() >= 1664 && camera.getxOffset() < 2582 && camera.getyOffset() < 300)
				player.getDadosPlayer().setCheckpoint(1664);
			if (camera.getxOffset() >= 2582 && camera.getyOffset() < 300)
				player.getDadosPlayer().setCheckpoint(2582);
		}
		if (player.getDadosPlayer().getMapa() == 3) {

			player.getDadosPlayer().setCheckpoint(-400);
		}
		// caindo fora do mapa

		for (int i = 0; i < mapas.size(); i++) {
			mapa = mapas.get(i);
			if (mapa.getMapaAtual() && mapa.isMapaFinalizado() && i < 2) {
				mapas.get(i).setMapaAtual(false);
				mapa = mapas.get(i + 1);
				player.getDadosPlayer().setMapa(player.getDadosPlayer().getMapa() + 1);
				mapa.setMapaAtual(true);
				player.getDadosPlayer().setCheckpoint(-300);
				player.getDadosPlayer().setPontos(30);
			}
			if (mapa.getMapaAtual() && i == 2 && player.getMatouChefao()) {
				this.reiniciarJogo();
			}
			if (mapa.getMapaAtual()) {
				player.setBlocoMovimentos(mapa.getBlocoMovimento());
				player.setBlocos(mapa.getBlocos());
				player.setViloes(mapa.getViloes());
				player.setObstaculoDiversos(mapa.getObstaculos());
				player.tick();
				mapa.tick();
			}
		}

	}

	@Override
	public void draw(Graphics g) {
		if (player.getDadosPlayer().getVida() < 0) {
			//gerenciadorDeEstado.draw(g);
		} else {
			player.draw(g);
			for (int i = 0; i < mapas.size(); i++) {
				mapa = mapas.get(i);
				if (mapa.getMapaAtual()) {
					mapa.draw(g);
				}
			}
		}
		g.drawString(camera.getxOffset() + "", 100, 100);
	}

	@Override
	public void keyPressed(int k) {
		player.KeyPressed(k);

	}

	@Override
	public void keyReleased(int k) {
		player.KeyReleased(k);

	}
	
	private void reiniciarJogo()
	{
		
		player.getDadosPlayer().salvarPontos();
		new RankingDAOTxt().gravar();
		gerenciadorDeEstado.getEstados().pop();
		gerenciadorDeEstado = new GerenciadorDeEstado();
	}

}
