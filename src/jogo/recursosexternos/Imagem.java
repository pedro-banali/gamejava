package jogo.recursosexternos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imagem {

	private BufferedImage[] imagens;
	private static Imagem instance;
	
	private Imagem() {
		imagens = new BufferedImage[6];
		try {
			imagens[0] = carregarImagem("/blocks/bloco64.png");
			imagens[1] = carregarImagem("/blocks/blocodegelo64.png");
			imagens[2] = carregarImagem("/blocks/blocometal64.png");
			imagens[3] = carregarImagem("/blocks/bolaespinho64.png");
			imagens[4] = carregarImagem("/blocks/cogumeloespinho64.png");
			imagens[5] = carregarImagem("/blocks/south64.png");
						
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
