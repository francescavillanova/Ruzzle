package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {

	
	public List<Pos> trovaParola(String parola, Board board){
		
		//devo controllare se c'è la prima lettera nella board
		for(Pos p: board.getPositions()) {  //per tutte le posizioni della board
			//la lettera in pos è == alla prima lettera di parola
			if(board.getCellValueProperty(p).get().charAt(0)==parola.charAt(0)) {    //posso usare == perchè sono caratteri
				//parte la ricorsione
				
				List<Pos> parziale= new ArrayList<Pos>();   //ogni volta si ricrea
				parziale.add(p); //aggiungo la posizione della prima lettera
				if(cerca(parola, board, 1, parziale))  //solo se trovo l'intera parola
				    return parziale; 
			}
		}
		//se non trovo la parola
		return null;
	}
	
	/**
	 * ritona un booleano che mi dice se ho trovato o meno la parola
	 * @param parola
	 * @param board
	 * @param livello
	 * @param percorso
	 * @return
	 */
	public boolean cerca(String parola, Board board, int livello, List<Pos> percorso ) {
		
		//caso terminale
		if(livello==parola.length())
			return true;
		
		Pos ultima = percorso.get(percorso.size()-1); //ultima posizione inserita
		List<Pos> adiacenti = board.getAdjacencies(ultima);
		for (Pos a: adiacenti) {
			if(!percorso.contains(a) && board.getCellValueProperty(a).get().charAt(0)==parola.charAt(livello)) {
				
				percorso.add(a);
				
				if(cerca(parola, board, livello+1, percorso))
					return true;    //se ho trovato tutta la parola non mi serve fare backtracking
				
				percorso.remove(percorso.size()-1);    //backtracking perchè potrei dover esplorare altri percorsi
			}
		}
		
		return false;
	}
}
