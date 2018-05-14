package jogo.fisica;

import java.awt.Point;

import jogo.entidades.Vilao;
import jogo.obstaculos.Obstaculo;

public class Colisao {
	
	public static boolean playerVilao(Point p, Vilao v) {
		return v.getRetangulo().contains(p);
	}
	
	public static boolean playerObstaculo(Point p, Obstaculo o) {
		return o.getRetangulo().contains(p);
	}
	
}
