package AI;
import java.util.ArrayList;
import java.util.Collection;

 

import logic.Board;

public class Node {
    public ArrayList<Node> nodes;
    public Board data;
    public float factor;
    public int mov;
    public int depth;
    public int best;
    
    public Node(Board data,int depth,int mov) {
        this.data=data;
        this.depth=depth;
        this.mov=mov;
        this.best = -1;
        nodes = new ArrayList<Node>();
    }     
    
    public Node getMove(int column, char player) {
    	Board b_temp = new Board(copyBoard());
    	b_temp.addPiece(column, player);
    	Node temp = new Node(b_temp, 0, column);
    	return temp;
    }
    
    public void setFactor(float factor) {
    	this.factor = factor;
    }
    
    public void createTreeN(Node node, int depth, char player, int horizont) {
    	if(node.depth < horizont && Evaluation.isMeta(node.data) == '-') {
    		char c = (player == 'A') ? 'P' : 'A';
    		for (int column = 0; column < node.data.pieces.length; column++) {
				if(node.data.canAdd(column)) {
					Board temp = new Board(copyBoard(node.data));
					temp.addPiece(column, c);
					Node ntemp = new Node(temp, depth + 1, column); 
					node.nodes.add(ntemp);
					if(Evaluation.isMeta(ntemp.data) == '-') {
						createTreeN(ntemp, depth + 1, c, horizont);
					} 
				}
			}
    	}
    }

    private char[][] copyBoard(){
    	return copyBoard(data);
    }
    
    private char[][] copyBoard(Board matriz){
        char[][] aux = new char[6][7];
        for(int i=0;i<6;i++) {
            for(int j=0;j<7;j++) {
                aux[i][j] = matriz.board[i][j];
            }
        }
        return aux;
    }
    
    public void print() {
    	System.out.println(toString());
    }
    
    @Override
    public String toString() {
    	return "Tablero: \n" + data.toString() + "\n Profundidad: " + this.depth + "\nFACTOR>" + factor + "\nMov: " + mov; 
    }
}