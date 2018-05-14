package jogo.recursosexternos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imagem {

	private BufferedImage[] imagens;
	private static Imagem instance;
	
	private Imagem() {
		imagens = new BufferedImage[12];
		try {
			imagens[0] = carregarImagem("/blocks/bloco64.png");
			imagens[1] = carregarImagem("/blocks/obstaculos/blocodegelo64.png");
			imagens[2] = carregarImagem("/blocks/obstaculos/blocometal64.png");
			imagens[3] = carregarImagem("/blocks/inimigos/bolaespinho64.png");
			imagens[4] = carregarImagem("/blocks/inimigos/cogumeloespinho64.png");
			imagens[5] = carregarImagem("/blocks/inimigos/south64.png");
			imagens[6] = carregarImagem("/blocks/obstaculos/vida64.png");
			imagens[7] = carregarImagem("/blocks/obstaculos/espinhudo64.png");
			imagens[8] = carregarImagem("/blocks/inimigos/goomba64.png");
			imagens[9] = carregarImagem("/blocks/inimigos/google64.png");
			imagens[10] = carregarImagem("/blocks/inimigos/mario64.png");
			imagens[11] = carregarImagem("/blocks/inimigos/chefao.png");
						
		}catch (IOException e) {
			e.printStackTrace();
		}
				
			
	}
	
	
	public static Imagem getInstance() {
		if(instance== null) {
			instance = new Imagem();
		}
		return instance;
		
	}
	
	public BufferedImage[] getImagens() {
		return imagens;
	}
	
	private BufferedImage carregarImagem(String caminho) throws IOException {
		return ImageIO.read(getClass().getResourceAsStream(caminho));
	}
}
