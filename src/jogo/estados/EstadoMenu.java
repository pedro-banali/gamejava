package jogo.estados;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import jogo.principal.PainelDoJogo;

public class EstadoMenu extends Estado {
	
	private String[] opcoes = {"Iniciar", "Help", "Quit"};
	private int currentSelection = 0;
	
	public EstadoMenu(GerenciadorDeEstado gerenciadorDeEstado) {
		super(gerenciadorDeEstado);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void inicializar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		g.setColor(new Color(10, 200 , 20));
		g.fillRect(0, 0, PainelDoJogo.WIDTH, PainelDoJogo.HEIGHT);
		
		for(int i = 0; i < opcoes.length; i++ ) {
			if(i == currentSelection) {
				g.setColor(Color.GREEN);
			}
			else {
				g.setColor(Color.BLACK);
			}
			//g.drawLine(PainelDoJogo.WIDTH / 2, 0, PainelDoJogo.WIDTH / 2, PainelDoJogo.HEIGHT);
			g.setFont(new Font("Arial", Font.PLAIN, 72));
			g.drawString(opcoes[i], PainelDoJogo.WIDTH / 2 -75 , 100 + i *100);
		}
	}

	@Override
	public void keyPressed(int k) {
		if( k == KeyEvent.VK_DOWN) {
			currentSelection ++;
			if(currentSelection >= opcoes.length) {
				currentSelection = 0;
			}
		}else if (k == KeyEvent.VK_UP) {
			currentSelection--;
			if(currentSelection <0) {
				currentSelection = opcoes.length -1;
			}
		}
		
		if(k == KeyEvent.VK_ENTER) {
			if(currentSelection == 0) {
				
				gerenciadorDeEstado.getEstados().push(new Fase(gerenciadorDeEstado));
				
			}else if(currentSelection == 1) {
				
			}else if (currentSelection == 2) {
				System.exit(0);
			}
		}
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
