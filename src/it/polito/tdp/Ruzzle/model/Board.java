package it.polito.tdp.Ruzzle.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Memorizza le lettere presenti nella scacchiera Ruzzle.
 * 
 * @author Fulvio
 *
 */
public class Board {
	private List<Pos> positions; // singola casella
	private Map<Pos, StringProperty> cells; // contenuto della cella; con la StringProperty l'interfacciaUtente si
											// aggiorna automaticamente

	private int size;

	/**
	 * Crea una nuova scacchiera della dimensione specificata
	 * 
	 * @param size
	 */
	public Board(int size) {
		this.size = size;

		// caselle
		this.positions = new ArrayList<>();
		for (int row = 0; row < this.size; row++) { // mi cotruisco '16 = size*size' oggetti di tipo 'pos', in cui salvo
													// la 'position'
			for (int col = 0; col < this.size; col++) {
				this.positions.add(new Pos(row, col));
			}
		}
		// contenuto
		this.cells = new HashMap<>();

		for (Pos p : this.positions) {
			this.cells.put(p, new SimpleStringProperty());
		}
	}

	/**
	 * Fornisce la {@link StringProperty} corrispondente alla {@link Pos}
	 * specificata.
	 * <p>
	 * 
	 * Può essere usata per sapere che lettera è presente (es.
	 * {@code getCellValueProperty(p).get()}) oppure per fare un binding della
	 * proprietà stessa sulla mappa visuale.
	 * 
	 * @param p
	 * @return
	 */
	public StringProperty getCellValueProperty(Pos p) {
		return this.cells.get(p);
	}

	/**
	 * Restituisce la lista di oggetti {@link Pos} che corrispondono alle posizioni
	 * lecite sulla scacchiera. Gli elementi sono ordinati per righe.
	 * 
	 * @return
	 */
	public List<Pos> getPositions() {
		return positions;
	}

	/**
	 * Crea una nuova scacchiera generando tutte lettere casuali
	 */
	public void reset() { // creo il contenuto della tastiera
		for (Pos p : this.positions) {
			int random = (int) (Math.random() * 26);
			String letter = Character.toString((char) ('A' + random));
			this.cells.get(p).set(letter);
		}
	}

	public List<Pos> getAdiacenti(Pos ultima) {
		
		List<Pos> result = new ArrayList<>();
		for (int riga = -1; riga <= 1; riga++) {
			for (int colonna = -1; colonna <= 1; colonna++) {
				//tutte le 9 posizioni nell'intorno della cella
				
				if (riga != 0 || colonna != 0) { // escludo la cella stessa (offset 0,0)
					Pos p = new Pos(ultima.getRow()+riga, ultima.getCol()+colonna);
					if (positions.contains(p)) {
						result.add(p);
					}
				}
			}

		}

		return result;
	}

}
