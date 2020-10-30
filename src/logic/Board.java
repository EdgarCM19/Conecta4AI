package logic;

import java.util.ArrayList;

public class Board {

	//public ArrayList [] board;
	public char [][] board;
	private final int MAX = 6;
	
	public Board() {
		board = new char[6][7];
		initBoard();
	}
	
	
	
	private void initBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = '-';
			}
		}
	}



	public boolean addPiece(int row, char name) {
		int index;
		if((index = getColumnIndex(row)) != -1) {
			board[row][index] = name;
			return true;
		}
		return false;
	}
	
	private int getColumnIndex(int row) {
		int index = -1;
		for (int i = board[row].length; i >= 0; i--) {
			if(board[row][i] == '-')
				return i;
		}
		return index;
	}
	
	public void print() {
		for (int i = 0; i < board.length; i++) {
			
		}
	}
	
}
