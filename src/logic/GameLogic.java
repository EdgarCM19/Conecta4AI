package logic;

import AI.Algorithm;
import AI.Evaluation;
import AI.Node;

public class GameLogic {
	
	public Board board;
	public Player p1, p2;
	
	public GameLogic() {
		
		board = new Board();
        p1 = new Player('P', board);
        p2 = new Player('A', board);
        
        //testPoda();
        
        /*
        board.addPiece(0, 'P');
        board.addPiece(1, 'A');
        board.addPiece(1, 'A');
        board.addPiece(1, 'A');
        board.addPiece(2, 'A');
        board.addPiece(3, 'P');
        board.addPiece(3, 'P');
        board.addPiece(4, 'P');
        board.addPiece(4, 'A');
        board.addPiece(4, 'P');
        board.addPiece(5, 'A');
        board.addPiece(5, 'P');
        board.addPiece(5, 'A');
        board.addPiece(5, 'P');
        board.addPiece(6, 'A');
        board.addPiece(6, 'A');
        board.addPiece(6, 'P');
        board.addPiece(6, 'A');
        System.out.println(Evaluation.closeToWin(board.board, 'P'));
        */
        
        
        /*
        board.addPiece(6, 'P');
        board.addPiece(5, 'A');
        board.addPiece(5, 'A');
        board.addPiece(5, 'A');
        board.addPiece(4, 'A');
        board.addPiece(3, 'P');
        //board.addPiece(3, 'P');
        board.addPiece(2, 'P');
        board.addPiece(2, 'A');
        board.addPiece(2, 'P');
        board.addPiece(1, 'A');
        board.addPiece(1, 'P');
        board.addPiece(1, 'A');
        board.addPiece(1, 'P');
        board.addPiece(0, 'A');
        board.addPiece(0, 'A');
        board.addPiece(0, 'P');
        board.addPiece(0, 'A');
        board.addPiece(0, 'P');
        System.out.println(Evaluation.closeToWin(board.board, 'P'));
        */
        
        
        
        /* 
        Node temp = new Node(board, 0, -1);
		temp.createTreeN(temp, 0, 'A', 3);
		Algorithm algo = new Algorithm(board);
		System.out.println("[++++++]> " + algo.poda());
		*/
		//temp.printNodeTree(temp);
        //test();
        
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
	
	
	private void testPoda() {
		
		//Nivel 3
		Node rama111 = new Node();
		rama111.depth = 3;
		rama111.nodes.add(new Node(5.0f, 4));
		rama111.nodes.add(new Node(6.0f, 4));
		Node rama112 = new Node();
		rama112.depth = 3;
		rama112.nodes.add(new Node(7f, 4));
		rama112.nodes.add(new Node(4f, 4));
		rama112.nodes.add(new Node(5f, 4));
		Node rama121 = new Node();
		rama121.depth = 3;
		rama121.nodes.add(new Node(3f, 4));
		
		Node rama211 = new Node();
		rama211.depth = 3;
		rama211.nodes.add(new Node(6f, 4));
		Node rama212 = new Node();
		rama212.depth = 3;
		rama212.nodes.add(new Node(6f, 4));
		rama212.nodes.add(new Node(9f, 4));
		Node rama221 = new Node();
		rama221.depth = 3;
		rama221.nodes.add(new Node(7f, 4));
		
		Node rama311 = new Node();
		rama311.depth = 3;
		rama311.nodes.add(new Node(5f, 4));
		Node rama321 = new Node();
		rama321.depth = 3;
		rama321.nodes.add(new Node(9f, 4));
		rama321.nodes.add(new Node(8f, 4));
		Node rama322 = new Node();
		rama322.depth = 3;
		rama322.nodes.add(new Node(6f, 4));
		
		//Nivel 2
		Node rama11 = new Node();
		rama11.depth = 2;
		rama11.nodes.add(rama111);
		rama11.nodes.add(rama112);
		Node rama12 = new Node();
		rama12.depth = 2;
		rama12.nodes.add(rama121);
		Node rama21 = new Node();
		rama21.depth = 2;
		rama21.nodes.add(rama211);
		rama21.nodes.add(rama212);
		Node rama22 = new Node();
		rama22.depth = 2;
		rama22.nodes.add(rama221);
		Node rama31 = new Node();
		rama31.depth = 2;
		rama31.nodes.add(rama311);
		Node rama32 = new Node();
		rama32.depth = 2;
		rama32.nodes.add(rama321);
		rama32.nodes.add(rama322);
		
		//Nivel 1
		Node rama1 = new Node();
		rama1.depth = 1;
		rama1.nodes.add(rama11);
		rama1.nodes.add(rama12);
		Node rama2 = new Node();
		rama2.depth = 1;
		rama2.nodes.add(rama21);
		rama2.nodes.add(rama22);
		Node rama3 = new Node();
		rama3.depth = 1;
		rama3.nodes.add(rama31);
		rama3.nodes.add(rama32);
		
		//Nivel 0 [Padre]
		Node parent = new Node(new Board(), 0, -1);
		parent.nodes.add(rama1);
		parent.nodes.add(rama2);
		parent.nodes.add(rama3);
		
		Algorithm algo = new Algorithm(board);
		float poda = algo.poda(parent, -100000f, 100000f, true);
		System.out.println("PODA>" + poda);
		System.out.println("BEST>" + algo.bestMov);
		for (int i = 0; i < parent.nodes.size(); i++) {
			if(parent.nodes.get(i).factor == poda) {
				System.out.println("[%]>" + i);
				break;
			}
				
		}
		parent.printNodeTree(parent);
		
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
		
		Board board2 = new Board();
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
        //board.addPiece(1, 'P');
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
         
        //board.addPiece(5, 'P');
        
        //board.addPiece(5, 'P');
        board.addPiece(5, 'P');
        //COl
	       board.addPiece(6, 'P');
	       board.addPiece(6, 'P');
	       //board.addPiece(6, 'P');
	       board.addPiece(6, 'A');
	       board.print();
       	Node temp = new Node(board2, 0, -1);
		temp.createTreeN(temp, 0, 'A', 2);
		Algorithm algo = new Algorithm(board2);
		System.out.println("[++++++]> " + algo.poda());
        
        System.out.println((Evaluation.evaluation(board, 'A')));
        //board.addPiece(6, 'A');
        //board.addPiece(6, 'A');
        
        //board.addPiece(6, 'A');
        //board.print()

	}
}
