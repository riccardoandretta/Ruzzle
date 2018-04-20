package it.polito.tdp.Ruzzle.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {
	// primo passo � iterativo: mi chiedo se la prima lettera c'� e basta esplorare la board
	// secondo passo � vedere che la seconda lettera ci sia e sia adiacente alla
	// prima lettera, e cos� via...

	public List<Pos> trovaParola(String parola, Board board) {
		
		for (Pos p : board.getPositions()) {
			if (board.getCellValueProperty(p).get().charAt(0) == parola.charAt(0)) {
				// inizio potenziale della parola --> faccio ricorsione
				List<Pos> percorso = new ArrayList<>();
				percorso.add(p); // la inserisco gi�
				if (cerca(parola, 1, percorso, board)) {
					return percorso;
				}
			}
		}
		
		return null;

	}

	private boolean cerca(String parola, int livello, List<Pos> percorso, Board board) { // la board conosce gli
																							// adiacenti
		// caso terminale
		if (livello == parola.length()) {
			return true;
		}

		Pos ultima = percorso.get(percorso.size() - 1);
		List<Pos> adiacenti = board.getAdiacenti(ultima);

		for (Pos nuova : adiacenti) {
			if (board.getCellValueProperty(nuova).get().charAt(0) == parola.charAt(livello)
					// prendo la stringa che c'� in 'nuova' e ne prendo il primo carattere che confronter�
					&& !percorso.contains(nuova)) {
					// faccio ricorsione
				percorso.add(nuova);
				if (cerca(parola, livello+1, percorso, board))
					return true; //uscita rapida in caso di soluzione
				percorso.remove(percorso.size()-1); // questo percorso.size() � diverso dal precedente!
			}
		}

		return false;

	}

}
