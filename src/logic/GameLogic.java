package logic;

import AI.Node;

public class GameLogic {
	
	public Board board;
	public Player p1, p2;
	
	public GameLogic() {
		board = new Board();
		//
		/*
		board.addPiece(1, 'A');
		board.addPiece(1, 'A');
		board.addPiece(1, 'A');
		board.addPiece(1, 'P');
		board.addPiece(1, 'A');
		board.addPiece(1, 'P');
		*/
		//Col
		board.addPiece(0, 'A');
		board.addPiece(0, 'A');
		board.addPiece(0, 'A');
		board.addPiece(0, 'P');
		board.addPiece(0, 'A');
		board.addPiece(0, 'P');
		//Col2
		board.addPiece(1, 'P');
		board.addPiece(1, 'P');
		board.addPiece(1, 'A');
		board.addPiece(1, 'P');
		//Col3
		board.addPiece(2, 'P');
		board.addPiece(2, 'A');
		board.addPiece(2, 'A');
		board.addPiece(2, 'P');
		//Col4
		board.addPiece(3, 'A');
		board.addPiece(3, 'P');
		board.addPiece(3, 'P');
		board.addPiece(3, 'A');
		board.addPiece(3, 'P');
		board.addPiece(3, 'A');
		//Col5
		board.addPiece(4, 'P');
		board.addPiece(4, 'P');
		board.addPiece(4, 'P');
		board.addPiece(4, 'A');
		board.addPiece(4, 'A');
		board.addPiece(4, 'P');
		//Col6
		board.addPiece(5, 'P');
		board.addPiece(5, 'A');
		board.addPiece(5, 'A');
		board.addPiece(5, 'P');
		//COl7
		board.addPiece(6, 'A');
		board.addPiece(6, 'P');
		board.addPiece(6, 'P');
		board.addPiece(6, 'A');
		//board.print();
		//*/
		p1 = new Player('P', board);
		p2 = new Player('A', board);
		Node temp = new Node(board,0,-1);
		//System.out.println("A ver");
		//temp.data.print();
		//temp.createChild('A',temp.depth + 1 );
		//temp.imprimmirHijos();
		//temp.nodes.get(0).createChild('P', 2,temp.nodes.get(0).data);
		//temp.nodes.get(0).imprimmirHijos();
		System.out.println("A ver");
		//temp.imprimmirHijos();
		temp.createTree(temp, 0, 'A', 5);
	}
}
