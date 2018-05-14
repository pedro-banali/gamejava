package jogo.mapas;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import jogo.entidades.Vilao;
import jogo.entidades.VilaoBola;
import jogo.entidades.VilaoCogumelo;
import jogo.entidades.VilaoSouth;
import jogo.obstaculos.Bloco;
import jogo.obstaculos.BlocoMovimento;
import jogo.obstaculos.ObstaculoDiverso;

public class PrimeiroMapa extends Mapa {
	

	public PrimeiroMapa(String caminho, boolean mapaAtual) {
		super(caminho, mapaAtual);
		blocoMovimentos = new ArrayList<BlocoMovimento>();
		viloes = new ArrayList<Vilao>();
		blocos = new Bloco[height][width];
		obstaculoDiversos = new ArrayList<ObstaculoDiverso>();

		carregarMapa();
	}

	public void tick() {
		for (int i = 0; i < blocoMovimentos.size(); i++) {
			blocoMovimentos.get(i).tick();
		}
		for (int i = 0; i < viloes.size(); i++) {
			viloes.get(i).tick();
		}
	}

	public void draw(Graphics g) {
		for (int i = 0; i < blocos.length; i++) {
			for (int j = 0; j < blocos[i].length; j++) {
				blocos[i][j].draw(g);
			}
		}

		for (int i = 0; i < blocoMovimentos.size(); i++) {
			blocoMovimentos.get(i).draw(g);
		}

		for (int i = 0; i < viloes.size(); i++) {
			viloes.get(i).draw(g);
		}
		
		for (int i = 0; i < obstaculoDiversos.size(); i++) {
			obstaculoDiversos.get(i).draw(g);
		}
	}

	public void carregarMapa() {

		InputStream is = this.getClass().getResourceAsStream(caminho);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		int length;

		int id = 0;
		try {
			height = Integer.parseInt(br.readLine());
			width = Integer.parseInt(br.readLine());

			blocos = new Bloco[width][height];

			for (int y = 0; y < height; y++) {
				line = br.readLine();
				String[] tokens = line.split("\\s+");
				for (int x = 0; x < width; x++) {
					id = Integer.parseInt(tokens[x]);
					blocos[x][y] = id != 0 ? new Bloco(x * Bloco.tamanhoBloco, y * Bloco.tamanhoBloco, id)
							: new Bloco(0, 0, id);
				}
			}

			line = br.readLine();
			line = br.readLine();

			length = Integer.parseInt(line);

			for (int i = 0; i < length; i++) {
				line = br.readLine();
				String[] tokens = line.split("\\s+");
				blocoMovimentos.add(new BlocoMovimento(Integer.parseInt(tokens[0]) * Bloco.tamanhoBloco,
						Integer.parseInt(tokens[1]) * Bloco.tamanhoBloco, Integer.parseInt(tokens[2]),
						Integer.parseInt(tokens[3]) * Bloco.tamanhoBloco,
						Integer.parseInt(tokens[4]) * Bloco.tamanhoBloco));
			}

			line = br.readLine();
			line = br.readLine();

			length = Integer.parseInt(line);

			for (int i = 0; i < length; i++) {
				line = br.readLine();
				String[] tokens = line.split("\\s+");
				if (i < 1) {
					viloes.add(new VilaoBola(Integer.parseInt(tokens[0]) * Bloco.tamanhoBloco,
							Integer.parseInt(tokens[1]) * Bloco.tamanhoBloco, Integer.parseInt(tokens[2]),
							Integer.parseInt(tokens[3]) * Bloco.tamanhoBloco,
							Integer.parseInt(tokens[4]) * Bloco.tamanhoBloco));
				} else if (i == 1) {
					viloes.add(new VilaoCogumelo(Integer.parseInt(tokens[0]) * Bloco.tamanhoBloco,
							Integer.parseInt(tokens[1]) * Bloco.tamanhoBloco, Integer.parseInt(tokens[2]),
							Integer.parseInt(tokens[3]) * Bloco.tamanhoBloco,
							Integer.parseInt(tokens[4]) * Bloco.tamanhoBloco));

				} else {
					viloes.add(new VilaoSouth(Integer.parseInt(tokens[0]) * Bloco.tamanhoBloco,
							Integer.parseInt(tokens[1]) * Bloco.tamanhoBloco, Integer.parseInt(tokens[2]),
							Integer.parseInt(tokens[3]) * Bloco.tamanhoBloco,
							Integer.parseInt(tokens[4]) * Bloco.tamanhoBloco));
				}
			}

		ObstaculoDiverso obs = new ObstaculoDiverso(2560, -0, id, 2);
		obstaculoDiversos.add(obs);
		
		obs = new ObstaculoDiverso(2560, -64, id, 2);
		obstaculoDiversos.add(obs);
		
		obs = new ObstaculoDiverso(260, 0, id, 6);
		obstaculoDiversos.add(obs);
		
		obs = new ObstaculoDiverso(764, -64, id, 7);
		obstaculoDiversos.add(obs);

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
