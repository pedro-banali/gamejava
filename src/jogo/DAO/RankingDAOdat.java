package jogo.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import jogo.modelos.Ranking;
import jogo.modelos.Usuario;

public class RankingDAOdat extends RankingDAO {

	@Override
	public void gravar() {
		String arquivo = "dados.dat";
		try {
			File file = new File(arquivo);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream f = new FileOutputStream(new File(arquivo));
			ObjectOutputStream o = new ObjectOutputStream(f);

			o.writeObject(Ranking.getInstance().getUsuarios());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void ler() {
		ArrayList<Usuario> usuarios;
		try {
			String arquivo = "dados.dat";
			File file = new File(arquivo);
			if (file.exists()) {
				FileInputStream fi = new FileInputStream(new File(arquivo));
				ObjectInputStream oi = new ObjectInputStream(fi);

				usuarios = (ArrayList<Usuario>) oi.readObject();
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
			}
		}  catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}