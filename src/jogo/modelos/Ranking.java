package jogo.modelos;

import java.util.ArrayList;

public class Ranking {
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private static Ranking instance;

	private Ranking() {

	}

	public static Ranking getInstance() {
		if (instance == null) {
			instance = new Ranking();
		}
		return instance;

	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void salvarUsuario(Usuario usuario) {
		boolean achou = false;
		if (usuarios.size() == 0)
			usuarios.add(usuario);
		else {
			for (int i = 0; i < usuarios.size(); i++) {
				if (usuario.getNome().equals(usuarios.get(i).getNome())
						&& usuario.getPontos() > usuarios.get(i).getPontos()) {
					usuarios.set(i, usuario);
					return;
				}
			}
			
			for (int i = 0; i < usuarios.size(); i++) {
				if (usuario.getNome().equals(usuarios.get(i).getNome())) {
					achou = true;
				}
			}
			
			if(!achou) {
				usuarios.add(usuario);
			}
		}

	}

	public void ordenarRanking() {
		Usuario usuarioAux;

		for (int i = 0; i < usuarios.size(); i++) {
			for (int j = 0; j < usuarios.size() - 1; j++) {

				if (usuarios.get(j).getPontos() < usuarios.get(j + 1).getPontos()) {
					usuarioAux = usuarios.get(j);
					usuarios.set(j, usuarios.get(j + 1));
					usuarios.set((j + 1), usuarioAux);
				}
			}
		}

		for (int i = 4; i < usuarios.size(); i++) {
			usuarios.remove(i);
		}
	}

	public String getRankingFormatado() {
		String message = "";
		for (int j = 0; j < usuarios.size(); j++) {
			Usuario usuario = usuarios.get(j);
			message += usuario.getNome() + ", " + usuario.getPontos() + "\n";
		}
		return message;
	}
}
