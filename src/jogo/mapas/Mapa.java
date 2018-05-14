package jogo.mapas;

import java.awt.Graphics;
import java.util.ArrayList;

import jogo.entidades.Vilao;
import jogo.obstaculos.Bloco;
import jogo.obstaculos.BlocoMovimento;
import jogo.obstaculos.ObstaculoDiverso;

public abstract class Mapa {
	protected String caminho;
	protected String line;
	protected int width, height;
	protected boolean mapaAtual;
	protected boolean mapaFinalizado;

	protected Bloco[][] blocos;

	protected ArrayList<BlocoMovimento> blocoMovimentos;
	protected ArrayList<Vilao> viloes;
	protected ArrayList<ObstaculoDiverso> obstaculoDiversos;
	public Mapa(String caminho , boolean mapaAtual)
	{
		this.caminho = caminho;
		this.mapaAtual = mapaAtual;
		mapaFinalizado = false;
	}
	
	public abstract void tick();

	public abstract void draw(Graphics g);

	public abstract void carregarMapa();
	
	public ArrayList<ObstaculoDiverso> getObstaculos() {
		return obstaculoDiversos;
	}

	public Bloco[][] getBlocos() {
		return blocos;
	}

	public ArrayList<Vilao> getViloes() {
		return viloes;
	}

	public ArrayList<BlocoMovimento> getBlocoMovimento() {
		return blocoMovimentos;
	}
	
	public boolean getMapaAtual() {
		return mapaAtual;
	}
}
