package jogo.modelos;

import java.io.Serializable;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private int checkPointX;
	private int checkPointY;
	private int pontos;
	private int vida;
	private int mapa;
	
	public Usuario() {
		this.vida = 10;
		this.mapa = 1;
		this.pontos = 0;
	}
	
	public void salvarPontos()
	{
		System.out.println("Nome: " + nome + "Pontos: " + pontos);
		Ranking.getInstance().ordenarRanking();
		Ranking.getInstance().salvarUsuario(this);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCheckPointX() {
		return checkPointX;
	}
	public void setCheckPointX(int checkPointX) {
		this.checkPointX = checkPointX;
	}
	public int getCheckPointY() {
		return checkPointY;
	}
	public void setCheckPointY(int checkPointY) {
		this.checkPointY = checkPointY;
	}
	public int getPontos() {
		return pontos;
	}
	public void setPontos(int pontos) {
		this.pontos += pontos;
	}
	public int getVida() {
		return vida;
	}
	public void setVida() {
		this.vida--;
	}

	public int getMapa() {
		return mapa;
	}

	public void setMapa(int mapa) {
		this.mapa = mapa;
	}
	
	public void setCheckpoint(int x) {
		this.setCheckPointX(x);
		this.setCheckPointY(-400);
	}

}
