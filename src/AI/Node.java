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
    
    public void createChild(char player,int depth) {
        for (int i =0;i<7; i++) {
        	if(data.canAdd(i)) {
                char[][] newBoard = copyBoard();
                Board tem = new Board(newBoard,data.pieces);
                tem.addPiece(i, player);
                nodes.add(new Node(tem,depth,-1));
            }
        }
        //System.out.print("A quie se queda");
    }
    
    public void createChild(char player,int depth,Board datas) {
    	for (int i =0;i<7; i++) {
            if(data.canAdd(i)) {
                char[][] newBoard = copyBoard(datas);
                Board tem = new Board(newBoard,data.pieces);
                tem.addPiece(i, player);
                tem.print();
                nodes.add(new Node(tem,depth,-1));
            }
        }
    }
    public void imprimmirHijos() {
    	for(int i=0;i<nodes.size();i++) {
    		System.out.println("Hijo: "+i);
    		nodes.get(i).print();
    	}
    }
    
    public void selectChildren() {
        
    }
    
    public void createTree(Node nodo,int depth,char player,int horizon) {
    	System.out.println("Nuevo llamado");
    	nodo.print();
        if(nodo.depth != horizon) {
        	char c = (player == 'A') ? 'P' : 'A';
            nodo.createChild(c,nodo.depth+1,nodo.data);
        	System.out.println("Tam "+nodo.nodes.size());
        	 nodo.imprimmirHijos();
             for(int i=0;i<nodo.nodes.size();i++){
            	 System.out.println("Siguen en el for");
                 createTree(nodo.nodes.get(i),nodo.nodes.get(i).depth,c,horizon);    
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
    	return "Tablero: \n" + data.toString() + "Factor: \n" + this.factor + "\n Profundidad: " + this.depth; 
    }
}