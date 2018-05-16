package jogo.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jogo.modelos.Ranking;
import jogo.modelos.Usuario;

public class RankingDAOTxt extends RankingDAO {

	@Override
	public void gravar() {
		String arquivo = "dados.txt";
		try (PrintWriter out = new PrintWriter(arquivo)) {
			File file = new File(arquivo);
			if (!file.exists()) {
				file.createNewFile();
			}
			for (Usuario usuario : Ranking.getInstance().getUsuarios())
				out.println(usuario.getNome() + "\t " + usuario.getPontos() + "");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void ler() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Usuario usuario;
		try {
			String arquivo = "dados.txt";
			File file = new File(arquivo);
			if (file.exists()) {
				BufferedReader buffRead;

				buffRead = new BufferedReader(new FileReader(arquivo));

				String linha = "";
				while (true) {
					linha = buffRead.readLine();
					if (linha != null) {
						usuario = new Usuario();
						usuario.setNome(linha.split("\\s+")[0]);
						usuario.setPontos(Integer.parseInt(linha.split("\\s+")[1]));
						usuarios.add(usuario);
						usuario = null;

					} else
						break;
				}
				if (Ranking.getInstance().getUsuarios().size() > 0) {
					for (int i = 0; i < Ranking.getInstance().getUsuarios().size(); i++) {
						for (int j = 0; j < usuarios.size(); j++) {
							if(Ranking.getInstance().getUsuarios().get(i).getNome().equals(usuarios.get(j).getNome()))
							{
								if(Ranking.getInstance().getUsuarios().get(i).getPontos() < usuarios.get(j).getPontos())
									Ranking.getInstance().getUsuarios().set(i,usuarios.get(j));
							}
						}
					}
				} else {
					Ranking.getInstance().setUsuarios(usuarios);
				}
				buffRead.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}