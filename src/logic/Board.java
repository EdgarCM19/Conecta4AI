package logic;

import java.util.ArrayList;

import AI.Evaluation;

public class Board {

	//public ArrayList [] board;
	public char [][] board;
	public int [] pieces;
	
	public Board() {
		board = new char[6][7];
		pieces = new int[7];
		initBoard();
	}
	
	
	
	public Board(char[][] board,int[] pieces) {
		this.board = board;
        this.pieces=pieces;
	}
	
	

	public Board(char[][] board) {
		this.board = board;
		pieces = new int[7];
        initPieces();
	}
	
	private void initPieces() {
		for (int column = 0; column < board[0].length; column++) {
			pieces[column] = getColumnIndex(column);
		}
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
			pieces[column]++;
			return true;
		}
		return false;
	}
	
	public int columnIndex(int column) {
		return pieces[column];
	}
	
	private int getColumnIndex(int column) {
		int index = -1;
		for (int i = board.length - 1; i >= 0; i--) {
			if(board[i][column] == '-')
				return i;
		}
		return index;
	}
	
	public boolean canAdd(int column) {
		return pieces[column] < board.length;
	}
	
	public void print() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println("");
		}
	}
	
	@Override
	public String toString() {
		StringBuffer cad = new StringBuffer();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				cad.append(board[i][j] + " ");
			}
			cad.append("\n");
		}
		return cad.toString();
	}
	
}
