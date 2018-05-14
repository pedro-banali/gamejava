package jogo.fisica;

import java.awt.Point;

import jogo.entidades.Vilao;
import jogo.obstaculos.Bloco;
import jogo.obstaculos.BlocoMovimento;
import jogo.obstaculos.Obstaculo;

public class Colisao {

	public static boolean playerBloco(Point p, Bloco b) {
		return b.contains(p);
	}
	
	public static boolean playerBlocoMovimento(Point p, BlocoMovimento b) {
		return b.contains(p);
	}
	
	public static boolean playerVilao(Point p, Vilao v) {
		return v.getRetangulo().contains(p);
	}
	
	public static boolean playerObstaculo(Point p, Obstaculo o) {
		return o.contains(p);
	}
	
}
