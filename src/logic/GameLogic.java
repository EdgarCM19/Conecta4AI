package logic;

import AI.Evaluation;
import AI.Node;

public class GameLogic {
	
	public Board board;
	public Player p1, p2;
	
	public GameLogic() {
		/*
		board = new Board();
        p1 = new Player('P', board);
        p2 = new Player('A', board);
        */
		test();
        //System.out.println("A ver");
        //temp.data.print();
        //temp.createChild('A',temp.depth + 1 );
        //temp.imprimmirHijos();
        //temp.nodes.get(0).createChild('P', 2,temp.nodes.get(0).data);
        //temp.nodes.get(0).imprimmirHijos();
        //temp.createTree(temp, 0, 'A', 3);
        //temp.createTreeN(temp, 0, 'A', 3);
        //temp.printTree();
        //temp.imprimmirHijos();
	}
	
	public void printTree(Board board2) {
		Node temp = new Node(board2, 0, -1);
		temp.createTreeN(temp, 0, 'A', 2);
		System.out.println("\n\n\n");
		System.out.println("\t$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("\t$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("\t$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("\tn$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        temp.printTree();
        temp = null;
	}
	
	private void test() {
		
		board = new Board();
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
        //board.addPiece(0, 'A');
        //board.addPiece(0, 'A');
        //board.addPiece(0, 'P');
        //board.addPiece(0, 'A');
        /*board.addPiece(0, 'P');
        board.addPiece(0, 'A');
        board.addPiece(0, 'P');*/
        //Col2
        board.addPiece(1, 'P');
        board.addPiece(1, 'P');
        board.addPiece(1, 'A');
        board.addPiece(1, 'A');
        board.addPiece(1, 'A');
        board.addPiece(1, 'P');
        board.addPiece(1, 'P');
        //Col3
        board.addPiece(2, 'P');
        board.addPiece(2, 'A');
        board.addPiece(2, 'P');
        board.addPiece(2, 'P');
        //Col4
        board.addPiece(3, 'A');
        board.addPiece(3, 'P');
        board.addPiece(3, 'A');
        board.addPiece(3, 'A');
        board.addPiece(3, 'A');
        /*board.addPiece(3, 'A');
        board.addPiece(3, 'P');
        board.addPiece(3, 'A');*/
        //Col5
        board.addPiece(4, 'P');
        board.addPiece(4, 'P');
        board.addPiece(4, 'A');
        /*board.addPiece(4, 'A');
        board.addPiece(4, 'A');
        board.addPiece(4, 'P');*/
        board.addPiece(4, 'A');
        board.addPiece(4, 'A');
        //Col6
        board.addPiece(5, 'P');
        board.addPiece(5, 'A');
        board.addPiece(5, 'A');
         
        board.addPiece(5, 'P');
        
        board.addPiece(5, 'P');
        board.addPiece(5, 'P');
        //COl
       board.addPiece(6, 'P');
       board.addPiece(6, 'P');
       board.addPiece(6, 'P');
        board.addPiece(6, 'A');
        board.print();
        System.out.println((Evaluation.evaluation(board, 'A')));
        //board.addPiece(6, 'A');
        //board.addPiece(6, 'A');
        
        //board.addPiece(6, 'A');
        //board.print()

	}
}
