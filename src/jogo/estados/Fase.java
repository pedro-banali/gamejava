package jogo.estados;

import java.awt.Font;
import java.awt.Graphics;

import jogo.entidades.Player;
import jogo.obstaculos.Mapa;
import jogo.principal.PainelDoJogo;

public class Fase extends Estado
{
	private Player player;
	private Mapa mapa, mapa_2, mapa_3;
	
	
	public Fase (GerenciadorDeEstado gerenciadorDeEstado) {
		super(gerenciadorDeEstado);
	}

	@Override
	public void inicializar() {
		player = new Player(30 , 30);
		mapa = new Mapa("/Maps/primeirafase.mapa");		
		
		xOffset = -300;
		yOffset = -300;
		
//		xOffset = -300; // Original
//		yOffset = -300;
		
		player.setCheckpoint((int)xOffset);
	}

	@Override
	public void tick() {
		//caindo fora do mapa
		if(yOffset > 100) {
			player.getCheckpoint();
		}
		if(xOffset == 640 && yOffset < 300)
			player.setCheckpoint(640);
		if(xOffset == 1664 && yOffset < 300)
			player.setCheckpoint(1664);
		if(xOffset == 2881 && yOffset < 300)
			player.setCheckpoint(2881);
		player.tick(mapa.getBlocos(), mapa.getBlocoMovimento(), mapa.getViloes(), mapa.getObstaculos());
		mapa.tick();
		
	}

	@Override
	public void draw(Graphics g) {
		player.draw(g);
		mapa.draw(g);
		g.drawString(xOffset + "", 100, 100);
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
