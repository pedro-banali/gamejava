package jogo.estados;

import java.awt.Graphics;
import java.util.ArrayList;

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
	public Fase(GerenciadorDeEstado gerenciadorDeEstado) {
		super(gerenciadorDeEstado);
		camera = Camera.getInstance();
	}

	@Override
	public void inicializar() {
		player = new Player(30, 30);
		mapas = new ArrayList<Mapa>();
		mapa = new SegundoMapa("/Maps/segundafase.mapa", true);
		mapas.add(new PrimeiroMapa("/Maps/primeirafase.mapa", false));
		mapas.add(mapa);
		mapas.add(new TerceiroMapa("/Maps/terceirafase.mapa", false));

		camera.setxOffset(-300);
		camera.setyOffset(-300);

		player.setCheckpoint((int) camera.getxOffset());
	}

	@Override
	public void tick() {
		// caindo fora do mapa
		if (camera.getyOffset() > 100) {
			player.getCheckpoint();
		}
		if (camera.getxOffset() == 640 && camera.getyOffset() < 300)
			player.setCheckpoint(640);
		if (camera.getxOffset() == 1664 && camera.getyOffset() < 300)
			player.setCheckpoint(1664);
		if (camera.getxOffset() == 2881 && camera.getyOffset() < 300)
			player.setCheckpoint(2881);

		for (int i = 0; i < mapas.size(); i++) {
			mapa = mapas.get(i);
			if (mapa.getMapaAtual()) {
				player.tick(mapa.getBlocos(), mapa.getBlocoMovimento(), mapa.getViloes(), mapa.getObstaculos());
				mapa.tick();
			}
		}

	}

	@Override
	public void draw(Graphics g) {
		Mapa mapa;
		player.draw(g);
		for (int i = 0; i < mapas.size(); i++) {
			mapa = mapas.get(i);
			if (mapa.getMapaAtual()) {
				mapa.draw(g);
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

}
