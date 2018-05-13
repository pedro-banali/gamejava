package jogo.estados;

import java.awt.Graphics;

import jogo.entidades.Player;
import jogo.obstaculos.Mapa;

public class PrimeiraFase extends Estado
{
	private Player player;
	private Mapa mapa;
	
	
	public PrimeiraFase (GerenciadorDeEstado gerenciadorDeEstado) {
		super(gerenciadorDeEstado);
	}

	@Override
	public void inicializar() {
		player = new Player(30 , 30);
		mapa = new Mapa("", 4, 4);		
		
		xOffset = -700;
		yOffset = -400;
	}

	@Override
	public void tick() {
		player.tick(mapa.getBlocos());
		
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
