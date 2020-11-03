package AI;

import java.util.Random;

import logic.Board;

public class Algorithm {
	
	private Node node;
	private Board board;
	private boolean is_max = true; //true = min | false = max
	
	private final float nI = -1000000f; 
	private final float pI = 1000000f;
	
	public Algorithm(Board board) {
		this.board = board;
	}
	
	public int poda() {
		Node temp = new Node(board, 0, -1);
		temp.createTreeN(temp, 0, 'P', 6);
		float pod = poda(temp, nI, pI, 6, is_max);
		for(Node n : temp.nodes) {
			if(n.best != -1) {
				System.out.println("Lo hace la AI");
				System.out.println("[MEJOR OPCION]>");
				System.out.println(n);
				System.out.println("Su ruta:");
				n.printResult(n);
				return n.best;
			}
			
			/*
			if(Evaluation.evaluation(n.data, 'A') == pod) {
				System.out.println("Entre jejej");
				return n.mov;
			}*/
		}
		temp.printNodeTree(temp);
		System.out.println("Valio verga");
		return new Random().nextInt(7);
	}
	
	private float poda(Node J, float alpha, float beta, int horizont, boolean max) {
		if(J.depth >= horizont || Evaluation.isMeta(J.data)) {
			return Evaluation.evaluation(J.data, max ? 'A' : 'P');
		}
		if(max) { //MAX
			float t_alpha = alpha;
			for(int i = 0; i < J.nodes.size(); i++) {
				Node Jk =J.nodes.get(i);
				t_alpha = Math.max(t_alpha, poda(Jk, t_alpha, beta, horizont, false));
				if(t_alpha > beta) {
					J.setBest(i);					
					return beta;
					//break;
				}
				
			}
			J.setBest(new Random().nextInt(J.nodes.size()));
			return t_alpha;
		} else {
			float t_beta = beta;
			for(int i = 0; i < J.nodes.size(); i++) {
				Node Jk = J.nodes.get(i);
				t_beta = Math.min(t_beta, poda(Jk, alpha, t_beta, horizont, true));
				if(alpha > t_beta) {
					J.setBest(i);
					//break;
					return alpha;
				}
				
			}
			J.setBest(new Random().nextInt(J.nodes.size()));
			return t_beta;
		}
	}

	
	

}
