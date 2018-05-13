package jogo.estados;

import java.awt.Font;
import java.awt.Graphics;

import jogo.entidades.Player;
import jogo.obstaculos.Mapa;
import jogo.principal.PainelDoJogo;

public class PrimeiraFase extends Estado
{
	private Player player;
	private Mapa mapa, mapa_2, mapa_3;
	
	
	public PrimeiraFase (GerenciadorDeEstado gerenciadorDeEstado) {
		super(gerenciadorDeEstado);
	}

	@Override
	public void inicializar() {
		player = new Player(30 , 30);
		mapa = new Mapa("/Maps/primeirafase.mapa");		
		
		xOffset = 3500;
		yOffset = -300;
		
//		xOffset = -300; // Original
//		yOffset = -300;
		
		player.setCheckpoint((int)xOffset);
	}
	
	public void fase2() {
		
	}

	@Override
	public void tick() {
		//caindo fora do mapa
		if(yOffset > 100) {
			player.getCheckpoint();
		}
		if(xOffset > 400 && xOffset < 499 && yOffset < 300)
			player.setCheckpoint(400);
		else if(xOffset > 500 && yOffset < 300)
			player.setCheckpoint(500);
		player.tick(mapa.getBlocos(), mapa.getBlocoMovimento(), mapa.getViloes());
		mapa.tick();
		
	}

	@Override
	public void draw(Graphics g) {
		player.draw(g);
		mapa.draw(g);
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
