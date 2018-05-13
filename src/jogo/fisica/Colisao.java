package jogo.fisica;

import java.awt.Point;

import jogo.obstaculos.Bloco;

public class Colisao {

	public static boolean playerBloco(Point p, Bloco b) {
		return b.contains(p);
	}
	
}
