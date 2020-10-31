package logic;

public class Player {
	
	public char name;
	public Board board;
	public int leftPieces;
	
	public Player(char name, Board board) {
		this.name = name;
		this.board = board;
		this.leftPieces = 21;
	}
	
	public boolean throwPiece(int column) {
		if(board.addPiece(column, this.name)) {
			leftPieces--;
			return true;
		}
		return false;
	}
	

}
