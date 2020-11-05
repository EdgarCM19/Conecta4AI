package AI;


import logic.Board;

public class Algorithm {
	
	private Board board;
	private boolean is_max = true; //true = min | false = max
	
	private final float nI = -1000000f; 
	private final float pI = 1000000f;
	
	public Algorithm(Board board) {
		this.board = board;
	}
	
	public int bestMov;	
	
	public int poda() {
		Node temp = new Node(board, 0, -1);
		temp.createTreeN(temp, 0, 'P', 6);
		poda(temp, nI, pI, is_max);
		return temp.best;
	}
		
	public float poda(Node J, float alpha, float beta, boolean max) {
		if(J.nodes.size() == 0 || Evaluation.isMeta(J.data) != '-') {
			J.setFactor(Evaluation.evaluation(J.data, max ? 'A' : 'P'));
			return J.factor;
		}
		if(max) { //MAX
			float t_alpha = alpha, ant;
			for(Node Jk : J.nodes) {
				ant = t_alpha;
				t_alpha = Math.max(t_alpha, poda(Jk, t_alpha, beta, false));
				J.setFactor(t_alpha);
				if(ant != t_alpha)					
					J.best = Jk.mov;					
				if(beta <= t_alpha) {
					J.best = Jk.mov;
					break;
				}
			}
			return t_alpha;
		} else {
			float t_beta = beta, ant;
			for(Node Jk : J.nodes) {
				ant = t_beta;
				t_beta = Math.min(t_beta, poda(Jk, alpha, t_beta, true));
				J.setFactor(t_beta);
				if(ant != t_beta)					
					J.best = Jk.mov;
				if(t_beta <= alpha) {
					bestMov = Jk.mov;
					break;
				}
			}
			return t_beta;
		}
	}

}
