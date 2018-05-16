package jogo.fisica;

import java.awt.Point;

import jogo.entidades.Personagem;
import jogo.obstaculos.Obstaculo;

public class Colisao {
	
	public static boolean playerVilao(Point p, Personagem v) {
		return v.getRetangulo().contains(p);
	}
	
	public static boolean playerObstaculo(Point p, Obstaculo o) {
		return o.getRetangulo().contains(p);
	}
	
}
