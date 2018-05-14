package jogo.principal;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Tela {
	private PainelDoJogo painel;

	public Tela() {
		painel = new PainelDoJogo();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.add(painel, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
}
