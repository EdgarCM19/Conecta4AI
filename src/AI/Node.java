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
    
    public Node(Board data,int depth,int mov) {
        this.data=data;
        this.depth=depth;
        this.mov=mov;
        nodes = new ArrayList<Node>();
    }
    
    public void createChild(char player) {
        for (int i =0;i<data.board.length - 1; i++) {
            if(data.canAdd(i)) {
                char[][] newBoard = copyBoard();
                Board tem = new Board(newBoard);
                tem.addPiece(i, player);
                nodes.add(new Node(tem,depth,0));
                break;
            }
        }
    }
    
    public void selectChildren() {
        
    }
    
    public void createTree(Node nodo,int depth,char player,int horizon) {
        char c = (player == 'A') ? 'P' : 'A';
        if(depth == horizon)
            nodo.createChild(c);
         else{
             for(int i=0;i<nodo.nodes.size();i++){
                 createTree(nodes.get(i),depth++,c,horizon);    
             }
         }
    }
    
    private char[][] copyBoard(){
        char[][] aux = new char[6][7];
        for(int i=0;i<6;i++) {
            for(int j=0;j<7;j++) {
                aux[i][j] = data.board[i][j];
            }
        }
        return aux;
    }
    
    @Override
    public String toString() {
    	return "Tablero: \n" + data.toString() + "Factor: \n" + this.factor + "Profundidad: " + this.depth; 
    }
}