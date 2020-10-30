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
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = '-';
			}
		}
	}



	public boolean addPiece(int column, char name) {
		int index;
		if((index = getColumnIndex(column)) != -1) {
			board[index][column] = name;
			return true;
		}
		return false;
	}
	
	private int getColumnIndex(int column) {
		int index = -1;
		for (int i = board.length - 1; i >= 0; i--) {
			if(board[i][column] == '-')
				return i;
		}
		return index;
	}
	
	public void print() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println("");
		}
	}
	
}
