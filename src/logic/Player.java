package logic;

public class Player {
	
	public int movs;
	public char name;
	public Board board;
	
	public Player(int movs, char name, Board board) {
		this.movs = movs;
		this.name = name;
		this.board = board;
	}
	
	public boolean throwPiece(int column) {
		return board.addPiece(column, this.name);
	}
	

}
