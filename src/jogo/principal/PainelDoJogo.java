package jogo.principal;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import jogo.estados.GerenciadorDeEstado;

public class PainelDoJogo extends JPanel implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 900;
	public static final int HEIGHT = 450;
	
	private Thread thread;
	private boolean isRunning = false;

	private int FPS = 60;
	private long tempoDeExecucao = 500 / FPS;
	
	private GerenciadorDeEstado gerenciadorDeEstado;
	
	public PainelDoJogo() {
		Dimension d = new Dimension(WIDTH, HEIGHT);
		super.setPreferredSize(d);
		
		addKeyListener(this);
		setFocusable(true);

		
		this.instanciarThread();
	}
	
	private void instanciarThread() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void tick() {
		gerenciadorDeEstado.tick();
	}
	
	@Override
	public void run() {
		long inicio, duracao, aguardando;
		
		gerenciadorDeEstado = new GerenciadorDeEstado();
		
		while(isRunning) 
		{
			inicio = System.nanoTime();
			tick();
			super.repaint();
			
			duracao = System.nanoTime() - inicio;
			
			aguardando = tempoDeExecucao - duracao / 1000000;
			
			if(aguardando <= 0) {
				aguardando = 5;
			}
			
			try {
				Thread.sleep(aguardando);
			}
			catch(Exception e) {
				
			}
		}		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, WIDTH, HEIGHT);
		try {
			g.drawImage(ImageIO.read(getClass().getResourceAsStream("/blocks/fundo.png")), 0, -0, WIDTH, HEIGHT, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gerenciadorDeEstado.draw(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		gerenciadorDeEstado.keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gerenciadorDeEstado.keyReleased(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}
