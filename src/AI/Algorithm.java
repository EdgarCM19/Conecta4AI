package AI;

import java.util.ArrayList;
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
	
	public int bestMov;
	
	public Node podaAlfaBeta(Board b) {
		return podaAlfaBeta(new Node(b, 0, -1), nI, pI, 4, is_max);
	}
	
	
	public Node podaAlfaBeta(Node J, float alpha, float beta, int horizont, boolean max) {
		float new_alpha;
		float new_beta;
		char player = max ? 'A' : 'P';
		if(Evaluation.isMeta(J.data) != '-' || horizont == 0) {
			//System.out.println(J);
			J.factor = Evaluation.evaluation(J.data, player);
			return J;
		}
		for(int column = 0; column < J.data.board[0].length; column++) {
			if(J.data.canAdd(column)) {
				if(max) {
					Node Jk = J.getMove(column, player);
					new_alpha = alpha;
					new_alpha = Math.max(new_alpha, podaAlfaBeta(Jk, new_alpha, beta, horizont - 1, !max).factor);
					if(new_alpha != alpha) {
						System.out.println("Chi max");
						J.best = column;
						J.nodes.add(Jk);
					}
					if(beta <= new_alpha) {
						J.best = column;
						J.setFactor(new_alpha);
						return Jk;
					}
				} else {
					Node Jk = J.getMove(column, player);
					new_beta = beta;
					new_beta = Math.min(new_beta, podaAlfaBeta(Jk, alpha, new_beta, horizont - 1, !max).factor);
					if(new_beta != beta) {
						System.out.println("Chi min");
						J.best = Jk.mov;
						J.nodes.add(Jk);
					}
					if(alpha > new_beta) {
						J.best = Jk.mov;
						J.setFactor(new_beta);
						return Jk;
					}
				}
			}
		}
		return J;
	}
	
	
	
	public int poda() {
		Node temp = new Node(board, 0, -1);
		temp.createTreeN(temp, 0, 'P', 6);
		float pod = poda(temp, nI, pI, is_max);
		int i = 0;
		System.out.println("Results: ");
		for(Node n : temp.nodes) {
			System.out.println(">" + n.factor);
		}
		//ArrayList<Integer> posibles = new ArrayList<Integer>();
		for(i = 0; i < temp.nodes.size(); i++) {
			if(temp.nodes.get(i).factor == pod) {
				return i;
				//posibles.add(i); 
			}
		}
		//i = new Random().nextInt(7);
		//i = posibles.get(new Random().nextInt(posibles.size()));
		System.out.println("[MOV]>" + i);
		return i;
	}
		
	public float poda(Node J, float alpha, float beta, boolean max) {
		if(J.nodes.size() == 0 || Evaluation.isMeta(J.data) != '-') {
			J.setFactor(Evaluation.evaluation(J.data, max ? 'A' : 'P'));
			System.out.println("[No se xd]>" + max);
			System.out.println(J);
			return J.factor;
		}
		if(max) { //MAX
			float t_alpha = alpha, ant;
			for(Node Jk : J.nodes) {
				ant = t_alpha;
				t_alpha = Math.max(t_alpha, poda(Jk, t_alpha, beta, false));
				J.setFactor(t_alpha);
				if(ant != t_alpha)
					bestMov = J.nodes.indexOf(Jk);					
				if(beta <= t_alpha) {
					break;
				}
			}
			return t_alpha;
			/*
			float t_alpha = alpha;
			for(int i = 0; i < J.nodes.size(); i++) {
				Node Jk =J.nodes.get(i);
				t_alpha = Math.max(t_alpha, poda(Jk, t_alpha, beta, false));
				J.setFactor(t_alpha);
				if(alpha != t_alpha) {
					bestMov = i;
				}
				if(t_alpha >= beta) {	
					J.setFactor(t_alpha);
					return beta;
				}
			}
			return t_alpha;
			*/
		} else {
			float t_beta = beta;
			for(Node Jk : J.nodes) {
				t_beta = Math.min(t_beta, poda(Jk, alpha, t_beta, true));
				J.setFactor(t_beta);
				if(t_beta <= alpha) {
					bestMov = J.nodes.indexOf(Jk);
					break;
				}
			}
			return t_beta;
			/*
			float t_beta = beta;
			for(int i = 0; i < J.nodes.size(); i++) {
				Node Jk = J.nodes.get(i);
				t_beta = Math.min(t_beta, poda(Jk, alpha, t_beta, true));
				J.setFactor(t_beta);
				if(beta != t_beta) {
					bestMov = i;
				}
				if(alpha > t_beta) {
					J.setFactor(t_beta);
					return t_beta;
					
				}
			}
			return alpha;
			*/
		}
	}

}
