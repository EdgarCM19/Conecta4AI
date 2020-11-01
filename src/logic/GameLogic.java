package logic;

import AI.Node;

public class GameLogic {
	
	public Board board;
	public Player p1, p2;
	
	public GameLogic() {
		board = new Board();
		/*
		board.addPiece(1, 'A');
		board.addPiece(3, 'P');
		board.addPiece(1, 'P');
		board.addPiece(1, 'A');
		board.addPiece(4, 'P');
		board.addPiece(5, 'A');
		board.addPiece(4, 'P');
		board.addPiece(0, 'P');
		board.addPiece(2, 'A');
		board.addPiece(6, 'P');
		
		Node nodo = new Node(board, 0, -1);
		
		*/
		board.print();
		p1 = new Player('P', board);
		p2 = new Player('A', board);
	}

}
