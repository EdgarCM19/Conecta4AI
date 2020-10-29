package logic;

import java.util.ArrayList;

public class Board {

	public ArrayList [] board;
	private final int MAX = 6;
	
	public Board() {
		board = new ArrayList[7];
		for (int i = 0; i < board.length; i++) {
			board[i] = new ArrayList<Character>();
		}
	}
	
	public boolean addPiece(int index, char name) {
		board[index].add(new Character(name));
		return true;
	}
	
	public void print() {
		for (int i = 0; i < board.length; i++) {
			
		}
	}
	
}
